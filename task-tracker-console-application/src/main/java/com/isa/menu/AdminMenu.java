package com.isa.menu;

import com.isa.task.ViewTasks;
import com.isa.user.Login;

import java.util.Scanner;

public class AdminMenu {
    private static void displayAdminMenu(){
        System.out.println("=====================================");
        System.out.println("             MENU ADMINA");
        System.out.println("=====================================");
        System.out.println("Opcje:");
        System.out.println("1. Wyświetl taski");
        System.out.println("2. Dodaj taski");
        System.out.println("3. Wyświetl uroply");
        System.out.println("4. Wprowadź nowy urlop");
        System.out.println("5. Wyświetl użytkowników");
        System.out.println("6. Dodaj role użytkownikom");
        System.out.println("7. Zablokuj użytkownika");
        System.out.println("8. Wyświetl raporty");
        System.out.println("9. Wyloguj");
        System.out.println();
        System.out.print("Wybierz opcję: ");
    }

    private static void printAdminMenuChoice(){
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        switch (userChoice) {
            case 1 -> ViewTasks.start();
            case 2 -> {
                System.out.println("Dodawanie tasków niedostępne. Powrót do menu...");
                printCompletedAdminMenu();
            }
            case 3 -> {
                System.out.println("Wyświetlanie urlopów niedostępne. Powrót do menu...");
                printCompletedAdminMenu();
            }
            case 4 -> {
                System.out.println("Wprowadźanie urlopów niedostępne. Powrót do menu...");
                printCompletedAdminMenu();
            }
            case 5 -> {
                System.out.println("Wyswietlanie użytkowników niedostpępne. Powrót do menu...");
                printCompletedAdminMenu();
            }
            case 6 -> {
                System.out.println("Dodawanie roli użytkownikom niedostępne. Powrót do menu...");
                printCompletedAdminMenu();
            }
            case 7 -> {
                System.out.println("Blokowanie użytkowników niedostępne. Powrót do menu...");
                printCompletedAdminMenu();
            }
            case 8 -> {
                System.out.println("Wyświetlanie raportów niedostępne. Powrót do menu...");
                printCompletedAdminMenu();
            }
            case 9 -> {
                Login.logOutUser();
                MainMenu.printCompletedMenu();
            }
            default -> {
                System.out.println("Wprowadź prawidłową wartość");
                printCompletedAdminMenu();
            }
        }
    }

    public static void printCompletedAdminMenu(){
        displayAdminMenu();
        printAdminMenuChoice();
    }
}
