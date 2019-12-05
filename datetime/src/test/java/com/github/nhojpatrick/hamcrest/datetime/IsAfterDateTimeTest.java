package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import static com.github.nhojpatrick.hamcrest.datetime.IsAfterDateTime.afterLocalDateTime;
import static com.github.nhojpatrick.hamcrest.datetime.IsAfterDateTime.afterOffsetDateTime;
import static com.github.nhojpatrick.hamcrest.datetime.IsAfterDateTime.afterZonedDateTime;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsAfterDateTimeTest {

    @Test
    public void checkIs_StaticUtilityClass() {
        final Executable testMethod = () -> {
            new IsAfterDateTime();
        };
        final AssertionError thrown = assertThrows(AssertionError.class, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo("Static utility class - cannot be instantiated."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Nested
    @DisplayName("afterLocalDateTime tests")
    class afterLocalDateTimeTests {

        @Test
        public void after() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime future = now.plusYears(1).plusMonths(2).plusDays(3);

            tester.assertFails(now, afterLocalDateTime(future),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            future,
                            now
                    ));
        }

        @Test
        public void before() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime past = now.minusYears(1).minusMonths(2).minusDays(3);

            tester.assertValid(now, afterLocalDateTime(past));
        }

        @Test
        public void beforeNull() {

            final LocalDateTime now = LocalDateTime.now();

            final Executable testMethod = () -> afterLocalDateTime(null);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied After must not be null")));
        }

        @Test
        public void equals() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();

            tester.assertFails(now, afterLocalDateTime(now),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

    }

    @Nested
    @DisplayName("afterLocalDateTime(CompareType) tests")
    class afterLocalDateTimeCompareTypeTests {

        @Test
        public void afterExclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime future = now.plusYears(1).plusMonths(2).plusDays(3);

            tester.assertFails(now, afterLocalDateTime(future, CompareType.EXCLUSIVE),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            future,
                            now
                    ));
        }

        @Test
        public void afterInclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime future = now.plusYears(1).plusMonths(2).plusDays(3);

            tester.assertFails(now, afterLocalDateTime(future, CompareType.INCLUSIVE),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            future,
                            now
                    ));
        }

        @Test
        public void beforeExclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime past = now.minusYears(1).minusMonths(2).minusDays(3);

            tester.assertValid(now, afterLocalDateTime(past, CompareType.EXCLUSIVE));
        }

        @Test
        public void beforeInclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime past = now.minusYears(1).minusMonths(2).minusDays(3);

            tester.assertValid(now, afterLocalDateTime(past, CompareType.INCLUSIVE));
        }

        @Test
        public void beforeCompareTypeNull() {

            final LocalDateTime now = LocalDateTime.now();

            final Executable testMethod = () -> afterLocalDateTime(now, null);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied CompareType must not be null")));
        }

        @Test
        public void beforeNullExclusive() {

            final Executable testMethod = () -> afterLocalDateTime(null, CompareType.EXCLUSIVE);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied After must not be null")));
        }

        @Test
        public void beforeNullInclusive() {

            final Executable testMethod = () -> afterLocalDateTime(null, CompareType.INCLUSIVE);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied After must not be null")));
        }

        @Test
        public void equalsExclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();

            tester.assertFails(now, afterLocalDateTime(now, CompareType.EXCLUSIVE),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

        @Test
        public void equalsInclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();

            tester.assertFails(now, afterLocalDateTime(now, CompareType.INCLUSIVE),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

    }

    @Nested
    @DisplayName("afterOffsetDateTime tests")
    class afterOffsetDateTimeTests {

        @Test
        public void after() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();
            final OffsetDateTime future = now.plusYears(1).plusMonths(2).plusDays(3);

            tester.assertFails(now, afterOffsetDateTime(future),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            future,
                            now
                    ));
        }

        @Test
        public void before() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();
            final OffsetDateTime past = now.minusYears(1).minusMonths(2).minusDays(3);

            tester.assertValid(now, afterOffsetDateTime(past));
        }

        @Test
        public void beforeNull() {

            final OffsetDateTime now = OffsetDateTime.now();

            final Executable testMethod = () -> afterOffsetDateTime(null);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied After must not be null")));
        }

        @Test
        public void equals() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();

            tester.assertFails(now, afterOffsetDateTime(now),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

    }

    @Nested
    @DisplayName("afterOffsetDateTime(CompareType) tests")
    class afterOffsetDateTimeCompareTypeTests {

        @Test
        public void afterExclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();
            final OffsetDateTime future = now.plusYears(1).plusMonths(2).plusDays(3);

            tester.assertFails(now, afterOffsetDateTime(future, CompareType.EXCLUSIVE),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            future,
                            now
                    ));
        }

        @Test
        public void afterInclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();
            final OffsetDateTime future = now.plusYears(1).plusMonths(2).plusDays(3);

            tester.assertFails(now, afterOffsetDateTime(future, CompareType.INCLUSIVE),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            future,
                            now
                    ));
        }

        @Test
        public void beforeExclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();
            final OffsetDateTime past = now.minusYears(1).minusMonths(2).minusDays(3);

            tester.assertValid(now, afterOffsetDateTime(past, CompareType.EXCLUSIVE));
        }

        @Test
        public void beforeInclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();
            final OffsetDateTime past = now.minusYears(1).minusMonths(2).minusDays(3);

            tester.assertValid(now, afterOffsetDateTime(past, CompareType.INCLUSIVE));
        }

        @Test
        public void beforeCompareTypeNull() {

            final OffsetDateTime now = OffsetDateTime.now();

            final Executable testMethod = () -> afterOffsetDateTime(now, null);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied CompareType must not be null")));
        }

        @Test
        public void beforeNullExclusive() {

            final Executable testMethod = () -> afterOffsetDateTime(null, CompareType.EXCLUSIVE);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied After must not be null")));
        }

        @Test
        public void beforeNullInclusive() {

            final Executable testMethod = () -> afterOffsetDateTime(null, CompareType.INCLUSIVE);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied After must not be null")));
        }

        @Test
        public void equalsExclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();

            tester.assertFails(now, afterOffsetDateTime(now, CompareType.EXCLUSIVE),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

        @Test
        public void equalsInclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();

            tester.assertFails(now, afterOffsetDateTime(now, CompareType.INCLUSIVE),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

    }


    @Nested
    @DisplayName("afterZonedDateTime tests")
    class afterZonedDateTimeTests {

        @Test
        public void after() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime future = now.plusYears(1).plusMonths(2).plusDays(3);

            tester.assertFails(now, afterZonedDateTime(future),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            future,
                            now
                    ));
        }

        @Test
        public void before() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime past = now.minusYears(1).minusMonths(2).minusDays(3);

            tester.assertValid(now, afterZonedDateTime(past));
        }

        @Test
        public void beforeNull() {

            final ZonedDateTime now = ZonedDateTime.now();

            final Executable testMethod = () -> afterZonedDateTime(null);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied After must not be null")));
        }

        @Test
        public void equals() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();

            tester.assertFails(now, afterZonedDateTime(now),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

    }

    @Nested
    @DisplayName("afterZonedDateTime(CompareType) tests")
    class afterZonedDateTimeCompareTypeTests {

        @Test
        public void afterExclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime future = now.plusYears(1).plusMonths(2).plusDays(3);

            tester.assertFails(now, afterZonedDateTime(future, CompareType.EXCLUSIVE),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            future,
                            now
                    ));
        }

        @Test
        public void afterInclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime future = now.plusYears(1).plusMonths(2).plusDays(3);

            tester.assertFails(now, afterZonedDateTime(future, CompareType.INCLUSIVE),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            future,
                            now
                    ));
        }

        @Test
        public void beforeExclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime past = now.minusYears(1).minusMonths(2).minusDays(3);

            tester.assertValid(now, afterZonedDateTime(past, CompareType.EXCLUSIVE));
        }

        @Test
        public void beforeInclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime past = now.minusYears(1).minusMonths(2).minusDays(3);

            tester.assertValid(now, afterZonedDateTime(past, CompareType.INCLUSIVE));
        }

        @Test
        public void beforeCompareTypeNull() {

            final ZonedDateTime now = ZonedDateTime.now();

            final Executable testMethod = () -> afterZonedDateTime(now, null);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied CompareType must not be null")));
        }

        @Test
        public void beforeNullExclusive() {

            final Executable testMethod = () -> afterZonedDateTime(null, CompareType.EXCLUSIVE);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied After must not be null")));
        }

        @Test
        public void beforeNullInclusive() {

            final Executable testMethod = () -> afterZonedDateTime(null, CompareType.INCLUSIVE);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied After must not be null")));
        }

        @Test
        public void equalsExclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();

            tester.assertFails(now, afterZonedDateTime(now, CompareType.EXCLUSIVE),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

        @Test
        public void equalsInclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();

            tester.assertFails(now, afterZonedDateTime(now, CompareType.INCLUSIVE),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

    }

}
