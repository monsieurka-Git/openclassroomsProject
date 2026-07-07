package com.openclassrooms.etudiant.configuration.security;

import com.openclassrooms.etudiant.service.JwtService;
import com.openclassrooms.etudiant.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;

public class JwtAuthenticationFilterTest {

    @Test
    void testDoFilterInternalWithValidToken() throws Exception {

        JwtService jwtService = Mockito.mock(JwtService.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtService, userRepository);

        String token = "Bearer faketoken";

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        FilterChain chain = Mockito.mock(FilterChain.class);

        Mockito.when(request.getHeader("Authorization")).thenReturn(token);
        Mockito.when(jwtService.extractLogin("faketoken")).thenReturn("John");
        Mockito.when(userRepository.findByLogin("John")).thenReturn(java.util.Optional.empty());
        Mockito.when(jwtService.isTokenValid("faketoken", null)).thenReturn(true);

        filter.doFilterInternal(request, response, chain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}
