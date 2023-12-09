package com.find.law.portal.core.parsers.laws.html;

import com.find.law.portal.exceptions.law.parser.HtmlLawParserException;
import com.find.law.portal.core.parsers.laws.AbstractConverterLawParser;
import com.find.law.portal.core.parsers.converters.LawDataConverter;
import com.find.law.portal.core.parsers.converters.ArticleDataLines;
import com.find.law.portal.exceptions.law.parser.LawParserDataLinesException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Парсер законов из HTML документа {@link Document}.
 */
public class HtmlConverterLawParser extends AbstractConverterLawParser<Document> {
    public HtmlConverterLawParser(LawDataConverter converter) {
        super(converter);
    }

    @Override
    protected List<ArticleDataLines> getLawDataLines(Document rawData) throws LawParserDataLinesException {
        return getFirstElement(rawData).map(this::getLines).orElseThrow(() -> new LawParserDataLinesException("Could not get element with law from html document"));
    }

    /**
     * Получить первый элемент, с которого начинается часть законов.
     *
     * @param rawData <b>сырые/исходные</b> данные в формате HTML.
     * @return {@link Optional} контейнер содержащий первый элемент.
     */
    private Optional<Element> getFirstElement(Document rawData) {
        return Optional.ofNullable(rawData.getElementById("p529"));
    }

    /**
     * Подготовить данные законов, начиная с первого элемента и заканчивая концом документа.
     *
     * @param current первый элемент документа.
     * @return подготовленные данные законов.
     * @throws LawParserDataLinesException не удалось подготовить данные закона.
     */
    private List<ArticleDataLines> getLines(Element current) throws LawParserDataLinesException {
        List<ArticleDataLines> lines = new ArrayList<>(450);
        Optional<ArticleDataLines> law = Optional.empty();

        try {
            while (current != null) {
                if (!current.hasText() || StringUtils.isEmptyOrWhitespace(current.text())) {
                    current = current.nextElementSibling();
                    continue;
                }

                String text = current.text();
                if (text.startsWith("Статья")) {
                    law.ifPresent(lines::add);
                    law = Optional.of(new ArticleDataLines(text, new ArrayList<>(20)));
                } else {
                    law.ifPresent(l -> l.lines().add(text));
                }
                current = current.nextElementSibling();
            }
        } catch (Throwable cause) {
            throw new HtmlLawParserException(current, cause);
        }

        return lines;
    }
}
