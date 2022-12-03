package com.find.law.portal.law.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Данные закона для парсинга.
 */
@Data
@AllArgsConstructor
public class LawData {
    /**
     * Статья закона.
     */
    private String article;

    /**
     * Наименование статьи.
     */
    private String name;

    /**
     * Части статьи.
     */
    private List<LawPartData> parts;
}
