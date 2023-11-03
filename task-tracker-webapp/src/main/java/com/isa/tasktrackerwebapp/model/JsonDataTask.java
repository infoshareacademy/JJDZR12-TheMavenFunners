package com.isa.tasktrackerwebapp.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonDataTask {
    private static List<Task> tasks = new ArrayList<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final File taskFile = new File("task-tracker-webapp/src/main/resources/database/tasks.json");

    private JsonDataTask() {
    }

    private static List<Task> readTasks() {
        objectMapper.registerModule(new JavaTimeModule());
        try {
            tasks = objectMapper.readValue(taskFile, new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public static List<Task> getTasks() {
        tasks.addAll(readTasks());
        return tasks;
    }

    public static void saveNewTask(Task task) {
        getTasks();
        tasks.add(task);
        saveTaskData();
    }

    private static void saveTaskData() {
        try {
            objectMapper.writeValue(taskFile, tasks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
