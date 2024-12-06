package hu.nye.model;
import hu.nye.model.Exam;
import hu.nye.stakeholders.Administrator;
import hu.nye.stakeholders.Teacher;
import hu.nye.stakeholders.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniversitySystem {
    private Map<String, Exam> exams;

    public UniversitySystem() {
        exams = new HashMap<>();
    }

    public void createExam(String examName, User creator) {
        if (!exams.containsKey(examName)) {
            exams.put(examName, new Exam(examName, creator));
            System.out.println("Exam " + examName + " created by " + creator.getName());
        } else {
            System.out.println("Exam " + examName + " already exists.");
        }
    }

    public void deleteExam(String examName, User deleter) {
        Exam exam = exams.get(examName);
        if (exam != null && (deleter instanceof Teacher || deleter instanceof Administrator)) {
            exams.remove(examName);
            System.out.println("Exam " + examName + " deleted by " + deleter.getName());
        } else {
            System.out.println("Exam " + examName + " not found or unauthorized.");
        }
    }

    public Exam getExam(String examName) {
        return exams.get(examName);
    }

}
