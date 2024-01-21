package com.isa.tasktrackerwebapp.model.entity;

public enum PageType {
    INDEX("Strona główna", "index"),
    REGISTRATION("Rejestracja", "registration"),
    ADD_TASK("Dodaj task", "add-task"),
    VIEW_TASKS("Wyświetl taski", "view-tasks"),
    LOGIN("Login", "login");

    private final String titleValue;
    private final String contentValue;

    PageType(String titleValue, String contentValue) {
        this.titleValue = titleValue;
        this.contentValue = contentValue;
    }

    public String getTitleValue() {
        return titleValue;
    }

    public String getContentValue() {
        return contentValue;
    }
}
