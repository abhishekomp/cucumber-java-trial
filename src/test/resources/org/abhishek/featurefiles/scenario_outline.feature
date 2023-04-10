@second-feature
Feature: Cucumber Feature File for demonstrating Scenario Outline
  This is a simple feature file to try out Cucumber for Java

  @scenario-outline-demo-string
  Scenario Outline: Login to web portal using scenario outline
    Given I open the login page for my web portal
    Then I enter "<username>" and "<password>"
    Then I click login button
    Then I am on the homepage
    Examples:
      | username | password |
      | admin    | admin    |
      | testing  | qa       |

  Scenario Outline: Example scenario outline using integer index
    Given I open the login page for my web portal <index>
    Then I am on the homepage <index>
    Examples:
      | index |
      | 0     |
      | 1     |
      | 2     |
      | 3     |
      | 4     |

#  Scenario: Login to web portal example two
#    Given I open the login page for my web portal
#    Then I enter the following login credentials
#      | username |         |
#      | password |         |
#    Then I click login button
#    Then I am on the homepage