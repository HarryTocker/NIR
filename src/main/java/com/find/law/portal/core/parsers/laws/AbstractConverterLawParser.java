package com.find.law.portal.core.parsers.laws;

import com.find.law.portal.core.parsers.LawParser;
import com.find.law.portal.core.parsers.converters.LawDataConverter;
import com.find.law.portal.exceptions.LawParseException;
import com.find.law.portal.core.content.laws.LawData;
import com.find.law.portal.core.parsers.converters.ArticleDataLines;
import com.find.law.portal.exceptions.law.parser.LawParserDataLinesException;

import java.util.List;

/**
 * Абстрактный парсер законов, основан на {@link LawDataConverter}.
 * <br>
 * Парсер получает <b>сырые/исходные</b> данные и маппит их в {@link ArticleDataLines} (маппинг происходит в наследуемых классах),
 * после чего передает их в конвертер.
 *
 * @param <R> тип <b>сырых/исходных</b> данных для парсинга.
 */
public abstract class AbstractConverterLawParser<R> implements LawParser<R> {
    private final LawDataConverter converter;

    protected AbstractConverterLawParser(LawDataConverter converter) {
        this.converter = converter;
    }

    /**
     * Подготовить <b>сырые/исходные</b> данные для конвертера, преобразовав их в {@link ArticleDataLines}.
     *
     * @param rawData <b>сырые/исходные</b> данные.
     * @return подготовленные данные для конвертера.
     * @throws LawParserDataLinesException не удалось подготовить данные для конвертации.
     */
    protected abstract List<ArticleDataLines> getLawDataLines(R rawData) throws LawParserDataLinesException;

    @Override
    public List<LawData> parse(R rawData) throws LawParseException {
        try {
            return converter.convert(getLawDataLines(rawData));
        } catch (LawParserDataLinesException cause) {
            throw new LawParseException("Could not get data lines for parsing", cause);
        } catch (Throwable cause) {
            throw new LawParseException("Could not parse laws", cause);
        }
    }
}
