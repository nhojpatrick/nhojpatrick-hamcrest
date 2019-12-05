package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static com.github.nhojpatrick.hamcrest.datetime.IsDateTime.localDateTime;
import static com.github.nhojpatrick.hamcrest.datetime.IsDateTime.offsetDateTime;
import static com.github.nhojpatrick.hamcrest.datetime.IsDateTime.zonedDateTime;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsDateTimeTest {

    @Test
    public void checkIs_StaticUtilityClass() {
        final Executable testMethod = () -> {
            new IsDateTime();
        };
        final AssertionError thrown = assertThrows(AssertionError.class, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo("Static utility class - cannot be instantiated."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Nested
    @DisplayName("localDateTime tests")
    class localDateTimeTests {

        @Test
        public void is() {

            final MatcherObjectTester<LocalDateTime> tester = new MatcherObjectTester<>();

            final LocalDateTime now = LocalDateTime.now();

            tester.assertValid(now, localDateTime());
        }

        @Test
        public void mismatch() {

            final MatcherObjectTester<LocalDateTime> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.of(2019, 12, 01);

            tester.assertFails(now, localDateTime(), "\nExpected: an instance of java.time.LocalDateTime\n      but: <2019-12-01> is a java.time.LocalDate");
        }

    }

    @Nested
    @DisplayName("localDateTime(Matcher> tests")
    class localDateTimeMatcherTests {

        @Test
        public void match() {

            final MatcherObjectTester<LocalDateTime> tester = new MatcherObjectTester<>();

            final LocalDateTime actual = LocalDateTime.of(2019, 01, 02, 12, 34, 56, 789);
            final LocalDateTime expected = LocalDateTime.of(2019, 01, 02, 12, 34, 56, 789);

            tester.assertValid(actual, localDateTime(is(expected)));
        }

        @Test
        public void mismatch() {

            final MatcherObjectTester<LocalDateTime> tester = new MatcherObjectTester<>();

            final LocalDateTime actual = LocalDateTime.of(2019, 01, 02, 12, 34, 56, 789);
            final LocalDateTime expected = LocalDateTime.of(2019, 03, 04, 21, 43, 6, 987);

            tester.assertFails(actual, localDateTime(equalTo(expected)), "\nExpected: java.time.LocalDateTime <2019-03-04T21:43:06.000000987>\n      but: was <2019-01-02T12:34:56.000000789>");
        }

    }

    @Nested
    @DisplayName("offsetDateTime tests")
    class offsetDateTimeTests {

        @Test
        public void is() {

            final MatcherObjectTester<OffsetDateTime> tester = new MatcherObjectTester<>();

            final OffsetDateTime now = OffsetDateTime.now();

            tester.assertValid(now, offsetDateTime());
        }

        @Test
        public void mismatch() {

            final MatcherObjectTester<OffsetDateTime> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.of(2019, 12, 01);

            tester.assertFails(now, offsetDateTime(), "\nExpected: an instance of java.time.OffsetDateTime\n      but: <2019-12-01> is a java.time.LocalDate");
        }

    }

    @Nested
    @DisplayName("offsetDateTime(Matcher) tests")
    class offsetDateTimeMatcherTests {

        @Test
        public void is() {

            final MatcherObjectTester<OffsetDateTime> tester = new MatcherObjectTester<>();

            final OffsetDateTime actual = OffsetDateTime.of(2019, 01, 02, 12, 34, 56, 789, ZoneOffset.UTC);
            final OffsetDateTime expected = OffsetDateTime.of(2019, 01, 02, 12, 34, 56, 789, ZoneOffset.UTC);

            tester.assertValid(actual, offsetDateTime(equalTo(expected)));
        }

        @Test
        public void mismatch() {

            final MatcherObjectTester<OffsetDateTime> tester = new MatcherObjectTester<>();

            final OffsetDateTime actual = OffsetDateTime.of(2019, 01, 02, 12, 34, 56, 789, ZoneOffset.UTC);
            final OffsetDateTime expected = OffsetDateTime.of(2019, 03, 04, 21, 43, 6, 987, ZoneOffset.UTC);

            tester.assertFails(actual, offsetDateTime(equalTo(expected)), "\nExpected: java.time.OffsetDateTime <2019-03-04T21:43:06.000000987Z>\n      but: was <2019-01-02T12:34:56.000000789Z>");
        }

    }

    @Nested
    @DisplayName("zonedDateTime tests")
    class zonedDateTimeTests {

        @Test
        public void is() {

            final MatcherObjectTester<ZonedDateTime> tester = new MatcherObjectTester<>();

            final ZonedDateTime now = ZonedDateTime.now();

            tester.assertValid(now, zonedDateTime());
        }

        @Test
        public void mismatch() {

            final MatcherObjectTester<ZonedDateTime> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.of(2019, 12, 01);

            tester.assertFails(now, zonedDateTime(), "\nExpected: an instance of java.time.ZonedDateTime\n      but: <2019-12-01> is a java.time.LocalDate");
        }

    }

    @Nested
    @DisplayName("zonedDateTime(Matcher) tests")
    class zonedDateTimeMatcherTests {

        @Test
        public void is() {

            final MatcherObjectTester<ZonedDateTime> tester = new MatcherObjectTester<>();

            final ZonedDateTime actual = ZonedDateTime.of(2019, 01, 02, 12, 34, 56, 789, ZoneId.of("Europe/London"));
            final ZonedDateTime expected = ZonedDateTime.of(2019, 01, 02, 12, 34, 56, 789, ZoneId.of("Europe/London"));

            tester.assertValid(actual, zonedDateTime(equalTo(expected)));
        }

        @Test
        public void mismatch() {

            final MatcherObjectTester<ZonedDateTime> tester = new MatcherObjectTester<>();

            final ZonedDateTime actual = ZonedDateTime.of(2019, 01, 02, 12, 34, 56, 789, ZoneId.of("Europe/London"));
            final ZonedDateTime expected = ZonedDateTime.of(2019, 03, 04, 21, 43, 6, 987, ZoneId.of("Europe/London"));

            tester.assertFails(actual, zonedDateTime(equalTo(expected)), "\nExpected: java.time.ZonedDateTime <2019-03-04T21:43:06.000000987Z[Europe/London]>\n      but: was <2019-01-02T12:34:56.000000789Z[Europe/London]>");
        }

    }

}
