package com.github.nhojpatrick.hamcrest.datetime.tests;

import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import static com.github.nhojpatrick.hamcrest.datetime.IsBetweenDateTime.betweenLocalDateTime;
import static com.github.nhojpatrick.hamcrest.datetime.IsBetweenDateTime.betweenOffsetDateTime;
import static com.github.nhojpatrick.hamcrest.datetime.IsBetweenDateTime.betweenZonedDateTime;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsBetweenDateTimeTest {

    @Nested
    @DisplayName("betweenLocalDateTime tests")
    class betweenLocalDateTimeTests {

        @Test
        public void valid() {

            final MatcherObjectTester<LocalDateTime> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime after = now.minusDays(2);
            final LocalDateTime before = now.plusDays(2);

            tester.assertValid(now, betweenLocalDateTime(after, before));
        }

        @Test
        public void after() {

            final MatcherObjectTester<LocalDateTime> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime after = now.minusDays(4);
            final LocalDateTime before = now.minusDays(2);

            tester.assertFails(now, betweenLocalDateTime(after, before),
                    String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>",
                            after,
                            before,
                            before,
                            now
                    ));
        }

        @Test
        public void afterNull() {

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime before = now.plusDays(2);

            final Executable testMethod = () -> betweenLocalDateTime(null, before);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied After must not be null")));
        }

        @Test
        public void before() {

            final MatcherObjectTester<LocalDateTime> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime after = now.plusDays(2);
            final LocalDateTime before = now.plusDays(4);

            tester.assertFails(now, betweenLocalDateTime(after, before),
                    String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>",
                            after,
                            before,
                            after,
                            now
                    ));
        }

        @Test
        public void beforeNull() {

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime after = now.minusDays(2);

            final Executable testMethod = () -> betweenLocalDateTime(after, null);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied Before must not be null")));
        }

        @Test
        public void equals() {

            final LocalDateTime now = LocalDateTime.now();

            final Executable testMethod = () -> betweenLocalDateTime(now, now);
            final IllegalStateException exception = assertThrows(IllegalStateException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo(String.format("After <%s> must not equal Before <%s>.",
                    now,
                    now
            ))));
        }

        @Test
        public void wrongWayAround() {

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime after = now.plusDays(2);
            final LocalDateTime before = now.minusDays(2);

            final Executable testMethod = () -> betweenLocalDateTime(after, before);
            final IllegalStateException exception = assertThrows(IllegalStateException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo(String.format("After <%s> must be Before <%s>.",
                    after,
                    before
            ))));
        }

    }

    @Nested
    @DisplayName("betweenOffsetDateTime tests")
    class betweenOffsetDateTimeTests {

        @Test
        public void valid() {

            final MatcherObjectTester<OffsetDateTime> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();
            final OffsetDateTime after = now.minusDays(2);
            final OffsetDateTime before = now.plusDays(2);

            tester.assertValid(now, betweenOffsetDateTime(after, before));
        }

        @Test
        public void after() {

            final MatcherObjectTester<OffsetDateTime> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();
            final OffsetDateTime after = now.minusDays(4);
            final OffsetDateTime before = now.minusDays(2);

            tester.assertFails(now, betweenOffsetDateTime(after, before),
                    String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>",
                            after,
                            before,
                            before,
                            now
                    ));
        }

        @Test
        public void afterNull() {

            final OffsetDateTime now = OffsetDateTime.now();
            final OffsetDateTime before = now.plusDays(2);

            final Executable testMethod = () -> betweenOffsetDateTime(null, before);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied After must not be null")));
        }

        @Test
        public void before() {

            final MatcherObjectTester<OffsetDateTime> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();
            final OffsetDateTime after = now.plusDays(2);
            final OffsetDateTime before = now.plusDays(4);

            tester.assertFails(now, betweenOffsetDateTime(after, before),
                    String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>",
                            after,
                            before,
                            after,
                            now
                    ));
        }

        @Test
        public void beforeNull() {

            final OffsetDateTime now = OffsetDateTime.now();
            final OffsetDateTime after = now.minusDays(2);

            final Executable testMethod = () -> betweenOffsetDateTime(after, null);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied Before must not be null")));
        }

        @Test
        public void equals() {

            final OffsetDateTime now = OffsetDateTime.now();

            final Executable testMethod = () -> betweenOffsetDateTime(now, now);
            final IllegalStateException exception = assertThrows(IllegalStateException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo(String.format("After <%s> must not equal Before <%s>.",
                    now,
                    now
            ))));
        }

        @Test
        public void wrongWayAround() {

            final OffsetDateTime now = OffsetDateTime.now();
            final OffsetDateTime after = now.plusDays(2);
            final OffsetDateTime before = now.minusDays(2);

            final Executable testMethod = () -> betweenOffsetDateTime(after, before);
            final IllegalStateException exception = assertThrows(IllegalStateException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo(String.format("After <%s> must be Before <%s>.",
                    after,
                    before
            ))));
        }

    }

    @Nested
    @DisplayName("betweenZonedDateTime tests")
    class betweenZonedDateTimeTests {

        @Test
        public void valid() {

            final MatcherObjectTester<ZonedDateTime> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime after = now.minusDays(2);
            final ZonedDateTime before = now.plusDays(2);

            tester.assertValid(now, betweenZonedDateTime(after, before));
        }

        @Test
        public void after() {

            final MatcherObjectTester<ZonedDateTime> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime after = now.minusDays(4);
            final ZonedDateTime before = now.minusDays(2);

            tester.assertFails(now, betweenZonedDateTime(after, before),
                    String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>",
                            after,
                            before,
                            before,
                            now
                    ));
        }

        @Test
        public void afterNull() {

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime before = now.plusDays(2);

            final Executable testMethod = () -> betweenZonedDateTime(null, before);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied After must not be null")));
        }

        @Test
        public void before() {

            final MatcherObjectTester<ZonedDateTime> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime after = now.plusDays(2);
            final ZonedDateTime before = now.plusDays(4);

            tester.assertFails(now, betweenZonedDateTime(after, before),
                    String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>",
                            after,
                            before,
                            after,
                            now
                    ));
        }

        @Test
        public void beforeNull() {

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime after = now.minusDays(2);

            final Executable testMethod = () -> betweenZonedDateTime(after, null);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied Before must not be null")));
        }

        @Test
        public void equals() {

            final ZonedDateTime now = ZonedDateTime.now();

            final Executable testMethod = () -> betweenZonedDateTime(now, now);
            final IllegalStateException exception = assertThrows(IllegalStateException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo(String.format("After <%s> must not equal Before <%s>.",
                    now,
                    now
            ))));
        }

        @Test
        public void wrongWayAround() {

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime after = now.plusDays(2);
            final ZonedDateTime before = now.minusDays(2);

            final Executable testMethod = () -> betweenZonedDateTime(after, before);
            final IllegalStateException exception = assertThrows(IllegalStateException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo(String.format("After <%s> must be Before <%s>.",
                    after,
                    before
            ))));
        }

    }

}
