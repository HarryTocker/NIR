package com.find.law.portal.core.utils.pair;

import java.util.Optional;

/**
 * Интерфейс пары значений.
 *
 * @param <F> первый аргумент.
 * @param <S> второй аргумент.
 */
public interface Pair<F, S> {
    /**
     * Получить первое значение.
     */
    F first();

    /**
     * Получить второе значение.
     */
    S second();

    /**
     * Вернуть {@link Optional} первого значения.
     */
    default Optional<F> optionalFirst() {
        return Optional.ofNullable(first());
    }

    /**
     * Вернуть {@link Optional} второго значения.
     */
    default Optional<S> optionalSecond() {
        return Optional.ofNullable(second());
    }

    /**
     * Создать неизменяемую пару значений.
     *
     * @param first первое значение.
     * @param second второе значение.
     * @return неизменяемая пара значений.
     */
    static <F, S> Pair<F, S> of(F first, S second) {
        return new PermanentPair<>(first, second);
    }
}
