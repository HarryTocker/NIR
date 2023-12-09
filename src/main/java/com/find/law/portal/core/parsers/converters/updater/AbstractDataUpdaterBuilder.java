package com.find.law.portal.core.parsers.converters.updater;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Абстрактный класс построителя обновления данных. Каждый реализующий класс может содержать свои методы для добавления
 * конкретных обновлений данных.
 *
 * @param <T> тип, для которого будет вызвано обновление данных.
 */
public abstract class AbstractDataUpdaterBuilder<T> implements DataUpdaterBuilder<T> {
    private final List<DataUpdater<T>> updaters = new LinkedList<>();

    @Override
    public DataUpdaterBuilder<T> addUpdater(DataUpdater<T> updater) {
        updaters.add(updater);
        return this;
    }

    @Override
    public List<DataUpdater<T>> build() {
        return updaters.stream().sorted(Comparator.comparing(DataUpdater::priority)).toList();
    }
}
