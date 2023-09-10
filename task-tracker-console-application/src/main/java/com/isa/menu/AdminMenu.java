package com.isa.menu;

import java.util.Scanner;

import static com.isa.menu.MainMenu.printCompletedMenu;

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
        System.out.println("5. Wyloguj");
        System.out.println("6. Wyświetl użytkowników");
        System.out.println("7. Dodaj role użytkownikom");
        System.out.println("8. Zablokuj użytkownika");
        System.out.println("9. Wyświetl raporty");
        System.out.println();
        System.out.print("Wybierz opcję: ");
    }

    private static void printAdminMenuChoice(){
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        switch (userChoice) {
            case 1 -> System.out.println("Wyswietlanie tasków niedostępne");
            case 2 -> System.out.println("Dodawanie tasków niedostępne");
            case 3 -> System.out.println("Wyświetlanie urlopów niedostępne");
            case 4 -> System.out.println("Wprowadźanie urlopów niedostępne");
            case 5 -> {
                MainMenu.printCompletedMenu();
            }
            case 6 -> System.out.println("Wyswietlanie użytkowników niedostpępne");
            case 7 -> System.out.println("Dodawanie roli użytkownikom niedostępne");
            case 8 -> System.out.println("Blokowanie użytkowników niedostępne");
            case 9 -> System.out.println("Wyświetlanie raportów niedostępne");
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
