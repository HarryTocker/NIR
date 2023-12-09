package com.find.law.portal.core.utils.tuple;

public record PermanentTuple<F, S, T>(F first, S second, T third) implements Tuple<F, S, T> {
}
