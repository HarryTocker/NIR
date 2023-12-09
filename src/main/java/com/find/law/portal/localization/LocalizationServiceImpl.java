package com.find.law.portal.localization;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Collection;
import java.util.Locale;

@Service
public class LocalizationServiceImpl implements LocalizationService {
    private static final String LOCALIZATION_ENUM_PREFIX = "portal.enum";

    private static final String LOCALIZATION_TEXT_PREFIX = "portal.ui.text.";

    private final LocaleResolver localeResolver;
    private final MessageSource messageSource;

    public LocalizationServiceImpl(LocaleResolver localeResolver, MessageSource messageSource) {
        this.localeResolver = localeResolver;
        this.messageSource = messageSource;
    }

    @Override
    public <T extends Enum<?>> String getEnumLocalization(T value, HttpServletRequest request) {
        return getEnumLocalization(value, localeResolver.resolveLocale(request));
    }

    @Override
    public String getTextLocalization(String value, HttpServletRequest request) {
        return getTextLocalization(value, localeResolver.resolveLocale(request));
    }

    @Override
    public <T> T localize(Localizable<T> entity, HttpServletRequest request) {
        T result = entity.getLocalizedData();
        var locale = localeResolver.resolveLocale(request);

        entity.getInfos().forEach(info -> {
            switch (info.type()) {
                case NONE -> info.updater().apply(result, info.value().get());
                case ENUM -> info.updater().apply(result, getEnumLocalization((Enum<?>) info.value().get(), locale));
                case INSTANCE -> info.updater().apply(result, localize((Localizable<?>) info.value().get(), request));
                case COLLECTION -> info.updater().apply(result, ((Collection<Localizable<?>>)info.value().get()).stream().map(e -> localize(e, request)).toList());
            }
        });

        return result;
    }

    private <T extends Enum<?>> String getEnumLocalization(T value, Locale locale) {
        if (value == null) {
            return null;
        }

        var className = value.getClass().getName().toLowerCase(Locale.ROOT);
        var valueName = value.name().toLowerCase(Locale.ROOT);
        var path = String.join(".", LOCALIZATION_ENUM_PREFIX, className, valueName);

        return messageSource.getMessage(path, null, locale);
    }

    private String getTextLocalization(String value, Locale locale) {
        if (value == null) {
            return null;
        }

        var path = LOCALIZATION_TEXT_PREFIX + value;
        return messageSource.getMessage(path, null, locale);
    }
}
