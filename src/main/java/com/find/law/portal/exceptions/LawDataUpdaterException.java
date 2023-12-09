package com.find.law.portal.exceptions;

/**
 * Исключение при обновлении данных закона.
 */
public class LawDataUpdaterException extends Exception {
    public LawDataUpdaterException(String message) {
        super(message);
    }

    public LawDataUpdaterException(String message, Throwable cause) {
        super(message, cause);
    }
}
