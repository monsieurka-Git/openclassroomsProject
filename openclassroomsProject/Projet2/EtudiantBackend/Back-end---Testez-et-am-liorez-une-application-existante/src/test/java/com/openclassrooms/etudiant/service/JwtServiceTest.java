package com.openclassrooms.etudiant.service;

import com.openclassrooms.etudiant.entities.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @Test
    void testGenerateToken() {
        User user = new User();
        user.setLogin("john");
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        String token = jwtService.generateToken(user);

        assertThat(token).isNotNull();
        assertThat(token).isNotEmpty();
    }

    @Test
    void testExtractLogin() {
        User user = new User();
        user.setLogin("john");
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        String token = jwtService.generateToken(user);
        String extractedLogin = jwtService.extractLogin(token);

        assertThat(extractedLogin).isEqualTo("john");
    }

    @Test
    void testIsTokenValid_shouldReturnTrue() {
        User user = new User();
        user.setLogin("john");
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        String token = jwtService.generateToken(user);
        boolean valid = jwtService.isTokenValid(token, user);

        assertThat(valid).isTrue();
    }

    @Test
    void testIsTokenValid_differentUser_shouldReturnFalse() {
        User user = new User();
        user.setLogin("john");
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        User differentUser = new User();
        differentUser.setLogin("jane");

        String token = jwtService.generateToken(user);
        boolean valid = jwtService.isTokenValid(token, differentUser);

        assertThat(valid).isFalse();
    }
}