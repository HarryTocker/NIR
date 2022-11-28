package com.find.law.portal.services;

import com.find.law.portal.repositories.entities.LawEntity;
import com.find.law.portal.repositories.entities.LawPartEntity;
import com.find.law.portal.repositories.entities.LawPartPunishEntity;

/**
 * Сервис для работы поиска законов.
 */
public interface LawsService {
    /**
     * Найти закон по статье или описанию. Если указана статья, поиск всегда выполняется по ней.
     * Если указано только описание, берется первый подходящий результат.
     *
     * @param article статья.
     * @param text описание.
     * @return закон.
     */
    LawEntity findLaw(String article, String text);

    /**
     * Найти максимальное наказание для указанной части закона.
     *
     * @param part часть закона.
     * @return максимальное наказание.
     */
    LawPartPunishEntity findMaxPunishment(LawPartEntity part);
}
