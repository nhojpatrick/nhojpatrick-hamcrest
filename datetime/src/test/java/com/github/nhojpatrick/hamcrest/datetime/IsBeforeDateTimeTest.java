package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import static com.github.nhojpatrick.hamcrest.datetime.IsBeforeDateTime.beforeLocalDateTime;
import static com.github.nhojpatrick.hamcrest.datetime.IsBeforeDateTime.beforeOffsetDateTime;
import static com.github.nhojpatrick.hamcrest.datetime.IsBeforeDateTime.beforeZonedDateTime;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsBeforeDateTimeTest {

    @Test
    public void checkIs_StaticUtilityClass() {
        final Executable testMethod = () -> {
            new IsBeforeDateTime();
        };
        final AssertionError thrown = assertThrows(AssertionError.class, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo("Static utility class - cannot be instantiated."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Nested
    @DisplayName("beforeLocalDateTime tests")
    class beforeLocalDateTimeTests {

        @Test
        public void after() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertValid(now, beforeLocalDateTime(future));
        }

        @Test
        public void before() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertFails(now, beforeLocalDateTime(past),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            past,
                            now
                    ));
        }

        @Test
        public void beforeNull() {

            final LocalDateTime now = LocalDateTime.now();

            final Executable testMethod = () -> beforeLocalDateTime(null);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied Before must not be null")));
        }

        @Test
        public void equals() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();

            tester.assertFails(now, beforeLocalDateTime(now),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

    }

    @Nested
    @DisplayName("beforeLocalDateTime(CompareType) tests")
    class beforeLocalDateTimeCompareTypeTests {

        @Test
        public void afterExclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertValid(now, beforeLocalDateTime(future, CompareType.EXCLUSIVE));
        }

        @Test
        public void afterInclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertValid(now, beforeLocalDateTime(future, CompareType.INCLUSIVE));
        }

        @Test
        public void beforeExclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertFails(now, beforeLocalDateTime(past, CompareType.EXCLUSIVE),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            past,
                            now
                    ));
        }

        @Test
        public void beforeInclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertFails(now, beforeLocalDateTime(past, CompareType.INCLUSIVE),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            past,
                            now
                    ));
        }

        @Test
        public void beforeCompareTypeNull() {

            final LocalDateTime now = LocalDateTime.now();

            final Executable testMethod = () -> beforeLocalDateTime(now, null);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied CompareType must not be null")));
        }

        @Test
        public void beforeNullExclusive() {

            final Executable testMethod = () -> beforeLocalDateTime(null, CompareType.EXCLUSIVE);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied Before must not be null")));
        }

        @Test
        public void beforeNullInclusive() {

            final Executable testMethod = () -> beforeLocalDateTime(null, CompareType.INCLUSIVE);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied Before must not be null")));
        }

        @Test
        public void equalsExclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();

            tester.assertFails(now, beforeLocalDateTime(now, CompareType.EXCLUSIVE),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

        @Test
        public void equalsInclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();

            tester.assertFails(now, beforeLocalDateTime(now, CompareType.INCLUSIVE),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

    }

    @Nested
    @DisplayName("beforeOffsetDateTime tests")
    class beforeOffsetDateTimeTests {

        @Test
        public void after() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();
            final OffsetDateTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertValid(now, beforeOffsetDateTime(future));
        }

        @Test
        public void before() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();
            final OffsetDateTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertFails(now, beforeOffsetDateTime(past),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            past,
                            now
                    ));
        }

        @Test
        public void beforeNull() {

            final OffsetDateTime now = OffsetDateTime.now();

            final Executable testMethod = () -> beforeOffsetDateTime(null);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied Before must not be null")));
        }

        @Test
        public void equals() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();

            tester.assertFails(now, beforeOffsetDateTime(now),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

    }

    @Nested
    @DisplayName("beforeOffsetDateTime(CompareType) tests")
    class beforeOffsetDateTimeCompareTypeTests {

        @Test
        public void afterExclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();
            final OffsetDateTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertValid(now, beforeOffsetDateTime(future, CompareType.EXCLUSIVE));
        }

        @Test
        public void afterInclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();
            final OffsetDateTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertValid(now, beforeOffsetDateTime(future, CompareType.INCLUSIVE));
        }

        @Test
        public void beforeExclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();
            final OffsetDateTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertFails(now, beforeOffsetDateTime(past, CompareType.EXCLUSIVE),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            past,
                            now
                    ));
        }

        @Test
        public void beforeInclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();
            final OffsetDateTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertFails(now, beforeOffsetDateTime(past, CompareType.INCLUSIVE),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            past,
                            now
                    ));
        }

        @Test
        public void beforeCompareTypeNull() {

            final OffsetDateTime now = OffsetDateTime.now();

            final Executable testMethod = () -> beforeOffsetDateTime(now, null);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied CompareType must not be null")));
        }

        @Test
        public void beforeNullExclusive() {

            final Executable testMethod = () -> beforeOffsetDateTime(null, CompareType.EXCLUSIVE);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied Before must not be null")));
        }

        @Test
        public void beforeNullInclusive() {

            final Executable testMethod = () -> beforeOffsetDateTime(null, CompareType.INCLUSIVE);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied Before must not be null")));
        }

        @Test
        public void equalsExclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();

            tester.assertFails(now, beforeOffsetDateTime(now, CompareType.EXCLUSIVE),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

        @Test
        public void equalsInclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();

            tester.assertFails(now, beforeOffsetDateTime(now, CompareType.INCLUSIVE),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

    }

    @Nested
    @DisplayName("beforeZonedDateTime tests")
    class beforeZonedDateTimeTests {

        @Test
        public void after() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertValid(now, beforeZonedDateTime(future));
        }

        @Test
        public void before() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertFails(now, beforeZonedDateTime(past),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            past,
                            now
                    ));
        }

        @Test
        public void beforeNull() {

            final ZonedDateTime now = ZonedDateTime.now();

            final Executable testMethod = () -> beforeZonedDateTime(null);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied Before must not be null")));
        }

        @Test
        public void equals() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();

            tester.assertFails(now, beforeZonedDateTime(now),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

    }

    @Nested
    @DisplayName("beforeZonedDateTime(CompareType) tests")
    class beforeZonedDateTimeCompareTypeTests {

        @Test
        public void afterExclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertValid(now, beforeZonedDateTime(future, CompareType.EXCLUSIVE));
        }

        @Test
        public void afterInclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertValid(now, beforeZonedDateTime(future, CompareType.INCLUSIVE));
        }

        @Test
        public void beforeExclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertFails(now, beforeZonedDateTime(past, CompareType.EXCLUSIVE),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            past,
                            now
                    ));
        }

        @Test
        public void beforeInclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertFails(now, beforeZonedDateTime(past, CompareType.INCLUSIVE),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            past,
                            now
                    ));
        }

        @Test
        public void beforeCompareTypeNull() {

            final ZonedDateTime now = ZonedDateTime.now();

            final Executable testMethod = () -> beforeZonedDateTime(now, null);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied CompareType must not be null")));
        }

        @Test
        public void beforeNullExclusive() {

            final Executable testMethod = () -> beforeZonedDateTime(null, CompareType.EXCLUSIVE);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied Before must not be null")));
        }

        @Test
        public void beforeNullInclusive() {

            final Executable testMethod = () -> beforeZonedDateTime(null, CompareType.INCLUSIVE);
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
            assertThat(exception.getMessage(), is(equalTo("Supplied Before must not be null")));
        }

        @Test
        public void equalsExclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();

            tester.assertFails(now, beforeZonedDateTime(now, CompareType.EXCLUSIVE),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

        @Test
        public void equalsInclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();

            tester.assertFails(now, beforeZonedDateTime(now, CompareType.INCLUSIVE),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

    }

}
