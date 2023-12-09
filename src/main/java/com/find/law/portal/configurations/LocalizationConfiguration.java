package com.find.law.portal.configurations;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;
import java.util.TimeZone;

/**
 * Конфигурация сервисов локализации.
 */
@Configuration
public class LocalizationConfiguration {
    @Bean("localeResolver")
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver("LOCALE_LANG");
        localeResolver.setDefaultLocale(Locale.forLanguageTag("ru"));
        localeResolver.setDefaultTimeZone(TimeZone.getTimeZone("UTC"));
        return localeResolver;
    }

    @Bean("messageSource")
    public MessageSource getMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("languages/localization");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
