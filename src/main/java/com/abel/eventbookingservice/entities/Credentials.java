package com.abel.eventbookingservice.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
public class Credentials {//serves as login dto?

    @Email(message = "incorrect email format")
    @NotEmpty(message = "provide the email")
    private String email;

    @Length(min = 8,message = "password must be more than 8 characters long")
    @NotEmpty(message = "provide the password")
    private String password;

}
