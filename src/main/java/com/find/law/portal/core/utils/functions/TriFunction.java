package com.find.law.portal.core.utils.functions;

import java.util.Objects;
import java.util.function.Function;

/**
 * Функциональный интерфейс, который принимает три параметра и возвращает значение.
 *
 * @param <T> первый параметр.
 * @param <U> второй параметр.
 * @param <V> третий параметр.
 * @param <R> возвращаемое значение.
 */
@FunctionalInterface
public interface TriFunction<T, U, V, R> {

    R apply(T t, U u, V v);

    default <K> TriFunction<T, U, V, K> andThen(Function<? super R, ? extends K> after) {
        Objects.requireNonNull(after);
        return (T t, U u, V v) -> after.apply(apply(t, u, v));
    }
}