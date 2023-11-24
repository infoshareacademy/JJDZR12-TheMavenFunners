package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginValidator {
    private static final List<User> users = JsonUserDataManager.getUsers();

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
