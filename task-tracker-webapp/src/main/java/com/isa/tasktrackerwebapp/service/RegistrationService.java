package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.dto.UserDto;
import com.isa.tasktrackerwebapp.model.entity.User;
import com.isa.tasktrackerwebapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final UserRepository userRepository;
    private final UserService userService;

    public RegistrationService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void registerNewUser(UserDto userDTO) {
        User user = userService.mapDTOToEntity(userDTO);
        user.setActive(true);
        userRepository.save(user);
    }

    public boolean doesEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }
}
