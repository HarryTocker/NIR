package com.find.law.portal.core.content.categories;

import com.find.law.portal.core.content.laws.LawType;
import com.find.law.portal.core.content.punishments.PunishmentData;
import lombok.*;

/**
 * Данные категории преступления.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CrimeCategoryData implements Cloneable {
    /**
     * Тип преступления.
     */
    private LawType lawType;

    /**
     * Тип категории преступления.
     */
    private CrimeCategoryType categoryType;

    /**
     * Тип сравнения для наказания по категории преступления.
     */
    private CrimeCategoryComparisonType comparisonType;

    /**
     * Наказание.
     */
    private PunishmentData punishment;

    @Override
    public CrimeCategoryData clone() {
        return new CrimeCategoryData(lawType, categoryType, comparisonType, punishment.clone());
    }
}
