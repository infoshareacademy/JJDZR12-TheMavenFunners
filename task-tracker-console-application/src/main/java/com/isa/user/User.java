package com.isa.user;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String login;
    private String password;
    private String email;
    private boolean isActive;
    private String firstName;
    private String lastName;

    public User(String login, String password, String email, boolean isActive) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.isActive = isActive;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public static List<User> getTestUsers() {
        List<User> users = new ArrayList<>();

        User u1 = new User("agata.kowalska", "Zaq123!@1", "agata.kowalska@omail.com", true);
        User u2 = new User("monika.nowak", "Zaq123!@2", "monika.nowak@omail.com", true);
        User u3 = new User("tomasz.bieraj", "Zaq123!@3", "tomasz.bieraj@omail.com", true);
        User u4 = new User("aleksandra.chmurska", "Zaq123!@4", "aleksandra.chmurska@omail.com", false);
        User u5 = new User("krzysztof.kwiatkowski", "Zaq123!@5", "krzysztof.kwiatkowski@omail.com", true);

        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);

        u1.setFirstName("Agata");
        u1.setLastName("Kowalska");

        return users;
    }
}
