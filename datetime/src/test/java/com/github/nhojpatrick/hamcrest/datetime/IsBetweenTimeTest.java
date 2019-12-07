package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalTime;
import java.time.OffsetTime;

import static com.github.nhojpatrick.hamcrest.datetime.IsBetweenTime.betweenLocalTime;
import static com.github.nhojpatrick.hamcrest.datetime.IsBetweenTime.betweenOffsetTime;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsBetweenTimeTest {

    @Test
    public void checkIs_StaticUtilityClass() {

        final Executable testMethod = IsBetweenTime::new;
        final AssertionError thrown = assertThrows(AssertionError.class, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo("Static utility class - cannot be instantiated."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Nested
    @DisplayName("betweenLocalTime tests")
    class betweenLocalTimeTests {

        @Test
        public void valid() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(9);
            final LocalTime after = now.minusHours(2);
            final LocalTime before = now.plusHours(2);

            tester.assertValid(now, betweenLocalTime(after, before));
        }

        @Test
        public void after() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(9);
            final LocalTime after = now.minusHours(4);
            final LocalTime before = now.minusHours(2);

            tester.assertFails(now, betweenLocalTime(after, before),
                    String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>",
                            after,
                            before,
                            before,
                            now
                    ));
        }

        @Test
        public void afterNull() {

            final LocalTime now = LocalTime.now().withHour(9);
            final LocalTime before = now.plusHours(2);

            final Executable testMethod = () -> betweenLocalTime(null, before);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied After must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void before() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(9);
            final LocalTime after = now.plusHours(2);
            final LocalTime before = now.plusHours(4);

            tester.assertFails(now, betweenLocalTime(after, before),
                    String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>",
                            after,
                            before,
                            after,
                            now
                    ));
        }

        @Test
        public void beforeNull() {

            final LocalTime now = LocalTime.now().withHour(9);
            final LocalTime after = now.minusHours(2);

            final Executable testMethod = () -> betweenLocalTime(after, null);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied Before must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void equals() {

            final LocalTime now = LocalTime.now().withHour(9);

            final Executable testMethod = () -> betweenLocalTime(now, now);
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

            final LocalTime now = LocalTime.now().withHour(9);
            final LocalTime after = now.plusHours(2);
            final LocalTime before = now.minusHours(2);

            final Executable testMethod = () -> betweenLocalTime(after, before);
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

    @Nested
    @DisplayName("betweenOffsetTime tests")
    class betweenOffsetTimeTests {

        @Test
        public void valid() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(9);
            final OffsetTime after = now.minusHours(2);
            final OffsetTime before = now.plusHours(2);

            tester.assertValid(now, betweenOffsetTime(after, before));
        }

        @Test
        public void after() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(9);
            final OffsetTime after = now.minusHours(4);
            final OffsetTime before = now.minusHours(2);

            tester.assertFails(now, betweenOffsetTime(after, before),
                    String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>",
                            after,
                            before,
                            before,
                            now
                    ));
        }

        @Test
        public void afterNull() {

            final OffsetTime now = OffsetTime.now().withHour(9);
            final OffsetTime before = now.plusHours(2);

            final Executable testMethod = () -> betweenOffsetTime(null, before);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied After must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void before() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(9);
            final OffsetTime after = now.plusHours(2);
            final OffsetTime before = now.plusHours(4);

            tester.assertFails(now, betweenOffsetTime(after, before),
                    String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>",
                            after,
                            before,
                            after,
                            now
                    ));
        }

        @Test
        public void beforeNull() {

            final OffsetTime now = OffsetTime.now().withHour(9);
            final OffsetTime after = now.minusHours(2);

            final Executable testMethod = () -> betweenOffsetTime(after, null);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied Before must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void equals() {

            final OffsetTime now = OffsetTime.now().withHour(9);

            final Executable testMethod = () -> betweenOffsetTime(now, now);
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

            final OffsetTime now = OffsetTime.now().withHour(9);
            final OffsetTime after = now.plusHours(2);
            final OffsetTime before = now.minusHours(2);

            final Executable testMethod = () -> betweenOffsetTime(after, before);
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
