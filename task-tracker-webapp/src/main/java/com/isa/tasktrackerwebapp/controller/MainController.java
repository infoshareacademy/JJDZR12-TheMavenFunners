package com.isa.tasktrackerwebapp.controller;

import com.isa.tasktrackerwebapp.model.PageType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

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
        model.addAttribute("content", PageType.ADD_TASK.getContentValue())
                .addAttribute("pageTitle", PageType.ADD_TASK.getTitleValue());
        return "main";
    }

    @GetMapping("/view-tasks")
    String viewTasks(Model model) {
        model.addAttribute("content", PageType.VIEW_TASKS.getContentValue())
                .addAttribute("pageTitle", PageType.VIEW_TASKS.getTitleValue());
        return "main";
    }
}
