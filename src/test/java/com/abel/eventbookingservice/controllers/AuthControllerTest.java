package com.abel.eventbookingservice.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.abel.eventbookingservice.dto.RegisterDto;
import com.abel.eventbookingservice.entities.Credentials;
import com.abel.eventbookingservice.repos.RoleRepository;
import com.abel.eventbookingservice.repos.UserRepository;
import com.abel.eventbookingservice.security.JwtTokenProvider;
import com.abel.eventbookingservice.services.AuthserviceImpl;

//@WebMvcTest(AuthController.class) 
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
	
	  @Autowired
	  private MockMvc mockMvc;

//	  @MockBean
//	  private AuthService authService;
	  
	    @MockBean
	    private  UserRepository userRepository;
	    @MockBean
	    private RoleRepository roleRepository;
	    @MockBean
	    private PasswordEncoder passwordEncoder;
	    @MockBean
	    private AuthenticationManager authenticationManager;
	    @MockBean
	    private JwtTokenProvider jwtTokenProvider;
	    
	    AuthserviceImpl authServiceImpl = Mockito.mock(AuthserviceImpl.class);
	  
//	  @MockBean  
//	  private AuthserviceImpl authServiceImpl = new AuthserviceImpl(userRepository,roleRepository,passwordEncoder
//			  ,authenticationManager,jwtTokenProvider);
	  
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testRegister() throws Exception {
		// Arrange
        String registerDtoJson = "{\"name\": \"user123\", \"email\": \"user123@example.com\", \"password\": \"password123\"}";

        when(authServiceImpl.register(any(RegisterDto.class))).thenReturn("User Registered Successfully!.");
        
        // Act & Assert
        //mockMvc.perform(post("/user")
        mockMvc.perform(post("/api/v1/user") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(registerDtoJson))
                .andExpect(status().isCreated());
    }
	

	@Test
	void testLogin() throws Exception {
		
		// Arrange
        String loginDtoJson = "{\"email\": \"user123@example.com\", \"password\": \"password123\"}";
        String mockToken = "mockAccessToken";

        // Mock behavior of the authService
        when(authServiceImpl.login(any(Credentials.class))).thenReturn(mockToken);

        // Act & Assert
        mockMvc.perform(post("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginDtoJson))
                .andExpect(status().isOk());
              //  .andExpect(jsonPath("$.accessToken").value(mockToken));
    }
		
	}


