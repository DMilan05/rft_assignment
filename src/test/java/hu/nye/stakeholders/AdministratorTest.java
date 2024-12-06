package hu.nye.stakeholders;

import hu.nye.model.UniversitySystem;
import hu.nye.model.Exam;
import hu.nye.stakeholders.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministratorTest {

    private UniversitySystem universitySystem;
    private Administrator admin;
    private String examName;

    @BeforeEach
    void setUp() {
        universitySystem = new UniversitySystem();
        admin = new Administrator("Admin Jane");
        examName = "Math Exam";
    }

    @Test
    void testCreateExam() {
        // Given
        String examName = "Math Exam";
        Administrator admin = this.admin;

        // When
        admin.createExam(universitySystem, examName);

        // Then
        Exam exam = universitySystem.getExam(examName);
        assertNotNull(exam, "Exam should be created by the administrator.");
        assertEquals(examName, exam.getName(), "Exam name should match the input name.");
        assertEquals(admin, exam.getCreator(), "Exam creator should be the administrator.");
    }

    @Test
    void testDeleteExam() {
        // Given
        String examName = "Math Exam";
        Administrator admin = this.admin;
        universitySystem.createExam(examName, admin);

        // When
        admin.deleteExam(universitySystem, examName);

        // Then
        Exam exam = universitySystem.getExam(examName);
        assertNull(exam, "Exam should be deleted by the administrator.");
    }

    @Test
    void testDeleteNonExistentExam() {
        // Given
        String examName = "Math Exam";
        Administrator admin = this.admin;

        // When
        admin.deleteExam(universitySystem, examName);

        // Then
        Exam exam = universitySystem.getExam(examName);
        assertNull(exam, "Exam should not exist as it was never created.");
    }
}
