package com.find.law.portal.core.parsers.general.html.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO объект ответа от удаленного источника.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LawApiResponseDto {
    /**
     * Текст ошибки
     */
    private String Error;

    /**
     * HTML страница с законами.
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
