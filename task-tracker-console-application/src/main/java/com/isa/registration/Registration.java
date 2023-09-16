package com.isa.registration;

import com.isa.data.JsonData;
import com.isa.user.Login;
import com.isa.user.User;

import java.util.List;
import java.util.Scanner;

public class Registration {
    private static final List<User> users = JsonData.getUsers();
    private static String firstName;
    private static String lastName;
    private static String login;
    private static String password;

    private Registration() {
    }

    private static boolean userExists(String login) {
        return users.stream()
                .anyMatch(user -> user.getLogin().equals(login));
    }

    private static boolean isValidPassword(String password) {
        // Tu powinniśmy podać kryteria walidacji hasła, np. minimalna długość, wymagane znaki, etc.
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]+$");

    }

    public static void getUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj imię: ");
        firstName = scanner.nextLine();
        System.out.print("Podaj nazwisko: ");
        lastName = scanner.nextLine();
        System.out.print("podaj login: ");
        login = scanner.nextLine();
        System.out.print("Podaj hasło: ");
        password = scanner.nextLine();

        validateInput();
        registerUser();
    }

    private static void validateInput() {
        try {
            if (firstName.isEmpty() || lastName.isEmpty() || login.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Wszystkie pola są wymagane.");
            }
            if (!isValidPassword(password)) {
                throw new IllegalArgumentException("Niepoprawne hasło. Hasło musi posiadać co najmniej 8 znaków, w tym przynajmniej jedną małą literę, jedną wielką literę oraz znak specjalny.");
            }
            if (userExists(login)) {
                System.out.println("Użytkownik istnieje.");
                System.out.println("Automatycznie przejście do opcji logowania...");
                Login.start();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Błąd: " + e.getMessage());
            System.out.println("Wpisz dane ponownie.");
            getUserInput();
        }

    }

    private static void registerUser() {

        User newUser = new User(login, password, "", true);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        JsonData.saveNewUser(newUser);
        System.out.println("Konto zostało utworzone.");
        System.out.println("Automatycznie przejście do opcji logowania...");
        Login.start();
    }
}


