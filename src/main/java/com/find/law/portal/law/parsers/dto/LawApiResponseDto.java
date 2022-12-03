package com.find.law.portal.law.parsers.dto;

import lombok.Data;

/**
 * DTO объект ответа от удаленного источника.
 */
@Data
public class LawApiResponseDto {
    /**
     * Текст ошибки
     */
    private String Error;

    /**
     * Страница с законами.
     */
    private String RedText;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "ApiResponse";
    }
}
