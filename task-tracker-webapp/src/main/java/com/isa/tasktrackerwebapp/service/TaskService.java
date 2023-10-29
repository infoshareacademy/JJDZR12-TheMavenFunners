package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.Task;
import com.isa.tasktrackerwebapp.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface TaskService {
    public static List<Task> createSampleTasks() {
        List<Task> taskList = new ArrayList<>();

        User user1 = new User("user1", "password1", "user1@example.com", true);
        User user2 = new User("user2", "password2", "user2@example.com", true);
        LocalDate now = LocalDate.now();
        Task task1 = new Task("Task 1", now.minusDays(3), now.plusDays(1), "Description 1", user1, false);
        Task task2 = new Task("Task 2", now.minusDays(2), now.plusDays(2), "Description 2", user1, false);
        Task task3 = new Task("Task 3", now.minusDays(1), now.plusDays(3), "Description 3", user2, true);
        Task task4 = new Task("Task 4", now.minusDays(4), now.plusDays(5), "Description 4", user2, true);
        Task task5 = new Task("Task 5", now.minusDays(5), now.plusDays(4), "Description 5", user1, false);
        Task task6 = new Task("Task 1", now.minusDays(2), now.plusDays(1), "Description 1", user1, true);
        Task task7 = new Task("Task 1", now.minusDays(1), now.plusDays(1), "Description 1", user1, false);

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        taskList.add(task5);
        taskList.add(task6);
        taskList.add(task7);
        return taskList;
    }

    private static List<Task> sortTaskByStartDateDes(List<Task> taskList) {
        taskList.sort((task1, task2) -> task2.getTaskStart().compareTo(task1.getTaskStart()));
        return taskList;
    }

    private static List<Task> sortTaskByStartDateAsc(List<Task> taskList) {
        Collections.reverse(sortTaskByStartDateDes(taskList));
        return taskList;
    }

    private static List<Task> sortTaskByEndDateDes(List<Task> taskList) {
        taskList.sort((task1, task2) -> task2.getTaskEnd().compareTo(task1.getTaskEnd()));
        return taskList;
    }

    private static List<Task> sortTasksByEndDateAsc(List<Task> taskList) {
        Collections.reverse(sortTaskByEndDateDes(taskList));
        return taskList;
    }

    public static List<Task> howToSortTask(String sortBy, List<Task> taskList) {
        if ("oldestByStartDate".equals(sortBy)) {
            sortTaskByStartDateAsc(taskList);
        }
        if ("newestByStartDate".equals(sortBy)) {
            sortTaskByStartDateDes(taskList);
        }
        if ("oldestByEndDate".equals(sortBy)) {
            sortTasksByEndDateAsc(taskList);
        }
        if ("newestByEndDate".equals(sortBy)) {
            sortTaskByEndDateDes(taskList);
        }


        return taskList;
    }

    public static List<Task> filterTasksByName(List<Task> taskList, String searchTaskName) {
        if (searchTaskName != null && !searchTaskName.isEmpty()) {
            return taskList.stream().filter(task -> task.getTaskName().toLowerCase().contains(searchTaskName.toLowerCase())).collect(Collectors.toList());

        }
        return taskList;
    }
    public static List<Task> filterTasksByActive(List<Task> taskList, String filterActive) {
        if (filterActive != null && !filterActive.isEmpty()) {
            boolean activeValue = Boolean.parseBoolean(filterActive);
            return taskList.stream().filter(task -> task.getActive() == activeValue).collect(Collectors.toList());
        }
        return taskList;
    }
}
