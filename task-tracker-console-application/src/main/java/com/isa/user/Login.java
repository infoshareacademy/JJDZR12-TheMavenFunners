package com.isa.user;

import com.isa.menu.MainMenu;
import com.isa.menu.MenuAfterLoggingIn;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Login {
    private static User loggedInUser;
    private static final List<User> users = User.getTestUsers();
    private static String inputLogin;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void logOutUser() {
        loggedInUser = null;
    }

    private static void displayLoginMessage() {
        System.out.println("=====================================");
        System.out.println("             ZALOGUJ SIĘ");
        System.out.println("=====================================");
        System.out.println("Podaj dane logowania lub wybierz \"0\", żeby powrócić do menu głównego.");
    }

    private static void logInUser(Scanner scanner) {
        System.out.print("Podaj login: ");
        validateLogin(scanner, 0);
    }

    private static void validateLogin(Scanner scanner, int incorrectLoginInputCount) {
        inputLogin = scanner.nextLine();
        if (inputLogin.equals("0")) {
            goToMainMenu();
        } else if (chosenUser().isEmpty()) {
            incorrectLoginInputCount++;
            System.out.println("Podany login nie istnieje.");
            if (incorrectLoginInputCount > 1) {
                System.out.println("Pamiętaj, że w każdej chwili możesz wrócić do menu głównego poprzez wybór \"0\".");
            }

            System.out.print("Podaj login ponownie: ");
            validateLogin(scanner, incorrectLoginInputCount);
        } else {
            System.out.print("Podaj hasło: ");
            validatePassword(scanner, 0);
        }
    }

    private static Optional<User> chosenUser() {
        return users.stream()
                .filter(user -> user.getLogin().equals(inputLogin))
                .findFirst();
    }

    private static void validatePassword(Scanner scanner, int incorrectPasswordInputCount) {
        String inputPassword = scanner.nextLine();
        if (inputPassword.equals("0")) {
            goToMainMenu();
        } else if (!isCorrectPassword(inputPassword)) {
            checkForIncorrectPassword(scanner, incorrectPasswordInputCount);
        } else {
            loggedInUser = chosenUser().get();
            printWelcomeMessage();
            if (loggedInUser.isActive()) {
                MenuAfterLoggingIn.printCompletedMenuAfterLoggingIn();
            } else {
                askForBlockedUserChoice(scanner);
            }
        }
    }

    private static void checkForIncorrectPassword(Scanner scanner, int incorrectPasswordInputCount) {
        incorrectPasswordInputCount++;
        System.out.println("Hasło niepoprawne.");
        if (incorrectPasswordInputCount > 1 && incorrectPasswordInputCount < 5) {
            System.out.println("Pamiętaj, że w każdej chwili możesz wrócić do menu głównego poprzez wybór \"0\".");
            if (incorrectPasswordInputCount == 4) {
                System.out.println("UWAGA: Przy kolejnej nieprawidłowej próbie zalogowania się twoje konto zostanie zablokowane.");
            }
        }
        if (incorrectPasswordInputCount != 5) {
            System.out.print("Podaj hasło ponownie: ");
            validatePassword(scanner, incorrectPasswordInputCount);
        } else {
            chosenUser().ifPresent(user -> user.setActive(false));
            System.out.println("Wykryto 5 nieprawidłowych prób zalogowania się. Twoje konto zostało zablokowane.");
            askForBlockedUserChoice(scanner);
        }
    }

    private static boolean isCorrectPassword(String inputPassword) {
        return chosenUser()
                .map(user -> inputPassword.equals(user.getPassword()))
                .orElse(false);
    }

    private static void printWelcomeMessage() {
        if (loggedInUser.isActive()) {
            System.out.print("Zalogowano poprawnie. Witaj, ");
            System.out.print(loggedInUser.getFirstName() != null && !loggedInUser.getFirstName().isBlank() ? loggedInUser.getFirstName() : loggedInUser.getLogin());
            System.out.println("!");
        } else {
            System.out.println("Twoje konto jest zablokowane. Jeśli potrzebujesz dostępu, skontaktuj się z administratorem.");
        }
    }

    private static void askForBlockedUserChoice(Scanner scanner) {
        System.out.println("Wybierz \"0\", żeby wrócić do menu głównegu, lub \"x\", żeby zakończyć program.");
        String blockedUserInput = scanner.nextLine();
        if (blockedUserInput.equals("0")) {
            logOutUser();
            goToMainMenu();
        } else if (blockedUserInput.equals("x")) {
            logOutUser();
            System.exit(0);
        } else {
            System.out.println("Wprowadź prawidłową wartość.");
            askForBlockedUserChoice(scanner);
        }
    }

    private static void goToMainMenu() {
        MainMenu.displayMainMenu();
        MainMenu.printUserMainMenuChoice();
    }

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        displayLoginMessage();
        logInUser(scanner);
        scanner.close();
    }
}
