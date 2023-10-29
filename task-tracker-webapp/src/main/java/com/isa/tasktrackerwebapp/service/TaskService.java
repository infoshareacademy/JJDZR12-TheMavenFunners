package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.Task;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface TaskService {
    
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
