package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.entity.Task;
import com.isa.tasktrackerwebapp.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class TaskServiceImpl implements TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;
    private final LoginService loginService;

    TaskServiceImpl(LoginService loginService, TaskRepository taskRepository) {
        this.loginService = loginService;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getSortedAndFilteredTasks(String sortBy, String searchTaskName, String filterActive) {
        List<Task> taskList;
        logger.debug("Sorting and filtering tasks. sortBy: {}, searchTaskName: {}, filterActive: {}", sortBy, searchTaskName, filterActive);

        if ("ascByStartDate".equals(sortBy)) {
            taskList = taskRepository.findAllByOrderByTaskStartAsc();
        } else if ("descByStartDate".equals(sortBy)) {
            taskList = taskRepository.findAllByOrderByTaskStartDesc();
        } else if ("ascByEndDate".equals(sortBy)) {
            taskList = taskRepository.findAllByOrderByTaskEndAsc();
        } else if ("descByEndDate".equals(sortBy)) {
            taskList = taskRepository.findAllByOrderByTaskEndDesc();
        } else {
            taskList = taskRepository.findAll();
        }

        taskList = filterTasks(taskList, searchTaskName, filterActive);

        logger.debug("Retrieved {} tasks from the database.", taskList.size());

        return taskList;
    }

    @Override
    public void saveTask(Task form) {
        form.setUser(loginService.getLoggedInUser());
        taskRepository.save(form);
    }

    private List<Task> filterTasks(List<Task> taskList, String searchTaskName, String filterActive) {
        logger.debug("Filtering tasks. searchTaskName: {}, filterActive: {}", searchTaskName, filterActive);

        List<Task> filteredTasks = taskList.stream()
                .filter(task -> isNameAndActiveMatch(searchTaskName, filterActive, task))
                .toList();

        logger.debug("Filtered tasks: {}", filteredTasks);

        return filteredTasks;
    }

    private boolean isNameAndActiveMatch(String searchTaskName, String filterActive, Task task) {
        boolean nameMatch = searchTaskName == null || searchTaskName.isEmpty() || task.getTaskName().toLowerCase().contains(searchTaskName.toLowerCase());
        boolean activeMatch = filterActive == null || filterActive.isEmpty() || task.getActive() == Boolean.parseBoolean(filterActive);
        return nameMatch && activeMatch;
    }

    @Override
    public boolean taskEndInvalid(Task form) {
        return form.getTaskEnd().isBefore(form.getTaskStart());
    }
}