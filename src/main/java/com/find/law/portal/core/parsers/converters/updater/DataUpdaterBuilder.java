package com.find.law.portal.core.parsers.converters.updater;

import java.util.List;

/**
 * Интерфейс построителя обновления данных.
 *
 * @param <T> тип, для которого будет вызвано обновление данных.
 */
public interface DataUpdaterBuilder<T> {
    /**
     * Добавить обновление данных в построитель.
     *
     * @param updater обновление данных.
     * @return текущий построитель.
     */
    DataUpdaterBuilder<T> addUpdater(DataUpdater<T> updater);

    /**
     * Собрать результат всех обновлений данных в коллекцию для вызова.
     *
     * @return коллекция обновлений данных.
     */
    List<DataUpdater<T>> build();
}
