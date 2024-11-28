package hu.nye.stakeholders;

// Student.java
import hu.nye.model.Exam;
import hu.nye.model.UniversitySystem;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private List<Exam> registeredExams;

    public Student(String name) {
        super(name);
        this.registeredExams = new ArrayList<>();
    }

    public void registerForExam(UniversitySystem system, String examName) {
        Exam exam = system.getExam(examName);
        if (exam != null) {
            registeredExams.add(exam);
            System.out.println(name + " registered for exam: " + examName);
        } else {
            System.out.println("Exam " + examName + " does not exist.");
        }
    }

    public List<Exam> getRegisteredExams() {
        return registeredExams;
    }
}