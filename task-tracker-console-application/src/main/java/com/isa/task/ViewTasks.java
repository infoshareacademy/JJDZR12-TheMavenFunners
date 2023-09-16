package com.isa.task;

import com.isa.data.JsonData;
import com.isa.data.JsonDataTask;
import com.isa.menu.AdminMenu;
import com.isa.menu.MenuAfterLoggingIn;
import com.isa.user.Login;
import com.isa.user.User;

import java.util.List;
import java.util.Scanner;

public class ViewTasks {
    private static List<Task> loadTasks() {
        loadUsers();
        return JsonDataTask.getTasks();
    }
    private static List<User> loadUsers() {
        return JsonData.getUsers();
    }

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
        if (!loadTasks().isEmpty()) {
            loadTasks().forEach(System.out::println);
        } else {
            System.out.println("Brak tasków do wyświetlenia.");
        }
    }

    private static void printUserTasks() {
        List<Task> userTasks = loadTasks().stream()
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
