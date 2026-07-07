package com.openclassrooms.etudiant.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.etudiant.dto.RegisterDTO;
import com.openclassrooms.etudiant.entities.User;
import com.openclassrooms.etudiant.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void fullRegisterAndLoginFlow() throws Exception {

        // ---------------------------
        // 1. REGISTER
        // ---------------------------

        RegisterDTO dto = new RegisterDTO();
        dto.setFirstName("John");
        dto.setLastName("Test");
        dto.setLogin("john");
        dto.setPassword("123456");
        // REGISTER -> 201 CREATED
        mockMvc.perform(post("/api/auth/register")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        // Vérifier que l’utilisateur est bien en base H2
        User user = userRepository.findByLogin("john").orElse(null);
        assertThat(user).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("John");

        // ---------------------------
        // 2. LOGIN -> 200 OK
        // ---------------------------
    
        String loginJson = """
            {
                "login": "john",
                "password": "123456"
            }
            """;

        mockMvc.perform(post("/api/auth/login")
                        .content(loginJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Vérifie que le statut HTTP est 200 OK
                .andExpect(jsonPath("$.token").exists());
    }
}
