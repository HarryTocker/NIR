package com.find.law.portal.core.parsers.converters.updater.updaters.laws;

import com.find.law.portal.core.parsers.converters.updater.AbstractDataUpdater;
import com.find.law.portal.core.parsers.PunishmentsParser;
import com.find.law.portal.exceptions.LawDataUpdaterException;
import com.find.law.portal.core.parsers.converters.updater.DataUpdaterPriority;
import com.find.law.portal.core.parsers.converters.ArticleDataLines;
import com.find.law.portal.core.content.laws.LawData;
import com.find.law.portal.core.content.laws.LawPartData;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Обновление данных частей закона {@link LawPartData} для {@link LawData}.
 * <br>
 * Определяет части закона из текста подготовленных данных статьи, парсит возможные наказания {@link PunishmentsParser},
 * учитывает части и пункты, которые утратили силу.
 */
public class LawPartDataUpdater extends AbstractDataUpdater<LawData> {
    /**
     * Regex паттерн примечаний. Примечания не фигурируют в {@link LawData}, поэтому пропускаются.
     */
    private static final Pattern SKIP_NOTES_PATTERN = Pattern.compile("Примечания\\.");

    /**
     * Regex паттерн некорректной строки, которую не нужно использовать во время обновления.
     */
    private static final Pattern INCORRECT_PART_PATTERN = Pattern.compile("^\\([А-Я]|^\\d+. \\(Д|^\\d+. \\(П|^ГЛАВА|^Примечан|^Раздел");

    /**
     * Regex паттерн для исключения текста, который не будет содержаться в части статьи.
     */
    private static final Pattern TEXT_IN_REDACTION_PATTERN = Pattern.compile("\\(В редакции|\\(Дополнение пунктом");

    /**
     * Regex паттерн для определения пункта в части статьи.
     */
    private static final Pattern PARAGRAPH_PATTERN = Pattern.compile("^.\\)");

    /**
     * Regex паттерн для получения текста из пункта части статьи.
     */
    private static final Pattern PARAGRAPH_ARTICLE_PATTERN = Pattern.compile("^.\\) (.+)");

    /**
     * Regex паттерн закона, содержащего несколько частей.
     */
    private static final Pattern MULTIPART_PATTERN = Pattern.compile("^\\d+.");

    /**
     * Строка для исключения частей, которые утратили силу.
     */
    private static final String PART_IS_NOT_VALID = "часть утратила силу";

    /**
     * Строка для исключения пунктов, которые утратили силу.
     */
    private static final String PARAGRAPH_IS_NOT_VALID = "пункт утратил силу";

    private final PunishmentsParser punishmentsParser;

    public LawPartDataUpdater(PunishmentsParser punishmentsParser) {
        super(DataUpdaterPriority.MEDIUM);
        this.punishmentsParser = punishmentsParser;
    }

    @Override
    public void update(LawData data, ArticleDataLines articleData) throws LawDataUpdaterException {
        List<LawPartData> parts = new ArrayList<>(10);
        Iterator<String> iterator = articleData.lines().iterator();
        Optional<LawPartData> part = Optional.empty();

        while (iterator.hasNext()) {
            String text = iterator.next();
            try {
                if (SKIP_NOTES_PATTERN.matcher(text).find()) {
                    break;
                }

                if (INCORRECT_PART_PATTERN.matcher(text).find()) {
                    continue;
                }

                if (MULTIPART_PATTERN.matcher(text).find()) {
                    part.ifPresent(parts::add);
                    part = text.toLowerCase(Locale.ROOT).contains(PART_IS_NOT_VALID)
                            ? Optional.empty()
                            : Optional.of(new LawPartData(formatPartName(text.substring(text.indexOf(' ') + 1)), List.of()));
                } else if (PARAGRAPH_PATTERN.matcher(text).find()) {
                    if (text.toLowerCase(Locale.ROOT).contains(PARAGRAPH_IS_NOT_VALID)) {
                        continue;
                    }
                    Matcher matcher = PARAGRAPH_ARTICLE_PATTERN.matcher(text);
                    if (matcher.find()) {
                        String paragraph = formatPartName(matcher.group(1));
                        part.ifPresent(p -> p.setName(p.getName() + "\n" + paragraph));
                    }
                } else if (text.toLowerCase(Locale.ROOT).contains("наказывается") || text.toLowerCase(Locale.ROOT).contains("наказываются")) {
                    part.ifPresent(p -> updatePunishments(p, text));
                } else if (text.toLowerCase(Locale.ROOT).contains(PART_IS_NOT_VALID)) {
                    part = Optional.empty();
                } else {
                    part = Optional.of(new LawPartData(formatPartName(text), List.of()));
                }
            } catch (Throwable cause) {
                throw new LawDataUpdaterException("Could not update law %s from text %s".formatted(data.getArticle(), text), cause);
            }
        }

        part.ifPresent(parts::add);
        data.setParts(parts);
    }

    /**
     * Форматировать название части статьи.
     *
     * @param text исходный текст.
     * @return отформатированное название.
     */
    private String formatPartName(String text) {
        text = text.trim();

        if (TEXT_IN_REDACTION_PATTERN.matcher(text).find()) {
            text = text.substring(0, text.indexOf('(') - 1);
        }

        if (text.endsWith(", -")) {
            return text.substring(0, text.length() - 3).trim();
        } else if (text.endsWith(";") || text.endsWith("-")) {
            return text.substring(0, text.length() - 1).trim();
        }

        return text.trim();
    }

    /**
     * Распарсить и установить наказания для части закона.
     *
     * @param part часть закона.
     * @param text исходный текст.
     */
    private void updatePunishments(LawPartData part, String text) {
        part.setPunishments(punishmentsParser.parse(text));
    }
}
