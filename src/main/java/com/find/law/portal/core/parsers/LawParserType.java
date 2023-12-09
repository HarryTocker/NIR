package com.find.law.portal.core.parsers;

/**
 * Тип парсера.
 *
 * @see #HTML_LOCAL
 * @see #HTML_REMOTE
 */
public enum LawParserType {
    /**
     * HTML парсер локального файла.
     */
    HTML_LOCAL,

    /**
     * HTML парсер из запроса на удаленный источник.
     */
    HTML_REMOTE
}
