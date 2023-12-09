package com.find.law.portal.exceptions;

/**
 * Исключение при парсинге законов.
 */
public class LawParseException extends Exception {
    public LawParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
