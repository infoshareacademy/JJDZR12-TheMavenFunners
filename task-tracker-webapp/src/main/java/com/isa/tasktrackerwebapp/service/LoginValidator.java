package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginValidator {
    private static final List<User> users = JsonUserDataManager.getUsers();
    public static boolean doesLoginExist(List<User> users, String login) {
        return users.stream().anyMatch(user -> user.getLogin().equals(login));
    }

    public boolean isLoginInputValid(String login, String password) {
        if (doesLoginExist(users, login)) {
            return isPasswordCorrect(login, password);
        }
        return false;
    }

    private boolean isPasswordCorrect(String login, String password) {
        return users.stream()
                .anyMatch(user -> user.getLogin().equals(login)
                        && user.getPassword().equals(password));
    }
}
