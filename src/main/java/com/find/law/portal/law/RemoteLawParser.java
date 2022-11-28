package com.find.law.portal.law;

import com.find.law.portal.exceptions.LawParseException;
import com.find.law.portal.law.dto.LawApiResponseDto;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoteLawParser implements LawParser {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final URL url;

    private final Pattern lawDataPattern = Pattern.compile("Статья (\\d+). (\\D+)");

    private final Pattern incorrectPart = Pattern.compile("(^\\W\\))|(^\\W\\d+\\))|(\\(Ч)|(\\(Д)|(\\(С)|(\\(Н)|(^Примечан)|(^ГЛАВА)|(^Раздел)");

    private final Pattern penaltyPart = Pattern.compile("в размере до (\\D+) рублей");

    private final Pattern lostPart = Pattern.compile("Часть утратила силу");

    private final Pattern endPart = Pattern.compile("Президент Российской Федерации");

    public RemoteLawParser(URL url) {
        this.url = url;
    }

    @Override
    public List<LawData> parseLaws() throws IOException {
        Document rawPage;
        try (InputStream stream = url.openStream()) {
            logger.info("Getting response from remote API {}", url);
            JsonReader reader = new JsonReader(new InputStreamReader(stream));
            LawApiResponseDto dto = new Gson().fromJson(reader, LawApiResponseDto.class);
            rawPage = Jsoup.parse(dto.RedText);
        }

        Element current = rawPage.getElementById("p529");
        List<LawData> laws = new ArrayList<>(300);

        if (current == null) {
            throw new RuntimeException("");
        }

        logger.info("Start parsing the body from remote request");
        LawData law = null;
        LawPartData lawPart = null;
        while (current != null) {
            try {
                if (current.hasText()) {
                    String text = current.text();
                    if (endPart.matcher(text).find()) {
                        break;
                    }

                    if (incorrectPart.matcher(text).find() || StringUtils.isEmptyOrWhitespace(text)) {
                        if (lostPart.matcher(text).find()) {
                            law = null;
                            lawPart = null;

                            current = current.nextElementSibling();
                            continue;
                        }
                        if (text.startsWith("Примечания")) {
                            while (true) {
                                if (current.hasText() && current.text().startsWith("Статья")) {
                                    text = current.text();
                                    break;
                                }

                                current = current.nextElementSibling();
                            }
                        } else {
                            current = current.nextElementSibling();
                            continue;
                        }
                    }

                    if (text.startsWith("Статья")) {
                        if (law != null) {
                            if (lawPart != null) {
                                law.getParts().add(lawPart);
                                lawPart = null;
                            }
                            laws.add(law);
                            law = null;
                        }

                        Matcher matcher = lawDataPattern.matcher(text);
                        if (matcher.find()) {
                            String number = matcher.group(1);
                            if (number.length() > 3) {
                                number = number.substring(0, 3) + "." + number.substring(3);
                            }
                            String name = matcher.group(2);
                            law = new LawData(number, name, new ArrayList<>(10));
                        }
                    } else if (text.startsWith("наказывается") || text.startsWith("наказываются")) {
                        if (lawPart == null) {
                            continue;
                        }

                        String[] words = Arrays.stream(text.split("\\s+"))
                                .map(w -> w.replaceAll(",", "")
                                        .replaceAll("-", "")
                                        .replaceAll("\\.", ""))
                                .toArray(String[]::new);

                        for (int i = 0; i < words.length; i++) {
                            String word = words[i];

                            LawPartPunishData result = null;
                            if (word.equalsIgnoreCase("ограничением")) {
                                if (words[i + 1].equalsIgnoreCase("свободы")) {
                                    result = parsePunish(words, i, lawPart.getPunishments());
                                    result.setType("Ограничение свободы");
                                } else if (words[i + 1].equalsIgnoreCase("по") && words[i + 2].equalsIgnoreCase("военной")) {
                                    result = parsePunish(words, i, lawPart.getPunishments());
                                    result.setType("Ограничение по военной службе");
                                }
                            } else if (word.equalsIgnoreCase("лишением")) {
                                if (words[i + 1].equalsIgnoreCase("свободы")) {
                                    result = parsePunish(words, i, lawPart.getPunishments());
                                    result.setType("Лишение свободы");
                                } else if (words[i + 1].equalsIgnoreCase("права")) {
                                    result = parsePunish(words, i, lawPart.getPunishments());
                                    result.setType("Лишение права занимать определенные должности или заниматься определенной деятельностью");
                                }
                            } else if (word.equalsIgnoreCase("принудительными")) {
                                if (words[i + 1].equalsIgnoreCase("работами")) {
                                    result = parsePunish(words, i, lawPart.getPunishments());
                                    result.setType("Принудительные работы");
                                }
                            } else if (word.equalsIgnoreCase("исправительными")) {
                                if (words[i + 1].equalsIgnoreCase("работами")) {
                                    result = parsePunish(words, i, lawPart.getPunishments());
                                    result.setType("Исправительные работы");
                                }
                            } else if (word.equalsIgnoreCase("обязательными")) {
                                if (words[i + 1].equalsIgnoreCase("работами")) {
                                    result = parsePunish(words, i, lawPart.getPunishments());
                                    result.setType("Обязательные работы");
                                }
                            } else if (word.equalsIgnoreCase("арестом")) {
                                result = parsePunish(words, i, lawPart.getPunishments());
                                result.setType("Арест");
                            } else if (word.equalsIgnoreCase("пожизненным")) {
                                result = new LawPartPunishData("Пожизненное заключение", null, null, null, true);
                                i += 2;
                            } else if (word.equalsIgnoreCase("штрафом")) {
                                Matcher matcher = penaltyPart.matcher(text);
                                if (matcher.find()) {
                                    result = new LawPartPunishData("Штраф", null, matcher.group(), null, false);
                                }
                            }

                            if (result != null) {
                                lawPart.getPunishments().add(result);
                            }
                        }
                    } else {
                        if (lawPart != null && law != null) {
                            law.getParts().add(lawPart);
                        }

                        lawPart = new LawPartData(text.replaceAll("-", "").replaceAll(":", ""), new ArrayList<>(10));
                    }
                }

                current = current.nextElementSibling();
            } catch (Exception cause) {
                logger.error("Fatal error occurred while parsing: {}", cause.getMessage());
                throw new LawParseException(current, cause);
            }
        }

        if (law != null) {
            laws.add(law);
        }

        return laws;
    }

    private static LawPartPunishData parsePunish(String[] words, int i, List<LawPartPunishData> results) {
        String min = null;
        String max = null;
        String dateType = null;

        while (i < words.length && (!words[i].equalsIgnoreCase("на") && !words[i].equalsIgnoreCase("до"))) {
            i++;
        }

        if (words[i + 1].equalsIgnoreCase("тот")) {
            LawPartPunishData last = results.get(results.size() - 1);
            min = last.getMin();
            max = last.getMax();
            dateType = last.getDateType();
        } else if (words[i + 2].equalsIgnoreCase("от")) {
            min = words[i + 3];
            if (words[i + 4].equalsIgnoreCase("до")) {
                max = words[i + 5];
                String rawType = words[i + 6];
                if (rawType.equalsIgnoreCase("часов") || rawType.equalsIgnoreCase("лет") || rawType.equalsIgnoreCase("года") || rawType.equalsIgnoreCase("месяцев")) {
                    dateType = rawType;
                } else {
                    max += " " + rawType;
                    dateType = words[i + 7];
                }
            } else {
                max = words[i + 6];
                String rawType = words[i + 7];
                if (rawType.equalsIgnoreCase("часов") || rawType.equalsIgnoreCase("лет") || rawType.equalsIgnoreCase("года") || rawType.equalsIgnoreCase("месяцев")) {
                    dateType = rawType;
                } else {
                    max += " " + rawType;
                    dateType = words[i + 8];
                }
            }
        } else if (words[i].equalsIgnoreCase("до")) {
            max = words[i + 1];
            String rawType = words[i + 2];
            if (rawType.equalsIgnoreCase("часов") || rawType.equalsIgnoreCase("лет") || rawType.equalsIgnoreCase("года") || rawType.equalsIgnoreCase("месяцев")) {
                dateType = rawType;
            } else {
                max += " " + rawType;
                dateType = words[i + 5];
            }
        } else {
            max = words[i + 3];
            String rawType = words[i + 4];
            if (rawType.equalsIgnoreCase("часов") || rawType.equalsIgnoreCase("лет") || rawType.equalsIgnoreCase("года") || rawType.equalsIgnoreCase("месяцев")) {
                dateType = rawType;
            } else {
                max += " " + rawType;
                dateType = words[i + 5];
            }
        }

        return new LawPartPunishData(null, min, max, dateType, false);
    }
}
