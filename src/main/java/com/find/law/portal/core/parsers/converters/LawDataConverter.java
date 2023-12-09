package com.find.law.portal.core.parsers.converters;

import com.find.law.portal.core.content.laws.LawData;
import com.find.law.portal.exceptions.law.converter.LawDataConverterException;

import java.util.List;

/**
 * Интерфейс конвертера {@link ArticleDataLines} в данные закона {@link LawData}.
 */
public interface LawDataConverter {
    /**
     * Конвертировать подготовленные данные в данные закона.
     *
     * @param lawData коллекция подготовленных данных.
     * @return коллекция данных законов.
     * @throws LawDataConverterException не удалось конвертировать данные закона.
     */
    List<LawData> convert(List<ArticleDataLines> lawData) throws LawDataConverterException;
}
