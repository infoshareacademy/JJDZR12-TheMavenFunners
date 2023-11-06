package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.JsonDataTask;
import com.isa.tasktrackerwebapp.model.Task;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
class TaskServiceImpl implements TaskService {

    @Override
    public List<Task> getSortedAndFilteredTasks(String sortBy, String searchTaskName, String filterActive) {
        List<Task> taskList = JsonDataTask.getTasks();

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

    private List<Task> filterTasks(List<Task> taskList, String searchTaskName, String filterActive) {
        return taskList.stream()
                .filter(task -> isNameAndActiveMatch(searchTaskName, filterActive, task))
                .collect(Collectors.toList());
    }

    private boolean isNameAndActiveMatch(String searchTaskName, String filterActive, Task task) {
        boolean nameMatch = searchTaskName == null || searchTaskName.isEmpty() || task.getTaskName().toLowerCase().contains(searchTaskName.toLowerCase());
        boolean activeMatch = filterActive == null || filterActive.isEmpty() || task.getActive() == Boolean.parseBoolean(filterActive);
        return nameMatch && activeMatch;
    }
}