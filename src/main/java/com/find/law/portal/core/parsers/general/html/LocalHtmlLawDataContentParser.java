package com.find.law.portal.core.parsers.general.html;

import com.find.law.portal.core.parsers.CrimeCategoryParser;
import com.find.law.portal.core.parsers.LawParser;
import com.find.law.portal.core.parsers.general.AbstractLawDataContentParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.Parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Парсер данных законов из HTML документа расположенного в файловой системе устройства.
 * <br>
 * Открывает файл, расположенный по переданному пути {@link Path} и преобразует к <b>сырым/исходным</b> данным {@link Document}.
 */
public class LocalHtmlLawDataContentParser extends AbstractLawDataContentParser<Document> {
    private final Path path;

    public LocalHtmlLawDataContentParser(Path path, CrimeCategoryParser<Document> crimeCategoryParser, LawParser<Document> lawParser) {
        super(crimeCategoryParser, lawParser);
        this.path = path;
    }

    @Override
    protected Document getRawData() throws IOException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("Local HTML file does not exist in path [%s]".formatted(path));
        }

        try {
            String content = Files.readString(path);
            return Jsoup.parse(content, new Parser(new HtmlTreeBuilder()));
        } catch (Throwable cause) {
            throw new IOException("Could not get HTML document from local file [%s]".formatted(path), cause);
        }
    }
}
