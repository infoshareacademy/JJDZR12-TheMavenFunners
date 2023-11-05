package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getSortedAndFilteredTasks(String sortBy, String searchTaskName, String filterActive);
}