package com.isa.MainMenu;

import java.util.Scanner;

public class MainMenu {
    public static void displayMainMenu() {
        System.out.println("=====================================");
        System.out.println("             MENU GŁÓWNE");
        System.out.println("=====================================");
        System.out.println("Opcje:");
        System.out.println("1. Rejestracja");
        System.out.println("2. Logowanie");
        System.out.println("3. Zaloguj się jako administrator");
        System.out.println("4. Zakończenie programu");
        System.out.println();
        System.out.print("Wybierz opcję: ");
    }

    public static void printUserMainMenuChoice(){
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        switch (userChoice) {
            case 1 -> System.out.println("Rejestracja niedostępna");
            case 2 -> System.out.println("logowanie niedostępne");
            case 3 -> System.out.println("logowanie jako administrator niedostępne");
            case 4 -> System.exit(0);
            default -> {
                System.out.println("Wprowadź prawidłową wartość");
                displayMainMenu();
                printUserMainMenuChoice();
            }
        }
    }
}