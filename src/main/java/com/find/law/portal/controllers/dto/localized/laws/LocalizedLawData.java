package com.find.law.portal.controllers.dto.localized.laws;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * DTO объект закона.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalizedLawData {
    /**
     * Статья закона.
     */
    private String article;

    /**
     * Наименование статьи.
     */
    private String name;

    /**
     *
     */
    private String type;

    /**
     * Коллекция частей закона.
     */
    private Collection<LocalizedLawPartData> parts;
}
