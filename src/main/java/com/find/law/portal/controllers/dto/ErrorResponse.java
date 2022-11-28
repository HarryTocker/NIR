package com.find.law.portal.controllers.dto;

public record ErrorResponse(int code, String message, String cause) { }
