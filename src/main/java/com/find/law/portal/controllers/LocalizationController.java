package com.find.law.portal.controllers;

import com.find.law.portal.localization.LocalizationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/localization")
public class LocalizationController {
    private final LocalizationService localizationService;

    public LocalizationController(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    @RequestMapping(value = "/for/text/{value}", produces = "application/text")
    public ResponseEntity<String> getTextLocalization(@PathVariable String value, HttpServletRequest request) {
        try {
            return ResponseEntity.ok(localizationService.getTextLocalization(value, request));
        } catch (Throwable cause) {
            return ResponseEntity.ok("");
        }
    }
}
