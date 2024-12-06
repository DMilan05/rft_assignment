package hu.nye.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwaggerConfigSteps {

    private OpenAPI openAPI;

    @Given("the SwaggerConfig is initialized")
    public void the_swagger_config_is_initialized() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        openAPI = swaggerConfig.customOpenAPI();
    }

    @When("I retrieve the OpenAPI object")
    public void i_retrieve_the_openapi_object() {
        // The OpenAPI object was already initialized in the Given step
    }

    @Then("the OpenAPI object should contain the correct title, version, and description")
    public void the_openapi_object_should_contain_the_correct_title_version_and_description() {
        Info info = openAPI.getInfo();
        assertEquals("Exam Management System API", info.getTitle());
        assertEquals("1.0", info.getVersion());
        assertEquals("API documentation for the Exam Management System", info.getDescription());
    }
}
