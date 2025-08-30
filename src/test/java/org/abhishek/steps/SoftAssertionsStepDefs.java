package org.abhishek.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SoftAssertionsStepDefs {
    private final List<String> actualErrors = new ArrayList<>();
    private List<Map<String, String>> userDataTable = new ArrayList<>(); // Stores the original input for assertion

    @When("I try to login with the following credentials:")
    public void try_login(List<Map<String, String>> userData) {
        this.userDataTable = userData;
        for (Map<String, String> row : userData) {
            String username = row.get("username");
            String password = row.get("password");

            String error;
            // Simulated validation logic
            // Here we are simulating what will be the actual error from backend,
            // and then we will check if the error was as per expectation.
            if (username == null || username.isBlank()) {
                error = "Username required";
            } else if (password == null || password.isBlank()) {
                error = "Password required";
            } else if ("root".equals(username)) {
                error = "Account locked";
            } else {
                error = "Invalid credentials";
            }
            actualErrors.add(error);
        }
    }

    @Then("I should see error messages for all")
    public void should_see_error_messages_for_all() {
        // The purpose of SoftAssertions
        SoftAssertions softly = new SoftAssertions();
        for (int i = 0; i < userDataTable.size(); i++) {
            String expected = userDataTable.get(i).get("expectedError");
            String actual = actualErrors.get(i);
            softly.assertThat(actual)
                    .as("Row %d: expected error '%s' but got '%s'", i, expected, actual)
                    .isEqualTo(expected);
        }
        softly.assertAll(); // Only here will any assertion failures throw
    }
}
