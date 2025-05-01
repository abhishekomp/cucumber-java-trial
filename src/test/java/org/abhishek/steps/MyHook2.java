package org.abhishek.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class MyHook2 {

    @After(value = "@RemoveFiles", order = Integer.MAX_VALUE)
    public void performSomeClean3() {
        System.out.println("Demonstrating <<<<ANOTHER>>>> hook with order = Integer.MAX_VALUE");
    }

}
