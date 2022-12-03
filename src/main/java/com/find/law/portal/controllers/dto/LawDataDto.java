package com.find.law.portal.controllers.dto;

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
public class LawDataDto {
    /**
     * Статья закона.
     */
    private String article;

    /**
     * Наименование статьи.
     */
    private String name;

    /**
     * Коллекция частей закона.
     */
    private Collection<LawPartDataDto> parts;
}
