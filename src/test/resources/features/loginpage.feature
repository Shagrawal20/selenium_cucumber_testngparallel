Feature: Login Functionality for OrangeHRM Website

  Background:
    Given I am on the OrangeHRM login page

  @TC01
  Scenario: Successful login with valid credentials
    Given I have entered a valid username and password
    When I click on the login button
    Then I should be logged in successfully

  @TC02
  Scenario Outline: Unsuccessful login with invalid or empty credentials
    Given I have entered invalid "<username>" and "<password>"
    When I click on the login button
    Then I should see an error message indicating "<error_message>"
    Examples:
      | username          | password        | error_message       |
      | invalid@email.com | invalidPassword | Invalid credentials |
      | abcccc            | validPassword   | Invalid credentials |
      | valid@email.com   | abccc           | Invalid credentials |
