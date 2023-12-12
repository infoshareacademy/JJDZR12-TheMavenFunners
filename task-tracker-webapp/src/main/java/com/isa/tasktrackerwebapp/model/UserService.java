package com.isa.tasktrackerwebapp.model;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save() {
        User user = new User("log", "ZaQ123!@1", "email@email.pl", true);
        user.setFirstName("name");
        user.setLastName("lastName");
        userRepository.save(user);
    }

    public User getUserByLogin() {
        return userRepository.findByLogin("log").orElse(null);
    }
}