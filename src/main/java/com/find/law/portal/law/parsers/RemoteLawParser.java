package com.find.law.portal.law.parsers;

import com.find.law.portal.exceptions.LawParseException;
import com.find.law.portal.law.data.LawData;
import com.find.law.portal.law.LawParser;
import com.find.law.portal.law.data.LawPartData;
import com.find.law.portal.law.data.LawPartPunishData;
import com.find.law.portal.law.parsers.dto.LawApiResponseDto;
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

/**
 * Парсер законов из удаленного источника.
 */
public class RemoteLawParser implements LawParser {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Путь удаленного источника.
     */
    private final URL url;

    /**
     * Паттерн статьи закона.
     */
    private final Pattern lawDataPattern = Pattern.compile("Статья (\\d+). (\\D+)");

    /**
     * Паттерн некорректной строки, которую не нужно использовать во время парсинга.
     */
    private final Pattern incorrectPart = Pattern.compile("(^\\W\\))|(^\\W\\d+\\))|(\\(Ч)|(\\(Д)|(\\(С)|(\\(Н)|(^Примечан)|(^ГЛАВА)|(^Раздел)");

    /**
     * Паттерн штрафа.
     */
    private final Pattern penaltyPart = Pattern.compile("в размере до (\\D+) рублей");

    /**
     * Паттерн для исключения части закона.
     */
    private final Pattern lostPart = Pattern.compile("Часть утратила силу");

    /**
     * Паттерн окончания парсинга.
     */
    private final Pattern endPart = Pattern.compile("Президент Российской Федерации");

    public RemoteLawParser(URL url) {
        this.url = url;
    }

    @Override
    public List<LawData> parseLaws() throws IOException {
        Document rawPage;
        try (InputStream stream = url.openStream()) {
            // Получаем ответ из удаленного источника в JSON формате.
            logger.info("Getting response from remote API {}", url);
            JsonReader reader = new JsonReader(new InputStreamReader(stream));
            LawApiResponseDto dto = new Gson().fromJson(reader, LawApiResponseDto.class);
            // JSON ответа содержит два поля: Error и RedText.
            // Если Error не пуст, произошла ошибка при получении ответа.
            if (dto.getError() != null && StringUtils.isEmptyOrWhitespace(dto.getError())) {
                throw new IOException(dto.getError());
            }

            // Если ошибки нет, парсим ответ из RedText. Ответ в RedText содержится в формате HTML.
            rawPage = Jsoup.parse(dto.getRedText());
        }

        // Получаем элемент с ID p529. С него начинается основная часть законов.
        Element current = rawPage.getElementById("p529");
        // Создаем список под 350 законов (с запасом).
        List<LawData> laws = new ArrayList<>(350);

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
                    // Если достигли конца, завершаем цикл.
                    if (endPart.matcher(text).find()) {
                        break;
                    }

                    // Если некорректная строка, пропускаем ее.
                    if (incorrectPart.matcher(text).find() || StringUtils.isEmptyOrWhitespace(text)) {
                        // Если пункт утратил силу пропускаем его.
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

                    // Если текст начинается со статьи, сохраняем предыдущую, если имеется, и начинаем новую.
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
                        // Если строка содержит наказания, парсим их в текущую часть статьи.
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
                        // Парсим часть статьи для которой будут указанны наказания.
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
