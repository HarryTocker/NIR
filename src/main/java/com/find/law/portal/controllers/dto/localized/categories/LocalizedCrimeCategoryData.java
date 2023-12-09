package com.find.law.portal.controllers.dto.localized.categories;

import com.find.law.portal.controllers.dto.localized.punishments.LocalizedPunishmentData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalizedCrimeCategoryData {
    private String lawType;

    private String categoryType;

    private String comparisonType;

    private LocalizedPunishmentData punishment;
}
