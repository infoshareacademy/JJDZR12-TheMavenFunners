package com.isa.tasktrackerwebapp.controller;

import com.isa.tasktrackerwebapp.model.PageTitle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @GetMapping("/addtask")
    String newTask(Model model) {
        model.addAttribute("content", "addtask")
                .addAttribute("pageTitle", PageTitle.ADD_TASK.getDisplayValue());
        return "main";
    }

    @GetMapping("/viewtasks")
    String viewTasks(Model model) {
        model.addAttribute("content", "viewtasks")
                .addAttribute("pageTitle", PageTitle.VIEW_TASKS.getDisplayValue());
        return "main";
    }
}
