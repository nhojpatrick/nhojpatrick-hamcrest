package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalTime;
import java.time.OffsetTime;

import static com.github.nhojpatrick.hamcrest.datetime.IsBeforeTime.beforeLocalTime;
import static com.github.nhojpatrick.hamcrest.datetime.IsBeforeTime.beforeOffsetTime;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsBeforeTimeTest {

    @Test
    public void checkIs_StaticUtilityClass() {
        final Executable testMethod = () -> {
            new IsBeforeTime();
        };
        final AssertionError thrown = assertThrows(AssertionError.class, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo("Static utility class - cannot be instantiated."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Nested
    @DisplayName("beforeLocalTime tests")
    class beforeLocalTimeTests {

        @Test
        public void after() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);
            final LocalTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertValid(now, beforeLocalTime(future));
        }

        @Test
        public void before() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);
            final LocalTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertFails(now, beforeLocalTime(past),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            past,
                            now
                    ));
        }

        @Test
        public void beforeNull() {

            final LocalTime now = LocalTime.now().withHour(12);

            final Executable testMethod = () -> beforeLocalTime(null);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied Before must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void equals() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);

            tester.assertFails(now, beforeLocalTime(now),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

    }

    @Nested
    @DisplayName("beforeLocalTime(CompareType) tests")
    class beforeLocalTimeCompareTypeTests {

        @Test
        public void afterExclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);
            final LocalTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertValid(now, beforeLocalTime(future, CompareType.EXCLUSIVE));
        }

        @Test
        public void afterInclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);
            final LocalTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertValid(now, beforeLocalTime(future, CompareType.INCLUSIVE));
        }

        @Test
        public void beforeExclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);
            final LocalTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertFails(now, beforeLocalTime(past, CompareType.EXCLUSIVE),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            past,
                            now
                    ));
        }

        @Test
        public void beforeInclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);
            final LocalTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertFails(now, beforeLocalTime(past, CompareType.INCLUSIVE),
                    String.format("\nExpected: before or equal to <%s>\n      but: was <%s>",
                            past,
                            now
                    ));
        }

        @Test
        public void beforeCompareTypeNull() {

            final LocalTime now = LocalTime.now().withHour(12);

            final Executable testMethod = () -> beforeLocalTime(now, null);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied CompareType must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void beforeNullExclusive() {

            final Executable testMethod = () -> beforeLocalTime(null, CompareType.EXCLUSIVE);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied Before must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void beforeNullInclusive() {

            final Executable testMethod = () -> beforeLocalTime(null, CompareType.INCLUSIVE);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied Before must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void equalsExclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);

            tester.assertFails(now, beforeLocalTime(now, CompareType.EXCLUSIVE),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

        @Test
        public void equalsInclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);

            tester.assertValid(now, beforeLocalTime(now, CompareType.INCLUSIVE));
        }

    }

    @Nested
    @DisplayName("beforeOffsetTime tests")
    class beforeOffsetTimeTests {

        @Test
        public void after() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);
            final OffsetTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertValid(now, beforeOffsetTime(future));
        }

        @Test
        public void before() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);
            final OffsetTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertFails(now, beforeOffsetTime(past),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            past,
                            now
                    ));
        }

        @Test
        public void beforeNull() {

            final OffsetTime now = OffsetTime.now().withHour(12);

            final Executable testMethod = () -> beforeOffsetTime(null);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied Before must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void equals() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);

            tester.assertFails(now, beforeOffsetTime(now),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

    }

    @Nested
    @DisplayName("beforeOffsetTime(CompareType) tests")
    class beforeOffsetTimeCompareTypeTests {

        @Test
        public void afterExclusive() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);
            final OffsetTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertValid(now, beforeOffsetTime(future, CompareType.EXCLUSIVE));
        }

        @Test
        public void afterInclusive() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);
            final OffsetTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertValid(now, beforeOffsetTime(future, CompareType.INCLUSIVE));
        }

        @Test
        public void beforeExclusive() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);
            final OffsetTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertFails(now, beforeOffsetTime(past, CompareType.EXCLUSIVE),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            past,
                            now
                    ));
        }

        @Test
        public void beforeInclusive() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);
            final OffsetTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertFails(now, beforeOffsetTime(past, CompareType.INCLUSIVE),
                    String.format("\nExpected: before or equal to <%s>\n      but: was <%s>",
                            past,
                            now
                    ));
        }

        @Test
        public void beforeCompareTypeNull() {

            final OffsetTime now = OffsetTime.now().withHour(12);

            final Executable testMethod = () -> beforeOffsetTime(now, null);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied CompareType must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void beforeNullExclusive() {

            final Executable testMethod = () -> beforeOffsetTime(null, CompareType.EXCLUSIVE);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied Before must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void beforeNullInclusive() {

            final Executable testMethod = () -> beforeOffsetTime(null, CompareType.INCLUSIVE);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied Before must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void equalsExclusive() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);

            tester.assertFails(now, beforeOffsetTime(now, CompareType.EXCLUSIVE),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

        @Test
        public void equalsInclusive() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);

            tester.assertValid(now, beforeOffsetTime(now, CompareType.INCLUSIVE));
        }

    }

}
