package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.dto.TaskDto;
import com.isa.tasktrackerwebapp.model.entity.Task;
import com.isa.tasktrackerwebapp.repository.TaskRepository;
import com.isa.tasktrackerwebapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class TaskServiceImpl implements TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;
    private final LoginService loginService;
    private final UserRepository userRepository;

    TaskServiceImpl(LoginService loginService, TaskRepository taskRepository, UserRepository userRepository) {
        this.loginService = loginService;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
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
    public List<Task> findLoggedInUsersActiveTasks() {
        List<Task> taskList = new ArrayList<>();
        logger.debug("Sorting and filtering tasks");

        if(loginService.isUserLoggedIn()) {
            taskList = taskRepository.findAllByUserAndActiveOrderByTaskEndAsc(loginService.getLoggedInUser(), true);
        }

        logger.debug("Retrieved {} tasks from the database.", taskList.size());

        return taskList;
    }

    public Task mapTaskDtoToEntityTask(TaskDto taskDto) {
        Task task = new Task();

        task.setTaskName(taskDto.getTaskName());
        task.setTaskStart(taskDto.getTaskStart());
        task.setTaskEnd(taskDto.getTaskEnd());
        task.setTaskDescription(taskDto.getTaskDescription());
        task.setUser(userRepository.findByLogin(taskDto.getUser()).get());
        task.setActive(taskDto.getActive());

        return task;
    }

    @Override
    public void saveTask(TaskDto form) {
        form.setActive(true);
        taskRepository.save(mapTaskDtoToEntityTask(form));
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
    public boolean taskEndInvalid(TaskDto form) {
        return form.getTaskEnd().isBefore(form.getTaskStart());
    }

    @Override
    @Transactional
    public void editTask(TaskDto editedTask, Task task) {
        task.setTaskDescription(mapTaskDtoToEntityTask(editedTask).getTaskDescription());
        task.setTaskEnd(mapTaskDtoToEntityTask(editedTask).getTaskEnd());
        task.setTaskName(mapTaskDtoToEntityTask(editedTask).getTaskName());
        task.setTaskStart(mapTaskDtoToEntityTask(editedTask).getTaskStart());
        taskRepository.save(task);
        logger.info("Task with Id " + task.getId() + " edited");
    }
    @Override
    public void toggleTaskStatus(Long taskId) {
        Task task = taskRepository.findTaskById(taskId);

        if (task != null) {
            task.setActive(!task.getActive());
            taskRepository.save(task);
            logger.info("Task with Id " + taskId + (task.getActive() ? " activated" : " deactivated"));
        } else {
            logger.warn("Task with Id " + taskId + " not found");
        }
    }

    @Override
    public Task findTaskById(Long id){
        return taskRepository.findTaskById(id);
    }
}