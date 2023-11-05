package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.JsonDataTask;
import com.isa.tasktrackerwebapp.model.Task;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {


    public static List<Task> filterTasks(List<Task> taskList, String searchTaskName, String filterActive) {
        return taskList.stream()
                .filter(task -> {
                    boolean nameMatch = searchTaskName == null || searchTaskName.isEmpty() || task.getTaskName().toLowerCase().contains(searchTaskName.toLowerCase());
                    boolean activeMatch = filterActive == null || filterActive.isEmpty() || task.getActive() == Boolean.parseBoolean(filterActive);
                    return nameMatch && activeMatch;
                })
                .collect(Collectors.toList());
    }

    public List<Task> sortAndFilterTasks(String sortBy, String searchTaskName, String filterActive, List<Task> taskList) {
        if ("oldestByStartDate".equals(sortBy)) {
            taskList.sort(Comparator.comparing(Task::getTaskStart));
        } else if ("newestByStartDate".equals(sortBy)) {
            taskList.sort(Comparator.comparing(Task::getTaskStart).reversed());
        } else if ("oldestByEndDate".equals(sortBy)) {
            taskList.sort(Comparator.comparing(Task::getTaskEnd));
        } else if ("newestByEndDate".equals(sortBy)) {
            taskList.sort(Comparator.comparing(Task::getTaskEnd).reversed());
        }

        taskList = filterTasks(taskList, searchTaskName, filterActive);

        return taskList;
    }

    public List<Task> getTasks() {
        return JsonDataTask.getTasks();
    }

    private List<Task> sortTaskByStartDateDes(List<Task> taskList) {
        taskList.sort((task1, task2) -> task2.getTaskStart().compareTo(task1.getTaskStart()));
        return taskList;
    }

    private List<Task> sortTaskByStartDateAsc(List<Task> taskList) {
        Collections.reverse(sortTaskByStartDateDes(taskList));
        return taskList;
    }

    private List<Task> sortTaskByEndDateDes(List<Task> taskList) {
        taskList.sort((task1, task2) -> task2.getTaskEnd().compareTo(task1.getTaskEnd()));
        return taskList;
    }

    private List<Task> sortTaskByEndDateAsc(List<Task> taskList) {
        Collections.reverse(sortTaskByEndDateDes(taskList));
        return taskList;
    }
}
