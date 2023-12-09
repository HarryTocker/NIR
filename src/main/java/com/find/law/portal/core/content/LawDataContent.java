package com.find.law.portal.core.content;

import com.find.law.portal.core.content.categories.CrimeCategoryData;
import com.find.law.portal.core.content.laws.LawData;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

/**
 * Распарсенные данные законов.
 * <br>
 * Содержит:
 * <ul>
 *     <li>Коллекцию категорий преступлений {@link CrimeCategoryData}</li>
 *     <li>Коллекцию законов {@link LawData}</li>
 * </ul>
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class LawDataContent {
    /**
     * Категории преступлений.
     */
    private List<CrimeCategoryData> categories;

    /**
     * Законы.
     */
    private List<LawData> laws;
}
