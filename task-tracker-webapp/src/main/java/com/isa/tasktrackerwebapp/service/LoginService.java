package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.entity.User;
import com.isa.tasktrackerwebapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static User loggedInUser;

    public void setLoggedInUser(User user) {
        loggedInUser = userRepository.findByLogin(user.getLogin()).orElse(null);
    }

    public void logOutUser() {
        loggedInUser = null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public boolean isUserLoggedIn() {
        return loggedInUser != null;
    }

    public List<User> getActiveUsers() {
        return userRepository.findAll().stream()
                .filter(x -> x.isActive()).toList();
    }
}