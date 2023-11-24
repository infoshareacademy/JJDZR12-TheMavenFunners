package com.isa.tasktrackerwebapp.controller;

import com.isa.tasktrackerwebapp.model.PageType;
import com.isa.tasktrackerwebapp.model.Task;
import com.isa.tasktrackerwebapp.service.LoginService;
import com.isa.tasktrackerwebapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
class TaskController {

    private final TaskService taskService;
    private final LoginService loginService;

    TaskController(TaskService taskService, LoginService loginService) {
        this.taskService = taskService;
        this.loginService = loginService;
    }

    @GetMapping("/view-tasks")
    String getTaskList(Model model,
                       @RequestParam(required = false) String sortBy,
                       @RequestParam(required = false) String searchTaskName,
                       @RequestParam(required = false) String filterActive) {
        List<Task> taskList = taskService.getSortedAndFilteredTasks(sortBy, searchTaskName, filterActive);
        model.addAttribute("taskList", taskList)
                .addAttribute("content", PageType.VIEW_TASKS.getContentValue())
                .addAttribute("pageTitle", PageType.VIEW_TASKS.getTitleValue());
        if (loginService.isUserLoggedIn()){
            model.addAttribute("isUserLoggedIn", loginService.isUserLoggedIn());
        }
        return "main";
    }

    @GetMapping("/add-task")
    String newTask(Model model) {
        model.addAttribute("content", PageType.ADD_TASK.getContentValue())
                .addAttribute("pageTitle", PageType.ADD_TASK.getTitleValue())
                .addAttribute("task", new Task());
        if (loginService.isUserLoggedIn()){
            model.addAttribute("isUserLoggedIn", loginService.isUserLoggedIn());
        }
        return "main";
    }

    @PostMapping("/add-task")
    String saveTask(@ModelAttribute Task form) {
        taskService.saveTask(form);
        return "redirect:add-task";
    }
}