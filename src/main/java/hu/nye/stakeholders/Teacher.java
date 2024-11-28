package hu.nye.stakeholders;

import hu.nye.model.UniversitySystem;

public class Teacher extends User {
    public Teacher(String name) {
        super(name);
    }

    public void createExam(UniversitySystem system, String examName) {
        system.createExam(examName, this);
    }

    public void deleteExam(UniversitySystem system, String examName) {
        system.deleteExam(examName, this);
    }
}