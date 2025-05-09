package org.abhishek.steps;

import java.util.Objects;

public class UserReference {

    private final String reference;

    private UserReference(String reference){
        this.reference = reference;
    }

    public static UserReference valueOf(String reference) {
        return new UserReference(reference);
    }

    public String getReference() {
        return this.reference;
    }

    @Override
    public String toString() {
        return "UserReference{" +
                "reference='" + reference + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserReference that = (UserReference) o;
        return Objects.equals(reference, that.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(reference);
    }
}
