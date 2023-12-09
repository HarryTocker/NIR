package com.find.law.portal.core.content.laws;

/**
 * Тип наказания.
 *
 * @see #FINE
 * @see #RIGHT_DEPRIVATION
 * @see #COMPULSORY_WORKS
 * @see #CORRECTIONAL_LABOR
 * @see #MILITARY_SERVICE_RESTRICTION
 * @see #DETENTION_IN_DISCIPLINARY_UNIT
 * @see #RESTRICTION_OF_FREEDOM
 * @see #FORCED_LABOR
 * @see #ARREST
 * @see #DEPRIVATION_OF_LIBERTY
 * @see #LIFE_IMPRISONMENT
 */
public enum LawPartPunishType {
    /**
     * Штраф.
     */
    FINE("штрафом"),
    /**
     * Лишение права занимать определенные должности.
     */
    RIGHT_DEPRIVATION("лишением права занимать определенные должности или заниматься определенной деятельностью"),
    /**
     * Обязательные работы.
     */
    COMPULSORY_WORKS("обязательными работами"),
    /**
     * Исправительные работы.
     */
    CORRECTIONAL_LABOR("исправительными работами"),
    /**
     * Ограничения по военной службе.
     */
    MILITARY_SERVICE_RESTRICTION("ограничением по военной службе"),
    /**
     * Содержание в дисциплинарной воинской части.
     */
    DETENTION_IN_DISCIPLINARY_UNIT("содержанием в дисциплинарной воинской части"),
    /**
     * Ограничение свободы.
     */
    RESTRICTION_OF_FREEDOM("ограничением свободы"),
    /**
     * Принудительные работы.
     */
    FORCED_LABOR("принудительными работами"),
    /**
     * Арест.
     */
    ARREST("арестом"),
    /**
     * Лишение свободы.
     */
    DEPRIVATION_OF_LIBERTY("лишением свободы"),
    /**
     * Пожизненное лишение свободы.
     */
    LIFE_IMPRISONMENT("пожизненным лишением свободы");

    private final String value;

    LawPartPunishType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
