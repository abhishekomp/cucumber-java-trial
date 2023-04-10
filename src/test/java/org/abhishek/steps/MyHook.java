package org.abhishek.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;

/**
 * Created by sca820 on 13 mars, 2023
 */
public class MyHook {

    @Before
    public void initializeTest() {
        System.out.println("Demonstrating @Before hook");
    }

    @After
    public void teardown() {
        System.out.println("Demonstrating @After hook");
    }
}
