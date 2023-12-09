package com.find.law.portal.core.parsers.converters;

import java.util.List;

/**
 * Подготовленные данные статьи для конвертации.
 *
 * @param article статья закона.
 * @param lines список строк полного текста статьи.
 */
public record ArticleDataLines(String article, List<String> lines) {
}
