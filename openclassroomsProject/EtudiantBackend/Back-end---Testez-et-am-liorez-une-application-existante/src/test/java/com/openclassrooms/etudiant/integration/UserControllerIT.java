package com.openclassrooms.etudiant.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.etudiant.dto.LoginRequestDTO;
import com.openclassrooms.etudiant.dto.RegisterDTO;
import com.openclassrooms.etudiant.entities.User;
import com.openclassrooms.etudiant.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    // ---------------------------
    // REGISTER
    // ---------------------------
   /* @Test
    void testRegisterSuccess() throws Exception {
        RegisterDTO dto = new RegisterDTO("test.agent", "123456", "Test", "Agent");

        Mockito.doNothing().when(userService).register(any());

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }*/
    @Test
    void testRegisterSuccess() throws Exception {
        RegisterDTO dto = new RegisterDTO();
        dto.setLogin("test.agent");
        dto.setPassword("123456");
        dto.setFirstName("Test");
        dto.setLastName("Agent");

        Mockito.doNothing().when(userService).register(any());

        mockMvc.perform(post("/api/auth/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated());
    }



    // ---------------------------
    // LOGIN
    // ---------------------------
    /*@Test
    void testLoginSuccess() throws Exception {
        LoginRequestDTO dto = new LoginRequestDTO("test.agent", "123456");

        Mockito.when(userService.login("test.agent", "123456"))
                .thenReturn("jwt-token");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token"));
    } */

     @Test
    void testLoginSuccess() throws Exception {
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setLogin("test.agent");
        dto.setPassword("123456");

        Mockito.when(userService.login("test.agent", "123456"))
            .thenReturn("jwt-token");

        mockMvc.perform(post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.token").value("jwt-token"));
    }
           

    // ---------------------------
    // GET ALL USERS
    // ---------------------------
    @Test
    void testGetAllUsers() throws Exception {
        User user = new User();
        user.setLogin("test.agent");
        user.setFirstName("Test");
        user.setLastName("Agent");

        Mockito.when(userService.getAllUsers()).thenReturn(List.of(user));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].login").value("test.agent"));
    }

    // ---------------------------
    // GET USER BY LOGIN
    // ---------------------------
    @Test
    void testGetUserByLogin() throws Exception {
        User user = new User();
        user.setLogin("test.agent");
        user.setFirstName("Test");
        user.setLastName("Agent");

        Mockito.when(userService.getUserByLogin("test.agent")).thenReturn(user);

        mockMvc.perform(get("/api/users/test.agent"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("test.agent"));
    }

    // ---------------------------
    // DELETE USER
    // ---------------------------
    @Test
    void testDeleteUser() throws Exception {
        Mockito.doNothing().when(userService).deleteUser(eq("test.agent"));

        mockMvc.perform(delete("/api/users/test.agent"))
                .andExpect(status().isNoContent());
    }
}
