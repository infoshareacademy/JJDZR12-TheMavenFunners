package com.isa.tasktrackerwebapp.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

public class UserDto {
    @NotBlank(message = "Login jest obowiązkowy.")
    private String login;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Hasło musi mieć min. 8 znaków, 1 małą i 1 wielką literę, 1 znak specjalny."
    )
    private String password;

    @NotBlank(message = "Email jest obowiązkowy.")
    @Email(message = "Podany tekst nie jest poprawnym adresem e-mail.")
    private String email;

    private boolean isActive;

    @NotBlank(message = "Imię jest obowiązkowe.")
    private String firstName;

    @NotBlank(message = "Nazwisko jest obowiązkowe.")
    private String lastName;

    public UserDto(String login, String password, String email, boolean isActive, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.isActive = isActive;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserDto() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto user = (UserDto) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return login;
    }
}
