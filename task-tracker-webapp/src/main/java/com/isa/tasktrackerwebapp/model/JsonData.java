package com.isa.tasktrackerwebapp.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonData {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final File userFile = new File("src/main/resources/database/users.json");
    private static List<User> users = new ArrayList<>();

    private JsonData() {
    }

    public static void updateUserData(User user) {
        users.addAll(getUsers());
        saveUserData(users.stream()
                .map(u -> u.equals(user) ? user : u)
                .toList());
    }

    public static void saveNewUser(User user) {
        users.addAll(getUsers());
        users.add(user);
        saveUserData(users);
    }

    private static List<User> readUsers() {
        try {
            users = objectMapper.readValue(userFile, new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static List<User> getUsers() {
        users.addAll(readUsers());
        return users;
    }

    private static void saveUserData(List<User> users) {
        try {
            objectMapper.writeValue(userFile, users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}