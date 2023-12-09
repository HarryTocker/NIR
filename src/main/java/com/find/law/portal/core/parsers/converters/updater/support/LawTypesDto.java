package com.find.law.portal.core.parsers.converters.updater.support;

import java.util.Collection;

public record LawTypesDto(Collection<String> negligence, Collection<String> intentional, Collection<String> unknown) {
}
