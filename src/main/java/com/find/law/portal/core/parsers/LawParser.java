package com.find.law.portal.core.parsers;

import com.find.law.portal.exceptions.LawParseException;
import com.find.law.portal.core.content.laws.LawData;

import java.util.List;

/**
 * Интерфейс парсера законов.
 *
 * @see LawData
 * @param <R> тип <b>сырых/исходных</b> данных для парсинга.
 */
public interface LawParser<R> {
    /**
     * Распарсить законы.
     *
     * @param rawData сырые/исходные данные для парсинга.
     * @return данные законов.
     * @throws LawParseException не удалось распарсить данные закона.
     */
    List<LawData> parse(R rawData) throws LawParseException;
}
