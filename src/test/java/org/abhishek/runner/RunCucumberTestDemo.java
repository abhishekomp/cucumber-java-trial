package org.abhishek.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Created by sca820 on 10 apr., 2023
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/org/abhishek/featurefiles",
        glue = {"org.abhishek.steps"},
        plugin = {"pretty",
                "json:target/test-cucumber_report.json",
                "html:target/html-reports.htm"}
        )
public class RunCucumberTestDemo {
    // This class will be used to run the Cucumber tests with JUnit
    // The @RunWith annotation tells JUnit to use Cucumber as the test runner
    // The @CucumberOptions annotation allows us to specify various options for running the tests {

}
