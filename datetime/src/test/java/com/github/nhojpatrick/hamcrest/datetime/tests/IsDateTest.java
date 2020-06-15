package com.github.nhojpatrick.hamcrest.datetime.tests;

import com.github.nhojpatrick.hamcrest.datetime.IsDate;
import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.github.nhojpatrick.hamcrest.datetime.IsDate.localDate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsDateTest {

    @Test
    public void checkIs_StaticUtilityClass() {

        final Executable testMethod = IsDate::new;
        final AssertionError thrown = assertThrows(AssertionError.class, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo("Static utility class - cannot be instantiated."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Nested
    @DisplayName("localDate tests")
    class localDateTests {

        @Test
        public void is() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();

            tester.assertValid(now, localDate());
        }

        @Test
        public void mismatch() {

            final MatcherObjectTester<LocalTime> tester = new MatcherObjectTester<>();

            final LocalTime now = LocalTime.of(20, 12, 1);

            tester.assertFails(now, localDate(), "\nExpected: an instance of java.time.LocalDate\n      but: <20:12:01> is a java.time.LocalTime");
        }

    }

    @Nested
    @DisplayName("localDate(Matcher> tests")
    class localDateMatcherTests {

        @Test
        public void match() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate actual = LocalDate.of(2019, 1, 2);
            final LocalDate expected = LocalDate.of(2019, 1, 2);

            tester.assertValid(actual, localDate(is(expected)));
        }

        @Test
        public void mismatch() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate actual = LocalDate.of(2019, 1, 2);
            final LocalDate expected = LocalDate.of(2019, 3, 4);

            tester.assertFails(actual, localDate(equalTo(expected)), "\nExpected: java.time.LocalDate <2019-03-04>\n      but: was <2019-01-02>");
        }

    }

}
