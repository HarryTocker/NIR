package com.find.law.portal.law.data;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Данные наказания по части закона для парсинга.
 */
@Data
@AllArgsConstructor
public class LawPartPunishData {
    /**
     * Тип наказания.
     */
    private String type;

    /**
     * Минимальный срок наказания.
     */
    private String min;

    /**
     * Максимальный срок наказания.
     */
    private String max;

    /**
     * Тип времени наказания.
     */
    private String dateType;

    /**
     * Является ли наказание пожизненным.
     */
    private boolean isLifeTime;
}
