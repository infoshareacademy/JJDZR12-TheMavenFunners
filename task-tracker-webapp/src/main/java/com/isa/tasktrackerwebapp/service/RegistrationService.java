package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.User;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    public void registerNewUser(User user) {
        JsonUserDataManager.saveNewUser(user);
    }

    public boolean doesEmailExist(String email) {
        return JsonUserDataManager.getUsers().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }
}
