package com.isa.tasktrackerwebapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.isa.tasktrackerwebapp.util.FileUtil;
import com.isa.tasktrackerwebapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class JsonUserDataManager {
    private static List<User> users = new ArrayList<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(JsonUserDataManager.class);


    private JsonUserDataManager() {
    }

    public static void loadData() {
        readUsers();
    }

    public static List<User> getUsers() {
        loadData();
        return users;
    }

    public static void saveNewUser(User user) {
        saveUserData(user);
    }

    private static List<User> readUsers() {
        objectMapper.registerModule(new JavaTimeModule());
        try {
            users = objectMapper.readValue(FileUtil.getUsersFile(), new TypeReference<>() {
            });
        } catch (Exception e) {
            logger.error("Błąd podczas odczytu użytkowników z pliku", e);
        }
        return users;
    }

    private static void saveUserData(User user) {
        users.add(user);
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(FileUtil.getUsersFile(), users);
        } catch (Exception e) {
            logger.error("Błąd podczas zapisu użytkownika do pliku", e);
        }
    }
}