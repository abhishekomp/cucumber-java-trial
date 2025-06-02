package org.abhishek.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.abhishek.model.Credentials;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyStepDefsDataTable {

    private final List<Credentials> credentials = new ArrayList<>();

    /**
     * This method is used to handle a DataTable containing user credentials.
     * The DataTable is expected to have two columns: "username" and "password".
     *
     * @param dataTable The DataTable containing user credentials.
     */
    @Given("the following user credentials:")
    public void theFollowingUserCredentials(DataTable dataTable) {
        // Convert the DataTable to a List of Maps
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
        // Iterate through each row of credentials
        for (Map<String, String> row : credentials) {
            // Extract username and password from the row
            String username = row.get("username");
            String password = row.get("password");
            // Print or process the credentials as needed
            System.out.println("Username: " + username + ", Password: " + password);
            // You can create a Credentials object or perform any other processing here
            this.credentials.add(new Credentials(username, password));
        }
    }

    @When("I process the user credentials")
    public void iProcessTheUserCredentials() {
        // This method can be used to process the user credentials
        // For demonstration, we will just print a message
        System.out.println("Processing user credentials...");
        // You can add logic here to handle the credentials as needed
        credentials.forEach(System.out::println);
    }

    @Then("I should see the processed credentials")
    public void iShouldSeeTheProcessedCredentials() {
        // This method can be used to verify the processed credentials
        // For demonstration, we will just print a message
        System.out.println("Processed user credentials:");
        // Print each credential
        for (Credentials cred : credentials) {
            System.out.println(cred);
        }
        // You can add assertions or further processing here as needed
    }
}
