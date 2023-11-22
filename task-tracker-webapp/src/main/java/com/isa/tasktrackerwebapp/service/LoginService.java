package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
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

}