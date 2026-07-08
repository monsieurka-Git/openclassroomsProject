package com.openclassrooms.etudiant.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterDtoTest {

    @Test
    void testRegisterDTO() {
        RegisterDTO dto = new RegisterDTO();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setLogin("johndoe");
        dto.setPassword("password");

        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
        assertEquals("johndoe", dto.getLogin());
        assertEquals("password", dto.getPassword());
    }

    @Test
    void testRegisterDTOEqualsAndHashCode() {
        RegisterDTO dto1 = new RegisterDTO();
        dto1.setFirstName("John");
        dto1.setLastName("Doe");
        dto1.setLogin("johndoe");
        dto1.setPassword("password");

        RegisterDTO dto2 = new RegisterDTO();
        dto2.setFirstName("John");
        dto2.setLastName("Doe");
        dto2.setLogin("johndoe");
        dto2.setPassword("password");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testRegisterDTONotEquals() {
        RegisterDTO dto1 = new RegisterDTO();
        dto1.setLogin("user1");

        RegisterDTO dto2 = new RegisterDTO();
        dto2.setLogin("user2");

        assertNotEquals(dto1, dto2);
        assertNotEquals(dto1, null);
        assertNotEquals(dto1, new Object());
    }

    @Test
    void testRegisterDTOToString() {
        RegisterDTO dto = new RegisterDTO();
        dto.setFirstName("John");
        dto.setLogin("johndoe");

        String toString = dto.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("John"));
        assertTrue(toString.contains("johndoe"));
    }
}