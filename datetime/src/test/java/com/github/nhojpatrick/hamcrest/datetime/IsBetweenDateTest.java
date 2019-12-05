package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static com.github.nhojpatrick.hamcrest.datetime.IsBetweenDate.betweenLocalDate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsBetweenDateTest {

    @Test
    public void checkIs_StaticUtilityClass() {
        final Executable testMethod = () -> {
            new IsBetweenDate();
        };
        final AssertionError thrown = assertThrows(AssertionError.class, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo("Static utility class - cannot be instantiated."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Nested
    @DisplayName("betweenLocalDate tests")
    class betweenLocalDateTests {

        @Test
        public void valid() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();
            final LocalDate after = now.minusDays(2);
            final LocalDate before = now.plusDays(2);

            tester.assertValid(now, betweenLocalDate(after, before));
        }

        @Test
        public void after() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();
            final LocalDate after = now.minusDays(4);
            final LocalDate before = now.minusDays(2);

            tester.assertFails(now, betweenLocalDate(after, before),
                    String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>",
                            after,
                            before,
                            before,
                            now
                    ));
        }

        @Test
        public void afterNull() {

            final LocalDate now = LocalDate.now();
            final LocalDate before = now.plusDays(2);

            final Executable testMethod = () -> betweenLocalDate(null, before);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied After must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void before() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();
            final LocalDate after = now.plusDays(2);
            final LocalDate before = now.plusDays(4);

            tester.assertFails(now, betweenLocalDate(after, before),
                    String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>",
                            after,
                            before,
                            after,
                            now
                    ));
        }

        @Test
        public void beforeNull() {

            final LocalDate now = LocalDate.now();
            final LocalDate after = now.minusDays(2);

            final Executable testMethod = () -> betweenLocalDate(after, null);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied Before must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void equals() {

            final LocalDate now = LocalDate.now();

            final Executable testMethod = () -> betweenLocalDate(now, now);
            final IllegalStateException thrown = assertThrows(IllegalStateException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo(String.format("After <%s> must not equal Before <%s>.",
                            now,
                            now
                    )))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void wrongWayAround() {

            final LocalDate now = LocalDate.now();
            final LocalDate after = now.plusDays(2);
            final LocalDate before = now.minusDays(2);

            final Executable testMethod = () -> betweenLocalDate(after, before);
            final IllegalStateException thrown = assertThrows(IllegalStateException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo(String.format("After <%s> must be Before <%s>.",
                            after,
                            before
                    )))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

    }

}
