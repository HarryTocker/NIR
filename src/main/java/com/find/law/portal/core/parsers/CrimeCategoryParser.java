package com.find.law.portal.core.parsers;

import com.find.law.portal.core.content.categories.CrimeCategoryData;
import com.find.law.portal.exceptions.CrimeCategoryParseException;

import java.util.List;

/**
 * Интерфейс парсера категорий преступлений.
 *
 * @see CrimeCategoryData
 * @param <R> сырые данные для парсинга.
 */
public interface CrimeCategoryParser<R> {
    /**
     * Распарсить категории преступлений.
     *
     * @param rawData сырые данные для парсинга.
     * @return данные категорий преступлений.
     * @throws CrimeCategoryParseException не удалось распарсить данные категории преступления.
     */
    List<CrimeCategoryData> parse(R rawData) throws CrimeCategoryParseException;
}
