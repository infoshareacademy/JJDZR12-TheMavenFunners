package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getSortedAndFilteredTasks(String sortBy, String searchTaskName, String filterActive);
    void saveTask(Task form);
    boolean taskEndInvalid(Task form);
    void editTask(Task editedTask, Task task);

    Task findTaskById(Long id);
}