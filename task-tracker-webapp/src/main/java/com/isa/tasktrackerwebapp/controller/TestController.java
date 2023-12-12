package com.isa.tasktrackerwebapp.controller;

import com.isa.tasktrackerwebapp.model.User;
import com.isa.tasktrackerwebapp.model.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public void test() {
        userService.save();
    }

    @GetMapping("/testGet")
    public User testGet() {
        return userService.getUserByLogin();
    }
}
