package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.dto.TaskDto;
import com.isa.tasktrackerwebapp.model.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getSortedAndFilteredTasks(String sortBy, String searchTaskName, String filterActive);
    void saveTask(TaskDto form);
    boolean taskEndInvalid(TaskDto form);
}