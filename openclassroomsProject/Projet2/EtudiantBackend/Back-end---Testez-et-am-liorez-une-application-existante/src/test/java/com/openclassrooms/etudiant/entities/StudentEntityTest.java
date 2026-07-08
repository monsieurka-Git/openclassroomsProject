package com.openclassrooms.etudiant.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentEntityTest {

    @Test
    void testStudentEntityFields() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@example.com");

        assertEquals(1L, student.getId());
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals("john.doe@example.com", student.getEmail());
    }

    @Test
    void testStudentEntityEquals() {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setFirstName("John");

        Student student2 = new Student();
        student2.setId(1L);
        student2.setFirstName("John");

        assertEquals(student1, student2);
        assertEquals(student1.hashCode(), student2.hashCode());
    }

    @Test
    void testStudentEntityNotEquals() {
        Student student1 = new Student();
        student1.setId(1L);

        Student student2 = new Student();
        student2.setId(2L);

        assertNotEquals(student1, student2);
    }

    @Test
    void testStudentEntityToString() {
        Student student = new Student();
        student.setFirstName("John");

        String toString = student.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("John"));
    }
}