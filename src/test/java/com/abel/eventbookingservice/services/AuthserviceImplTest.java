package com.abel.eventbookingservice.services;

import com.abel.eventbookingservice.dto.RegisterDto;
import com.abel.eventbookingservice.entities.Role;
import com.abel.eventbookingservice.entities.User;
import com.abel.eventbookingservice.repos.RoleRepository;
import com.abel.eventbookingservice.repos.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

class AuthserviceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthserviceImpl authserviceImpl;

    @BeforeEach
    void setUp() {
    }

    @Test
    void register_SuccessfulRegistration_ReturnsSuccessMessage() {

        // Arrange
        RegisterDto registerDto = new RegisterDto();
        registerDto.setName("newUser");
        registerDto.setEmail("newuser@example.com");
        registerDto.setPassword("password123");

        when(userRepository.existsByName("newUser")).thenReturn(false);
        when(userRepository.existsByEmail("newuser@example.com")).thenReturn(false);

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        when(roleRepository.findByName("ROLE_USER")).thenReturn(userRole);

        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");

        // Act
        String result = authserviceImpl.register(registerDto);

        // Assert
        assertEquals("User Registered Successfully!.", result);

        // Verify
        verify(userRepository).existsByName("newUser");
        verify(userRepository).existsByEmail("newuser@example.com");
        verify(roleRepository).findByName("ROLE_USER");
        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(any(User.class));

    }

    @Test
    void login() {
    }
}