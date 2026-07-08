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

    @Test
    void testLoginRequestDTOEqualsAndHashCode() {
        LoginRequestDTO dto1 = new LoginRequestDTO();
        dto1.setLogin("john");
        dto1.setPassword("pass");

        LoginRequestDTO dto2 = new LoginRequestDTO();
        dto2.setLogin("john");
        dto2.setPassword("pass");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testLoginRequestDTONotEquals() {
        LoginRequestDTO dto1 = new LoginRequestDTO();
        dto1.setLogin("user1");

        LoginRequestDTO dto2 = new LoginRequestDTO();
        dto2.setLogin("user2");

        assertNotEquals(dto1, dto2);
    }

    @Test
    void testLoginRequestDTOToString() {
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setLogin("john");
        String toString = dto.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("john"));
    }
}