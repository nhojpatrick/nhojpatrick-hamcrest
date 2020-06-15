package com.github.nhojpatrick.hamcrest.lang.tests;

import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;

import static com.github.nhojpatrick.hamcrest.lang.IsThrowable.throwable;

public class IsThrowableTest {

    class TestingIsThrowableException
            extends Throwable {

        public TestingIsThrowableException() {
            super();
        }

        public TestingIsThrowableException(final String message) {
            super(message);
        }

    }

    private static final MatcherObjectTester<Throwable> TESTER = new MatcherObjectTester<>();

    @TestFactory
    public Collection<DynamicTest> nullActual() {

        return Arrays.asList(

                DynamicTest.dynamicTest("Message", () -> {
                    TESTER.assertFails(null, throwable("message"),
                            "\nExpected: Throwable with message \"message\"\n      but: was null");
                }),

                DynamicTest.dynamicTest("Type", () -> {
                    TESTER.assertFails(null, throwable(TestingIsThrowableException.class),
                            "\nExpected: Throwable of class \"com.github.nhojpatrick.hamcrest.lang.tests.IsThrowableTest$TestingIsThrowableException\"\n      but: was null");
                }),

                DynamicTest.dynamicTest("Type & Message", () -> {
                    TESTER.assertFails(null, throwable(TestingIsThrowableException.class, "message"),
                            "\nExpected: Throwable of class \"com.github.nhojpatrick.hamcrest.lang.tests.IsThrowableTest$TestingIsThrowableException\" with message \"message\"\n      but: was null");
                })

        );
    }

    @TestFactory
    public Collection<DynamicTest> match() {

        return Arrays.asList(

                DynamicTest.dynamicTest("Message", () -> {
                    final TestingIsThrowableException actual = new TestingIsThrowableException("message");
                    TESTER.assertValid(actual, throwable("message"));
                }),

                DynamicTest.dynamicTest("Type", () -> {
                    final TestingIsThrowableException actual = new TestingIsThrowableException();
                    TESTER.assertValid(actual, throwable(TestingIsThrowableException.class));
                }),

                DynamicTest.dynamicTest("Type & Message", () -> {
                    final TestingIsThrowableException actual = new TestingIsThrowableException("message");
                    TESTER.assertValid(actual, throwable(TestingIsThrowableException.class, "message"));
                })

        );
    }

    @TestFactory
    public Collection<DynamicTest> mismatch() {

        return Arrays.asList(

                DynamicTest.dynamicTest("Message", () -> {
                    final TestingIsThrowableException actual = new TestingIsThrowableException("message");
                    TESTER.assertFails(actual, throwable("different"),
                            "\nExpected: Throwable with message \"different\"\n      but: was Throwable with message \"message\"");
                }),

                DynamicTest.dynamicTest("Type", () -> {
                    final TestingIsThrowableException actual = new TestingIsThrowableException();
                    TESTER.assertFails(actual, throwable(RuntimeException.class),
                            "\nExpected: Throwable of class \"java.lang.RuntimeException\"\n      but: was Throwable of class \"com.github.nhojpatrick.hamcrest.lang.tests.IsThrowableTest$TestingIsThrowableException\"");
                }),

                DynamicTest.dynamicTest("Type & Message", () -> {
                    final TestingIsThrowableException actual = new TestingIsThrowableException("message");
                    TESTER.assertFails(actual, throwable(RuntimeException.class, "different"),
                            "\nExpected: Throwable of class \"java.lang.RuntimeException\" with message \"different\"\n      but: was Throwable of class \"com.github.nhojpatrick.hamcrest.lang.tests.IsThrowableTest$TestingIsThrowableException\" with message \"message\"");
                })

        );
    }

}
