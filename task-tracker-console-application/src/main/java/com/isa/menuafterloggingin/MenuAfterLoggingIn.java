package com.isa.menuafterloggingin;

import java.util.Scanner;

import static com.isa.mainmenu.MainMenu.displayMainMenu;
import static com.isa.mainmenu.MainMenu.printUserMainMenuChoice;

public class MenuAfterLoggingIn {
    public static void displayMenuAfterLoggingIn() {
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

    public static void printUserMenuChoiceAfterLoggingIn(){
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        switch (userChoice) {
            case 1 -> System.out.println("Wyswietlanie tasków niedostępne");
            case 2 -> System.out.println("Dodawanie tasków niedostępne");
            case 3 -> System.out.println("Wyświetlanie urlopów niedostępne");
            case 4 -> System.out.println("Wprowadźanie urlopów niedostępne");
            case 5 -> {
                displayMainMenu();
                printUserMainMenuChoice();
            }
            default -> {
                System.out.println("Wprowadź prawidłową wartość");
                displayMenuAfterLoggingIn();
                printUserMenuChoiceAfterLoggingIn();
            }
        }
    }

    public static void printCompletedMenuAfterLoggingIn(){
        displayMenuAfterLoggingIn();
        printUserMenuChoiceAfterLoggingIn();
    }
}