# Created by kjss920 at 2025-06-02
Feature: Feature file for demonstrating Data Table in Cucumber
    This feature file demonstrates the usage of Data Table in Cucumber for Java

  Scenario: Data Table Example as a List of Maps
    Given the following user credentials:
      | username | password |
      | user1    | pass1    |
      | user2    | pass2    |
    When I process the user credentials
    Then I should see the processed credentials