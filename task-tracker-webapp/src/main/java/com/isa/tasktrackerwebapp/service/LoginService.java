package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.User;
import com.isa.tasktrackerwebapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static User loggedInUser;

    public void setLoggedInUser(User user) {
        loggedInUser = userRepository.findByLogin(user.getLogin()).orElse(null);
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public boolean isUserLoggedIn() {
        return loggedInUser != null;
    }
}