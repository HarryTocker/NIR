package com.find.law.portal.mappers;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Интерфейс преобразователя объектов из одной сущности в другую.
 *
 * @param <F> исходная сущность.
 * @param <T> результирующая сущность.
 */
public interface ForwardMapper<F, T> {
    /**
     * Преобразовать переданную сущность к новой сущности.
     *
     * @param entity переданная сущность.
     * @return новая сущность.
     */
    T map(F entity);

    /**
     * Преобразовать коллекцию переданных сущностей к коллекции новых сущностей.
     *
     * @param entities коллекция переданных сущностей.
     * @return коллекция новых сущностей.
     */
    default Collection<T> map(Collection<F> entities) {
        return entities.stream().map(this::map).collect(Collectors.toList());
    }
}
