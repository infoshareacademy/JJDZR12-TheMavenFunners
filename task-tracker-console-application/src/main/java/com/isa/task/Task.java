package com.isa.task;

import com.isa.user.User;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private String taskName;
    private String taskStart;
    private String taskEnd;
    private String taskDescription;
    private User login;
    private Boolean active;

    public Task(String taskName, String taskStart, String taskEnd, String taskDescription, Boolean active) {
        this.taskName = taskName;
        this.taskStart = taskStart;
        this.taskEnd = taskEnd;
        this.taskDescription = taskDescription;
        this.active = active;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskStart() {
        return taskStart;
    }

    public void setTaskStart(String taskStart) {
        this.taskStart = taskStart;
    }

    public String getTaskEnd() {
        return taskEnd;
    }

    public void setTaskEnd(String taskEnd) {
        this.taskEnd = taskEnd;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public User getLogin() {
        return login;
    }

    public void setLogin(User login) {
        this.login = login;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public static void getTestTasks() {;
        Task t1 = new Task("Opis projektu", "10.09.2023", "11.09.2023", "Zadanie wymaga wykonania opisu projektu", true);
        Task t2 = new Task("Rozbudowanie aplikacji", "10.09.2023", "17.09.2023", "Zadanie polega na rozbudowaniu aplikacji", true);
        Task t3 = new Task("Testowanie aplikacji", "17.09.2023", "20.09.2023", "Przetestowanie działania aplikacji", true);
    }
}

