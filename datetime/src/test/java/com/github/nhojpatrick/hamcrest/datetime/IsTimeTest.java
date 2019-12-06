package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;

import static com.github.nhojpatrick.hamcrest.datetime.IsTime.localTime;
import static com.github.nhojpatrick.hamcrest.datetime.IsTime.offsetTime;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsTimeTest {

    @Test
    public void checkIs_StaticUtilityClass() {
        final Executable testMethod = () -> {
            new IsTime();
        };
        final AssertionError thrown = assertThrows(AssertionError.class, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo("Static utility class - cannot be instantiated."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Nested
    @DisplayName("localTime tests")
    class localTimeTests {

        @Test
        public void is() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.now().withHour(12);

            tester.assertValid(now, localTime());
        }

        @Test
        public void mismatch() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.of(2019, 12, 01);

            tester.assertFails(now, localTime(), "\nExpected: an instance of java.time.LocalTime\n      but: <2019-12-01> is a java.time.LocalDate");
        }

    }

    @Nested
    @DisplayName("localTime(Matcher> tests")
    class localTimeMatcherTests {

        @Test
        public void match() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime actual = LocalTime.of(12, 34, 56, 789);
            final LocalTime expected = LocalTime.of(12, 34, 56, 789);

            tester.assertValid(actual, localTime(is(expected)));
        }

        @Test
        public void mismatch() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime actual = LocalTime.of(12, 34, 56);
            final LocalTime expected = LocalTime.of(21, 43, 6);

            tester.assertFails(actual, localTime(equalTo(expected)), "\nExpected: java.time.LocalTime <21:43:06>\n      but: was <12:34:56>");
        }

    }

    @Nested
    @DisplayName("offsetTime tests")
    class offsetTimeTests {

        @Test
        public void is() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final OffsetTime now = OffsetTime.now().withHour(12);

            tester.assertValid(now, offsetTime());
        }

        @Test
        public void mismatch() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.of(2019, 12, 01);

            tester.assertFails(now, offsetTime(), "\nExpected: an instance of java.time.OffsetTime\n      but: <2019-12-01> is a java.time.LocalDate");
        }

    }

    @Nested
    @DisplayName("offsetTime(Matcher) tests")
    class offsetTimeMatcherTests {

        @Test
        public void is() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime actual = OffsetTime.of(12, 34, 56, 789, ZoneOffset.UTC);
            final OffsetTime expected = OffsetTime.of(12, 34, 56, 789, ZoneOffset.UTC);

            tester.assertValid(actual, offsetTime(equalTo(expected)));
        }

        @Test
        public void mismatch() {

            final MatcherObjectTester<OffsetTime> tester = new MatcherObjectTester<>();

            final OffsetTime actual = OffsetTime.of(12, 34, 56, 789, ZoneOffset.UTC);
            final OffsetTime expected = OffsetTime.of(21, 43, 6, 987, ZoneOffset.UTC);

            tester.assertFails(actual, offsetTime(equalTo(expected)), "\nExpected: java.time.OffsetTime <21:43:06.000000987Z>\n      but: was <12:34:56.000000789Z>");
        }

    }

}
