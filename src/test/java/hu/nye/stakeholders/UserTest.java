package hu.nye.stakeholders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private String userName;

    @BeforeEach
    void setUp() {
        userName = "John Doe";
        user = new Teacher(userName); // You can replace with Student or Administrator if needed
    }

    @Test
    void testGetName() {
        // Given
        String expectedName = userName;

        // When
        String actualName = user.getName();

        // Then
        assertEquals(expectedName, actualName, "The name should match.");
    }
}
