package org.abhishek.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.abhishek.model.Book;
import org.abhishek.model.Credentials;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyStepDefsDataTable {

    private final List<Credentials> credentials = new ArrayList<>();
    private final List<Book> books = new ArrayList<>();

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

    /**
     * This method is used to handle a DataTable containing book details.
     * The DataTable is expected to have columns: "title", "author", "year", and "genre".
     *
     * @param books The list of books to be processed.
     */
    @Given("the following book details:")
    public void theFollowingBookDetails(List<Book> books) {
        // The books parameter is automatically populated by Cucumber
        // using the DataTableType defined in DataTableTypeDefinitions class

        // This method can be used to handle book details
        // For demonstration, we will just print a message
        System.out.println("Handling book details...");
        // You can add logic here to process book details as needed
        for (Book book : books) {
            System.out.println("Book: " + book);
            // Add the book to the list of books
            this.books.add(book);
        }
    }

    @When("I process the book details")
    public void iProcessTheBookDetails() {
        // This method can be used to process the book details
        // For demonstration, we will just print a message
        System.out.println("Processing book details...");
        // You can add logic here to handle the book details as needed
    }

    @Then("I should see the the count of books processed as {int}")
    public void iShouldSeeTheTheCountOfBooksProcessedAs(int expectedCount) {
        // This method can be used to verify the count of processed books
        System.out.println("Verifying the count of processed books...");
        // Check if the count matches the expected count
        if (books.size() == expectedCount) {
            System.out.println("Count matches: " + expectedCount);
        } else {
            System.out.println("Count does not match. Expected: " + expectedCount + ", Actual: " + books.size());
        }
    }
}
