package com.isa.menu;

import com.isa.registration.Registration;
import com.isa.user.Login;

import java.util.Scanner;

public class MainMenu {
    private static void displayMainMenu() {
        System.out.println("=====================================");
        System.out.println("             MENU GŁÓWNE");
        System.out.println("=====================================");
        System.out.println("Opcje:");
        System.out.println("1. Rejestracja");
        System.out.println("2. Logowanie");
        System.out.println("3. Zakończenie programu");
        System.out.println();
        System.out.print("Wybierz opcję: ");
    }

    private static void printUserMainMenuChoice(){
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        switch (userChoice) {
            case 1 -> Registration.getUserInput();
            case 2 -> Login.start();
            case 3 -> System.exit(0);
            default -> {
                System.out.println("Wprowadź prawidłową wartość");
                displayMainMenu();
                printUserMainMenuChoice();
            }
        }
    }

    public static void printCompletedMenu(){
        displayMainMenu();
        printUserMainMenuChoice();
    }
}