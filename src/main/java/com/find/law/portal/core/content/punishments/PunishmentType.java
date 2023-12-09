package com.find.law.portal.core.content.punishments;

/**
 * Тип срока/суммы наказания.
 *
 * @see #HOURS
 * @see #DAYS
 * @see #WEEKS
 * @see #MONTHS
 * @see #YEARS
 * @see #AMOUNT
 * @see #THOUSAND_RUBLES
 * @see #MILLION_RUBLES
 */
public enum PunishmentType {
    /**
     * Сумма.
     */
    AMOUNT,
    /**
     * Тысяч рублей.
     */
    THOUSAND_RUBLES,
    /**
     * Миллионов рублей.
     */
    MILLION_RUBLES,
    /**
     * Часы.
     */
    HOURS,
    /**
     * Дни.
     */
    DAYS,
    /**
     * Недели.
     */
    WEEKS,
    /**
     * Месяцы.
     */
    MONTHS,
    /**
     * Года.
     */
    YEARS
}
