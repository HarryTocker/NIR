package com.find.law.portal.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LawPartDataDto {
    private String part;

    private LawPartPunishmentDataDto punishment;
}
