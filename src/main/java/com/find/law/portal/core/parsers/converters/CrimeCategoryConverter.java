package com.find.law.portal.core.parsers.converters;

import com.find.law.portal.core.content.categories.CrimeCategoryData;

import java.util.List;

/**
 * Интерфейс конвертера {@link ArticleDataLines} в данные категорий преступлений {@link CrimeCategoryData}.
 */
public interface CrimeCategoryConverter {
    /**
     * Конвертировать подготовленные данные в данные категории преступления.
     *
     * @param lawData подготовленные данные.
     * @return коллекция данных категорий преступлений.
     */
    List<CrimeCategoryData> convert(ArticleDataLines lawData);
}
