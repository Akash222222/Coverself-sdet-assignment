Feature: API Generator Data Verification

  Scenario: Generate API and validate data preview
    Given I navigate to the API generator page
    When I fill in the dataset form with required columns and values
    Then I should see 5 rows in the API preview table
    And I generate the API and capture the endpoint