package com.github.nhojpatrick.hamcrest.optionals;

import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.github.nhojpatrick.hamcrest.optionals.IsOptional.optionalContains;
import static com.github.nhojpatrick.hamcrest.optionals.IsOptional.optionalIsEmpty;
import static com.github.nhojpatrick.hamcrest.optionals.IsOptional.optionalIsPresent;

public class IsOptionalTest {

    private static final MatcherObjectTester<Optional> TESTER = new MatcherObjectTester<>();

    @Nested
    @DisplayName("Optional Contains tests")
    class optionalContainsTests {

        @Nested
        @DisplayName("expected null")
        class expectedNullTests {

            @Test
            public void empty() {
                final Optional<Object> actual = Optional.empty();
                TESTER.assertValid(actual, optionalContains(null));
            }

            @Test
            public void nullObject() {
                final Object actual = null;
                TESTER.assertFails(actual, optionalContains(null),
                        "\nExpected: Optional containing null\n      but: was null");
            }

            @Test
            public void nullTyped() {
                final Optional<Object> actual = null;
                TESTER.assertFails(actual, optionalContains(null),
                        "\nExpected: Optional containing null\n      but: was null");
            }

            @Test
            public void ofNullableNull() {
                final Optional<Object> actual = Optional.ofNullable(null);
                TESTER.assertValid(actual, optionalContains(null));
            }

            @Test
            public void populated() {
                final Optional<Object> actual = Optional.of("qwerty");
                TESTER.assertFails(actual, optionalContains(null),
                        "\nExpected: Optional containing null\n      but: was <Optional[qwerty]>");
            }

        }

        @Nested
        @DisplayName("expected qwerty")
        class expectedQwertyTests {

            @Test
            public void empty() {
                final Optional<Object> actual = Optional.empty();
                TESTER.assertFails(actual, optionalContains("qwerty"),
                        "\nExpected: Optional containing \"qwerty\"\n      but: was <Optional.empty>");
            }

            @Test
            public void nullObject() {
                final Object actual = null;
                TESTER.assertFails(actual, optionalContains("qwerty"),
                        "\nExpected: Optional containing \"qwerty\"\n      but: was null");
            }

            @Test
            public void nullTyped() {
                final Optional<Object> actual = null;
                TESTER.assertFails(actual, optionalContains("qwerty"),
                        "\nExpected: Optional containing \"qwerty\"\n      but: was null");
            }

            @Test
            public void ofNullableNull() {
                final Optional<Object> actual = Optional.ofNullable(null);
                TESTER.assertFails(actual, optionalContains("qwerty"),
                        "\nExpected: Optional containing \"qwerty\"\n      but: was <Optional.empty>");
            }

            @Test
            public void populated() {
                final Optional<Object> actual = Optional.of("qwerty");
                TESTER.assertValid(actual, optionalContains("qwerty"));
            }

        }

    }

    @Nested
    @DisplayName("Optional Is Empty tests")
    class optionalIsEmpty {

        @Test
        public void empty() {
            final Optional<Object> actual = Optional.empty();
            TESTER.assertValid(actual, optionalIsEmpty());
        }

        @Test
        public void nullObject() {
            final Object actual = null;
            TESTER.assertFails(actual, optionalIsEmpty(),
                    "\nExpected: Optional.empty()\n      but: was null");
        }

        @Test
        public void nullTyped() {
            final Optional<Object> actual = Optional.ofNullable(null);
            TESTER.assertValid(actual, optionalIsEmpty());
        }

        @Test
        public void populated() {
            final Optional<String> actual = Optional.of("qwerty");
            TESTER.assertFails(actual, optionalIsEmpty(),
                    "\nExpected: Optional.empty()\n      but: was <Optional[qwerty]>");
        }

    }

    @Nested
    @DisplayName("Optional Is Present tests")
    class optionalIsPresent {

        @Test
        public void empty() {
            final Optional<Object> actual = Optional.empty();
            TESTER.assertFails(actual, optionalIsPresent(),
                    "\nExpected: Optional.isPresent()\n      but: was <Optional.empty>");
        }

        @Test
        public void nullObject() {
            final Object actual = null;
            TESTER.assertFails(actual, optionalIsPresent(),
                    "\nExpected: Optional.isPresent()\n      but: was null");
        }

        @Test
        public void nullTyped() {
            final Optional<Object> actual = Optional.ofNullable(null);
            TESTER.assertFails(actual, optionalIsPresent(),
                    "\nExpected: Optional.isPresent()\n      but: was <Optional.empty>");
        }

        @Test
        public void populated() {
            final Optional<String> actual = Optional.of("qwerty");
            TESTER.assertValid(actual, optionalIsPresent());
        }

    }

}
