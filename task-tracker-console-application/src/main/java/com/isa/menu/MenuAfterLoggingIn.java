package com.isa.menu;

import com.isa.task.AddTask;
import com.isa.task.ViewTasks;
import com.isa.user.Login;

import java.util.Scanner;

public class MenuAfterLoggingIn {

    private MenuAfterLoggingIn() {
    }

    private static void displayMenuAfterLoggingIn() {
        System.out.println("=====================================");
        System.out.println("             MENU PO ZALOGOWANIU");
        System.out.println("=====================================");
        System.out.println("Opcje:");
        System.out.println("1. Wyświetl taski");
        System.out.println("2. Dodaj taski");
        System.out.println("3. Wyświetl uroply");
        System.out.println("4. Wprowadź nowy urlop");
        System.out.println("5. Wyloguj");
        System.out.println();
        System.out.print("Wybierz opcję: ");
    }

    private static void printUserMenuChoiceAfterLoggingIn() {
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        switch (userChoice) {
            case 1 -> ViewTasks.start();
            case 2 -> AddTask.addTask();
            case 3 -> {
                System.out.println("Wyświetlanie urlopów niedostępne. Powrót do menu...");
                printCompletedMenuAfterLoggingIn();
            }
            case 4 -> {
                System.out.println("Wprowadźanie urlopów niedostępne. Powrót do menu...");
                printCompletedMenuAfterLoggingIn();
            }
            case 5 -> {
                Login.logOutUser();
                MainMenu.printCompletedMenu();
            }
            default -> {
                System.out.println("Wprowadź prawidłową wartość");
                printCompletedMenuAfterLoggingIn();
            }
        }
    }

    public static void printCompletedMenuAfterLoggingIn() {
        displayMenuAfterLoggingIn();
        printUserMenuChoiceAfterLoggingIn();
    }
}