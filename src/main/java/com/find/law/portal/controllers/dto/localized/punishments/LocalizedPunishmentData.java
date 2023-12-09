package com.find.law.portal.controllers.dto.localized.punishments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalizedPunishmentData {
    private String text;

    private Double value;

    private String type;
}
