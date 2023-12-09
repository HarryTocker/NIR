package com.find.law.portal.core.parsers.converters.categories;

import static com.find.law.portal.core.content.laws.LawType.*;
import static com.find.law.portal.core.content.categories.CrimeCategoryType.*;
import static com.find.law.portal.core.content.categories.CrimeCategoryComparisonType.*;

import com.find.law.portal.core.content.categories.CrimeCategoryComparisonType;
import com.find.law.portal.core.content.categories.CrimeCategoryData;
import com.find.law.portal.core.content.categories.CrimeCategoryType;
import com.find.law.portal.core.content.laws.LawType;
import com.find.law.portal.core.parsers.converters.CrimeCategoryConverter;
import com.find.law.portal.core.parsers.converters.ArticleDataLines;
import com.find.law.portal.core.parsers.punishments.PunishmentDataParser;
import com.find.law.portal.core.utils.pair.Pair;

import java.util.*;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Конвертер {@link ArticleDataLines} в данные категорий преступлений {@link CrimeCategoryData}.
 * <br>
 * Работа конвертера основана на regex выражениях для каждого типа категории преступления.
 *
 * @see CrimeCategoryType
 */
public class GenericCrimeCategoryConverter implements CrimeCategoryConverter {
    /**
     * Парсер данных наказания.
     */
    private final PunishmentDataParser punishmentDataParser = new PunishmentDataParser();

    /**
     * Коллекция функций-конвертеров для regex паттернов.
     */
    private final List<Pair<Pattern, BiFunction<CrimeCategoryType, Matcher, CrimeCategoryData>>> converters = List.of(
            Pair.of(Pattern.compile("умышленные .+? не превышает (\\D+?) (лет)"), this::getDataForIntentionalLessType),
            Pair.of(Pattern.compile("неосторожные .+? не превышает (\\D+?) (лет)"), this::getDataForNegligenceLessType),
            Pair.of(Pattern.compile("умышленные .+? свыше (\\D+?) (лет)"), this::getDataForIntentionalBiggerType),
            Pair.of(Pattern.compile("неосторожные .+? свыше (\\D+?) (лет)"), this::getDataForNegligenceBiggerType)
    );

    /**
     * Коллекция типов категорий преступлений для regex паттернов.
     */
    private final Map<Pattern, CrimeCategoryType> crimeTypes = Map.ofEntries(
            Map.entry(Pattern.compile("преступлениями небольшой тяжести признаются"), MINOR),
            Map.entry(Pattern.compile("преступлениями средней тяжести признаются"), MEDIUM),
            Map.entry(Pattern.compile("(?<!особо) тяжкими преступлениями признаются"), SERIOUS),
            Map.entry(Pattern.compile("особо тяжкими преступлениями признаются"), PARTICULARLY_SERIOUS)
    );

    /**
     * Создать умышленное преступление с указанной степенью тяжести с наказанием <b>до</b> определенного срока.
     */
    private CrimeCategoryData getDataForIntentionalLessType(CrimeCategoryType type, Matcher matcher) {
        return getData(INTENTIONAL, type, LESS, matcher.group(2), matcher.group(1));
    }

    /**
     * Создать преступление по неосторожности с указанной степенью тяжести с наказанием <b>до</b> определенного срока.
     */
    private CrimeCategoryData getDataForNegligenceLessType(CrimeCategoryType type, Matcher matcher) {
        return getData(NEGLIGENCE, type, LESS, matcher.group(2), matcher.group(1));
    }

    /**
     * Создать умышленное преступление с указанной степенью тяжести с наказанием <b>свыше</b> определенного срока.
     */
    private CrimeCategoryData getDataForIntentionalBiggerType(CrimeCategoryType type, Matcher matcher) {
        return getData(INTENTIONAL, type, BIGGER, matcher.group(2), matcher.group(1));
    }

    /**
     * Создать преступление по неосторожности с указанной степенью тяжести с наказанием <b>свыше</b> определенного срока.
     */
    private CrimeCategoryData getDataForNegligenceBiggerType(CrimeCategoryType type, Matcher matcher) {
        return getData(NEGLIGENCE, type, BIGGER, matcher.group(2), matcher.group(1));
    }

    /**
     * Создать данные категории преступления.
     *
     * @param lawType тип преступления.
     * @param categoryType тип тяжести преступления.
     * @param comparisonType тип срока преступления.
     * @param punishmentType тип срока наказания.
     * @param value срок наказания.
     */
    private CrimeCategoryData getData(LawType lawType, CrimeCategoryType categoryType, CrimeCategoryComparisonType comparisonType, String punishmentType, String value) {
        return new CrimeCategoryData(lawType, categoryType, comparisonType, punishmentDataParser.parse(value, punishmentType));
    }

    @Override
    public List<CrimeCategoryData> convert(ArticleDataLines lawData) {
        return lawData.lines().stream()
                .map(this::convert)
                .flatMap(Collection::stream)
                .toList();
    }

    /**
     * Получить данные категории преступления.
     * <br>
     * Так как одна строка может содержать несколько категорий, метод возвращает коллекцию.
     *
     * @param line линия текста статьи.
     * @return коллекция категорий.
     */
    private List<CrimeCategoryData> convert(String line) {
        final String lowerLine = line.toLowerCase(Locale.ROOT);
        Optional<CrimeCategoryType> typeOptional = getCrimeType(lowerLine);
        if (typeOptional.isEmpty()) {
            return List.of();
        }

        CrimeCategoryType type = typeOptional.get();
        return converters.stream()
                .map(p -> Pair.of(p.first().matcher(line), p.second()))
                .filter(p -> p.first().find())
                .map(p -> p.second().apply(type, p.first()))
                .toList();
    }

    /**
     * Получить тип категории преступления.
     *
     * @param line линия текста статьи.
     */
    private Optional<CrimeCategoryType> getCrimeType(String line) {
        return crimeTypes.entrySet().stream()
                .filter(e -> e.getKey().matcher(line).find())
                .findFirst().map(Map.Entry::getValue);
    }
}
