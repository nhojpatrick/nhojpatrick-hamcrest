package com.github.nhojpatrick.hamcrest.lang.tests;

import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.Test;

import static com.github.nhojpatrick.hamcrest.lang.IsToString.toStringGenerated;

public class IsToStringTest {

    public static class IsToStringObject {

        private final String toString;

        public IsToStringObject(final String toString) {
            this.toString = toString;
        }

        @Override
        public String toString() {
            return this.toString;
        }

    }

    private static final MatcherObjectTester<Object> TESTER = new MatcherObjectTester<>();

    @Test
    public void nullActual() {
        TESTER.assertFails(null, toStringGenerated("toStringValue"),
                "\nExpected: Object toString \"toStringValue\"\n      but: was null");
    }

    @Test
    public void match() {
        final IsToStringObject actual = new IsToStringObject("toStringValue");
        TESTER.assertValid(actual, toStringGenerated("toStringValue"));
    }

    @Test
    public void misMatch() {
        final IsToStringObject actual = new IsToStringObject("differentToString");
        TESTER.assertFails(actual, toStringGenerated("toStringValue"),
                "\nExpected: Object toString \"toStringValue\"\n      but: was \"differentToString\"");
    }

}
