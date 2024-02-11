package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginValidator {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginValidator(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean doesLoginExist(String login) {
        return userRepository.existsByLogin(login);
    }

    public boolean isLoginInputValid(String login, String password) {
        return doesLoginExist(login) && isPasswordCorrect(login, password);
    }

    private boolean isPasswordCorrect(String login, String password) {
        return userRepository.findByLogin(login)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
    }
}
