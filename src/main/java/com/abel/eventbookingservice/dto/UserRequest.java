package com.abel.eventbookingservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequest {

    @NotEmpty(message = "Name field is required")
    @Max(value = 100,message = "user name cannot exceed hundred characters")
    private String name;
    @NotEmpty(message = "email field is required")
    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty(message = "password field is required")
    @Min(value = 8,message = "Password must exceed 8 characters")
    private String password;


//    User:
//    type: object
//    required:
//            - name
//      - email
//      - password
//    properties:
//    name:
//    type: string
//    maxLength: 100
//    email:
//    type: string
//    format: email
//    password:
//    type: string
//    minLength: 8
}
