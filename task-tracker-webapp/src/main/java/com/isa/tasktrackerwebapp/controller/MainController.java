package com.isa.tasktrackerwebapp.controller;

import com.isa.tasktrackerwebapp.model.entity.PageType;
import com.isa.tasktrackerwebapp.model.entity.Task;
import com.isa.tasktrackerwebapp.service.LoginService;
import com.isa.tasktrackerwebapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
class MainController {
    private final LoginService loginService;
    private final TaskService taskService;

    MainController(LoginService loginService, TaskService taskService) {
        this.loginService = loginService;
        this.taskService = taskService;
    }

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("content", PageType.INDEX.getContentValue())
                .addAttribute("pageTitle", PageType.INDEX.getTitleValue());
        if (loginService.isUserLoggedIn()) {
            model.addAttribute("isUserLoggedIn", loginService.isUserLoggedIn())
                    .addAttribute("user", loginService.getLoggedInUser().getLogin())
                    .addAttribute("localDate", LocalDate.now());

            List<Task> taskList = taskService.findLoggedInUsersActiveTasks();
            model.addAttribute("taskList", taskList);
//            List<Task> taskList = taskService.getSortedAndFilteredTasks("","","");

        }
        return "main";
    }
}
