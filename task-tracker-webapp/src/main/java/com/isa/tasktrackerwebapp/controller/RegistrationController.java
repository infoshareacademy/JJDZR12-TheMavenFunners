package com.isa.tasktrackerwebapp.controller;

import com.isa.tasktrackerwebapp.model.dto.UserDTO;
import com.isa.tasktrackerwebapp.model.entity.PageType;
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
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    private final LoginService loginService;
    private final RegistrationService registrationService;
    private final LoginValidator loginValidator;

    RegistrationController(LoginService loginService, RegistrationService registrationService, LoginValidator loginValidator) {
        this.loginService = loginService;
        this.registrationService = registrationService;
        this.loginValidator = loginValidator;
    }


    @GetMapping("/registration")
    String registration(Model model, UserDTO user) {
        model.addAttribute("content", PageType.REGISTRATION.getContentValue())
                .addAttribute("pageTitle", PageType.REGISTRATION.getTitleValue())
                .addAttribute("user", user);

        if (loginService.isUserLoggedIn()) {
            model.addAttribute("isUserLoggedIn", loginService.isUserLoggedIn());
        }
        return "main";
    }

    @PostMapping("/registerUser")
    String registerUser(@ModelAttribute @Valid UserDTO user, BindingResult bindingResult,
                        @RequestParam("repeatPassword") String repeatPassword,
                        Model model) {
        boolean hasErrors = bindingResult.hasErrors();
        boolean loginExists = loginValidator.doesLoginExist(user.getLogin());
        boolean emailExists = registrationService.doesEmailExist(user.getEmail());
        boolean passwordsDontMatch = !repeatPassword.equals(user.getPassword());

        StringBuilder failureReasonsBuilder = new StringBuilder("Registration attempt failed: ");

        if (hasErrors) {
            failureReasonsBuilder.append(" user fields validations not passed;");
        }

        if (loginExists) {
            failureReasonsBuilder.append(" login already exists;");
        }

        if (emailExists) {
            failureReasonsBuilder.append(" email already exists;");
        }

        if (passwordsDontMatch) {
            failureReasonsBuilder.append(" passwords don't match;");
        }

        if (hasErrors || loginExists || emailExists || passwordsDontMatch) {
            logger.warn(failureReasonsBuilder.toString());

            model.addAttribute("content", PageType.REGISTRATION.getContentValue())
                    .addAttribute("pageTitle", PageType.REGISTRATION.getTitleValue())
                    .addAttribute("user", user)
                    .addAttribute("loginExists", loginExists)
                    .addAttribute("emailExists", emailExists)
                    .addAttribute("passwordsDontMatch", passwordsDontMatch);

            return "main";
        }

        registrationService.registerNewUser(user);
        logger.info("Registered new user: {}", user);
        return "redirect:/login?registrationSuccessful=true";
    }
}
