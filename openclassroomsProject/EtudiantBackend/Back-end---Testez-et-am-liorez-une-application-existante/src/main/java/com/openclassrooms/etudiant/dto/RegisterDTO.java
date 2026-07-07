package com.openclassrooms.etudiant.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @JsonAlias("username")
    @NotBlank
    private String login;
    @NotBlank
    private String password;

}
