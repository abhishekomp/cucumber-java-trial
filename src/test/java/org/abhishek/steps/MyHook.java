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

    @After(value = "@RemoveFiles", order = 1)
    public void performSomeClean1() {
        System.out.println("Demonstrating hook with order = 1");
    }

    @After(value = "@RemoveFiles", order = 2)
    public void performSomeClean2() {
        System.out.println("Demonstrating hook with order = 2");
    }

    @After(value = "@RemoveFiles", order = Integer.MAX_VALUE)
    public void performSomeClean3() {
        System.out.println("Demonstrating hook with order = Integer.MAX_VALUE");
    }

    @After(value = "@RemoveFiles", order = 0)
    public void performSomeClean4() {
        System.out.println("Demonstrating hook with order = 0");
    }

    //This is the order in which the after hooks were executed
//    Demonstrating hook with order = Integer.MAX_VALUE
//    Demonstrating @After hook
//    Demonstrating hook with order = 2
//    Demonstrating hook with order = 1
//    Demonstrating hook with order = 0
}
