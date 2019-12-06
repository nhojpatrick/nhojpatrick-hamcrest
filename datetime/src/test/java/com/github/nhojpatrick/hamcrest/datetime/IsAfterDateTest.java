package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static com.github.nhojpatrick.hamcrest.datetime.IsAfterDate.afterLocalDate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsAfterDateTest {

    @Test
    public void checkIs_StaticUtilityClass() {
        final Executable testMethod = () -> {
            new IsAfterDate();
        };
        final AssertionError thrown = assertThrows(AssertionError.class, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo("Static utility class - cannot be instantiated."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Nested
    @DisplayName("afterLocalDate tests")
    class afterLocalDateTests {

        @Test
        public void after() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();
            final LocalDate future = now.plusYears(1).plusMonths(2).plusDays(3);

            tester.assertFails(now, afterLocalDate(future),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            future,
                            now
                    ));
        }

        @Test
        public void before() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();
            final LocalDate past = now.minusYears(1).minusMonths(2).minusDays(3);

            tester.assertValid(now, afterLocalDate(past));
        }

        @Test
        public void beforeNull() {

            final LocalDate now = LocalDate.now();

            final Executable testMethod = () -> afterLocalDate(null);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied After must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void equals() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();

            tester.assertFails(now, afterLocalDate(now),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

    }

    @Nested
    @DisplayName("afterLocalDate(CompareType) tests")
    class afterLocalDateCompareTypeTests {

        @Test
        public void afterExclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();
            final LocalDate future = now.plusYears(1).plusMonths(2).plusDays(3);

            tester.assertFails(now, afterLocalDate(future, CompareType.EXCLUSIVE),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            future,
                            now
                    ));
        }

        @Test
        public void afterInclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();
            final LocalDate future = now.plusYears(1).plusMonths(2).plusDays(3);

            tester.assertFails(now, afterLocalDate(future, CompareType.INCLUSIVE),
                    String.format("\nExpected: after or equal to <%s>\n      but: was <%s>",
                            future,
                            now
                    ));
        }

        @Test
        public void beforeExclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();
            final LocalDate past = now.minusYears(1).minusMonths(2).minusDays(3);

            tester.assertValid(now, afterLocalDate(past, CompareType.EXCLUSIVE));
        }

        @Test
        public void beforeInclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();
            final LocalDate past = now.minusYears(1).minusMonths(2).minusDays(3);

            tester.assertValid(now, afterLocalDate(past, CompareType.INCLUSIVE));
        }

        @Test
        public void beforeCompareTypeNull() {

            final LocalDate now = LocalDate.now();

            final Executable testMethod = () -> afterLocalDate(now, null);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied CompareType must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void beforeNullExclusive() {

            final Executable testMethod = () -> afterLocalDate(null, CompareType.EXCLUSIVE);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied After must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void beforeNullInclusive() {

            final Executable testMethod = () -> afterLocalDate(null, CompareType.INCLUSIVE);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied After must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void equalsExclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();

            tester.assertFails(now, afterLocalDate(now, CompareType.EXCLUSIVE),
                    String.format("\nExpected: after <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

        @Test
        public void equalsInclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();

            tester.assertValid(now, afterLocalDate(now, CompareType.INCLUSIVE));
        }

    }

}
