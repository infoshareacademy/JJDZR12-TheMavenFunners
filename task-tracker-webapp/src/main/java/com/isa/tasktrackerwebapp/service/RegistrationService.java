package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.User;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    public void registerNewUser(User user) {
        JsonUserDataManager.saveNewUser(user);
    }
}
