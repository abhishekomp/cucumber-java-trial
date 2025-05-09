package org.abhishek.steps;

import io.cucumber.java.ParameterType;

/**
 * This class maps cucumber {customType} in expressions to their respective java type.
 * The name 'customType' would need to match a method name below and have the @ParameterType annotation.
 */
public class CucumberParameterTypes {

    @ParameterType(value = "[A-Z_0-9]+")
    public UserReference userReference(String userReference) {
        return UserReference.valueOf(userReference);
    }

}
