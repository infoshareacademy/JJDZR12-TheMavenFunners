package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.Task;
import com.isa.tasktrackerwebapp.model.User;
import com.isa.tasktrackerwebapp.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private LoginService loginService;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnSortedAndFilteredTasks() {
        // Given
        String sortBy = "descByEndDate";
        String searchTaskName = "Task";
        String filterActive = "true";

        List<Task> mockTasks = getMockTasks();

        when(taskRepository.findAllByOrderByTaskEndDesc()).thenReturn(mockTasks);

        // When
        List<Task> result = taskService.getSortedAndFilteredTasks(sortBy, searchTaskName, filterActive);

        // Then
        assertEquals(3, result.size());
        assertEquals("Task 1", result.get(0).getTaskName());
        assertEquals("Task 2", result.get(1).getTaskName());
        assertEquals("Task 4", result.get(2).getTaskName());
    }

    private static List<Task> getMockTasks() {
        List<Task> mockTasks = Arrays.asList(
                new Task("Task 1", LocalDate.now(), LocalDate.now().plusDays(4), "description1", new User(), true),
                new Task("Task 2", LocalDate.now(), LocalDate.now().plusDays(3), "description2", new User(), true),
                new Task("Task 3", LocalDate.now(), LocalDate.now().plusDays(2), "description3", new User(), false),
                new Task("Task 4", LocalDate.now(), LocalDate.now().plusDays(1), "description4", new User(), true)
        );
        return mockTasks;
    }



    @Test
    void shouldReturnFalseForTaskEndInvalidWhenDateValid() {
        // Given
        Task task = new Task("New Task", LocalDate.now(), LocalDate.now().plusDays(1), "description", new User(), true);

        // When
        boolean result = taskService.taskEndInvalid(task);

        // Then
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueForTaskEndInvalidWhenDateInvalid() {
        // Given
        Task task = new Task("New Task", LocalDate.now(), LocalDate.now().minusDays(1), "description", new User(), true);

        // When
        boolean result = taskService.taskEndInvalid(task);

        // Then
        assertTrue(result);
    }
}
