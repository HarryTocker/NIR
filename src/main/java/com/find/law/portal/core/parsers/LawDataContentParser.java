package com.find.law.portal.core.parsers;

import com.find.law.portal.core.content.LawDataContent;
import com.find.law.portal.exceptions.CrimeCategoryParseException;
import com.find.law.portal.exceptions.LawParseException;

import java.io.IOException;

/**
 * Интерфейс парсера данных законов.
 *
 * @see LawDataContent
 * @see LawParser
 * @see CrimeCategoryParser
 */
public interface LawDataContentParser {
    /**
     * Распарсить данные законов.
     *
     * @return данные законов {@link LawDataContent}.
     * @throws IOException не удалось получить <b>сырые/исходные</b> данные для парсинга.
     * @throws CrimeCategoryParseException не удалось распарсить данные категории преступления.
     * @throws LawParseException не удалось распарсить данные закона.
     */
    LawDataContent parse() throws IOException, CrimeCategoryParseException, LawParseException;
}
