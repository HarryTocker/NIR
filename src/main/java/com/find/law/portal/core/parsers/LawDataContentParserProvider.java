package com.find.law.portal.core.parsers;

/**
 * Интерфейс провайдера парсеров контента законов.
 *
 * @see LawDataContentParser
 */
public interface LawDataContentParserProvider {
    /**
     * Получить парсер контента законов.
     */
    LawDataContentParser get();
}
