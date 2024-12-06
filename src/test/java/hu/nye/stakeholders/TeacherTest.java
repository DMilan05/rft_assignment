package hu.nye.stakeholders;

import hu.nye.exception.UnauthorizedActionException;
import hu.nye.model.UniversitySystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    private UniversitySystem universitySystem;
    private Teacher teacher;
    private String examName;

    @BeforeEach
    void setUp() {
        universitySystem = new UniversitySystem();
        teacher = new Teacher("Teacher John");
        examName = "Math Exam";
    }

    @Test
    void testCreateExam() {
        // Given
        String examName = "Math Exam";
        Teacher teacher = this.teacher;

        // When
        teacher.createExam(universitySystem, examName);

        // Then
        assertNotNull(universitySystem.getExam(examName), "Exam should be created successfully.");
    }

    @Test
    void testDeleteExam() {
        // Given
        String examName = "Math Exam";
        Teacher teacher = this.teacher;
        universitySystem.createExam(examName, teacher);

        // When & Then
        UnauthorizedActionException thrown = assertThrows(
                UnauthorizedActionException.class,
                () -> teacher.deleteExam(universitySystem, examName),
                "Expected deleteExam to throw an UnauthorizedActionException when called by a Teacher"
        );

        assertEquals("User is not authorized to delete this exam.", thrown.getMessage());

        // Ensure the exam still exists
        assertNotNull(universitySystem.getExam(examName), "Exam should not be deleted by a teacher.");
    }


    @Test
    void testDeleteNonExistentExam() {
        // Given
        String examName = "Math Exam";
        Teacher teacher = this.teacher;

        // When
        teacher.deleteExam(universitySystem, examName);

        // Then
        assertNull(universitySystem.getExam(examName), "Non-existent exam should not be deleted.");
    }
}
