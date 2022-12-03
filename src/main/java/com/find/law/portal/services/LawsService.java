package com.find.law.portal.services;

import com.find.law.portal.repositories.entities.LawEntity;

/**
 * Сервис для работы с законами.
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
}
