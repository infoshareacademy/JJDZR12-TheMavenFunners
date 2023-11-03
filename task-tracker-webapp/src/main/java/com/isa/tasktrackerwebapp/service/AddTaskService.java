package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.JsonDataTask;
import com.isa.tasktrackerwebapp.model.Task;
import org.springframework.stereotype.Service;



@Service
public class AddTaskService {
    public static void saveTask(Task form) {
        JsonDataTask.saveNewTask(form);
    }
}
