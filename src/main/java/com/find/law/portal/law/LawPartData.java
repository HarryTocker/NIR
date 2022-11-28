package com.find.law.portal.law;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LawPartData {
    private String name;

    private List<LawPartPunishData> punishments;
}
