package com.find.law.portal.core.parsers;

import com.find.law.portal.core.content.laws.LawPartPunishData;

import java.util.List;

/**
 * Интерфейс парсера данных наказания по части закона из текста.
 */
public interface PunishmentsParser {
    /**
     * Распарсить наказания из текста.
     *
     * @param text текст.
     * @return коллекция наказаний.
     */
    List<LawPartPunishData> parse(String text);
}
