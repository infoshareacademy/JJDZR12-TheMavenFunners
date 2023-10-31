package com.isa.tasktrackerwebapp.controller;

import com.isa.tasktrackerwebapp.model.JsonDataTask;
import com.isa.tasktrackerwebapp.model.Task;
import com.isa.tasktrackerwebapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskController {
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/taskList")
    public String taskList(Model model, @RequestParam(required = false) String sortBy, @RequestParam(required = false) String searchTaskName, @RequestParam(required = false) String filterActive) {
        List<Task> taskList = JsonDataTask.getTasks();

        taskList = taskService.sortAndFilterTasks(sortBy, searchTaskName, filterActive, taskList);

        model.addAttribute("taskList", taskList);
        return "taskList";
    }


}
