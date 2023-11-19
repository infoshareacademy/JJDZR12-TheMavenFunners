package com.isa.tasktrackerwebapp.controller;

import com.isa.tasktrackerwebapp.model.PageType;
import com.isa.tasktrackerwebapp.model.User;
import com.isa.tasktrackerwebapp.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    String loginMenu(Model model) {
        model.addAttribute("content", PageType.LOGIN.getContentValue())
                .addAttribute("pageTitle", PageType.LOGIN.getTitleValue())
                .addAttribute("user", new User());
        return "main";
    }

    @PostMapping("/logUser")
    String logUser(@ModelAttribute User user, Model model) {
        if (loginService.isLoggingValid(user.getLogin(), user.getPassword())) {
            loginService.setLoggedUser(user);
            System.out.println(user);
            return "redirect:/";
        }
        return "redirect:/login?error=true";

    }
}