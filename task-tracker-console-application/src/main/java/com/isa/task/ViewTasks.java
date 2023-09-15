package com.isa.task;

import com.isa.data.JsonDataTask;
import com.isa.menu.AdminMenu;
import com.isa.menu.MenuAfterLoggingIn;
import com.isa.user.Login;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ViewTasks {
    private static List<Task> tasks = JsonDataTask.getTasks();

    private static void askForUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wprowadź dowolną wartość, aby powrócić do menu po zalogowaniu: ");
        scanner.next();
    }

    private static void printTasks() {
        if (Login.getLoggedInUser().getLogin().equals("admin")) {
            printAllTasks();
        } else {
            printUserTasks();
        }
    }

    private static void printAllTasks() {
        if (!tasks.isEmpty()) {
            tasks.forEach(System.out::println);
        } else {
            System.out.println("Brak tasków do wyświetlenia.");
        }
    }

    private static void printUserTasks() {
        List<Task> userTasks = tasks.stream()
                .filter(task -> task.getUser().equals(Login.getLoggedInUser()))
                .toList();

        if (!userTasks.isEmpty()) {
            userTasks.forEach(System.out::println);
        } else {
            System.out.println("Brak tasków do wyświetlenia.");
        }
    }

    public static void start() {
        printTasks();
        askForUserInput();
        if (Login.getLoggedInUser().getLogin().equals("admin")) {
            AdminMenu.printCompletedAdminMenu();
        } else {
            MenuAfterLoggingIn.printCompletedMenuAfterLoggingIn();
        }
    }
}
