package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.User;
import com.isa.tasktrackerwebapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LoginValidatorTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoginValidator loginValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnTrueForSearchingForExistingLogin() {
        // Given
        String existingLogin = "existing.login";
        when(userRepository.existsByLogin(existingLogin)).thenReturn(true);

        // When
        boolean loginExists = loginValidator.doesLoginExist(existingLogin);

        // Then
        assertTrue(loginExists);
    }

    @Test
    void shouldReturnFalseForSearchingForNonExistingLogin() {
        // Given
        String nonExistingLogin = "nonexisting.login";
        when(userRepository.existsByLogin(nonExistingLogin)).thenReturn(false);

        // When
        boolean loginExists = loginValidator.doesLoginExist(nonExistingLogin);

        // Then
        assertFalse(loginExists);
    }

    @Test
    void shouldReturnTrueForExistingLoginAndValidPassword() {
        // Given
        String existingLogin = "existing.login";
        String validPassword = "validPassword";

        when(userRepository.existsByLogin(existingLogin)).thenReturn(true);
        when(userRepository.findByLogin(existingLogin)).thenReturn(Optional.of(new User(existingLogin, validPassword, "", true)));

        // When
        boolean isLoginInputValid = loginValidator.isLoginInputValid(existingLogin, validPassword);

        // Then
        assertTrue(isLoginInputValid);
    }

    @Test
    void shouldReturnFalseForNonExistingLogin() {
        // Given
        String nonExistingLogin = "nonexisting.login";
        String somePassword = "somePassword";

        when(userRepository.existsByLogin(nonExistingLogin)).thenReturn(false);
        when(userRepository.findByLogin(nonExistingLogin)).thenReturn(Optional.of(new User(nonExistingLogin, somePassword, "", true)));

        // When
        boolean isLoginInputValid = loginValidator.isLoginInputValid(nonExistingLogin, somePassword);

        // Then
        assertFalse(isLoginInputValid);
    }

    @Test
    void shouldReturnFalseForExistingLoginAndIncorrectPassword() {
        // Given
        String existingLogin = "existing.login";
        String incorrectPassword = "incorrectPassword";

        when(userRepository.existsByLogin(existingLogin)).thenReturn(true);
        when(userRepository.findByLogin(existingLogin)).thenReturn(Optional.of(new User(existingLogin, "validPassword", "", true)));

        // When
        boolean isLoginInputValid = loginValidator.isLoginInputValid(existingLogin, incorrectPassword);

        // Then
        assertFalse(isLoginInputValid);
    }
}