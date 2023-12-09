package com.find.law.portal.exceptions.law.parser;

import org.jsoup.nodes.Element;

/**
 * Исключение при парсинге закона.
 */
public class HtmlLawParserException extends LawParserDataLinesException {
    public HtmlLawParserException(Element current, Throwable cause) {
        super("Could not parse element [%s] with text [%s]".formatted(current.id(), current.text()), cause);
    }
}
