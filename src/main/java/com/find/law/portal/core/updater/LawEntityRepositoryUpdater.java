package com.find.law.portal.core.updater;

import com.find.law.portal.core.content.laws.LawData;

import java.util.Collection;

/**
 * Интерфейс сервиса обновления распарсенных законов в базе данных.
 */
public interface LawEntityRepositoryUpdater {
    /**
     * Обновить распарсенные законы в базе данных.
     *
     * @param laws законы.
     */
    void update(Collection<LawData> laws);
}
