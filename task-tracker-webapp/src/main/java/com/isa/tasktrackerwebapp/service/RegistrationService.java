package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.User;
import com.isa.tasktrackerwebapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerNewUser(User user) {
        user.setActive(true);
        userRepository.save(user);
    }

    public boolean doesEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }
}
