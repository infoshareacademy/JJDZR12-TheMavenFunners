package com.isa.Registration;

import com.isa.user.User;

import java.util.ArrayList;
import java.util.List;

public class Registration {
    private List<User> users;
    private Object args;

    public Registration() {
        users = new ArrayList<>();
    }

    public boolean isUserExists(String login) {
        return users.stream()
                .anyMatch(user -> user.getLogin().equals(login));
    }

    public void registerUser(String firstName, String lastName, String login, String password) {
        try {
            if (firstName.isEmpty() || lastName.isEmpty() || login.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Wszystkie pola są wymagane.");
            }

            if (isUserExists(login)) {
                System.out.println("Użytkownik istnieje.");
            } else {
                User newUser = new User(login, password, "", false);
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                users.add(newUser);
                System.out.println("Konto zostało utworzone.");
                System.out.println("Automatycznie przejście do opcji logowania...");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Błąd: " + e.getMessage());

        public static void main(String[] args) {
            Registration registration = new Registration();
            String firstName = "Jan";
            String lastName = "Kowalski";
            String login = "Jan.kowalski";
            String password = "Password123 !";
            registration.registerUser(firstName, lastName, login, password);
        }
    }

