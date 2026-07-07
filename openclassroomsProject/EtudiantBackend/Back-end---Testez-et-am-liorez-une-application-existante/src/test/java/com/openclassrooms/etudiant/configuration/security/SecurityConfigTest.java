package com.openclassrooms.etudiant.configuration.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.openclassrooms.etudiant.repository.UserRepository;
import com.openclassrooms.etudiant.service.JwtService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@org.springframework.test.context.ActiveProfiles("test")
public class SecurityConfigTest {

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityFilterChain securityFilterChain;

    @Test
    void testSecurityFilterChainIsLoaded() {
        assertNotNull(securityFilterChain);
    }
}
