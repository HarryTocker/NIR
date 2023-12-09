package com.find.law.portal.core.parsers.converters.updater;

/**
 * Приоритет вызова обновления данных.
 *
 * @see #LOW
 * @see #MEDIUM
 * @see #HIGH
 * @see #VERY_HIGH
 */
public enum DataUpdaterPriority {
    /**
     * Самый высокий приоритет.
     */
    VERY_HIGH,

    /**
     * Высокий приоритет.
     */
    HIGH,

    /**
     * Средний приоритет.
     */
    MEDIUM,

    /**
     * Низкий приоритет.
     */
    LOW
}
