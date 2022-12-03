package com.find.law.portal.law.comparators;

import com.find.law.portal.controllers.dto.LawPartPunishmentDataDto;

import java.util.Comparator;

/**
 * Компаратор наказаний по части закона. Наказания сортируются от самого минимального до максимально возможного.
 */
public class LawPartPunishmentComparator implements Comparator<LawPartPunishmentDataDto> {
    private final String[] sortedPunishments = new String[] {
            "Штраф",
            "Лишение права занимать определенные должности или заниматься определенной деятельностью",
            "Обязательные работы",
            "Исправительные работы",
            "Ограничение по военной службе",
            "Ограничение свободы",
            "Принудительные работы",
            "Арест",
            "Лишение свободы",
            "Пожизненное заключение"
    };

    @Override
    public int compare(LawPartPunishmentDataDto current, LawPartPunishmentDataDto other) {
        int currentCost = getCost(current.getType());
        int otherCost = getCost(other.getType());

        return currentCost - otherCost;
    }

    private int getCost(String type) {
        for(int i = 0; i < sortedPunishments.length; i++) {
            if (type.equals(sortedPunishments[i])) {
                return i;
            }
        }

        return -1;
    }
}
