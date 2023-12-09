package com.find.law.portal.configurations;

import com.find.law.portal.core.parsers.LawDataContentParserProvider;
import com.find.law.portal.core.parsers.LawParserType;
import com.find.law.portal.core.parsers.providers.LawParsersTypeProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.util.StringUtils;

/**
 * Конфигурация провайдера парсеров.
 */
@Configuration
public class LawParserProviderConfiguration {
    /**
     * Создать провайдер парсеров.
     *
     * @param parserType тип парсера.
     * @param parserPath путь парсера.
     * @return провайдер парсеров.
     */
    @Bean
    public LawDataContentParserProvider getLawParserProvider(
            @Value("${portal.laws.updater.parser.type}") LawParserType parserType,
            @Value("${portal.laws.updater.parser.path}") String parserPath,
            @Value("${portal.laws.updater.parser.law-types.path}") String lawTypesPath)
    {

        if (StringUtils.isEmptyOrWhitespace(parserPath)) {
            throw new IllegalArgumentException("Parser path not specified in portal settings [portal.laws.updater.parser.path]");
        }

        try {
            return new LawParsersTypeProvider(parserType, parserPath, lawTypesPath);
        } catch (IllegalArgumentException cause) {
            throw new RuntimeException("Could not get parser type from value [%s]".formatted(parserType), cause);
        }
    }
}
