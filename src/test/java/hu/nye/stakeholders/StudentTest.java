package hu.nye.stakeholders;

import hu.nye.model.Exam;
import hu.nye.model.UniversitySystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private UniversitySystem universitySystem;
    private Student student;
    private String examName;

    @BeforeEach
    void setUp() {
        universitySystem = new UniversitySystem();
        student = new Student("John Doe");
        examName = "Math Exam";
    }

    @Test
    void testRegisterForExam() {
        // Given
        String examName = "Math Exam";
        Student student = this.student;
        universitySystem.createExam(examName, new Teacher("Teacher Jane"));

        // When
        student.registerForExam(universitySystem, examName);

        // Then
        assertEquals(1, student.getRegisteredExams().size(), "Student should be registered for one exam.");
        assertEquals(examName, student.getRegisteredExams().get(0).getName(), "Registered exam name should match.");
    }

    @Test
    void testRegisterForNonExistentExam() {
        // Given
        String examName = "Math Exam";
        Student student = this.student;

        // When
        student.registerForExam(universitySystem, examName);

        // Then
        assertEquals(0, student.getRegisteredExams().size(), "Student should not be registered for a non-existent exam.");
    }
}
