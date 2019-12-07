package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static com.github.nhojpatrick.hamcrest.datetime.IsBeforeDate.beforeLocalDate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsBeforeDateTest {

    @Test
    public void checkIs_StaticUtilityClass() {

        final Executable testMethod = IsBeforeDate::new;
        final AssertionError thrown = assertThrows(AssertionError.class, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo("Static utility class - cannot be instantiated."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Nested
    @DisplayName("beforeLocalDate tests")
    class beforeLocalDateTests {

        @Test
        public void after() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();
            final LocalDate future = now.plusYears(1).plusMonths(2).plusDays(3);

            tester.assertValid(now, beforeLocalDate(future));
        }

        @Test
        public void before() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();
            final LocalDate past = now.minusYears(1).minusMonths(2).minusDays(3);

            tester.assertFails(now, beforeLocalDate(past),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            past,
                            now
                    ));
        }

        @Test
        public void beforeNull() {

            final LocalDate now = LocalDate.now();

            final Executable testMethod = () -> beforeLocalDate(null);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied Before must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void equals() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();

            tester.assertFails(now, beforeLocalDate(now),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

    }

    @Nested
    @DisplayName("beforeLocalDate(CompareType) tests")
    class beforeLocalDateCompareTypeTests {

        @Test
        public void afterExclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();
            final LocalDate future = now.plusYears(1).plusMonths(2).plusDays(3);

            tester.assertValid(now, beforeLocalDate(future, CompareType.EXCLUSIVE));
        }

        @Test
        public void afterInclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();
            final LocalDate future = now.plusYears(1).plusMonths(2).plusDays(3);

            tester.assertValid(now, beforeLocalDate(future, CompareType.INCLUSIVE));
        }

        @Test
        public void beforeExclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();
            final LocalDate past = now.minusYears(1).minusMonths(2).minusDays(3);

            tester.assertFails(now, beforeLocalDate(past, CompareType.EXCLUSIVE),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            past,
                            now
                    ));
        }

        @Test
        public void beforeInclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();
            final LocalDate past = now.minusYears(1).minusMonths(2).minusDays(3);

            tester.assertFails(now, beforeLocalDate(past, CompareType.INCLUSIVE),
                    String.format("\nExpected: before or equal to <%s>\n      but: was <%s>",
                            past,
                            now
                    ));
        }

        @Test
        public void beforeCompareTypeNull() {

            final LocalDate now = LocalDate.now();

            final Executable testMethod = () -> beforeLocalDate(now, null);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied CompareType must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void beforeNullExclusive() {

            final Executable testMethod = () -> beforeLocalDate(null, CompareType.EXCLUSIVE);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied Before must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void beforeNullInclusive() {

            final Executable testMethod = () -> beforeLocalDate(null, CompareType.INCLUSIVE);
            final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
            assertAll(
                    () -> assertThat(thrown.getMessage(), is(equalTo("Supplied Before must not be null"))),
                    () -> assertThat(thrown.getCause(), is(nullValue()))
            );
        }

        @Test
        public void equalsExclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();

            tester.assertFails(now, beforeLocalDate(now, CompareType.EXCLUSIVE),
                    String.format("\nExpected: before <%s>\n      but: was <%s>",
                            now,
                            now
                    ));
        }

        @Test
        public void equalsInclusive() {

            final MatcherObjectTester<LocalDate> tester = new MatcherObjectTester<>();

            final LocalDate now = LocalDate.now();

            tester.assertValid(now, beforeLocalDate(now, CompareType.INCLUSIVE));
        }

    }

}
