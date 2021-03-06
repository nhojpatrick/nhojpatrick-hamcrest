package com.github.nhojpatrick.hamcrest.datetime.tests;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType;
import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.OffsetDateTime;
import java.util.stream.Stream;

import static com.github.nhojpatrick.hamcrest.datetime.IsBeforeDateTime.beforeOffsetDateTime;
import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.INCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.DOWN;
import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.NONE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.UP;
import static com.github.nhojpatrick.hamcrest.datetime.internal.before.AbstractIsBefore.SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL;
import static com.github.nhojpatrick.hamcrest.datetime.internal.before.AbstractIsBefore.SUPPLIED_EXPECTED_MUST_NOT_BE_NULL;
import static com.github.nhojpatrick.hamcrest.datetime.internal.before.AbstractIsBefore.SUPPLIED_ROUNDING_TYPE_MUST_NOT_BE_NULL;
import static com.github.nhojpatrick.hamcrest.testing.util.RandomHelper.randomIntBetween;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class IsBeforeDateTime_OffsetDateTimeTest {

    private static final OffsetDateTime NOW = OffsetDateTime.now()
            .withHour(randomIntBetween(9, 15))
            .withNano(randomIntBetween(250_000_000, 750_000_000));

    private static final OffsetDateTime PAST = NOW
            .minusHours(randomIntBetween(2, 4))
            .minusNanos(randomIntBetween(100_000_000, 125_000_000));

    private static final OffsetDateTime FUTURE = NOW
            .plusHours(randomIntBetween(2, 4))
            .plusNanos(randomIntBetween(100_000_000, 125_000_000));

    private static final MatcherObjectTester<OffsetDateTime> TESTER = new MatcherObjectTester<>();

    @ParameterizedTest
    @MethodSource("noargs_exceptionSource")
    @DisplayName("OffsetDateTime before() exceptions")
    public <T extends Throwable> void noargs_exceptions(final Class<T> exception,
                                                        final OffsetDateTime expected,
                                                        final String msg) {

        final Executable testMethod = () -> beforeOffsetDateTime(expected);
        final T thrown = assertThrows(exception, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo(msg))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    public static Stream<Arguments> noargs_exceptionSource() {

        return Stream.of(
                Arguments.of(IllegalArgumentException.class, null, SUPPLIED_EXPECTED_MUST_NOT_BE_NULL)
        );
    }

    @TestFactory
    @DisplayName("OffsetDateTime before() tests")
    public Stream<DynamicTest> noargs_tests() {

        return Stream.of(
                // after
                dynamicTest("Actual is after Before()", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(PAST), String.format("\nExpected: before <%s>\n      but: was <%s>", PAST, NOW))),

                // before
                dynamicTest("Actual is before Before()", () -> TESTER.assertValid(NOW, beforeOffsetDateTime(FUTURE))),

                // boundary
                dynamicTest("Actual is Before()", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(NOW), String.format("\nExpected: before <%s>\n      but: was <%s>", NOW, NOW)))
        );
    }

    @ParameterizedTest
    @MethodSource("CompareType_exceptionSource")
    @DisplayName("OffsetDateTime before(CompareType) exceptions")
    public <T extends Throwable> void CompareType_exceptions(final Class<T> exception,
                                                             final OffsetDateTime expected,
                                                             final CompareType compareType,
                                                             final String msg) {

        final Executable testMethod = () -> beforeOffsetDateTime(expected, compareType);
        final T thrown = assertThrows(exception, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo(msg))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    public static Stream<Arguments> CompareType_exceptionSource() {

        return Stream.of(
                Arguments.of(IllegalArgumentException.class, null, EXCLUSIVE, SUPPLIED_EXPECTED_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, null, INCLUSIVE, SUPPLIED_EXPECTED_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, null, SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL)
        );
    }

    @TestFactory
    @DisplayName("OffsetDateTime before(CompareType) tests")
    public Stream<DynamicTest> CompareType_tests() {

        return Stream.of(
                // after
                dynamicTest("Actual is after Before(EXCLUSIVE)", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(PAST, EXCLUSIVE), String.format("\nExpected: before <%s>\n      but: was <%s>", PAST, NOW))),
                dynamicTest("Actual is after Before(INCLUSIVE)", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(PAST, INCLUSIVE), String.format("\nExpected: before or equal to <%s>\n      but: was <%s>", PAST, NOW))),

                // before
                dynamicTest("Actual is before Before(EXCLUSIVE)", () -> TESTER.assertValid(NOW, beforeOffsetDateTime(FUTURE, EXCLUSIVE))),
                dynamicTest("Actual is before Before(INCLUSIVE)", () -> TESTER.assertValid(NOW, beforeOffsetDateTime(FUTURE, INCLUSIVE))),

                // boundary
                dynamicTest("Actual is Before(EXCLUSIVE)", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(NOW, EXCLUSIVE), String.format("\nExpected: before <%s>\n      but: was <%s>", NOW, NOW))),
                dynamicTest("Actual is Before(INCLUSIVE)", () -> TESTER.assertValid(NOW, beforeOffsetDateTime(NOW, INCLUSIVE)))
        );
    }

    @ParameterizedTest
    @MethodSource("CompareType_RoundingType_exceptionSource")
    @DisplayName("OffsetDateTime before(CompareType,RoundingType) exceptions")
    public <T extends Throwable> void CompareType_RoundingType_exceptions(final Class<T> exception,
                                                                          final OffsetDateTime expected,
                                                                          final CompareType compareType,
                                                                          final RoundingType coundingType,
                                                                          final String msg) {

        final Executable testMethod = () -> beforeOffsetDateTime(expected, compareType, coundingType);
        final T thrown = assertThrows(exception, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo(msg))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    public static Stream<Arguments> CompareType_RoundingType_exceptionSource() {

        return Stream.of(
                Arguments.of(IllegalArgumentException.class, null, EXCLUSIVE, DOWN, SUPPLIED_EXPECTED_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, null, EXCLUSIVE, NONE, SUPPLIED_EXPECTED_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, null, EXCLUSIVE, UP, SUPPLIED_EXPECTED_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, null, INCLUSIVE, DOWN, SUPPLIED_EXPECTED_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, null, INCLUSIVE, NONE, SUPPLIED_EXPECTED_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, null, INCLUSIVE, UP, SUPPLIED_EXPECTED_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, null, DOWN, SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, null, NONE, SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, null, UP, SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, EXCLUSIVE, null, SUPPLIED_ROUNDING_TYPE_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, INCLUSIVE, null, SUPPLIED_ROUNDING_TYPE_MUST_NOT_BE_NULL)
        );
    }

    @TestFactory
    @DisplayName("OffsetDateTime before(CompareType,RoundingType) tests")
    public Stream<DynamicTest> CompareType_RoundingType_tests() {

        return Stream.of(
                // after
                dynamicTest("1Actual is before Before(EXCLUSIVE,DOWN)", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(PAST, EXCLUSIVE, DOWN), String.format("\nExpected: before <%s>\n      but: was <%s>", PAST.withNano(0), NOW))),
                dynamicTest("2Actual is before Before(EXCLUSIVE,NONE)", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(PAST, EXCLUSIVE, NONE), String.format("\nExpected: before <%s>\n      but: was <%s>", PAST, NOW))),
                dynamicTest("3Actual is before Before(EXCLUSIVE,UP)", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(PAST, EXCLUSIVE, UP), String.format("\nExpected: before <%s>\n      but: was <%s>", PAST.withNano(999_999_999), NOW))),
                dynamicTest("4Actual is before Before(INCLUSIVE,DOWN)", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(PAST, INCLUSIVE, DOWN), String.format("\nExpected: before or equal to <%s>\n      but: was <%s>", PAST.withNano(0), NOW))),
                dynamicTest("5Actual is before Before(INCLUSIVE,NONE)", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(PAST, INCLUSIVE, NONE), String.format("\nExpected: before or equal to <%s>\n      but: was <%s>", PAST, NOW))),
                dynamicTest("6Actual is before Before(INCLUSIVE,UP)", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(PAST, INCLUSIVE, UP), String.format("\nExpected: before or equal to <%s>\n      but: was <%s>", PAST.withNano(999_999_999), NOW))),

                // before
                dynamicTest("1Actual is after Before(EXCLUSIVE,DOWN)", () -> TESTER.assertValid(NOW, beforeOffsetDateTime(FUTURE, EXCLUSIVE, DOWN))),
                dynamicTest("2Actual is after Before(EXCLUSIVE,NONE)", () -> TESTER.assertValid(NOW, beforeOffsetDateTime(FUTURE, EXCLUSIVE, NONE))),
                dynamicTest("3Actual is after Before(EXCLUSIVE,UP)", () -> TESTER.assertValid(NOW, beforeOffsetDateTime(FUTURE, EXCLUSIVE, UP))),
                dynamicTest("4Actual is after Before(INCLUSIVE,DOWN)", () -> TESTER.assertValid(NOW, beforeOffsetDateTime(FUTURE, INCLUSIVE, DOWN))),
                dynamicTest("5Actual is after Before(INCLUSIVE,NONE)", () -> TESTER.assertValid(NOW, beforeOffsetDateTime(FUTURE, INCLUSIVE, NONE))),
                dynamicTest("6Actual is after Before(INCLUSIVE,UP)", () -> TESTER.assertValid(NOW, beforeOffsetDateTime(FUTURE, INCLUSIVE, UP))),

                // boundary
                dynamicTest("1Actual is Before(EXCLUSIVE,DOWN)", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(NOW, EXCLUSIVE, DOWN), String.format("\nExpected: before <%s>\n      but: was <%s>", NOW.withNano(0), NOW))),
                dynamicTest("2Actual is Before(EXCLUSIVE,NONE)", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(NOW, EXCLUSIVE, NONE), String.format("\nExpected: before <%s>\n      but: was <%s>", NOW, NOW))),
                dynamicTest("3Actual is Before(EXCLUSIVE,UP)", () -> TESTER.assertValid(NOW, beforeOffsetDateTime(NOW, EXCLUSIVE, UP))),
                dynamicTest("4Actual is Before(INCLUSIVE,DOWN)", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(NOW, INCLUSIVE, DOWN), String.format("\nExpected: before or equal to <%s>\n      but: was <%s>", NOW.withNano(0), NOW))),
                dynamicTest("5Actual is Before(INCLUSIVE,NONE)", () -> TESTER.assertValid(NOW, beforeOffsetDateTime(NOW, INCLUSIVE, NONE))),
                dynamicTest("6Actual is Before(INCLUSIVE,UP)", () -> TESTER.assertValid(NOW, beforeOffsetDateTime(NOW, INCLUSIVE, UP)))
        );
    }

    @ParameterizedTest
    @MethodSource("RoundingType_exceptionSource")
    @DisplayName("OffsetDateTime before(RoundingType) exceptions")
    public <T extends Throwable> void RoundingType_exceptions(final Class<T> exception,
                                                              final OffsetDateTime expected,
                                                              final RoundingType roundingType,
                                                              final String msg) {

        final Executable testMethod = () -> beforeOffsetDateTime(expected, roundingType);
        final T thrown = assertThrows(exception, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo(msg))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    public static Stream<Arguments> RoundingType_exceptionSource() {

        return Stream.of(
                Arguments.of(IllegalArgumentException.class, null, DOWN, SUPPLIED_EXPECTED_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, null, NONE, SUPPLIED_EXPECTED_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, null, UP, SUPPLIED_EXPECTED_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, null, SUPPLIED_ROUNDING_TYPE_MUST_NOT_BE_NULL)
        );
    }

    @TestFactory
    @DisplayName("OffsetDateTime before(RoundingType) fails")
    public Stream<DynamicTest> RoundingType_fails() {

        return Stream.of(

                // boundary
        );
    }

    @TestFactory
    @DisplayName("OffsetDateTime before(RoundingType) tests")
    public Stream<DynamicTest> RoundingType_tests() {

        return Stream.of(
                // after
                dynamicTest("Actual is after Before(DOWN)", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(PAST, DOWN), String.format("\nExpected: before <%s>\n      but: was <%s>", PAST.withNano(0), NOW))),
                dynamicTest("Actual is after Before(NONE)", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(PAST, NONE), String.format("\nExpected: before <%s>\n      but: was <%s>", PAST, NOW))),
                dynamicTest("Actual is after Before(UP)", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(PAST, UP), String.format("\nExpected: before <%s>\n      but: was <%s>", PAST.withNano(999_999_999), NOW))),

                // before
                dynamicTest("Actual is before Before(DOWN)", () -> TESTER.assertValid(NOW, beforeOffsetDateTime(FUTURE, DOWN))),
                dynamicTest("Actual is before Before(NONE)", () -> TESTER.assertValid(NOW, beforeOffsetDateTime(FUTURE, NONE))),
                dynamicTest("Actual is before Before(UP)", () -> TESTER.assertValid(NOW, beforeOffsetDateTime(FUTURE, UP))),

                // boundary
                dynamicTest("Actual is Before(DOWN)", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(NOW, DOWN), String.format("\nExpected: before <%s>\n      but: was <%s>", NOW.withNano(0), NOW))),
                dynamicTest("Actual is Before(NONE)", () -> TESTER.assertFails(NOW, beforeOffsetDateTime(NOW, NONE), String.format("\nExpected: before <%s>\n      but: was <%s>", NOW, NOW))),
                dynamicTest("Actual is Before(UP)", () -> TESTER.assertValid(NOW, beforeOffsetDateTime(NOW, UP)))
        );
    }

}
