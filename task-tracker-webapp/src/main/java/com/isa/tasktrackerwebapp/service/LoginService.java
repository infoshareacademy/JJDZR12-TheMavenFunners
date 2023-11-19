package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.JsonData;
import com.isa.tasktrackerwebapp.model.User;
import com.isa.tasktrackerwebapp.model.LoginValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    private static final List<User> users = JsonData.getUsers();
    private static User loggedInUser;

    public void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public boolean isUserLoggedIn() {
        return loggedInUser != null;
    }

    public boolean isLoginInputValid(String login, String password) {
        if (LoginValidator.doesLoginExist(users, login)) {
            return isPasswordCorrect(login, password);
        }
        return false;
    }

    private boolean isPasswordCorrect(String login, String password) {
        User user = users.stream().filter(u -> u.getLogin().equals(login)).findFirst().orElse(null);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }
}