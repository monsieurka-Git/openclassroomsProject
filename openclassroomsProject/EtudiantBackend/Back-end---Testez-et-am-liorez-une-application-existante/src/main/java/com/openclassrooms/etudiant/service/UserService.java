package com.openclassrooms.etudiant.service;

import com.openclassrooms.etudiant.entities.User;
import com.openclassrooms.etudiant.repository.UserRepository;
import com.openclassrooms.etudiant.dto.RegisterDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // ---------------------------
    // REGISTER
    // ---------------------------
    public void register(User user) {
        Assert.notNull(user, "User must not be null");

        user.setLogin(user.getLogin().toLowerCase());
        log.info("Registering new user");

        if (userRepository.findByLogin(user.getLogin()).isPresent()) {
            throw new IllegalArgumentException("User with login " + user.getLogin() + " already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // ---------------------------
    // LOGIN
    // ---------------------------
    public String login(String login, String password) {
        Assert.notNull(login, "Login must not be null");
        Assert.notNull(password, "Password must not be null");

        login = login.toLowerCase();
        log.info("Tentative de login pour {}", login);

        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("Login incorrect"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            log.warn("Mot de passe incorrect pour {}", login);
            throw new IllegalArgumentException("Mot de passe incorrect");
        }

        log.info("Authentification réussie pour {}", login);
        return jwtService.generateToken(user);
    }

    // ---------------------------
    // GET ALL USERS
    // ---------------------------
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ---------------------------
    // GET USER BY LOGIN
    // ---------------------------
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login.toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + login));
    }

    // ---------------------------
    // DELETE USER
    // ---------------------------
    public void deleteUser(String login) {
        User user = getUserByLogin(login);
        userRepository.delete(user);
    }

    // ---------------------------
    // UPDATE USER (OPTIONNEL)
    // ---------------------------
    public User updateUser(String login, RegisterDTO dto) {
        User user = getUserByLogin(login);

        if (dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) user.setLastName(dto.getLastName());
        if (dto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return userRepository.save(user);
    }
}
