package com.find.law.portal.localization;

import jakarta.servlet.http.HttpServletRequest;

public interface LocalizationService {
    <T extends Enum<?>> String getEnumLocalization(T value, HttpServletRequest request);

    String getTextLocalization(String value, HttpServletRequest request);

    <T> T localize(Localizable<T> entity, HttpServletRequest request);
}
