package com.find.law.portal.controllers.dto.errors;

/**
 * DTO объект ошибки.
 *
 * @param code код ошибки.
 * @param message сообщение ошибки.
 * @param cause причина ошибки.
 */
public record ErrorResponseDto(int code, String message, String cause) { }
