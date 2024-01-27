package com.abel.eventbookingservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @NotEmpty(message = "provide the user name")
    @Length(max = 100, message = "user name cannot be more that 100 characters")
    private String name;
    @NotEmpty(message = "provide the email")
    @Email(message = "incorrect email format")
    private String email;

    @NotEmpty(message = "provide the password")
    @Length(min = 8, message = "password must be more than 8 characters")
    private String password;
}
