package com.find.law.portal.core.utils.pair;

/**
 * Неизменяемая пара значений.
 *
 * @param first первое значение.
 * @param second второе значение.
 */
public record PermanentPair<F, S>(F first, S second) implements Pair<F, S> {
}
