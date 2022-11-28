package com.find.law.portal.law.dto;

import lombok.Data;

@Data
public class LawApiResponseDto {
    public String Error;

    public String RedText;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "ApiResponse";
    }
}
