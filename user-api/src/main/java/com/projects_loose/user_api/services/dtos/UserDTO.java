package com.projects_loose.user_api.services.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    @NotBlank(message = "Name is mandatory!")
    private String name;
    @NotBlank(message = "CPF is mandatory!")
    private String cpf;
    @NotBlank(message = "Email is mandatory!")
    @Email
    private String email;
    private String address;
    private String phoneNumber;
    private LocalDateTime registrationDate;
}
