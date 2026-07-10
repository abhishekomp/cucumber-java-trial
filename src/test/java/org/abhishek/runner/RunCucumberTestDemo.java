package org.abhishek.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Created by sca820 on 10 apr., 2023
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/",
        glue = {"org.abhishek.steps"},
        // Exclude scenarios tagged @demo-regex-mismatch — those are intentionally
        // undefined to demonstrate what happens when a @ParameterType regex doesn't
        // match. They are kept in the feature file for learning purposes but must be
        // excluded here so they don't cause a build failure.
        tags = "not @demo-regex-mismatch",
        plugin = {"pretty",
                "json:target/test-cucumber_report.json",
                "html:target/html-reports.htm"}
        )
public class RunCucumberTestDemo {
    // This class will be used to run the Cucumber tests with JUnit
    // The @RunWith annotation tells JUnit to use Cucumber as the test runner
    // The @CucumberOptions annotation allows us to specify various options for running the tests {

}
