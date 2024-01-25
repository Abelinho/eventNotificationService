package com.abel.eventbookingservice.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Credentials {

    @Email
    @NotEmpty(message = "provide the email")
    private String email;

    @Min(value = 8)
    @NotEmpty(message = "provide the password")
    private String password;

}
