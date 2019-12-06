package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalTime;
import java.time.OffsetTime;

import static com.github.nhojpatrick.hamcrest.datetime.IsAfterTime.afterLocalTime;
import static com.github.nhojpatrick.hamcrest.datetime.IsAfterTime.afterOffsetTime;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsAfterTimeTest {

    @Test
    public void checkIs_StaticUtilityClass() {
        final Executable testMethod = () -> {
            new IsAfterTime();
        };
        final AssertionError thrown = assertThrows(AssertionError.class, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo("Static utility class - cannot be instantiated."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Nested
    @DisplayName("afterLocalTime tests")
    class afterLocalTimeTests {

        @Test
        public void after() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);
            final LocalTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertFails(now, afterLocalTime(future),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            future,
                            now
                    ));
        }

        @Test
        public void before() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);
            final LocalTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertValid(now, afterLocalTime(past));
        }

        @Test
        public void beforeNull() {

            final LocalTime now = LocalTime.now().withHour(12);

            final Executable testMethod = () -> afterLocalTime(null);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied After must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void equals() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);

            tester.assertFails(now, afterLocalTime(now),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

    }

    @Nested
    @DisplayName("afterLocalTime(CompareType) tests")
    class afterLocalTimeCompareTypeTests {

        @Test
        public void afterExclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);
            final LocalTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertFails(now, afterLocalTime(future, CompareType.EXCLUSIVE),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            future,
                            now
                    ));
        }

        @Test
        public void afterInclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);
            final LocalTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertFails(now, afterLocalTime(future, CompareType.INCLUSIVE),
                    String.format("\nExpected: after or equal to <%s>\n      but: was <%s>",
                            future,
                            now
                    ));
        }

        @Test
        public void beforeExclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);
            final LocalTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertValid(now, afterLocalTime(past, CompareType.EXCLUSIVE));
        }

        @Test
        public void beforeInclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);
            final LocalTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertValid(now, afterLocalTime(past, CompareType.INCLUSIVE));
        }

        @Test
        public void beforeCompareTypeNull() {

            final LocalTime now = LocalTime.now().withHour(12);

            final Executable testMethod = () -> afterLocalTime(now, null);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied CompareType must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void beforeNullExclusive() {

            final Executable testMethod = () -> afterLocalTime(null, CompareType.EXCLUSIVE);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied After must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void beforeNullInclusive() {

            final Executable testMethod = () -> afterLocalTime(null, CompareType.INCLUSIVE);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied After must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void equalsExclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);

            tester.assertFails(now, afterLocalTime(now, CompareType.EXCLUSIVE),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

        @Test
        public void equalsInclusive() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);

            tester.assertValid(now, afterLocalTime(now, CompareType.INCLUSIVE));
        }

    }

    @Nested
    @DisplayName("afterOffsetTime tests")
    class afterOffsetTimeTests {

        @Test
        public void after() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);
            final OffsetTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertFails(now, afterOffsetTime(future),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            future,
                            now
                    ));
        }

        @Test
        public void before() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);
            final OffsetTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertValid(now, afterOffsetTime(past));
        }

        @Test
        public void beforeNull() {

            final OffsetTime now = OffsetTime.now().withHour(12);

            final Executable testMethod = () -> afterOffsetTime(null);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied After must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void equals() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);

            tester.assertFails(now, afterOffsetTime(now),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

    }

    @Nested
    @DisplayName("afterOffsetTime(CompareType) tests")
    class afterOffsetTimeCompareTypeTests {

        @Test
        public void afterExclusive() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);
            final OffsetTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertFails(now, afterOffsetTime(future, CompareType.EXCLUSIVE),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            future,
                            now
                    ));
        }

        @Test
        public void afterInclusive() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);
            final OffsetTime future = now.plusHours(1).plusMinutes(2).plusSeconds(3);

            tester.assertFails(now, afterOffsetTime(future, CompareType.INCLUSIVE),
                    String.format("\nExpected: after or equal to <%s>\n      but: was <%s>",
                            future,
                            now
                    ));
        }

        @Test
        public void beforeExclusive() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);
            final OffsetTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertValid(now, afterOffsetTime(past, CompareType.EXCLUSIVE));
        }

        @Test
        public void beforeInclusive() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);
            final OffsetTime past = now.minusHours(1).minusMinutes(2).minusSeconds(3);

            tester.assertValid(now, afterOffsetTime(past, CompareType.INCLUSIVE));
        }

        @Test
        public void beforeCompareTypeNull() {

            final OffsetTime now = OffsetTime.now().withHour(12);

            final Executable testMethod = () -> afterOffsetTime(now, null);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied CompareType must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void beforeNullExclusive() {

            final Executable testMethod = () -> afterOffsetTime(null, CompareType.EXCLUSIVE);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied After must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void beforeNullInclusive() {

            final Executable testMethod = () -> afterOffsetTime(null, CompareType.INCLUSIVE);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied After must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void equalsExclusive() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);

            tester.assertFails(now, afterOffsetTime(now, CompareType.EXCLUSIVE),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

        @Test
        public void equalsInclusive() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);

            tester.assertValid(now, afterOffsetTime(now, CompareType.INCLUSIVE));
        }

    }

}
