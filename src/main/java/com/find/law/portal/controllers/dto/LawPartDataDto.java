package com.find.law.portal.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * DTO объект части закона.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LawPartDataDto {
    /**
     * Наименование части закона.
     */
    private String part;

    /**
     * Коллекция наказаний части закона.
     */
    private Collection<LawPartPunishmentDataDto> punishments;
}
