package com.find.law.portal.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер для возврата стартовой страницы с поиском.
 */
@Controller("/")
public class IndexController {
    @GetMapping
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("settings")
    public String getSettingsPage() {
        return "settings";
    }

    @GetMapping("login")
    public String getLoginPage() {
        return "login";
    }
}
