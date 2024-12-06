package hu.nye.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExamSteps {

    private Response response;
    private String examName;

    @Given("I have an exam name {string}")
    public void i_have_an_exam_name(String name) {
        this.examName = name;
    }

    @When("I send a POST request to {string}")
    public void i_send_a_post_request_to(String endpoint) {
        response = RestAssured.given()
                .contentType("application/json")
                .body("{\"name\": \"" + examName + "\"}")
                .post("http://localhost:8080" + endpoint);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response body should contain {string}")
    public void the_response_body_should_contain(String message) {
        assertEquals(true, response.getBody().asString().contains(message));
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = RestAssured.given().get("http://localhost:8080" + endpoint);
    }
}
