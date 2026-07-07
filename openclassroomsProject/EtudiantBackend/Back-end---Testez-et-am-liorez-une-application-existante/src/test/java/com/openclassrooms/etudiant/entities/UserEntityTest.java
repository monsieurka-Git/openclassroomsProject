package com.openclassrooms.etudiant.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserEntityTest {

    @Test
    void testUserEntity() {
        User user = new User();
        user.setLogin("John");
        user.setPassword("pass");
        assertEquals("John", user.getLogin());
        assertEquals("pass", user.getPassword());
    }
}
