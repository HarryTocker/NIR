package com.find.law.portal.core.parsers.general.html;

import com.find.law.portal.core.parsers.CrimeCategoryParser;
import com.find.law.portal.core.parsers.LawParser;
import com.find.law.portal.core.parsers.general.AbstractLawDataContentParser;
import com.find.law.portal.core.parsers.general.html.dto.LawApiResponseDto;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.Parser;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Парсер данных законов из HTML документа по запросу на удаленный источник.
 * <br>
 * Выполняет запрос на переданный {@link URL} и преобразует ответ к <b>сырым/исходным</b> данным {@link Document}.
 */
public class RemoteHtmlLawDataContentParser extends AbstractLawDataContentParser<Document> {
    private final URL url;

    public RemoteHtmlLawDataContentParser(URL url, CrimeCategoryParser<Document> crimeCategoryParser, LawParser<Document> lawParser) {
        super(crimeCategoryParser, lawParser);
        this.url = url;
    }

    @Override
    protected Document getRawData() throws IOException {
        try (InputStream stream = url.openStream()) {
            JsonReader reader = new JsonReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            LawApiResponseDto dto = new Gson().fromJson(reader, LawApiResponseDto.class);
            // JSON ответ содержит два поля: Error и RedText.
            // Если Error не пуст, произошла ошибка при получении ответа.
            if (StringUtils.isEmptyOrWhitespace(dto.getError())) {
                throw new IOException(dto.getError());
            }

            // Если ошибки нет, парсим ответ из RedText. Ответ в RedText содержится в формате HTML.
            return Jsoup.parse(dto.getRedText(), new Parser(new HtmlTreeBuilder()));
        } catch (Throwable cause) {
            throw new IOException("Could not get HTML document from remote source [%s]".formatted(url), cause);
        }
    }
}
