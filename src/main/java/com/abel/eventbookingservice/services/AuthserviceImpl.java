package com.abel.eventbookingservice.services;

import com.abel.eventbookingservice.dto.RegisterDto;
import com.abel.eventbookingservice.entities.Credentials;
import com.abel.eventbookingservice.entities.Role;
import com.abel.eventbookingservice.entities.User;
import com.abel.eventbookingservice.exceptions.EventAPIException;
import com.abel.eventbookingservice.repos.RoleRepository;
import com.abel.eventbookingservice.repos.UserRepository;
import com.abel.eventbookingservice.security.JwtTokenProvider;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthserviceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDto registerDto) {

        // check name already exists in database
        if(userRepository.existsByName(registerDto.getName())){
            throw new EventAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        // check email is already exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new EventAPIException(HttpStatus.BAD_REQUEST, "Email already exists!.");
        }

                User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");//ROLE_USER ?
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);

        return "User Registered Successfully!.";

    }

    @Override
    public String login(Credentials loginDto) {

                //authenticate user cred
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()
        ));
                //set to security context
                SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;

    }

}
