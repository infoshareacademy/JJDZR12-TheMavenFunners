package com.isa.tasktrackerwebapp.unused;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.isa.tasktrackerwebapp.model.entity.User;
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

    public static List<User> getUsers() {
        loadUsersData();
        return users;
    }

    public static void saveNewUser(User user) {
        saveUsersData(user);
    }

    private static void loadUsersData() {
        objectMapper.registerModule(new JavaTimeModule());
        try {
            users = objectMapper.readValue(FileUtil.getUsersFile(), new TypeReference<>() {
            });
        } catch (Exception e) {
            logger.error("Error reading users from file", e);
        }
    }

    private static void saveUsersData(User user) {
        users.add(user);
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(FileUtil.getUsersFile(), users);
        } catch (Exception e) {
            logger.error("Error saving users to file", e);
        }
    }
}