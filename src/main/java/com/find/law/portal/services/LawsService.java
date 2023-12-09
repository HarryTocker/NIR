package com.find.law.portal.services;

import com.find.law.portal.controllers.dto.generic.LawsByTypeDto;
import com.find.law.portal.core.content.categories.CrimeCategoryData;
import com.find.law.portal.core.content.laws.LawData;
import com.find.law.portal.core.content.laws.LawPartData;
import com.find.law.portal.core.content.laws.LawPartPunishData;
import com.find.law.portal.core.utils.pair.Pair;
import com.find.law.portal.core.utils.tuple.Tuple;

import java.util.Collection;

/**
 * Сервис для работы с законами.
 */
public interface LawsService {
    /**
     * Получить статьи законов по типам тяжести преступления.
     *
     * @return законы по типам тяжести преступления.
     */
    LawsByTypeDto getLawsByType();

    /**
     * Обновить типы тяжести преступлений для статей законов.
     *
     * @param lawsType статьи законов по типам тяжести преступления.
     */
    void updateLawsType(LawsByTypeDto lawsType);

    /**
     * Найти закон по статье или описанию. Если указана статья, поиск всегда выполняется по ней.
     * Если указано только описание, берется первый подходящий результат.
     *
     * @param article статья.
     * @param text описание.
     * @return закон.
     */
    LawData findLaw(String article, String text);

    /**
     * Найти закон с максимальным наказанием по нему и типом тяжести преступления.
     *
     * @param article статья.
     * @param text описание.
     * @return закон с максимальным наказанием по нему и типом тяжести преступления.
     */
    Pair<LawData, Collection<Tuple<LawPartData, LawPartPunishData, Pair<CrimeCategoryData, CrimeCategoryData>>>> findLawWithMaxPunishment(String article, String text);
}
