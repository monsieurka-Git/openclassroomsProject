package com.openclassrooms.etudiant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.etudiant.dto.StudentDTO;
import com.openclassrooms.etudiant.entities.Student;
import com.openclassrooms.etudiant.service.StudentService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class StudentControllerTest {

    private static final String URL_STUDENTS = "/api/students";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL = "john.doe@example.com";

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createStudentSuccess() throws Exception {
        StudentDTO dto = new StudentDTO();
        dto.setFirstName(FIRST_NAME);
        dto.setLastName(LAST_NAME);
        dto.setEmail(EMAIL);

        StudentDTO savedDto = new StudentDTO();
        savedDto.setId(1L);
        savedDto.setFirstName(FIRST_NAME);
        savedDto.setLastName(LAST_NAME);
        savedDto.setEmail(EMAIL);

        when(studentService.create(any())).thenReturn(savedDto);

        mockMvc.perform(post(URL_STUDENTS)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(LAST_NAME))
                .andExpect(jsonPath("$.email").value(EMAIL));

        verify(studentService).create(any());
    }

    @Test
    public void getAllStudentsSuccess() throws Exception {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setFirstName(FIRST_NAME);
        student1.setLastName(LAST_NAME);
        student1.setEmail(EMAIL);

        Student student2 = new Student();
        student2.setId(2L);
        student2.setFirstName("Jane");
        student2.setLastName("Smith");
        student2.setEmail("jane.smith@example.com");

        when(studentService.getAll()).thenReturn(List.of(student1, student2));

        mockMvc.perform(get(URL_STUDENTS)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$[1].firstName").value("Jane"));
    }

    @Test
    public void getStudentByIdSuccess() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName(FIRST_NAME);
        student.setLastName(LAST_NAME);
        student.setEmail(EMAIL);

        when(studentService.getById(1L)).thenReturn(student);

        mockMvc.perform(get(URL_STUDENTS + "/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(LAST_NAME));
    }

    @Test
    public void getStudentByIdNotFound() throws Exception {
        when(studentService.getById(999L)).thenThrow(new RuntimeException("Étudiant non trouvé"));

        mockMvc.perform(get(URL_STUDENTS + "/{id}", 999L)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void updateStudentSuccess() throws Exception {
        StudentDTO dto = new StudentDTO();
        dto.setFirstName("Updated");
        dto.setLastName(LAST_NAME);
        dto.setEmail(EMAIL);

        StudentDTO updatedDto = new StudentDTO();
        updatedDto.setId(1L);
        updatedDto.setFirstName("Updated");
        updatedDto.setLastName(LAST_NAME);
        updatedDto.setEmail(EMAIL);

        when(studentService.update(eq(1L), any())).thenReturn(updatedDto);

        mockMvc.perform(put(URL_STUDENTS + "/{id}", 1L)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Updated"));
    }

    @Test
    public void deleteStudentSuccess() throws Exception {
        mockMvc.perform(delete(URL_STUDENTS + "/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk());

        verify(studentService).delete(1L);
    }

    @Test
    public void deleteStudentNotFound() throws Exception {
        doThrow(new RuntimeException("Étudiant non trouvé"))
                .when(studentService).delete(999L);

        mockMvc.perform(delete(URL_STUDENTS + "/{id}", 999L))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }
}