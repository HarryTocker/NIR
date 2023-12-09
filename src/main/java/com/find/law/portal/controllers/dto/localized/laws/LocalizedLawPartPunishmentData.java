package com.find.law.portal.controllers.dto.localized.laws;

import com.find.law.portal.controllers.dto.localized.punishments.LocalizedPunishmentData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * DTO объект наказания по части закона.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalizedLawPartPunishmentData {
    /**
     * Тип наказания.
     */
    private String type;

    /**
     * Минимальный срок наказания.
     */
    private LocalizedPunishmentData min;

    /**
     * Максимальный срок наказания.
     */
    private LocalizedPunishmentData max;

    /**
     *
     */
    private Collection<LocalizedLawPartPunishmentData> optionals;

    /**
     * Является ли наказание пожизненным.
     */
    private Boolean isLifeTime;

    public LocalizedLawPartPunishmentData(String type, LocalizedPunishmentData min, LocalizedPunishmentData max, Boolean isLifeTime) {
        this.type = type;
        this.min = min;
        this.max = max;
        this.isLifeTime = isLifeTime;
    }
}
