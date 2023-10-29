package com.isa.tasktrackerwebapp.controller;

import com.isa.tasktrackerwebapp.model.Task;
import com.isa.tasktrackerwebapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class TaskController {
    @GetMapping("/taskList")
    public String taskList(Model model, @RequestParam(required = false) String sortBy, @RequestParam(required = false) String searchTaskName, @RequestParam(required = false) String filterActive) {
        List<Task> taskList = TaskService.createSampleTasks();

        taskList = TaskService.howToSortTask(sortBy, taskList);

        taskList = TaskService.filterTasksByName(taskList, searchTaskName);

        taskList = TaskService.filterTasksByActive(taskList, filterActive);

        model.addAttribute("taskList", taskList);
        return "taskList";
    }



}
