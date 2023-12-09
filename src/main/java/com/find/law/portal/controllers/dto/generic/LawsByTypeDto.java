package com.find.law.portal.controllers.dto.generic;

import java.util.Collection;

public record LawsByTypeDto(Collection<String> negligence, Collection<String> intentional, Collection<String> unknown) {
}
