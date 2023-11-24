package com.isa.tasktrackerwebapp.controller;

import com.isa.tasktrackerwebapp.model.PageType;
import com.isa.tasktrackerwebapp.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final LoginService loginService;

    private MainController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("content", PageType.INDEX.getContentValue())
                .addAttribute("pageTitle", PageType.INDEX.getTitleValue());
        if (loginService.isUserLoggedIn()) {
            model.addAttribute("isUserLoggedIn", loginService.isUserLoggedIn())
                    .addAttribute("user", loginService.getLoggedInUser().getLogin());
        }
        return "main";
    }

    @GetMapping("/registration")
    String registration(Model model) {
        model.addAttribute("content", PageType.REGISTRATION.getContentValue())
                .addAttribute("pageTitle", PageType.REGISTRATION.getTitleValue());
        if (loginService.isUserLoggedIn()) {
            model.addAttribute("isUserLoggedIn", loginService.isUserLoggedIn());
        }
        return "main";
    }
}