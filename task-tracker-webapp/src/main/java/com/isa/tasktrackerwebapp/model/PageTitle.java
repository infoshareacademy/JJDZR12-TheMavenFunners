package com.isa.tasktrackerwebapp.model;

public enum PageTitle {
    INDEX("Strona główna"),
    REGISTRATION("Rejestracja"),
    ADD_TASK("Dodaj task"),
    VIEW_TASKS("Wyświetl taski");

    private final String displayValue;

    PageTitle(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
