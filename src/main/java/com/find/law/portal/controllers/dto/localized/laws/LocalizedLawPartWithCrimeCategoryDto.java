package com.find.law.portal.controllers.dto.localized.laws;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalizedLawPartWithCrimeCategoryDto {
    private String name;

    private String partPunishType;

    private String punishType;

    private String categoryType;

    private Double number;

    private String optionalCategoryType;

    private boolean isLifeTime;
}
