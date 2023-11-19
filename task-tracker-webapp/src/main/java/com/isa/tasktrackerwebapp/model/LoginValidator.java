package com.isa.tasktrackerwebapp.model;

import java.util.List;

public class LoginValidator {

    public static boolean doesLoginExist(List<User> users, String login) {
        return users.stream().anyMatch(user -> user.getLogin().equals(login));
    }
}
