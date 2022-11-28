package com.find.law.portal.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LawPartPunishmentDataDto {
    private String type;

    private String minTime;

    private String maxTime;

    private String dateType;

    private Boolean isLifeTime;
}
