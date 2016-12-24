package com.softwaretestingtraning.app;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class RepetitionRule extends TestWatcher {

    @Override
    protected void starting(Description description) {
        if (description.getAnnotation(NeedsRepetition.class) != null) {
            System.out.println("This test is annotated with @NeedsRepetition");
        }
    }

}
