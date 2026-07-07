package com.openclassrooms.etudiant.service;

import com.openclassrooms.etudiant.dto.RegisterDTO;
import com.openclassrooms.etudiant.entities.User;
import com.openclassrooms.etudiant.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String LOGIN = "LOGIN";
    private static final String PASSWORD = "PASSWORD";
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @InjectMocks
    private UserService userService;
    


    @Test
    public void test_create_null_user_throws_IllegalArgumentException() {
        // GIVEN

        // THEN
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.register(null));
    }

    @Test
    public void test_create_already_exist_user_throws_IllegalArgumentException() {
        // GIVEN
        User user = new User();
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        when(passwordEncoder.encode(PASSWORD)).thenReturn(PASSWORD);
        when(userRepository.findByLogin(any())).thenReturn(Optional.of(user));

        // THEN
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.register(user));
    }

    @Test
    public void test_create_user() {
        // GIVEN
        User user = new User();
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        when(passwordEncoder.encode(PASSWORD)).thenReturn(PASSWORD);
        when(userRepository.findByLogin(any())).thenReturn(Optional.empty());

        // WHEN
        userService.register(user);

        // THEN
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        assertThat(userCaptor.getValue()).isEqualTo(user);
    }

    @Test
        void test_login_null_login_throws_exception() {
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> userService.login(null, "pass"));
    }

    @Test
        void test_login_null_password_throws_exception() {
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> userService.login("login", null));
    }
    
    @Test
        void test_login_incorrect_login_throws_exception() {
        when(userRepository.findByLogin("login")).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class,
            () -> userService.login("login", "pass"));
    }

    @Test
        void test_login_wrong_password_throws_exception() {
        User user = new User();
        user.setLogin("login");
        user.setPassword("encoded");

        when(userRepository.findByLogin("login")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("pass", "encoded")).thenReturn(false);

        Assertions.assertThrows(IllegalArgumentException.class,
            () -> userService.login("login", "pass"));
    }

    @Test
        void test_login_success_returns_token() {
        User user = new User();
        user.setLogin("login");
        user.setPassword("encoded");

        when(userRepository.findByLogin("login")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("pass", "encoded")).thenReturn(true);
        when(jwtService.generateToken(user)).thenReturn("TOKEN");

        String token = userService.login("login", "pass");

        assertThat(token).isEqualTo("TOKEN");
    }

    @Test
        void test_get_all_users() {
        List<User> users = List.of(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        assertThat(userService.getAllUsers()).hasSize(2);
    }

    @Test
        void test_get_user_by_login_not_found() {
        when(userRepository.findByLogin("login")).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class,
            () -> userService.getUserByLogin("login"));
    }

    @Test
        void test_get_user_by_login_success() {
        User user = new User();
        user.setLogin("login");

        when(userRepository.findByLogin("login")).thenReturn(Optional.of(user));

        assertThat(userService.getUserByLogin("login")).isEqualTo(user);
    }

    @Test
        void test_delete_user() {
        User user = new User();
        user.setLogin("login");

        when(userRepository.findByLogin("login")).thenReturn(Optional.of(user));

        userService.deleteUser("login");

        verify(userRepository).delete(user);
    }

    @Test
        void test_update_user() {
        User user = new User();
        user.setLogin("login");
        user.setFirstName("old");
        user.setLastName("old");
        user.setPassword("old");

        RegisterDTO dto = new RegisterDTO();
        dto.setFirstName("new");
        dto.setLastName("new");
        dto.setPassword("newpass");

        when(userRepository.findByLogin("login")).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newpass")).thenReturn("encoded");
        when(userRepository.save(any())).thenReturn(user);

        User updated = userService.updateUser("login", dto);

        assertThat(updated.getFirstName()).isEqualTo("new");
        assertThat(updated.getLastName()).isEqualTo("new");
        assertThat(updated.getPassword()).isEqualTo("encoded");
    }







  

}
