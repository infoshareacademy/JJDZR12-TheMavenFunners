package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.User;
import com.isa.tasktrackerwebapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class RegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSuccessfullyCreateActiveUser() {
        // Given
        User user = new User("testUser", "password", "test@example.com", false);

        // When
        registrationService.registerNewUser(user);

        // Then
        assertTrue(user.isActive());
        assertNotNull(user);
    }

    @Test
    void shouldReturnTrueForSearchingForExistingEmail() {
        // Given
        String existingEmail = "existing@example.com";
        when(userRepository.existsByEmail(existingEmail)).thenReturn(true);

        // When
        boolean emailExists = registrationService.doesEmailExist(existingEmail);

        // Then
        assertTrue(emailExists);
    }

    @Test
    void shouldReturnFalseForSearchingForNonExistingEmail() {
        // Given
        String nonExistingEmail = "nonexisting@example.com";
        when(userRepository.existsByEmail(nonExistingEmail)).thenReturn(false);

        // When
        boolean emailExists = registrationService.doesEmailExist(nonExistingEmail);

        // Then
        assertFalse(emailExists);
    }
}