package com.find.law.portal.core.parsers.providers;

import com.find.law.portal.core.parsers.*;
import com.find.law.portal.core.parsers.converters.CrimeCategoryConverter;
import com.find.law.portal.core.parsers.converters.LawDataConverter;
import com.find.law.portal.core.parsers.converters.categories.GenericCrimeCategoryConverter;
import com.find.law.portal.core.parsers.converters.laws.UpdatersLawDataConverter;
import com.find.law.portal.core.parsers.converters.updater.builders.LawDataUpdaterBuilder;
import com.find.law.portal.core.parsers.categories.html.HtmlConverterCrimeCategoryParser;
import com.find.law.portal.core.parsers.general.html.LocalHtmlLawDataContentParser;
import com.find.law.portal.core.parsers.general.html.RemoteHtmlLawDataContentParser;
import com.find.law.portal.core.parsers.laws.html.HtmlConverterLawParser;

import java.net.URL;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Провайдер парсеров контента законов.
 * <br>
 * Создает парсер учитывая тип {@link LawParserType}.
 *
 * @see LawDataContentParser
 */
public class LawParsersTypeProvider implements LawDataContentParserProvider {
    /**
     * Тип парсера.
     */
    private final LawParserType type;

    /**
     * Путь парсера.
     */
    private final String parserPath;

    /**
     *
     */
    private final String lawTypesPath;

    /**
     * Функции создания парсеров.
     */
    private final Map<LawParserType, Supplier<LawDataContentParser>> parsers;

    public LawParsersTypeProvider(LawParserType type, String parserPath, String lawTypesPath) {
        this.type = type;
        this.parserPath = parserPath;
        this.lawTypesPath = lawTypesPath;
        this.parsers = Map.ofEntries(
                Map.entry(LawParserType.HTML_LOCAL, this::getHtmlParser),
                Map.entry(LawParserType.HTML_REMOTE, this::getHtmlParser)
        );
    }

    @Override
    public LawDataContentParser get() {
        Supplier<LawDataContentParser> getter = parsers.get(type);
        if (getter == null) {
            throw new IllegalArgumentException("Could not get parser for type [%s]".formatted(type));
        }

        return getter.get();
    }

    /**
     * Создать HTML парсер законов из локального файла или удаленного источника.
     */
    private LawDataContentParser getHtmlParser() {
        try {
            LawDataUpdaterBuilder builder = new LawDataUpdaterBuilder(lawTypesPath);
            LawDataConverter lawConverter = new UpdatersLawDataConverter(builder
                    .addArticleUpdater()
                    .addLawIsNotValidUpdater()
                    .addPartUpdater()
                    .addLawTypeFromArticleDataUpdater()
                    .addLawTypeFromFileDataUpdater()
                    .build());

            CrimeCategoryConverter crimeCategoryConverter = new GenericCrimeCategoryConverter();

            HtmlConverterLawParser lawParser = new HtmlConverterLawParser(lawConverter);
            HtmlConverterCrimeCategoryParser crimeCategoryParser = new HtmlConverterCrimeCategoryParser(crimeCategoryConverter);

            return switch (type) {
                case HTML_LOCAL -> new LocalHtmlLawDataContentParser(Path.of(parserPath), crimeCategoryParser, lawParser);
                case HTML_REMOTE -> new RemoteHtmlLawDataContentParser(new URL(parserPath), crimeCategoryParser, lawParser);
            };
        } catch (Throwable cause) {
            throw new RuntimeException("Could not get HTML parser", cause);
        }
    }
}
