package com.saud.ecommerce.service;

import com.saud.ecommerce.exception.ResourceNotFoundException;
import com.saud.ecommerce.model.User;
import com.saud.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        List<User> users = Arrays.asList(
                new User(1L, "john", "password", "john@example.com"),
                new User(2L, "jane", "password", "jane@example.com")
        );
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();
        assertEquals(2, result.size());
    }

    @Test
    void getUserById_NotFound() {
        // Arrange: Set up the repository to return an empty Optional
        Long nonExistentUserId = 1L;
        when(userRepository.findById(nonExistentUserId)).thenReturn(Optional.empty());

        // Act & Assert: Verify that the ResourceNotFoundException is thrown
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.getUserById(nonExistentUserId);
        });

        // Verify the exception message
        assertEquals("User not found", exception.getMessage());

        // Verify that the repository was called with the correct parameter
        verify(userRepository, times(1)).findById(nonExistentUserId);
    }

    @Test
    void getAllUsersTwo() {
        // Create mock User objects
        User user1 = mock(User.class);
        User user2 = mock(User.class);
        // Set up the mock behavior
        when(user1.getId()).thenReturn(1L);
        when(user1.getUsername()).thenReturn("john");
        when(user1.getPassword()).thenReturn("password");
        when(user1.getEmail()).thenReturn("john@example.com");

        when(user2.getId()).thenReturn(2L);
        when(user2.getUsername()).thenReturn("jane");
        when(user2.getPassword()).thenReturn("password");
        when(user2.getEmail()).thenReturn("jane@example.com");

        // Create a list of mock Users
        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();
        assertEquals(2, result.size());
    }

    @Test
    void getUserById() {
        User user = new User(1L, "john", "password", "john@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);
        assertEquals("john", result.getUsername());
    }

    @Test
    void saveUserSuccessfully() {
        User user = new User(1L, "john", "password", "john@example.com");
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.saveUser(user);
        assertEquals("john", result.getUsername());
    }

    @Test
    void deleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateUserEmail() {
        User user = new User(1L, "john", "password", "john@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.updateUserEmail(1L, "newemail@example.com");
        assertNotNull(result);
        assertEquals("newemail@example.com", result.getEmail());
    }
}
