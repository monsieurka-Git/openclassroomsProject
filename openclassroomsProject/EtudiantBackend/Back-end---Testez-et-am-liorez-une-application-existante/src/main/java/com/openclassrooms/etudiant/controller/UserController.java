package com.openclassrooms.etudiant.controller;

import com.openclassrooms.etudiant.dto.LoginRequestDTO;
import com.openclassrooms.etudiant.dto.RegisterDTO;
import com.openclassrooms.etudiant.dto.UserDTO;
import com.openclassrooms.etudiant.mapper.UserDtoMapper;
import com.openclassrooms.etudiant.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    // ---------------------------
    // REGISTER
    // ---------------------------
    @PostMapping(path = {"/api/auth/register", "/register"})
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO registerDTO) {
        userService.register(userDtoMapper.toEntity(registerDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // ---------------------------
    // LOGIN
    // ---------------------------
    @PostMapping(path = {"/api/auth/login", "/login"})
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginDTO) {
        System.out.println(">>> CONTROLLER LOGIN REÇU <<<");
        String token = userService.login(loginDTO.getLogin(), loginDTO.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }

    // ---------------------------
    // GET ALL USERS
    // ---------------------------
    @GetMapping("/api/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers()
                .stream()
                .map(userDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(users);
    }

    // ---------------------------
    // GET USER BY LOGIN
    // ---------------------------
    @GetMapping("/api/users/{login}")
    public ResponseEntity<UserDTO> getUserByLogin(@PathVariable String login) {

        var user = userService.getUserByLogin(login);

        if (user == null) {
            return ResponseEntity.notFound().build();   // <-- AJOUT ESSENTIEL
        }

        return ResponseEntity.ok(userDtoMapper.toDto(user));
    }

    // ---------------------------
    // DELETE USER
    // ---------------------------
    @DeleteMapping("/api/users/{login}")
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        userService.deleteUser(login);
        return ResponseEntity.noContent().build();
    }
}
