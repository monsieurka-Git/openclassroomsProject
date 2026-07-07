package com.openclassrooms.etudiant.handler;

import com.openclassrooms.etudiant.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.file.AccessDeniedException;   // ✔ GARDER SEULEMENT CELUI-CI

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class RestExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void handleIllegalArgumentException() throws Exception {
        doThrow(new IllegalArgumentException("Invalid data"))
                .when(userService).register(any());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                        .content("{\"login\":\"\",\"password\":\"\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void handleBadCredentialsException() throws Exception {
        doThrow(new BadCredentialsException("Invalid credentials"))
                .when(userService).login(any(), any());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                        .content("{\"login\":\"john\",\"password\":\"wrong\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
        public void handleAccessDeniedException() throws Exception {

        // Mockito ne peut pas lancer une checked exception via doThrow(),
        // mais il peut la lancer via une lambda.
        when(userService.getAllUsers()).thenAnswer(invocation -> {
            throw new AccessDeniedException("Access denied");
        });

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }


    @Test
    public void handleGenericException() throws Exception {
        doThrow(new RuntimeException("Unexpected error"))
                .when(userService).getAllUsers();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
}
