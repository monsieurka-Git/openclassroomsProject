package com.openclassrooms.etudiant.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserEntityTest {

    @Test
    void testUserEntity() {
        User user = new User();
        user.setLogin("John");
        user.setPassword("pass");
        user.setFirstName("John");
        user.setLastName("Doe");
        assertEquals("John", user.getLogin());
        assertEquals("pass", user.getPassword());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
    }

    @Test
    void testUserDetailsMethods() {
        User user = new User();
        user.setLogin("John");
        user.setPassword("pass");

        assertEquals("John", user.getUsername());
        assertEquals("pass", user.getPassword());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
        assertNotNull(user.getAuthorities());
        assertTrue(user.getAuthorities().isEmpty());
    }

    @Test
    void testUserEntityAllArgsConstructor() {
        User user = new User(1L, "John", "Doe", "john", "pass", null, null);
        assertEquals(1L, user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("john", user.getLogin());
        assertEquals("pass", user.getPassword());
    }

    @Test
    void testUserEntityEqualsAndHashCode() {
        User user1 = new User(1L, "John", "Doe", "john", "pass", null, null);
        User user2 = new User(1L, "John", "Doe", "john", "pass", null, null);

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testUserEntityToString() {
        User user = new User();
        user.setLogin("john");
        user.setFirstName("John");

        String toString = user.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("john"));
    }

    @Test
    void testUserEntityNotEquals() {
        User user1 = new User();
        user1.setId(1L);

        User user2 = new User();
        user2.setId(2L);

        assertNotEquals(user1, user2);
        assertNotEquals(user1, null);
        assertNotEquals(user1, new Object());
    }
}