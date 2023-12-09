package com.find.law.portal.core.parsers.punishments;

import static com.find.law.portal.core.content.laws.LawPartPunishType.*;

import com.find.law.portal.core.parsers.PunishmentsParser;
import com.find.law.portal.core.content.laws.LawPartPunishData;
import com.find.law.portal.core.content.laws.LawPartPunishType;
import com.find.law.portal.core.content.punishments.PunishmentData;
import com.find.law.portal.core.utils.functions.FourFunction;
import com.find.law.portal.core.utils.pair.Pair;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Парсер данных наказания по части закона из текста.
 * <br>
 * Работа парсера основана на regex выражениях. Для каждого {@link LawPartPunishType} создается набор выражений,
 * учитывая срок наказания.
 */
public class GenericPunishmentsParser implements PunishmentsParser {
    /**
     * Regex выражение сроков/сумм наказаний.
     */
    private static final String PUNISHMENT_TYPE_REGEX = "(часов|дней|недель|месяцев|лет|года|тысяч|миллионов|миллиона|сумма|суммы|суммой)";
    /**
     * Regex начало строки.
     */
    private static final String START_LINE_REGEX = "^";
    /**
     * Regex выражение для определения опциональных наказаний.
     */
    private static final String OPTIONAL_LINE_REGEX = "(?<=с |со )";
    /**
     * Regex выражение для определения наказания <b>до</b> определенного срока/суммы.
     */
    private static final String PUNISHMENT_TO_REGEX = "(?! на тот же срок | .+? от ).+? до (\\D+?) ";
    /**
     * Regex выражение для определения наказания <b>от</b> и <b>до</b> определенного срока/суммы.
     */
    private static final String PUNISHMENT_BETWEEN_REGEX = "(?! на тот же срок).+? от (\\D+?)" + PUNISHMENT_TYPE_REGEX + "? до (\\D+?) ";
    /**
     * Regex выражение для определения сроков наказания из предыдущих данных наказания.
     */
    private static final String PUNISHMENT_FROM_PREVIOUS_REGEX = " на тот же срок";

    /**
     * Парсер срока наказания.
     */
    private final PunishmentDataParser punishmentDataParser = new PunishmentDataParser();

    /**
     * Парсеры законов с начала строки.
     */
    private final Map<LawPartPunishType, List<Pair<Pattern, FourFunction<LawPartPunishType, Matcher, LawPartPunishData, LawPartPunishData, LawPartPunishData>>>> startLinePatterns = new HashMap<>();

    /**
     * Парсеры опциональных законов.
     */
    private final Map<LawPartPunishType, List<Pair<Pattern, FourFunction<LawPartPunishType, Matcher, LawPartPunishData, LawPartPunishData, LawPartPunishData>>>> optionalLinePatterns = new HashMap<>();

    public GenericPunishmentsParser() {
        startLinePatterns.put(LIFE_IMPRISONMENT, List.of(Pair.of(Pattern.compile(LIFE_IMPRISONMENT.getValue()), (t, m, c, p) -> new LawPartPunishData())));
        Arrays.stream(values()).forEach(this::createPatternsForPunishment);
    }

    /**
     * Создать парсеры для определенного типа наказания.
     * @param type тип наказания.
     */
    private void createPatternsForPunishment(LawPartPunishType type) {
        if (type == LIFE_IMPRISONMENT) {
            return;
        }

        String regex = type.getValue();
        startLinePatterns.put(type, List.of(
                Pair.of(Pattern.compile(START_LINE_REGEX + regex + PUNISHMENT_TO_REGEX + PUNISHMENT_TYPE_REGEX), this::parsePunishToPattern),
                Pair.of(Pattern.compile(START_LINE_REGEX + regex + PUNISHMENT_BETWEEN_REGEX + PUNISHMENT_TYPE_REGEX), this::parsePunishBetweenPattern),
                Pair.of(Pattern.compile(START_LINE_REGEX + regex + PUNISHMENT_FROM_PREVIOUS_REGEX), this::parsePunishFromPreviousPattern)
        ));

        optionalLinePatterns.put(type, List.of(
                Pair.of(Pattern.compile(OPTIONAL_LINE_REGEX + regex + PUNISHMENT_TO_REGEX + PUNISHMENT_TYPE_REGEX), this::parseOptionalToPattern),
                Pair.of(Pattern.compile(OPTIONAL_LINE_REGEX + regex + PUNISHMENT_BETWEEN_REGEX + PUNISHMENT_TYPE_REGEX), this::parseOptionalBetweenPattern)
        ));
    }

    /**
     * Получить данные наказания.
     *
     * @param value значение наказания.
     * @param type тип наказания {@link #PUNISHMENT_TYPE_REGEX}.
     */
    private PunishmentData parsePunishmentData(String value, String type) {
        return punishmentDataParser.parse(value, type);
    }

    /**
     * Распарсить наказание <b>до</b> определенного срока/суммы.
     *
     * @param type тип наказания.
     * @param matcher матчер подходящего regex выражения.
     * @param parent (не используется в данном методе).
     * @param previous (не используется в данном методе).
     */
    private LawPartPunishData parsePunishToPattern(LawPartPunishType type, Matcher matcher, LawPartPunishData parent, LawPartPunishData previous) {
        PunishmentData max = parsePunishmentData(matcher.group(1), matcher.group(2));
        return new LawPartPunishData(type, null, max);
    }

    /**
     * Распарсить наказание <b>от</b> и <b>до</b> определенного срока/суммы.
     *
     * @param type тип наказания.
     * @param matcher матчер подходящего regex выражения.
     * @param parent (не используется в данном методе).
     * @param previous (не используется в данном методе).
     */
    private LawPartPunishData parsePunishBetweenPattern(LawPartPunishType type, Matcher matcher, LawPartPunishData parent, LawPartPunishData previous) {
        String minType = matcher.group(2) == null ? matcher.group(4) : matcher.group(2);
        PunishmentData min = parsePunishmentData(matcher.group(1), minType);
        PunishmentData max = parsePunishmentData(matcher.group(3), matcher.group(4));
        return new LawPartPunishData(type, min, max);
    }

    /**
     * Распарсить наказание из предыдущего элемента.
     *
     * @param type тип наказания.
     * @param matcher (не используется в данном методе).
     * @param parent (не используется в данном методе).
     * @param previous предыдущий элемент.
     */
    private LawPartPunishData parsePunishFromPreviousPattern(LawPartPunishType type, Matcher matcher, LawPartPunishData parent, LawPartPunishData previous) {
        if (previous == null) {
            throw new RuntimeException("Previous element is does not exist");
        }
        if (previous.getType() == LIFE_IMPRISONMENT) {
            throw new RuntimeException("Life imprisonment cannot contain punishment from previous element");
        }
        PunishmentData min = previous.getMin() == null ? null : previous.getMin().clone();
        PunishmentData max = previous.getMax() == null ? null : previous.getMax().clone();
        return new LawPartPunishData(type, min, max);
    }

    /**
     * Распарсить наказание <b>до</b> определенного срока/суммы и установить его в родительский элемент.
     *
     * @param type тип наказания.
     * @param matcher матчер подходящего regex выражения.
     * @param parent родительский элемент.
     * @param previous (не используется в данном методе).
     */
    private LawPartPunishData parseOptionalToPattern(LawPartPunishType type, Matcher matcher, LawPartPunishData parent, LawPartPunishData previous) {
        LawPartPunishData data = parsePunishToPattern(type, matcher, parent, previous);
        parent.getOptionals().add(data);
        return data;
    }

    /**
     * Распарсить наказание <b>от</b> и <b>до</b> определенного срока/суммы и установить его в родительский элемент.
     *
     * @param type тип наказания.
     * @param matcher матчер подходящего regex выражения.
     * @param parent родительский элемент.
     * @param previous (не используется в данном методе).
     */
    private LawPartPunishData parseOptionalBetweenPattern(LawPartPunishType type, Matcher matcher, LawPartPunishData parent, LawPartPunishData previous) {
        LawPartPunishData data = parsePunishBetweenPattern(type, matcher, parent, previous);
        parent.getOptionals().add(data);
        return data;
    }

    @Override
    public List<LawPartPunishData> parse(String text) {
        text = text.toLowerCase(Locale.ROOT);
        if (text.startsWith("наказывается") || text.startsWith("наказываются")) {
            text = text.substring(12).trim();
        }

        List<String> lines = Arrays.stream(text.split("либо(?! без такового)"))
                .map(s -> s.replaceAll("\\,|\\.|\\(в редакции .+\\)", ""))
                .map(String::trim)
                .toList();

        List<LawPartPunishData> result = new ArrayList<>(lines.size());
        LawPartPunishData previous = null;
        for (String line : lines) {
            Optional<LawPartPunishData> current = parse(line, previous);
            if (current.isPresent()) {
                previous = current.get();
                result.add(current.get());
            }
        }

        return result;
    }

    /**
     * Распарсить текст. Сначала парсинг происходит по паттернам начала сроки {@link #startLinePatterns}, после чего
     * парсятся опциональные наказания для данной части.
     *
     * @param line текст с одним наказанием либо с одним наказанием и опциональными наказаниями.
     * @param previous предыдущий элемент.
     * @return распарсенные данные наказания по части закона.
     */
    private Optional<LawPartPunishData> parse(String line, LawPartPunishData previous) {
        Optional<LawPartPunishData> data = parse(startLinePatterns, line, null, previous, true);
        if (data.isEmpty()) {
            return data;
        }

        parse(optionalLinePatterns, line, data.get(), previous, false);

        return data;
    }

    /**
     * Распарсить текст подходящим парсером.
     * <br>
     * Метод может прервать парсинг на первом подходящем элементе, за это отвечает аргумент <b>skipOnFirst</b>.
     * Прерывание подойдет для парсеров начала сроки, так как основное наказие в тексте всегда одно.
     * Выполнение без прерывания подойдет для парсеров опциональных наказаний, так как в тексте их может быть несколько.
     *
     * @param parsers парсеры с паттернами для сравнения.
     * @param line текст с одним наказанием либо с одним наказанием и опциональными наказаниями.
     * @param current текущий элемент.
     * @param previous предыдущий элемент.
     * @param skipOnFirst завершить парсинг при нахождении первого элемента.
     * @return распарсенные данные наказания по части закона.
     */
    private Optional<LawPartPunishData> parse(
            Map<LawPartPunishType, List<Pair<Pattern, FourFunction<LawPartPunishType, Matcher, LawPartPunishData, LawPartPunishData, LawPartPunishData>>>> parsers,
            String line,
            LawPartPunishData current,
            LawPartPunishData previous,
            boolean skipOnFirst)
    {
        LawPartPunishData data = null;
        for (Map.Entry<LawPartPunishType, List<Pair<Pattern, FourFunction<LawPartPunishType, Matcher, LawPartPunishData, LawPartPunishData, LawPartPunishData>>>> entry : parsers.entrySet()) {
            LawPartPunishType type = entry.getKey();
            for (Pair<Pattern, FourFunction<LawPartPunishType, Matcher, LawPartPunishData, LawPartPunishData, LawPartPunishData>> pair : entry.getValue()) {
                Matcher matcher = pair.first().matcher(line);
                if (matcher.find()) {
                    data = pair.second().apply(type, matcher, current, previous);
                    break;
                }
            }

            if (data != null && skipOnFirst) {
                break;
            }
        }

        return Optional.ofNullable(data);
    }
}
