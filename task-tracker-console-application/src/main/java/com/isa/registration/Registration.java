package com.isa.registration;

import com.isa.user.Login;
import com.isa.user.User;

import java.util.List;
import java.util.Scanner;

public class Registration {
    private static final List<User> users = User.getTestUsers();
    private static String firstName;
    private static String lastName;
    private static String login;
    private static String password;

    public static boolean isUserExists(String login) {
        return users.stream()
                .anyMatch(user -> user.getLogin().equals(login));
    }

    public static boolean isValidPassword(String password) {
        // Tu powinniśmy podać kryteria walidacji hasła, np. minimalna długość, wymagane znaki, etc.
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]+$");

    }

    public static void getUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj imię");
        firstName = scanner.nextLine();
        System.out.println("Podaj nazwisko");
        lastName = scanner.nextLine();
        System.out.println("podaj login");
        login = scanner.nextLine();
        System.out.println("Podaj hasło");
        password = scanner.nextLine();

        validateInput();
        registerUser();
    }

    public static void validateInput() {
        try {
            if (firstName.isEmpty() || lastName.isEmpty() || login.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Wszystkie pola są wymagane.");
            }

            if (!isValidPassword(password)) {
                throw new IllegalArgumentException("Niepoprawne hasło.");
            }
            if (isUserExists(login)) {
                System.out.println("Użytkownik istnieje.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Błąd: " + e.getMessage());
            getUserInput();
        }

    }

    public static void registerUser() {

        User newUser = new User(login, password, "", true);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        users.add(newUser);
        System.out.println("Konto zostało utworzone.");
        System.out.println("Automatycznie przejście do opcji logowania...");
        Login.start();
    }
}


