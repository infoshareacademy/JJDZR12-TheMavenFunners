package com.isa.task;


import com.isa.data.JsonDataTask;
import com.isa.menu.MenuAfterLoggingIn;
import com.isa.user.Login;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AddTask {

    private static List<Task> tasks = JsonDataTask.getTasks();
    public static void addTask() {
        startDisplay();
        getTask();
        getNextTask();
    }

    private static void startDisplay() {
        System.out.println("=====================================");
        System.out.println("            DODAJ ZADANIE");
        System.out.println("=====================================");
    }

    public static void getTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Wprowadź nazwę zadania: ");
        String taskName = scanner.nextLine();

        System.out.print("Wprowadź datę rozpoczęcia zadania (" + DateValidator.getFormatStr().toLowerCase() + "): ");
        LocalDate taskStartDate = getDate(scanner);

        System.out.print("Wprowadź datę zakończenia zadania (" + DateValidator.getFormatStr().toLowerCase() + "): ");
        LocalDate taskEndDate = getDate(scanner);
        while(taskEndDate.isBefore(taskStartDate)) {
            System.out.print("Data zakończenia nie może być większa niż rozpoczęcia! Spróbuj ponownie: ");
            taskEndDate = getDate(scanner);
        }

        System.out.print("Wprowadź opis zadania: ");
        String taskDescription = scanner.nextLine();


        Task task = new Task(taskName, taskStartDate, taskEndDate, taskDescription, Login.getLoggedInUser(), true);
        JsonDataTask.saveNewTask(task);

        System.out.println("Zadanie zostało dodane!");
    }

    private static LocalDate getDate(Scanner scanner) {
        String taskDate = scanner.nextLine();
        while(!DateValidator.isValidDate(taskDate)) {
            System.out.print("Niepoprawny format! Spróbuj ponownie: ");
            taskDate = scanner.nextLine();
        }
        return LocalDate.parse(taskDate, DateValidator.getFormatter());
    }

    private static void getNextTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Aby dodać następne zadanie wybierz 1, aby powrócić do Menu wybierz 0: ");
        String number = scanner.nextLine();

        if (number.equals("1")) {
            getTask();
            getNextTask();
        } else if (number.equals("0")) {
            MenuAfterLoggingIn.printCompletedMenuAfterLoggingIn();
        } else {
            System.out.println("Wybierz prawidłowy numer.");
            getNextTask();
        }
    }
}