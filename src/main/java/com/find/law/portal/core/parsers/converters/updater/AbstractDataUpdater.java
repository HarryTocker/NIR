package com.find.law.portal.core.parsers.converters.updater;

/**
 * Абстрактный класс обновления данных. Реализует метод получения приоритета.
 *
 * @see #priority()
 */
public abstract class AbstractDataUpdater<T> implements DataUpdater<T> {
    /**
     * Приоритет вызова.
     */
    private final DataUpdaterPriority priority;

    protected AbstractDataUpdater(DataUpdaterPriority priority) {
        this.priority = priority;
    }

    @Override
    public DataUpdaterPriority priority() {
        return priority;
    }
}
