Feature: Invalid login attempts

  Scenario: Trying multiple invalid logins
    When I try to login with the following credentials:
      | username | password    | expectedError         |
      | alice    | wrongpass   | Invalid credentials   |
      | bob      |             | Password required     |
      |          | secret      | Username required     |
      | root     | foo         | Account locked        |
    Then I should see error messages for all