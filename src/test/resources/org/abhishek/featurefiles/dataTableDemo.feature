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

#    Scenario: Data Table Example as a List of Lists
#    Given the following user credentials:
#      | user1 | pass1 |
#      | user2 | pass2 |
#    When I process the user credentials
#    Then I should see the processed credentials

#    Scenario: Data Table Example as a List of Strings
#        Given the following user credentials:
#        | user1 |
#        | user2 |
#        When I process the user credentials
#        Then I should see the processed credentials

  Scenario: Data Table Example using @DataTableType
    Given the following book details:
      | title                 | author              | year |
      | The Great Gatsby      | F. Scott Fitzgerald | 1925 |
      | To Kill a Mockingbird | Harper Lee          | 1960 |
    When I process the book details
    Then I should see the the count of books processed as 2