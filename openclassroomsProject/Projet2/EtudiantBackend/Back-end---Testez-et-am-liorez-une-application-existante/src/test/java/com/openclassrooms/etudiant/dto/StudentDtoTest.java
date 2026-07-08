package com.openclassrooms.etudiant.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentDtoTest {

    @Test
    void testStudentDTO() {
        StudentDTO dto = new StudentDTO();
        dto.setId(1L);
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setEmail("john.doe@example.com");

        assertEquals(1L, dto.getId());
        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
        assertEquals("john.doe@example.com", dto.getEmail());
    }

    @Test
    void testStudentDTOEquals() {
        StudentDTO dto1 = new StudentDTO();
        dto1.setId(1L);
        dto1.setFirstName("John");
        dto1.setLastName("Doe");
        dto1.setEmail("john.doe@example.com");

        StudentDTO dto2 = new StudentDTO();
        dto2.setId(1L);
        dto2.setFirstName("John");
        dto2.setLastName("Doe");
        dto2.setEmail("john.doe@example.com");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testStudentDTOToString() {
        StudentDTO dto = new StudentDTO();
        dto.setId(1L);
        dto.setFirstName("John");

        String toString = dto.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("John"));
    }

    @Test
    void testStudentDTONotEquals() {
        StudentDTO dto1 = new StudentDTO();
        dto1.setId(1L);

        StudentDTO dto2 = new StudentDTO();
        dto2.setId(2L);

        assertNotEquals(dto1, dto2);
        assertNotEquals(dto1, null);
        assertNotEquals(dto1, new Object());
    }

}