package com.find.law.portal.core.parsers.categories;

import com.find.law.portal.core.parsers.CrimeCategoryParser;
import com.find.law.portal.core.parsers.converters.CrimeCategoryConverter;
import com.find.law.portal.core.parsers.converters.ArticleDataLines;
import com.find.law.portal.core.content.categories.CrimeCategoryData;
import com.find.law.portal.exceptions.CrimeCategoryParseException;

import java.io.IOException;
import java.util.List;

/**
 * Абстрактный парсер категорий преступлений, основан на {@link CrimeCategoryConverter}.
 * <br>
 * Парсер получает <b>сырые/исходные</b> данные и маппит их в {@link ArticleDataLines} (маппинг происходит в наследуемых классах),
 * после чего передает их в конвертер.
 *
 * @param <R> тип <b>сырых/исходных</b> данных для парсинга.
 */
public abstract class AbstractConverterCrimeCategoryParser<R> implements CrimeCategoryParser<R> {
    private final CrimeCategoryConverter converter;

    protected AbstractConverterCrimeCategoryParser(CrimeCategoryConverter converter) {
        this.converter = converter;
    }

    /**
     * Получить данные одной статьи закона для конвертации.
     * Используется одна статья, так как все категории преступлений описаны в рамках одной статьи.
     *
     * @param rawData <b>сырые/исходные</b> данные.
     * @return подготовленные данные для конвертера.
     * @throws IOException не удалось подготовить данные для конвертации.
     */
    protected abstract ArticleDataLines getLawDataLines(R rawData) throws IOException;

    @Override
    public List<CrimeCategoryData> parse(R rawData) throws CrimeCategoryParseException {
        try {
            return converter.convert(getLawDataLines(rawData));
        } catch (Throwable cause) {
            throw new CrimeCategoryParseException("Could not parse crimes categories", cause);
        }
    }
}
