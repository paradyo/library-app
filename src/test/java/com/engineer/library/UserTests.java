package com.engineer.library;

import com.engineer.library.model.User;
import com.engineer.library.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    public void testCreateSuccess() {
        User user = new User();
        user.setId(1);
        user.setUsername("john_doe");
        user.setFullName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("mysecretpassword");
        userRepository.save(user);
        assertNotNull(userRepository.findById(1).get());
    }

    @Test
    @Order(2)
    public void testReadAllSuccess() {
        List<User> userList = userRepository.findAll();
        assertThat(userList).size().isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void testReadSuccess() {
        User user = userRepository.findById(1).get();
        assertEquals("john_doe", user.getUsername());
        assertEquals("John Doe", user.getFullName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("mysecretpassword", user.getPassword());
    }

    @Test
    @Order(4)
    public void testReadNonExistentUser() {
        // Try to find a user with an ID that does not exist (e.g., 100)
        // The expected behavior is a NoSuchElementException (404 status code)
        assertThrows(NoSuchElementException.class, () -> {
            User user = userRepository.findById(100).get();
        });
    }


    @Test
    @Order(5)
    public void testUpdateSuccess() {
        User user = userRepository.findById(1).get();
        user.setPassword("newsecretpassword");
        userRepository.save(user);
        assertNotEquals("mysecretpassword", userRepository.findById(1).get().getPassword());
    }

    @Test
    @Order(6)
    public void testDeleteSuccess() {
        userRepository.deleteById(1);
        assertThat(userRepository.existsById(1)).isFalse();
    }
}
