package com.find.law.portal.controllers.dto.localized.laws;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalizedLawWithCrimeCategoryDto {
    private String article;

    private String name;

    private String type;

    private Collection<LocalizedLawPartWithCrimeCategoryDto> parts;
}
