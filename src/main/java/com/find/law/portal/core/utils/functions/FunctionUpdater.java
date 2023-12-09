package com.find.law.portal.core.utils.functions;

@FunctionalInterface
public interface FunctionUpdater<E, D> {
    void apply(E entity, D data);
}
