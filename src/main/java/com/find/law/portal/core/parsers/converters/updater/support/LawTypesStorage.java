package com.find.law.portal.core.parsers.converters.updater.support;

import java.util.Set;

public interface LawTypesStorage {
    Set<String> getNegligence();

    Set<String> getIntentional();

    Set<String> getUnknown();
}
