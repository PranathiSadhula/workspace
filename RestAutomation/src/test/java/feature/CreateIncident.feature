Feature: create incident in servicenow
Scenario: create incident only with shrt description
    Given Request URL is initiated
    And Auth is performed
    When Body is posted with Json file data1.json
    Then staus code should be 201
    And Response is within 5 secs
    And Get Incident Number