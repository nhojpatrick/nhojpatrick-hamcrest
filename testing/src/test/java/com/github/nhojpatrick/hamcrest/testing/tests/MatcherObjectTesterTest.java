package com.github.nhojpatrick.hamcrest.testing.tests;

import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MatcherObjectTesterTest {

    private static final MatcherObjectTester<String> TESTER = new MatcherObjectTester<>();

    private static final String EMPTY_STRING = "";
    private static final String QWERTY = "Qwerty";
    private static final String POIUYT = "Poiuyt";

    @Nested
    @DisplayName("MatcherObjectTester assertFails tests")
    class assertFails {

        @Test
        public void matcherNull() {
            final Executable testMethod = () -> TESTER.assertFails(EMPTY_STRING, null, null);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Null Matcher"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void success() {
            TESTER.assertFails(QWERTY, is(equalTo(POIUYT)), String.format("\nExpected: is \"%s\"\n      but: was \"%s\"", POIUYT, QWERTY));
        }

    }

    @Nested
    @DisplayName("MatcherObjectTester assertValid tests")
    class assertValid {

        @Test
        public void matcherNull() {
            final Executable testMethod = () -> TESTER.assertValid(EMPTY_STRING, null);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Null Matcher"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void success() {
            TESTER.assertValid(QWERTY, is(equalTo(QWERTY)));
        }

    }

}
