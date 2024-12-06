package hu.nye.model;

import hu.nye.model.Exam;
import hu.nye.stakeholders.User;
import hu.nye.stakeholders.Teacher;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExamSteps {

    private Exam exam;
    private String examName;
    private User creator;

    @Given("an exam with name {string} and creator {string}")
    public void an_exam_with_name_and_creator(String name, String creatorName) {
        creator = new Teacher(creatorName); // Assuming Teacher is a subclass of User
        exam = new Exam(name, creator);
        examName = name;
    }

    @Then("the exam should have the correct name and creator")
    public void the_exam_should_have_the_correct_name_and_creator() {
        assertEquals(examName, exam.getName());
        assertEquals(creator, exam.getCreator());
    }
}
