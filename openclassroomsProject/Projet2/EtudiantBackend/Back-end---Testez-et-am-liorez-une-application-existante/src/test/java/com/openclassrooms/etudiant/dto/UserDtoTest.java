package com.openclassrooms.etudiant.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserDtoTest {

    @Test
    void testUserDTOAllArgsConstructor() {
        UserDTO dto = new UserDTO("john", "John", "Doe");

        assertEquals("john", dto.getLogin());
        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
    }

    @Test
    void testUserDTO() {
        UserDTO dto = new UserDTO();
        dto.setLogin("jane");
        dto.setFirstName("Jane");
        dto.setLastName("Smith");

        assertEquals("jane", dto.getLogin());
        assertEquals("Jane", dto.getFirstName());
        assertEquals("Smith", dto.getLastName());
    }

    @Test
    void testUserDTOEqualsAndHashCode() {
        UserDTO dto1 = new UserDTO("john", "John", "Doe");
        UserDTO dto2 = new UserDTO("john", "John", "Doe");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testUserDTONotEquals() {
        UserDTO dto1 = new UserDTO("john", "John", "Doe");
        UserDTO dto2 = new UserDTO("jane", "Jane", "Smith");

        assertNotEquals(dto1, dto2);
        assertNotEquals(dto1, null);
        assertNotEquals(dto1, new Object());
    }

    @Test
    void testUserDTOToString() {
        UserDTO dto = new UserDTO("john", "John", "Doe");

        String toString = dto.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("john"));
        assertTrue(toString.contains("John"));
    }
}