package com.find.law.portal.law;

import com.find.law.portal.repositories.entities.LawPartPunishEntity;

public class LawComparator {
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

    public boolean comparePunishments(LawPartPunishEntity current, LawPartPunishEntity other) {
        int currentCost = getCost(current.getType());
        int otherCost = getCost(other.getType());

        return otherCost > currentCost;
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
