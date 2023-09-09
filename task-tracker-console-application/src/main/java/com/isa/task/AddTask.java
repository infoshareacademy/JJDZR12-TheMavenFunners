package com.isa.task;

import com.isa.menu.MenuAfterLoggingIn;

import java.util.Scanner;

public class AddTask {
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

    private static void getTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Wprowadź nazwę zadania: ");
        String taskName = scanner.nextLine();

        System.out.print("Wprowadź datę rozpoczęcia zadania: ");
        String taskStart = scanner.nextLine();

        System.out.print("Wprowadź datę zakończenia zadania: ");
        String taskEnd = scanner.nextLine();

        System.out.print("Wprowadź opis zadania: ");
        String taskDescription = scanner.nextLine();

        System.out.println("Zadanie zostało dodane!");
    }

    private static void getNextTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Aby dodać następne zadanie wybierz 1, aby powrócić do Menu wybierz 0: ");
        int number = scanner.nextInt();

        if (number == 1) {
            getTask();
            getNextTask();
        } else if (number == 0) {
            MenuAfterLoggingIn.printCompletedMenuAfterLoggingIn();
        } else {
            System.out.println("Wybierz prawidłowy numer.");
            getNextTask();
        }
    }
}
