package com.openclassrooms.etudiant.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserDtoTest {

    @Test
    void testUserDTO() {
        UserDTO dto = new UserDTO("John", "John", "Doe");

        assertEquals("John", dto.getLogin());
        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
    }
}
