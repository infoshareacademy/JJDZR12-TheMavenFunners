package com.isa.tasktrackerwebapp.controller;

import com.isa.tasktrackerwebapp.model.PageType;
import com.isa.tasktrackerwebapp.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    private final LoginService loginService;

    public MainController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("content", PageType.INDEX.getContentValue())
                .addAttribute("pageTitle", PageType.INDEX.getTitleValue())
                .addAttribute("isUserLoggedIn", loginService.isUserLogged())
                .addAttribute("successMessage", "Zalogowano poprawnie. Witaj " + loginService.getLoggedInUser().getLogin());
        return "main";
    }

    @GetMapping("/registration")
    String registration(Model model) {
        model.addAttribute("content", PageType.REGISTRATION.getContentValue())
                .addAttribute("pageTitle", PageType.REGISTRATION.getTitleValue());
        return "main";
    }

    @GetMapping("/add-task")
    String newTask(Model model) {
        model.addAttribute("content", PageType.ADD_TASK.getContentValue())
                .addAttribute("pageTitle", PageType.ADD_TASK.getTitleValue());
        return "main";
    }
}
