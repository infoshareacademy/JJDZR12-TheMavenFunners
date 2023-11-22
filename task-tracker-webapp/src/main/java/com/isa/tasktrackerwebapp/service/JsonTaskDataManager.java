package com.isa.tasktrackerwebapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.isa.tasktrackerwebapp.util.FileUtil;
import com.isa.tasktrackerwebapp.model.Task;
import com.isa.tasktrackerwebapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class JsonTaskDataManager {
    private static List<Task> tasks = new ArrayList<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(JsonTaskDataManager.class);

    private JsonTaskDataManager() {
    }

    public static void loadData() {
        readTasks();
    }

    public static List<Task> getTasks() {
        loadData();
        return tasks;
    }

    public static void saveNewTask(Task task, User loggedInUser) {
        task.setUser(loggedInUser);
        task.setActive(true);
        saveTaskData(task);
    }

    private static List<Task> readTasks() {
        objectMapper.registerModule(new JavaTimeModule());
        try {
            tasks = objectMapper.readValue(FileUtil.getTasksFile(), new TypeReference<>() {
            });
        } catch (Exception e) {
            logger.error("Błąd podczas odczytu tasków z pliku", e);
        }
        return tasks;
    }

    private static void saveTaskData(Task task) {
        tasks.add(task);
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(FileUtil.getTasksFile(), tasks);
        } catch (Exception e) {
            logger.error("Błąd podczas zapisu tasków do pliku", e);
        }
    }
}