package com.spring_boilerplate.Boilerplate.user;

import com.spring_boilerplate.Boilerplate.controller.user.UserController;
import com.spring_boilerplate.Boilerplate.dto.UserDTO;
import com.spring_boilerplate.Boilerplate.entity.User;
import com.spring_boilerplate.Boilerplate.exception.CustomException;
import com.spring_boilerplate.Boilerplate.service.UserService;
import com.spring_boilerplate.Boilerplate.util.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_Success() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setName("John Doe");
        userDTO.setEmail("johndoe@example.com");
        userDTO.setPhone("1234567890");
        userDTO.setPassword("password123");

        User mockUser = new User();
        mockUser.setName("John Doe");
        mockUser.setEmail("johndoe@example.com");
        mockUser.setPhone("1234567890");
        mockUser.setPassword("password123");

        when(userService.createUser(userDTO)).thenReturn(mockUser);

        // Act
        ApiResponse<User> response = userController.createUser(userDTO);
        System.out.println("Response <> " + response);

        // Assert
        assertNotNull(response);
        assertEquals(201, response.getStatusCode());
        assertTrue(response.isSuccess());
        assertEquals("User created successfully", response.getMessage());
        assertNotNull(response.getData());
        assertEquals("John Doe", response.getData().getName());
        assertEquals("johndoe@example.com", response.getData().getEmail());

         verify(userService, times(1)).createUser(userDTO);
    }

    @Test
    void testCreateUser_EmailAlreadyInUse() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Jane Doe");
        userDTO.setEmail("janedoe@example.com");
        userDTO.setPhone("1234567890");
        userDTO.setPassword("password123");

        when(userService.createUser(userDTO))
                .thenThrow(new CustomException(400, "Email already in use", null));

        // Act & Assert
        CustomException exception = assertThrows(CustomException.class, () -> {
            userController.createUser(userDTO);
        });

        System.out.println("Exception <> " + exception);

        assertNotNull(exception);
        assertEquals(400, exception.getStatusCode());
        assertEquals("Email already in use", exception.getMessage());
         verify(userService, times(1)).createUser(userDTO);
    }
}
