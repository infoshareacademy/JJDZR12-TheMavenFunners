package com.isa.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.isa.task.Task;
import com.isa.user.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonDataTask {
    private static List<Task> tasks = new ArrayList<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final File userFile = new File("task-tracker-console-application/src/main/resources/database/tasks.json");

    private static List<Task> readTasks() {
        objectMapper.registerModule(new JavaTimeModule());
        try {
            tasks = objectMapper.readValue(userFile, new TypeReference<>() {
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
            objectMapper.writeValue(userFile, tasks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printTasks(User user) {
        tasks.addAll(getTasks());
        if (user.getLogin().equals("admin")) {
            if (tasks.isEmpty()) {
                System.out.println("Brak tasków do wyświetlenia.");
            } else {
                tasks.forEach(System.out::println);
            }
        } else {
//            tasks.stream()
//                    .filter(task -> task.getUser().equals(user))
//                    .findAny()
//                    .ifPresentOrElse(task -> {
//                        System.out.println(task);
//                    }, () -> System.out.println("Nie ma żadnych tasków."));
            if (tasks.stream().filter(task->task.getUser().equals(user)).count()<1) {
                System.out.println("Brak tasków do wyświetlenia.");
            } else {
                tasks.forEach(System.out::println);
            }
        }

    }
}
