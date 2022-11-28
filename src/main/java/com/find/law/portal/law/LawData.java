package com.find.law.portal.law;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LawData {
    private String article;

    private String name;

    private List<LawPartData> parts;
}
