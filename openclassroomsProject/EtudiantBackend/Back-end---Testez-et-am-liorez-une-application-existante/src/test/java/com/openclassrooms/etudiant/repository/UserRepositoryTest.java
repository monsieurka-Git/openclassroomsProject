package com.openclassrooms.etudiant.repository;

import com.openclassrooms.etudiant.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

   @Test
    void testFindByLogin() {
        User user = new User();
        user.setLogin("john");
        user.setPassword("pass");
        user.setFirstName("John");
        user.setLastName("Doe");

        repository.saveAndFlush(user);

        assertTrue(repository.findByLogin("john").isPresent());
}


    
    @Test
    void testExistsByLogin() {
        User user = new User();
        user.setLogin("John");
        user.setPassword("pass");
        user.setFirstName("John");
        user.setLastName("Doe");

        repository.saveAndFlush(user);

        assertTrue(repository.findByLogin("John").isPresent());
}

}
