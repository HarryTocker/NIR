package com.find.law.portal.core.parsers.general;

import com.find.law.portal.core.parsers.CrimeCategoryParser;
import com.find.law.portal.core.parsers.LawDataContentParser;
import com.find.law.portal.core.parsers.LawParser;
import com.find.law.portal.core.content.LawDataContent;
import com.find.law.portal.exceptions.CrimeCategoryParseException;
import com.find.law.portal.exceptions.LawParseException;

import java.io.IOException;

/**
 * Абстрактный парсер данных законов.
 * <br>
 * Получает <b>сырые/исходные</b> данные и передает их в конкретный парсер законов, категорий преступлений, etc.
 *
 * @param <R> тип <b>сырых/исходных</b> данных.
 */
public abstract class AbstractLawDataContentParser<R> implements LawDataContentParser {
    private final CrimeCategoryParser<R> crimeCategoryParser;

    private final LawParser<R> lawParser;

    protected AbstractLawDataContentParser(CrimeCategoryParser<R> crimeCategoryParser, LawParser<R> lawParser) {
        this.crimeCategoryParser = crimeCategoryParser;
        this.lawParser = lawParser;
    }

    /**
     * Получить <b>сырые/исходные</b> данные для парсинга.
     *
     * @return <b>сырые/исходные</b> данные.
     * @throws IOException не удалось получить <b>сырые/исходные</b> данные для парсинга.
     */
    protected abstract R getRawData() throws IOException;

    @Override
    public LawDataContent parse() throws IOException, CrimeCategoryParseException, LawParseException {
        R rawData = getRawData();
        return new LawDataContent(crimeCategoryParser.parse(rawData), lawParser.parse(rawData));
    }
}
