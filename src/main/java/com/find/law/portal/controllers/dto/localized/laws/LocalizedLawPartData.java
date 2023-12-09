package com.find.law.portal.controllers.dto.localized.laws;

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
public class LocalizedLawPartData {
    /**
     * Наименование части закона.
     */
    private String name;

    /**
     * Коллекция наказаний части закона.
     */
    private Collection<LocalizedLawPartPunishmentData> punishments;
}
