package com.openclassrooms.etudiant.configuration.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilsTest {

    private JwtUtils jwtUtils;

    @BeforeEach
    void setUp() {
        jwtUtils = new JwtUtils();
        ReflectionTestUtils.setField(jwtUtils, "secretKey", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+/*");
        ReflectionTestUtils.setField(jwtUtils, "expirationMs", 1000L);
        jwtUtils.init();
    }

    @Test
    void testGenerateToken() {
        String token = jwtUtils.generateToken("John");
        assertNotNull(token);
    }

    @Test
    void testExtractUsername() {
        String token = jwtUtils.generateToken("John");
        assertEquals("John", jwtUtils.extractUsername(token));
    }

    @Test
    void testValidateToken() {
        String token = jwtUtils.generateToken("John");
        assertTrue(jwtUtils.validateToken(token, "John"));
    }

    @Test
    void testTokenExpiration() throws InterruptedException {
        String token = jwtUtils.generateToken("John");
        Thread.sleep(1100); // attendre que le token expire
        assertFalse(jwtUtils.validateToken(token, "John"));
    }
}
