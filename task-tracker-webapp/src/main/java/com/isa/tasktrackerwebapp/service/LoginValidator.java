package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginValidator {
    UserRepository userRepository;

    public LoginValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean doesLoginExist(String login) {
        return userRepository.existsByLogin(login);
    }

    public boolean isLoginInputValid(String login, String password) {
        return doesLoginExist(login) && isPasswordCorrect(login, password);
    }

    private boolean isPasswordCorrect(String login, String password) {
        return userRepository.findByLogin(login)
                .map(user -> user.getPassword().equals(password)).orElse(false);
    }
}
