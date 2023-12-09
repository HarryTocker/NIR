package com.find.law.portal.core.parsers.categories.html;

import com.find.law.portal.core.parsers.converters.CrimeCategoryConverter;
import com.find.law.portal.core.parsers.converters.ArticleDataLines;
import com.find.law.portal.core.parsers.categories.AbstractConverterCrimeCategoryParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Парсер категорий преступлений из HTML документа {@link Document}.
 */
public class HtmlConverterCrimeCategoryParser extends AbstractConverterCrimeCategoryParser<Document> {

    public HtmlConverterCrimeCategoryParser(CrimeCategoryConverter converter) {
        super(converter);
    }

    @Override
    protected ArticleDataLines getLawDataLines(Document rawData) throws IOException {
        return getFirstElement(rawData).map(this::getLines).orElseThrow(() -> new IOException("Could not get element with crime categories from html document"));
    }

    /**
     * Получить первый элемент, с которого начинается часть категорий преступлений.
     *
     * @param rawData <b>сырые/исходные</b> данные в формате HTML.
     * @return {@link Optional} контейнер содержащий первый элемент.
     */
    private Optional<Element> getFirstElement(Document rawData) {
        return Optional.ofNullable(rawData.getElementById("p54"));
    }

    /**
     * Подготовить данные категорий преступлений, начиная с первого элемента и заканчивая на новой статье.
     *
     * @param current первый элемент документа.
     * @return подготовленные категории преступлений.
     */
    private ArticleDataLines getLines(Element current) {
        String article = current.text();
        List<String> lines = new ArrayList<>(10);
        current = current.nextElementSibling();

        while (true) {
            if (current == null) {
                break;
            }

            if (!current.hasText() || StringUtils.isEmptyOrWhitespace(current.text())) {
                current = current.nextElementSibling();
                continue;
            }

            String text = current.text();
            if (text.toLowerCase(Locale.ROOT).startsWith("статья")) {
                break;
            }

            lines.add(text);
            current = current.nextElementSibling();
        }

        return new ArticleDataLines(article, lines);
    }
}
