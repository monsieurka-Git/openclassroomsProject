package com.openclassrooms.etudiant.service;

import com.openclassrooms.etudiant.dto.StudentDTO;
import com.openclassrooms.etudiant.entities.Student;
import com.openclassrooms.etudiant.repository.StudentRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class StudentServiceTest {

    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL = "john.doe@example.com";

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testCreateStudent() {
        StudentDTO dto = new StudentDTO();
        dto.setFirstName(FIRST_NAME);
        dto.setLastName(LAST_NAME);
        dto.setEmail(EMAIL);

        Student savedStudent = new Student();
        savedStudent.setId(1L);
        savedStudent.setFirstName(FIRST_NAME);
        savedStudent.setLastName(LAST_NAME);
        savedStudent.setEmail(EMAIL);

        when(studentRepository.save(any())).thenReturn(savedStudent);

        StudentDTO result = studentService.create(dto);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(result.getLastName()).isEqualTo(LAST_NAME);
        assertThat(result.getEmail()).isEqualTo(EMAIL);

        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(captor.capture());
        assertThat(captor.getValue().getFirstName()).isEqualTo(FIRST_NAME);
    }

    @Test
    void testGetAllStudents() {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setFirstName(FIRST_NAME);

        Student student2 = new Student();
        student2.setId(2L);
        student2.setFirstName("Jane");

        when(studentRepository.findAll()).thenReturn(List.of(student1, student2));

        List<Student> result = studentService.getAll();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(result.get(1).getFirstName()).isEqualTo("Jane");
    }

    @Test
    void testGetStudentByIdSuccess() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName(FIRST_NAME);
        student.setLastName(LAST_NAME);
        student.setEmail(EMAIL);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student result = studentService.getById(1L);

        assertThat(result).isEqualTo(student);
        assertThat(result.getFirstName()).isEqualTo(FIRST_NAME);
    }

    @Test
    void testGetStudentByIdNotFound() {
        when(studentRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> studentService.getById(999L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Étudiant non trouvé");
    }

    @Test
    void testUpdateStudent() {
        StudentDTO dto = new StudentDTO();
        dto.setFirstName("Updated");
        dto.setLastName(LAST_NAME);
        dto.setEmail(EMAIL);

        Student existingStudent = new Student();
        existingStudent.setId(1L);
        existingStudent.setFirstName(FIRST_NAME);
        existingStudent.setLastName(LAST_NAME);
        existingStudent.setEmail(EMAIL);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(any())).thenReturn(existingStudent);

        StudentDTO result = studentService.update(1L, dto);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getFirstName()).isEqualTo("Updated");

        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(captor.capture());
        assertThat(captor.getValue().getFirstName()).isEqualTo("Updated");
    }

    @Test
    void testDeleteStudent() {
        studentService.delete(1L);
        verify(studentRepository).deleteById(1L);
    }
}