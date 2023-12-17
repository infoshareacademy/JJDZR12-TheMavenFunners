package com.isa.tasktrackerwebapp.controller;

import com.isa.tasktrackerwebapp.model.entity.PageType;
import com.isa.tasktrackerwebapp.model.entity.Task;
import com.isa.tasktrackerwebapp.service.LoginService;
import com.isa.tasktrackerwebapp.service.TaskService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
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
    String saveTask(@Valid @ModelAttribute Task form, BindingResult bindingResult, Model model) {
        boolean hasErrors = bindingResult.hasErrors();
        boolean taskEndError = false;
        if(!bindingResult.hasFieldErrors("taskStart") && !bindingResult.hasFieldErrors("taskEnd")) {
            taskEndError = taskService.taskEndInvalid(form);
        }

        StringBuilder failureReasonsBuilder = new StringBuilder("Add task attempt failed: ");

        if (hasErrors) {
            failureReasonsBuilder.append(" task fields validation not passed;");
        }

        if (taskEndError) {
            failureReasonsBuilder.append(" task end is before task start;");
        }

        if (hasErrors || taskEndError) {
            logger.warn(failureReasonsBuilder.toString());

            model.addAttribute("content", PageType.ADD_TASK.getContentValue())
                    .addAttribute("pageTitle", PageType.ADD_TASK.getTitleValue())
                    .addAttribute("task", form)
                    .addAttribute("taskEndError", taskEndError);
            return "main";
        }

        taskService.saveTask(form);
        logger.info("Added new task: {}", form);
        return "redirect:/add-task?addTaskSuccessful";
    }
}