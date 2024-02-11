package com.isa.tasktrackerwebapp.model.dto;

import com.isa.tasktrackerwebapp.model.entity.AbstractEntity;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Check;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class TaskDto extends AbstractEntity {

    @NotBlank(message = "Nazwa zadania nie może być pusta.")
    private String taskName;

    @Check(constraints = "task_end >= task_start")
    @NotNull(message = "Data rozpoczęcia nie może być pusta.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate taskStart;

    @Check(constraints = "task_end >= task_start")
    @NotNull(message = "Data zakończenia nie może być pusta.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate taskEnd;

    @NotBlank(message = "Opis zadania nie moży być pusty.")
    private String taskDescription;

    private String user;

    private boolean active;

    public TaskDto(String taskName, LocalDate taskStart, LocalDate taskEnd, String taskDescription, String user, boolean active) {
        this.taskName = taskName;
        this.taskStart = taskStart;
        this.taskEnd = taskEnd;
        this.taskDescription = taskDescription;
        this.user = user;
        this.active = active;
    }

    public TaskDto() {
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDate getTaskStart() {
        return taskStart;
    }

    public void setTaskStart(LocalDate taskStart) {
        this.taskStart = taskStart;
    }

    public LocalDate getTaskEnd() {
        return taskEnd;
    }

    public void setTaskEnd(LocalDate taskEnd) {
        this.taskEnd = taskEnd;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Nazwa: " + taskName + "\n" +
                "Data rozpoczęcia: " + taskStart + "\n" +
                "Data zakończenia: " + taskEnd + "\n" +
                "Opis: " + taskDescription + "\n" +
                "Użytkownik: " + user + "\n" +
                "Status: " + (active ? "aktywne" : "nieaktywne");
    }
}
