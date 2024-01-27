package com.abel.eventbookingservice.services;

import com.abel.eventbookingservice.dto.RegisterDto;
import com.abel.eventbookingservice.entities.Credentials;

public interface AuthService {
    String register(RegisterDto registerDto);

    String login(Credentials loginDto);
}
