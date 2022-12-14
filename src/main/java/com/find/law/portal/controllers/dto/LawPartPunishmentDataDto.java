package com.find.law.portal.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO объект наказания по части закона.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LawPartPunishmentDataDto {
    /**
     * Тип наказания.
     */
    private String type;

    /**
     * Минимальный срок наказания.
     */
    private String minTime;

    /**
     * Максимальный срок наказания.
     */
    private String maxTime;

    /**
     * Тип времени наказания.
     */
    private String dateType;

    /**
     * Является ли наказание пожизненным.
     */
    private Boolean isLifeTime;
}
