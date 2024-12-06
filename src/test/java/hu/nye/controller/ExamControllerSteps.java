package hu.nye.controller;

import hu.nye.model.Exam;
import hu.nye.model.UniversitySystem;
import hu.nye.stakeholders.Administrator;
import hu.nye.stakeholders.Student;
import hu.nye.stakeholders.Teacher;
import hu.nye.stakeholders.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExamControllerSteps {

    private ExamController examController;
    private ResponseEntity<String> response;
    private String examName;

    @Given("the ExamController is initialized")
    public void the_exam_controller_is_initialized() {
        examController = new ExamController();
    }

    @Given("an exam name {string} is provided")
    public void an_exam_name_is_provided(String name) {
        this.examName = name;
    }

    @When("a POST request is sent to create an exam")
    public void a_post_request_is_sent_to_create_an_exam() {
        Exam exam = new Exam(examName); // Assuming the constructor takes the exam name
        response = examController.createExam(exam);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(int statusCode) {
        assertEquals(statusCode, response.getStatusCodeValue());
    }

    @Then("the response body should contain {string}")
    public void the_response_body_should_contain(String message) {
        assertEquals(message, response.getBody());
    }

    @When("a DELETE request is sent to delete the exam")
    public void a_delete_request_is_sent_to_delete_the_exam() {
        response = examController.deleteExam(examName);
    }

    @When("a PUT request is sent to register a student for the exam")
    public void a_put_request_is_sent_to_register_a_student_for_the_exam() {
        Student student = new Student("StudentName");
        response = examController.registerForExam(examName, student);
    }
}
