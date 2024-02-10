package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getSortedAndFilteredTasks(String sortBy, String searchTaskName, String filterActive);
    List<Task> findLoggedInUsersActiveTasks();
    void saveTask(Task form);
    boolean taskEndInvalid(Task form);
    void editTask(Task editedTask, Task task);
    void toggleTaskStatus(Long taskId);

    Task findTaskById(Long id);
}