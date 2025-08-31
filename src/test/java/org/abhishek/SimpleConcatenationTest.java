package org.abhishek;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by sca820 on 16 mars, 2023
 */
public class SimpleConcatenationTest {

    @Test
    public void performConcatenation() {
        SimpleConcatenation simpleConcatenation = new SimpleConcatenation();
        final String result = simpleConcatenation.performConcatenation("Hello", "World");
        //System.out.println("result = " + result);
        // JUnit4 assertion
        assertEquals("HelloWorld", result);
        // AssertJ assertion
        assertThat(result).isEqualTo("HelloWorld");
    }

    @Test
    public void print_list() {
        List<String> strings = List.of("I", "got", "the", "power");
        System.out.println(String.join(", ",strings));
        // AssertJ assertion
        assertThat(strings).isEqualTo(List.of("I", "got", "the", "power"));
    }
}