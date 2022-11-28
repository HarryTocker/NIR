package com.find.law.portal.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LawDataDto {
    private String article;

    private String name;

    private Collection<LawPartDataDto> parts;
}
