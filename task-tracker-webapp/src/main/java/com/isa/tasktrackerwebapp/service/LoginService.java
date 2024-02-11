package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.dto.UserDto;
import com.isa.tasktrackerwebapp.model.entity.User;
import com.isa.tasktrackerwebapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final UserService userService;

    public LoginService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
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

    public void setUserAsLoggedIn(Model model) {
        if (isUserLoggedIn()) {
            model.addAttribute("isUserLoggedIn", isUserLoggedIn());
        }
    }



    public List<UserDto> getActiveUsers() {
        return userService.mapEntityToDtoList().stream()
                .filter(x -> x.isActive()).toList();
    }
}