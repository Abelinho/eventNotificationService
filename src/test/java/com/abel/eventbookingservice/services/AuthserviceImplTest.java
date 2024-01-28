package com.abel.eventbookingservice.services;

import com.abel.eventbookingservice.dto.RegisterDto;
import com.abel.eventbookingservice.entities.Credentials;
import com.abel.eventbookingservice.entities.Role;
import com.abel.eventbookingservice.entities.User;
import com.abel.eventbookingservice.repos.RoleRepository;
import com.abel.eventbookingservice.repos.UserRepository;
import com.abel.eventbookingservice.security.JwtTokenProvider;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;


class AuthserviceImplTest {

//    @Mock
//    private UserRepository userRepository; 
	
	UserRepository userRepository = Mockito.mock(UserRepository.class);

	RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
	
	PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
	
	AuthenticationManager authenticationManager = Mockito.mock(AuthenticationManager.class);
	
	JwtTokenProvider jwtTokenProvider = Mockito.mock(JwtTokenProvider.class);
	
	
	AuthserviceImpl authserviceImpl = new AuthserviceImpl(userRepository, roleRepository, passwordEncoder, authenticationManager, jwtTokenProvider);// = Mockito.mock(AuthserviceImpl.class);	
	
	static User user;
	
	static Role role;

	static RegisterDto registerDto;
	
	static Authentication authentication;
	
	static Credentials loginDto;

    @BeforeAll
  static  void setUp() {
    	
    	 user = new User();
    	
    	user.setName("testname");
    	user.setEmail("testEmail");
    	user.setPassword("testPassword");
    	
    	 role = new Role();
    	
    	role.setName("testRole");
    	
    	registerDto = new RegisterDto();
    	
      registerDto.setName("newUser");
      registerDto.setEmail("newuser@example.com");
      registerDto.setPassword("password123");
      
   // Arrange
       loginDto = Credentials.builder()
      .email("user@example.com")
      .password("password123")
      .build();
      
      
      UserRepository userRepository = Mockito.mock(UserRepository.class);

  	RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
  	
  	PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
  	
  	 authentication = Mockito.mock(Authentication.class);

  	AuthserviceImpl authserviceImpl = Mockito.mock(AuthserviceImpl.class);
      
      when(userRepository.existsByName("newUser")).thenReturn(false);
      when(userRepository.existsByEmail("newuser@example.com")).thenReturn(false);
    	
    }

    @Test
    void register_SuccessfulRegistration_ReturnsSuccessMessage() {      

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        when(roleRepository.findByName("ROLE_USER")).thenReturn(userRole);

        when(passwordEncoder.encode("password123")).thenReturn("$2a$10$stnLYSo6pP4Pi4vDyCQf6.u0u0uI8g014fpPoUdbA7PU11l2sYxhO");

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
    void login_SuccessfulAuthentication_ReturnsToken() {      

        when(authenticationManager.authenticate((Authentication) any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

        String generatedToken = "generatedToken";
        when(jwtTokenProvider.generateToken(authentication)).thenReturn(generatedToken);

        // Act
        String result = authserviceImpl.login(loginDto);

        // Assert
        assertEquals(generatedToken, result);

        // Verify
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtTokenProvider).generateToken(authentication);
        verifyNoMoreInteractions(authenticationManager, jwtTokenProvider);
    }

    @Test
    void login_AuthenticationFailure_ThrowsException() {
       

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(new RuntimeException("Authentication failed"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> authserviceImpl.login(loginDto));

        // Verify
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verifyNoInteractions(jwtTokenProvider);
    }

}