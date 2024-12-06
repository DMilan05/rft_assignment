Feature: Exam Management API

  Scenario: Create an exam
    Given I have an exam name "Math 101"
    When I send a POST request to "/api/exams"
    Then the response status should be 200
    And the response body should contain "Exam created successfully"

  Scenario: Get all exams
    When I send a GET request to "/api/exams"
    Then the response status should be 200
    And the response body should contain "Math 101"
