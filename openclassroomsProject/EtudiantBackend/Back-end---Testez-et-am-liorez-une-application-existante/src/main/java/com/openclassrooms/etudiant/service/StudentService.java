package com.openclassrooms.etudiant.service;

import com.openclassrooms.etudiant.dto.StudentDTO;
import com.openclassrooms.etudiant.entities.Student;
import com.openclassrooms.etudiant.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public StudentDTO create(StudentDTO dto) {
        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());

        Student saved = repository.save(student);
        dto.setId(saved.getId());
        return dto;
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
    }

    public StudentDTO update(Long id, StudentDTO dto) {
        Student student = getById(id);

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());

        repository.save(student);
        dto.setId(id);
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
