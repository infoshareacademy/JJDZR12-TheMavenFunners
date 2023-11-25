package com.isa.tasktrackerwebapp.controller;

import com.isa.tasktrackerwebapp.service.LoginValidator;
import com.isa.tasktrackerwebapp.model.PageType;
import com.isa.tasktrackerwebapp.model.User;
import com.isa.tasktrackerwebapp.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final LoginService loginService;
    private final LoginValidator loginValidator;

    public LoginController(LoginService loginService, LoginValidator loginValidator) {
        this.loginService = loginService;
        this.loginValidator = loginValidator;
    }


    @GetMapping("/login")
    String loginMenu(Model model) {
        model.addAttribute("content", PageType.LOGIN.getContentValue())
                .addAttribute("pageTitle", PageType.LOGIN.getTitleValue())
                .addAttribute("user", new User());
        if (loginService.isUserLoggedIn()){
            model.addAttribute("isUserLoggedIn", loginService.isUserLoggedIn());
        }
        return "main";
    }

    @PostMapping("/logUser")
    String logUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        if (loginValidator.isLoginInputValid(user.getLogin(), user.getPassword())) {
            loginService.setLoggedInUser(user);
            redirectAttributes.addFlashAttribute("login", true);
            logger.info("User logged in: {}", user);
            return "redirect:/";
        }
        logger.warn("Login attempt failed for user: {}", user.getLogin());
        return "redirect:/login?error=true";
    }

    @GetMapping("/logout")
    String logout(Model model) {
        loginService.setLoggedInUser(null);
        model.addAttribute("content", PageType.INDEX.getContentValue())
                .addAttribute("pageTitle", PageType.INDEX.getTitleValue())
                .addAttribute("logout", true);
        return "main";
    }
}