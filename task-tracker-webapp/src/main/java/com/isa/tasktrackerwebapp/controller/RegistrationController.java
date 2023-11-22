package com.isa.tasktrackerwebapp.controller;

import com.isa.tasktrackerwebapp.model.PageType;
import com.isa.tasktrackerwebapp.model.User;
import com.isa.tasktrackerwebapp.service.LoginService;
import com.isa.tasktrackerwebapp.service.LoginValidator;
import com.isa.tasktrackerwebapp.service.RegistrationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class RegistrationController {
    private final LoginService loginService;
    private final RegistrationService registrationService;

    RegistrationController(LoginService loginService, RegistrationService registrationService) {
        this.loginService = loginService;
        this.registrationService = registrationService;
    }

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @GetMapping("/registration")
    String registration(Model model) {
        model.addAttribute("content", PageType.REGISTRATION.getContentValue())
                .addAttribute("pageTitle", PageType.REGISTRATION.getTitleValue())
                .addAttribute("user", new User());

        if (loginService.isUserLoggedIn()) {
            model.addAttribute("isUserLoggedIn", loginService.isUserLoggedIn());
        }
        return "main";
    }

    @PostMapping("/registerUser")
    String registerUser(@ModelAttribute @Valid User user, BindingResult bindingResult,
                        @RequestParam("repeatPassword") String repeatPassword) {
        if (bindingResult.hasErrors()) {
            logger.warn("Registration attempt failed; user fields validations not passed.");
            return "redirect:/registration?error=true";
        }
        if (LoginValidator.doesLoginExist(user.getLogin())) {
            logger.warn("Registration attempt failed; login already exists.");
            return "redirect:/registration?loginExistsError=true";
        }
        if (!repeatPassword.equals(user.getPassword())) {
            logger.warn("Registration attempt failed; passwords don't match.");
            return "redirect:/registration?passwordsDontMatchError=true";
        }

        registrationService.registerNewUser(user);
        logger.info("Registered new user: {}", user);
        return "redirect:/login?registrationSuccessful";
    }
}
