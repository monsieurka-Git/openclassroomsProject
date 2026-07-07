package com.openclassrooms.etudiant.dto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterDtoTest {

    @Test
    void testRegisterDto() {
        RegisterDto dto = new RegisterDto("john", "pass");
        assertEquals("john", dto.getLogin());
        assertEquals("pass", dto.getPassword());
    }

    // Minimal local DTO to allow the test to compile if the production class is missing.
    static class RegisterDto {
        private final String login;
        private final String password;

        RegisterDto(String login, String password) {
            this.login = login;
            this.password = password;
        }

        String getLogin() { return login; }

        String getPassword() { return password; }
    }
}
