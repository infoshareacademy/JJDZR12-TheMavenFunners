package com.isa.tasktrackerwebapp.controller;

import com.isa.tasktrackerwebapp.model.PageTitle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("content", "index")
                .addAttribute("pageTitle", PageTitle.INDEX.getDisplayValue());
        return "main";
    }

    @GetMapping("/registration")
    String registration(Model model) {
        model.addAttribute("content", "registration")
                .addAttribute("pageTitle", PageTitle.REGISTRATION.getDisplayValue());
        return "main";
    }

    @GetMapping("/add-task")
    String newTask(Model model) {
        model.addAttribute("content", "add-task")
                .addAttribute("pageTitle", PageTitle.ADD_TASK.getDisplayValue());
        return "main";
    }

    @GetMapping("/view-tasks")
    String viewTasks(Model model) {
        model.addAttribute("content", "view-tasks")
                .addAttribute("pageTitle", PageTitle.VIEW_TASKS.getDisplayValue());
        return "main";
    }
}
