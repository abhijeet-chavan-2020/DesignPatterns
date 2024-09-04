Feature: Register page feature



    @TestNew
  Scenario: Test Register page Input field with Builder Patter
    Given  Registration data is prepared
    When  Registration page is launched
    Then  Fill Registration page data

  @TestNew
  Scenario: Test Register page Labels
    Given Registration page is launched
    Then  Verify field labels
   | input-firstname| First Name  |
   | input-lastname| Last Name    |
   | input-email| E-Mail          |
