@first-feature
Feature: Cucumber First Feature File
  This is a simple feature file to try out Cucumber for Java

  @web-login @web-login-simple
  Scenario: Scenario Example - Perform login to the web portal
    Given I open the login page for my web portal
    Then I enter the username as "web-login-simple" and password as "web-login-simple-password"
    Then I click login button
    Then I am on the homepage

  @web-login @web-login-param
  Scenario: Scenario Example - Example scenario for passing multiple parameters to a step
    Given I open the login page for my web portal
    Then I enter the following login credentials
      | username | web-login-param          |
      | password | web-login-param-password |
    Then I click login button
    Then I am on the homepage

  @web-navigation @web-navigation-test
  Scenario: Scenario Example - Example scenario for passing multiple parameters to a step
    Given I open the login page for my web portal
    Then I enter the following login credentials
      | username | web-navigation-test          |
      | password | web-navigation-test-password |
    Then I click login button
    Then I am on the homepage