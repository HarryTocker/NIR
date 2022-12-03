package com.find.law.portal.law.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Данные части закона для парсинга.
 */
@Data
@AllArgsConstructor
public class LawPartData {
    /**
     * Наименование части закона.
     */
    private String name;

    /**
     * Возможные наказания части закона.
     */
    private List<LawPartPunishData> punishments;
}
