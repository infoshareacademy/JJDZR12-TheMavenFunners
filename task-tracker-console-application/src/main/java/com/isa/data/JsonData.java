package com.isa.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.user.User;

import java.io.File;
import java.util.List;

public class JsonData {
    private static List<User> users;
    private static final File userFile = new File("task-tracker-console-application/src/main/resources/database/users.json");

    public static List<User> getUsers() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            users = objectMapper.readValue(userFile, new TypeReference<List<User>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void saveNewUser(User user) {
        ObjectMapper objectMapper = new ObjectMapper();
        users = getUsers();
        users.add(user);
        try {
            objectMapper.writeValue(userFile, users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
