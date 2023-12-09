package com.find.law.portal.core.utils.functions;

import java.util.Objects;
import java.util.function.Function;

/**
 * Функциональный интерфейс, который принимает четыре параметра и возвращает значение.
 *
 * @param <T> первый параметр.
 * @param <U> второй параметр.
 * @param <V> третий параметр.
 * @param <Y> четвертый параметр.
 * @param <R> возвращаемое значение.
 */
@FunctionalInterface
public interface FourFunction<T, U, V, Y, R> {
    R apply(T t, U u, V v, Y y);

    default <K> FourFunction<T, U, V, Y, K> andThen(Function<? super R, ? extends K> after) {
        Objects.requireNonNull(after);
        return (T t, U u, V v, Y y) -> after.apply(apply(t, u, v, y));
    }
}
