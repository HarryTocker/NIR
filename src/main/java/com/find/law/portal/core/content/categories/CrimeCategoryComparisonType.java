package com.find.law.portal.core.content.categories;

/**
 * Тип сравнения для наказания по категории преступления.
 * <br>
 * Определяет, является ли наказание по категории <b>до</b> указанного срока или <b>свыше</b>.
 *
 * @see #LESS
 * @see #BIGGER
 */
public enum CrimeCategoryComparisonType {
    /**
     * До
     */
    LESS,

    /**
     * Свыше
     */
    BIGGER
}
