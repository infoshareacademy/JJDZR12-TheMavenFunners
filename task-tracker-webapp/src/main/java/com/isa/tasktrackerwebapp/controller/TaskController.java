package com.isa.tasktrackerwebapp.controller;

import com.isa.tasktrackerwebapp.model.PageType;
import com.isa.tasktrackerwebapp.model.Task;
import com.isa.tasktrackerwebapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
class TaskController {

    private final TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/view-tasks")
    String taskList(Model model,
                    @RequestParam(required = false) String sortBy,
                    @RequestParam(required = false) String searchTaskName,
                    @RequestParam(required = false) String filterActive) {
        List<Task> taskList = taskService.getSortedAndFilteredTasks(sortBy, searchTaskName, filterActive);
        model.addAttribute("taskList", taskList)
                .addAttribute("content", PageType.VIEW_TASKS.getContentValue())
                .addAttribute("pageTitle", PageType.VIEW_TASKS.getTitleValue());
        return "main";
    }
}