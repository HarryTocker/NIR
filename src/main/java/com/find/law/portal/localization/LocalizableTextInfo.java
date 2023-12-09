package com.find.law.portal.localization;

import com.find.law.portal.core.utils.functions.FunctionUpdater;

import java.util.function.Supplier;


public record LocalizableTextInfo<T>(LocalizableType type, Supplier<Object> value, FunctionUpdater<T, Object> updater) {
}
