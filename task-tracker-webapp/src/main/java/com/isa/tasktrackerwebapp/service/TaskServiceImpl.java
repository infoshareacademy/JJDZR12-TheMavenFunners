package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.Task;
import com.isa.tasktrackerwebapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final LoginService loginService;

    TaskServiceImpl(LoginService loginService, TaskRepository taskRepository) {
        this.loginService = loginService;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getSortedAndFilteredTasks(String sortBy, String searchTaskName, String filterActive) {
        List<Task> taskList;

        if ("oldestByStartDate".equals(sortBy)) {
            taskList = taskRepository.findAllByOrderByTaskStartAsc();
        } else if ("newestByStartDate".equals(sortBy)) {
            taskList = taskRepository.findAllByOrderByTaskStartDesc();
        } else if ("oldestByEndDate".equals(sortBy)) {
            taskList = taskRepository.findAllByOrderByTaskEndAsc();
        } else if ("newestByEndDate".equals(sortBy)) {
            taskList = taskRepository.findAllByOrderByTaskEndDesc();
        } else {
            taskList = taskRepository.findAll();
        }

        taskList = filterTasks(taskList, searchTaskName, filterActive);

        return taskList;
    }

    @Override
    public void saveTask(Task form) {
        form.setUser(loginService.getLoggedInUser());
        taskRepository.save(form);
    }

    private List<Task> filterTasks(List<Task> taskList, String searchTaskName, String filterActive) {
        return taskList.stream()
                .filter(task -> isNameAndActiveMatch(searchTaskName, filterActive, task))
                .toList();
    }

    private boolean isNameAndActiveMatch(String searchTaskName, String filterActive, Task task) {
        boolean nameMatch = searchTaskName == null || searchTaskName.isEmpty() || task.getTaskName().toLowerCase().contains(searchTaskName.toLowerCase());
        boolean activeMatch = filterActive == null || filterActive.isEmpty() || task.getActive() == Boolean.parseBoolean(filterActive);
        return nameMatch && activeMatch;
    }

    @Override
    public boolean taskEndValid(Task form) {
        return form.getTaskEnd().isBefore(form.getTaskStart());
    }
}