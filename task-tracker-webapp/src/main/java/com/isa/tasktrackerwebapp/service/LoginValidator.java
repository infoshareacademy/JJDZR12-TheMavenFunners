package com.isa.tasktrackerwebapp.service;

import org.springframework.stereotype.Service;

@Service
public class LoginValidator {

    public boolean doesLoginExist(String login) {
        return JsonUserDataManager.getUsers().stream().anyMatch(user -> user.getLogin().equals(login));
    }

    public boolean isLoginInputValid(String login, String password) {
        return doesLoginExist(login) && isPasswordCorrect(login, password);
    }

    private boolean isPasswordCorrect(String login, String password) {
        return JsonUserDataManager.getUsers().stream()
                .anyMatch(user -> user.getLogin().equals(login)
                        && user.getPassword().equals(password));
    }
}
