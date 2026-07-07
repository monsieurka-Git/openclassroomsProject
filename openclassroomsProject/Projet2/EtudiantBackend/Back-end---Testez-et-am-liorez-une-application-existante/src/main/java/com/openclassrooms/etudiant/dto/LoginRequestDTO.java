package com.openclassrooms.etudiant.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @JsonAlias({"username", "email"})
    private String login;
    private String password;

}
