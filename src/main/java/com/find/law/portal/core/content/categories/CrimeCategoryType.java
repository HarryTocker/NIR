package com.find.law.portal.core.content.categories;

/**
 * Тип тяжести категории преступления.
 *
 * @see #MINOR
 * @see #MEDIUM
 * @see #SERIOUS
 * @see #PARTICULARLY_SERIOUS
 */
public enum CrimeCategoryType {
    /**
     * Особо тяжкое преступление.
     */
    PARTICULARLY_SERIOUS("Особо тяжкое преступление"),

    /**
     * Тяжкое преступление.
     */
    SERIOUS("Тяжкое преступление"),

    /**
     * Преступление средней тяжести.
     */
    MEDIUM("Преступление средней тяжести"),

    /**
     * Преступление небольшой тяжести.
     */
    MINOR("Преступление небольшой тяжести");

    private final String value;

    CrimeCategoryType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
