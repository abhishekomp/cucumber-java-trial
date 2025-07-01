package org.abhishek.steps;

import io.cucumber.java.ParameterType;

import java.time.LocalDate;

/**
 * This class maps cucumber {customType} in expressions to their respective java type.
 * The name 'customType' would need to match a method name below and have the @ParameterType annotation.
 */
public class CucumberParameterTypes {

    @ParameterType(value = "[A-Z_0-9]+")
    public UserReference userReference(String userReference) {
        return UserReference.valueOf(userReference);
    }

    @ParameterType("LOW|MEDIUM|HIGH")
    public Priority priority(String value) {
        // This method converts a string value to a Priority enum type
        // The regex matches the strings LOW, MEDIUM, or HIGH
        // Cucumber will automatically call this method when it encounters a parameter of type {priority} in a step definition
        return Priority.valueOf(value);
    }

    @ParameterType("\\d{4}-\\d{2}-\\d{2}")
    public LocalDate isoDate(String date) {
        // This method converts a string in the format YYYY-MM-DD to a LocalDate object
        return LocalDate.parse(date);
    }

    //@ParameterType("T-\\d+")
    @ParameterType("T-\\d{5}")
    public String orderNum(String id) {
        // Here we are defining the format of the order number as T- followed by exactly 5 digits
        return id; // Could wrap in a custom type as well
    }
}
