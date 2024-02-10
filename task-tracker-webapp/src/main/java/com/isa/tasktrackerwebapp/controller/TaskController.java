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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        loginService.setUserAsLoggedIn(model);
        List<Task> taskList = taskService.getSortedAndFilteredTasks(sortBy, searchTaskName, filterActive);
        model.addAttribute("taskList", taskList)
                .addAttribute("content", PageType.VIEW_TASKS.getContentValue())
                .addAttribute("pageTitle", PageType.VIEW_TASKS.getTitleValue())
                .addAttribute("localDate", LocalDate.now());

        return "main";
    }

    @GetMapping("/add-task")
    String newTask(Model model) {
        loginService.setUserAsLoggedIn(model);
        model.addAttribute("content", PageType.ADD_TASK.getContentValue())
                .addAttribute("pageTitle", PageType.ADD_TASK.getTitleValue())
                .addAttribute("task", new Task());
        return "main";
    }

    @PostMapping("/add-task")
    String saveTask(@Valid @ModelAttribute Task form, BindingResult bindingResult, Model model) {
        loginService.setUserAsLoggedIn(model);
        boolean hasErrors = bindingResult.hasErrors();
        boolean taskEndError = false;
        if (!bindingResult.hasFieldErrors("taskStart") && !bindingResult.hasFieldErrors("taskEnd")) {
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

    @GetMapping("/edit-task/{taskId}")
    String editTask(Model model, @PathVariable Long taskId,
                    @RequestParam(required = false) String src) {
        loginService.setUserAsLoggedIn(model);

        Task task = taskService.findTaskById(taskId);
        model.addAttribute("task", task)
                .addAttribute("content", PageType.EDIT_TASK.getContentValue())
                .addAttribute("pageTitle", PageType.EDIT_TASK.getTitleValue());

        return "main";
    }

    @PostMapping("/edit-task/{taskId}")
    String editTask(@Valid @ModelAttribute("task") Task form,
                    BindingResult bindingResult,
                    @RequestParam(required = false) String src,
                    @PathVariable Long taskId,
                    Model model) {
        loginService.setUserAsLoggedIn(model);
        boolean hasErrors = bindingResult.hasErrors();
        boolean taskEndError = false;
        if (!bindingResult.hasFieldErrors("taskStart") && !bindingResult.hasFieldErrors("taskEnd")) {
            taskEndError = taskService.taskEndInvalid(form);
        }

        StringBuilder failureReasonsBuilder = new StringBuilder("Edit task attempt failed: ");

        if (hasErrors) {
            failureReasonsBuilder.append(" task fields validation not passed;");
        }

        if (taskEndError) {
            failureReasonsBuilder.append(" task end is before task start;");
        }

        if (hasErrors || taskEndError) {
            logger.warn(failureReasonsBuilder.toString());

            model.addAttribute("content", PageType.EDIT_TASK.getContentValue())
                    .addAttribute("pageTitle", PageType.EDIT_TASK.getTitleValue())
                    .addAttribute("task", form)
                    .addAttribute("taskEndError", taskEndError);
            return "main";
        }
        Task task = taskService.findTaskById(taskId);
        taskService.editTask(form, task);

        if ("index".equals(src)) {
            return "redirect:/?editSuccessful=true";
        } else {
            return "redirect:/view-tasks?editSuccessful=true";
        }

    }

    @GetMapping("/toggle-task/{taskId}")
    public String toggleTaskStatus(@PathVariable Long taskId, @RequestParam(required = false) String src) {
        taskService.toggleTaskStatus(taskId);
        if ("index".equals(src)) {
            return "redirect:/";
        } else {
            return "redirect:/view-tasks";
        }
    }

}