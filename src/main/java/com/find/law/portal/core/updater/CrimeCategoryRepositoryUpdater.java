package com.find.law.portal.core.updater;

import com.find.law.portal.core.content.categories.CrimeCategoryData;

import java.util.Collection;

/**
 * Интерфейс сервиса обновления распарсенных категорий преступлений в базе данных.
 */
public interface CrimeCategoryRepositoryUpdater {
    /**
     * Обновить распарсенные категории преступлений в базе данных.
     *
     * @param categories категории преступлений.
     */
    void update(Collection<CrimeCategoryData> categories);
}
