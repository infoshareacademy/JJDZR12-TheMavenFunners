package com.isa.tasktrackerwebapp.controller;

import com.isa.tasktrackerwebapp.model.PageType;
import com.isa.tasktrackerwebapp.model.Task;
import com.isa.tasktrackerwebapp.service.AddTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MainController {

    @Autowired
    AddTaskService addTaskService;
    public MainController(AddTaskService addTaskService) {
        this.addTaskService = addTaskService;
    }

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("content", PageType.INDEX.getContentValue())
                .addAttribute("pageTitle", PageType.INDEX.getTitleValue());
        return "main";
    }

    @GetMapping("/registration")
    String registration(Model model) {
        model.addAttribute("content", PageType.REGISTRATION.getContentValue())
                .addAttribute("pageTitle", PageType.REGISTRATION.getTitleValue());
        return "main";
    }

    @GetMapping("/add-task")
    String newTask(Model model) {
        Task task = new Task();
        model.addAttribute("content", PageType.ADD_TASK.getContentValue())
                .addAttribute("pageTitle", PageType.ADD_TASK.getTitleValue())
                .addAttribute("task", task);
        return "main";
    }

    @PostMapping("/add-task")
    String saveTask(@ModelAttribute Task form) {
        AddTaskService.saveTask(form);
        return "redirect:add-task";
    }

    @GetMapping("/view-tasks")
    String viewTasks(Model model) {
        model.addAttribute("content", PageType.VIEW_TASKS.getContentValue())
                .addAttribute("pageTitle", PageType.VIEW_TASKS.getTitleValue());
        return "main";
    }
}
