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

  @process_order @RemoveFiles
  Scenario: Scenario Example - Example scenario for processing Order passed from workflow dispatch
    Given An Order number is received
    Then The Order is processed

  Scenario: Demonstrating passing an object to the step definition
    Given SUPER_USER has created the organization "ACME Org"

   # Demonstrating usage of Enum in step definition
  Scenario: Demonstrating usage of Enum in step definition
    Given I have a user with role ADMIN
    Then I can perform actions that only ADMIN can perform

    # Demonstrating usage of Boolean in step definition
  Scenario: Demonstrating usage of Boolean in step definition
    Given I have a user with role USER
    Then It is true that I can perform actions that USER can perform

    # Demonstrating usage of @ParameterType in step definition
  Scenario: Demonstrating usage of @ParameterType in step definition
    Given I have an Order with Order number T-12345 with priority HIGH and delivery date 2025-12-31
    Then I can process the Order with Order number T-12345

    # Demonstrating usage of @ParameterType in step definition
  Scenario: Demonstrating usage of @ParameterType in step definition
    Given I have an Order with Order number T-123456 with priority HIGH and delivery date 2025-12-31
    Then I can process the Order with Order number T-12345
    # The Order number in this implementation must start with T- and be followed by 5 digits

