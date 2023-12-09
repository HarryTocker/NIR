package com.find.law.portal.core.parsers.punishments;

import static com.find.law.portal.core.content.punishments.PunishmentType.*;

import com.find.law.portal.core.content.punishments.PunishmentData;
import com.find.law.portal.core.content.punishments.PunishmentType;
import com.find.law.portal.core.utils.pair.Pair;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Парсер срока наказания.
 */
public class PunishmentDataParser {
    private final List<Pair<Pattern, PunishmentType>> types = List.of(
            Pair.of(Pattern.compile("часов|часа"), HOURS),
            Pair.of(Pattern.compile("дней|дня"), DAYS),
            Pair.of(Pattern.compile("недель|недели"), WEEKS),
            Pair.of(Pattern.compile("месяцев|месяца"), MONTHS),
            Pair.of(Pattern.compile("лет|года"), YEARS),
            Pair.of(Pattern.compile("сумма|суммы|суммой"), AMOUNT),
            Pair.of(Pattern.compile("тысяч|тысячи"), THOUSAND_RUBLES),
            Pair.of(Pattern.compile("миллионов|миллиона"), MILLION_RUBLES)
    );

    /**
     * Распарсить данные значения и типа наказания в {@link PunishmentData}.
     *
     * @param value значение наказания.
     * @param type тип наказания.
     */
    public PunishmentData parse(String value, String type) {
        return new PunishmentData(getPunishmentValue(value), getType(type));
    }

    /**
     * Форматировать значение наказания:
     * <ul>
     *     <li>полутора => один целых пять десятых</li>
     *     <li>тримирование текста</li>
     * </ul>
     *
     * @param value значение наказания.
     * @return отформатированное значение.
     */
    private String getPunishmentValue(String value) {
        if (value.equals("полутора")) {
            value = "один целых пять десятых";
        }

        return value.trim();
    }

    /**
     * Получить тип срока/суммы наказания.
     *
     * @param value значение.
     * @return тип срока/суммы наказания.
     */
    private PunishmentType getType(String value) {
        return types.stream()
                .filter(p -> p.first().matcher(value).find())
                .findFirst()
                .map(Pair::second)
                .orElseThrow(() -> new IllegalArgumentException("Could not get type for value [%s]".formatted(value)));
    }
}
