package com.find.law.portal.core.utils.tuple;

import com.find.law.portal.core.utils.pair.Pair;

import java.util.Optional;

/**
 * Интерфейс кортежа значений. Расширение {@link Pair} до трёх аргументов.
 *
 * @param <F> первый аргумент.
 * @param <S> второй аргумент.
 * @param <T> третий аргумент.
 */
public interface Tuple<F, S, T> extends Pair<F, S> {
    T third();

    default Optional<T> optionalThird() {
        return Optional.ofNullable(third());
    }

    static <F, S, T> Tuple<F, S, T> of(F first, S second, T third) {
        return new PermanentTuple<>(first, second, third);
    }
}
