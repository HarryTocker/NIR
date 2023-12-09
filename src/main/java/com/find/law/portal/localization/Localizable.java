package com.find.law.portal.localization;

import java.util.Collection;

public interface Localizable<T> {
    Collection<LocalizableTextInfo<T>> getInfos();

    T getLocalizedData();
}
