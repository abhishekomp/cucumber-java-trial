package org.abhishek.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.Map;

/**
 * Created by sca820 on 13 mars, 2023
 */
public class MyStepDefinitions {

    //@Given("I open the login page for my web portal")
    @Given("^I open the login page for my web portal$")
    public void i_open_the_login_page_for_my_web_portal() {
        System.out.println(">>>>>>>I open the login page for the web portal");
    }

    @Then("I enter the username as {string} and password as {string}")
    public void i_enter_the_username_as_and_password_as(String username, String password) {
        System.out.println(">>>>>>>>>>I enter username as '" + username + "' and password as '" + password + "'");
    }

    @Then("I click login button")
    public void iClickLoginButton() {
        System.out.println("I click the login button");
    }

    @Then("I am on the homepage")
    public void i_am_on_the_homepage() {
        System.out.println("I can view the homepage after login");
    }

    @Then("I enter the following login credentials")
    public void iEnterTheFollowingLoginCredentials(Map<String, String> params) {
        String username = params.getOrDefault("username", "default_username");
        String password = params.getOrDefault("password", "default_password");
        System.out.println("Using username '" + username + "' and password '" + password + "' for login");
    }

    //@Then("I enter <username> and <password>")
    //@Then("I enter {string} and {string}")
    @Then("^I enter \"([^\"]*)\" and \"([^\"]*)\"$")
    //@Then("^I enter \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iEnterUsernameAndPassword(String username, String password) {
        System.out.println("Using username '" + username + "' and password '" + password + "' for login");
    }

    //@Given("I open the login page for my web portal <index>")
    @Given("I open the login page for my web portal {int}")
    public void iOpenTheLoginPageForMyWebPortalIndex(int index) {
        System.out.println("Opening web portal with index " + index);
    }

    @Then("I am on the homepage {int}")
    public void iAmOnTheHomepageIndex(int index) {
        System.out.println("I am on the homepage " + index);
    }

    @Given("An Order number is received")
    public void anOrderNumberIsReceived() {
        System.out.println("Processing OrderNumber = " + System.getenv("TEST_ORDERNUM"));
    }

    @Then("The Order is processed")
    public void theOrderIsProcessed() {
        System.out.println("Order was processed");
    }

    @Given("{userReference} has created the organization {string}")
    public void super_userHasCreatedTheOrganization(UserReference userReference, String orgName) {
        System.out.println(userReference.getReference() + " has created an organization with name " + orgName);
    }

    @Given("I have a user with role {}")
    public void iHaveAUserWithRoleADMIN(Role role) {
        System.out.println("I have a user with role: " + role);
    }

    @Then("I can perform actions that only {} can perform")
    public void iCanPerformActionsThatOnlyADMINCanPerform(Role role) {
        if (role == Role.ADMIN) {
            System.out.println("Performing actions that only ADMIN can perform");
        } else {
            System.out.println("Cannot perform actions for role: " + role);
        }
    }
}
