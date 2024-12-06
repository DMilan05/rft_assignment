package hu.nye.model;

import hu.nye.exception.UnauthorizedActionException;
import hu.nye.stakeholders.Administrator;
import hu.nye.stakeholders.Teacher;
import hu.nye.stakeholders.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniversitySystemTest {

    private UniversitySystem universitySystem;
    private User teacher;
    private User admin;
    private String examName;

    @BeforeEach
    void setUp() {
        universitySystem = new UniversitySystem();
        teacher = new Teacher("Teacher John");
        admin = new Administrator("Admin Jane");
        examName = "Math Exam";
    }

    @Test
    void testCreateExam() {
        // Given
        String examName = "Math Exam";
        User creator = teacher;

        // When
        universitySystem.createExam(examName, creator);

        // Then
        Exam exam = universitySystem.getExam(examName);
        assertNotNull(exam, "Exam should be created successfully.");
        assertEquals(examName, exam.getName(), "Exam name should match the input name.");
        assertEquals(creator, exam.getCreator(), "Exam creator should match the input teacher.");
    }

    @Test
    void testDeleteExamByAuthorizedUser() {
        // Given
        String examName = "Math Exam";
        User creator = teacher;
        universitySystem.createExam(examName, creator);

        // When
        universitySystem.deleteExam(examName, admin);

        // Then
        Exam exam = universitySystem.getExam(examName);
        assertNull(exam, "Exam should be deleted by the administrator.");
    }

    @Test
    void testDeleteExamByUnauthorizedUser() {
        // Given
        String examName = "Math Exam";
        User creator = teacher;
        universitySystem.createExam(examName, creator);

        // When & Then
        UnauthorizedActionException thrown = assertThrows(
                UnauthorizedActionException.class,
                () -> universitySystem.deleteExam(examName, teacher), // Teacher should not be able to delete
                "Expected deleteExam to throw an UnauthorizedActionException when called by a Teacher"
        );
        assertEquals("User is not authorized to delete this exam.", thrown.getMessage());

        // Check that the exam still exists
        Exam exam = universitySystem.getExam(examName);
        assertNotNull(exam, "Exam should not be deleted by an unauthorized user.");
    }


    @Test
    void testCreateExamWhenAlreadyExists() {
        // Given
        String examName = "Math Exam";
        User creator = teacher;
        universitySystem.createExam(examName, creator);

        // When
        universitySystem.createExam(examName, creator);

        // Then
        Exam exam = universitySystem.getExam(examName);
        assertNotNull(exam, "Exam should exist after the second creation attempt.");
    }
}
