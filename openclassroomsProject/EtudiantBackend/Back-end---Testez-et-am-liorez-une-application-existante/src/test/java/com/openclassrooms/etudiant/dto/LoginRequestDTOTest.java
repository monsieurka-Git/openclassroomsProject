package com.openclassrooms.etudiant.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginRequestDTOTest {

    @Test
    void testLoginRequestDTO() {
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setLogin("John");
        dto.setPassword("pass");
        assertEquals("John", dto.getLogin());
        assertEquals("pass", dto.getPassword());
    }
}
