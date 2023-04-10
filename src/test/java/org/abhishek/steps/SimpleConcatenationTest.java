package org.abhishek.steps;

import org.junit.Test;

import java.util.List;

/**
 * Created by sca820 on 16 mars, 2023
 */
public class SimpleConcatenationTest {

    @Test
    public void performConcatenation() {
        SimpleConcatenation simpleConcatenation = new SimpleConcatenation();
        final String result = simpleConcatenation.performConcatenation("Hello", "World");
        System.out.println("result = " + result);
    }

    @Test
    public void print_list() {
        List<String> strings = List.of("I", "got", "the", "power");
        System.out.println(String.join(", ",strings));
    }
}