package com.find.law.portal.exceptions;

import org.jsoup.nodes.Element;

public class LawParseException extends RuntimeException {
    public LawParseException(Element current, Exception cause) {
        super("Could not parse element [%s] with text [%s]".formatted(current.id(), current.text()), cause);
    }
}
