package utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ResourcesUtils {
    private static final Path HTML_DOCUMENT_PATH = Path.of("src", "test", "resources", "parsers", "html_laws.html");

    private static final Path LAW_TYPES_PATH = Path.of("src", "test", "resources", "law_types.json");

    private ResourcesUtils() {}

    public static Document getHtmlDocument() throws IOException {
        return Jsoup.parse(Files.readString(HTML_DOCUMENT_PATH), new Parser(new HtmlTreeBuilder()));
    }

    public static Path getLawTypesPath() {
        return LAW_TYPES_PATH;
    }
}
