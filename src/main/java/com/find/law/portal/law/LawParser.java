package com.find.law.portal.law;

import com.find.law.portal.law.data.LawData;

import java.io.IOException;
import java.util.List;

/**
 * Парсер законов
 */
public interface LawParser {
    /**
     * Распарсить законы.
     *
     * @return данные законов.
     * @throws IOException исключение, если не удалось прочитать данные из источника.
     */
    List<LawData> parseLaws() throws IOException;
}
