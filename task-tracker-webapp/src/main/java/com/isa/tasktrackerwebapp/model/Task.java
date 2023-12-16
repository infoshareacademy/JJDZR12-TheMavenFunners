package com.isa.tasktrackerwebapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    @NotBlank(message = "Nazwa zadania nie może być pusta.")
    private String taskName;
    @FutureOrPresent(message = "Nie można wprowadzić daty z przeszłości.")
    @NotNull(message = "Data rozpoczęcia nie może być pusta.")
    private LocalDate taskStart;
    @FutureOrPresent(message = "Nie można wprowadzić daty z przeszłości.")
    @NotNull(message = "Data zakończenia nie może być pusta.")
    private LocalDate taskEnd;
    @NotBlank(message = "Opis zadania nie moży być pusty.")
    private String taskDescription;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    private boolean active;

    public Task(String taskName, LocalDate taskStart, LocalDate taskEnd, String taskDescription, User user, boolean active) {
        this.taskName = taskName;
        this.taskStart = taskStart;
        this.taskEnd = taskEnd;
        this.taskDescription = taskDescription;
        this.user = user;
        this.active = active;
    }

    public Task() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
