package hu.nye.controller;

import hu.nye.model.Exam;
import hu.nye.model.UniversitySystem;
import hu.nye.stakeholders.Administrator;
import hu.nye.stakeholders.Student;
import hu.nye.stakeholders.Teacher;
import hu.nye.stakeholders.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final UniversitySystem universitySystem;

    public ExamController() {
        // Creation of default instance
        this.universitySystem = new UniversitySystem();
    }

    @Operation(summary = "List all exams", description = "Retrieve all exams in the system.")
    @GetMapping
    public Collection<Exam> getAllExams() {
        return universitySystem.getExams().values();
    }

    @Operation(summary = "Create an exam", description = "Create a new exam with a given name.")
    @PostMapping
    public ResponseEntity<String> createExam(@RequestBody Exam exam) {
        User creator = new Teacher("Default Teacher");
        universitySystem.createExam(exam.getName(), creator);
        return ResponseEntity.ok("Exam created successfully.");
    }

    @Operation(summary = "Delete an exam", description = "Delete an existing exam by name.")
    @DeleteMapping("/{examName}")
    public ResponseEntity<String> deleteExam(@PathVariable String examName) {
        User deleter = new Administrator("Default Admin");
        universitySystem.deleteExam(examName, deleter);
        return ResponseEntity.ok("Exam deleted successfully.");
    }

    @Operation(summary = "Register for an exam", description = "Register a student for an existing exam.")
    @PutMapping("/register/{examName}")
    public ResponseEntity<String> registerForExam(@PathVariable String examName, @RequestBody Student student) {
        student.registerForExam(universitySystem, examName);
        return ResponseEntity.ok(student.getName() + " registered for " + examName);
    }

}