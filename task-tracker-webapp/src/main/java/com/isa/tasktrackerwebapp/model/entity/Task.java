package com.isa.tasktrackerwebapp.model.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name="task")
public class Task extends AbstractEntity {

    @Column(nullable = false)
    private String taskName;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate taskStart;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate taskEnd;

    @Column(nullable = false)
    private String taskDescription;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @Column(nullable = false)
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
