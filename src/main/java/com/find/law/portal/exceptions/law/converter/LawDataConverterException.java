package com.find.law.portal.exceptions.law.converter;

import com.find.law.portal.core.content.laws.LawData;

public class LawDataConverterException extends RuntimeException {
    public LawDataConverterException(LawData data, String message, Throwable cause) {
        super("Could not update law data [%s]: %s".formatted(data.getArticle(), message), cause);
    }
}
