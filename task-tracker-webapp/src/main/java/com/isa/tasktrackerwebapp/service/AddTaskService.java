package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddTaskService {
    public Task getTask() {
        Task task = new Task();
        return task;
    }
}
