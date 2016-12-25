package com.softwaretestingtraning.app;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

class RepetitionRule implements TestRule {

    @Override
    public Statement apply(Statement base, Description desc) {
        int numberOfRepetitions = 1;
        Unstable repetitionAnnotation = desc.getAnnotation(Unstable.class);
        if (repetitionAnnotation != null) {
            System.out.println("This test is annotated with @Unstable");
            numberOfRepetitions = repetitionAnnotation.value();
            System.out.println("The value is " + numberOfRepetitions);

        }
        return new RunTwiceStatement(base, numberOfRepetitions);
    }

    private class RunTwiceStatement extends Statement {

        private final Statement base;
        private final int numberOfRepetitions;

        RunTwiceStatement(Statement base, int numberOfRepetitions) {
            this.base = base;
            this.numberOfRepetitions = numberOfRepetitions;
        }

        @Override
        public void evaluate() throws Throwable {
            for (int i = 0; i < (this.numberOfRepetitions - 1); i++) {
                try {
                    base.evaluate();
                } catch (Throwable t) {
                    System.out.println("Test failed to pass after the " + (i + 1) +
                            " attempt out of " + this.numberOfRepetitions +
                            " maximum. Restarting...");
                }
            }

            base.evaluate();
        }

    }

}
