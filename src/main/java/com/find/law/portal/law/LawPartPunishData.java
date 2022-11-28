package com.find.law.portal.law;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LawPartPunishData {
    private String type;

    private String min;

    private String max;

    private String dateType;

    private boolean isLifeTime;
}
