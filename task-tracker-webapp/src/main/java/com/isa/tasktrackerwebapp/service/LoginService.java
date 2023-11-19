package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.JsonData;
import com.isa.tasktrackerwebapp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    private static final List<User> users = JsonData.getUsers();
    private static User loggedInUser;

    public void addLoggedUser(User user) {
        loggedInUser = user;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public boolean isUserLogged() {
        return loggedInUser != null;
    }

    public boolean canUserLogIn(String login, String password) {
        if (doLoginExist(login)) {
            return isPasswordCorrect(login, password);
        }
        return false;
    }

    private boolean doLoginExist(String login) {
        return users.stream().anyMatch(user -> user.getLogin().equals(login));
    }

    private boolean isPasswordCorrect(String login, String password) {
        User user = users.stream().filter(u -> u.getLogin().equals(login)).findFirst().orElse(null);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }
}