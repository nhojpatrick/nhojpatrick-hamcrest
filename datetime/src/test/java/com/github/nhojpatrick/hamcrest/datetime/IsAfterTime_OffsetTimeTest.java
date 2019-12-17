package com.github.nhojpatrick.hamcrest.datetime;

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

import java.time.OffsetTime;
import java.util.stream.Stream;

import static com.github.nhojpatrick.hamcrest.datetime.IsAfterTime.afterOffsetTime;
import static com.github.nhojpatrick.hamcrest.datetime.RandomHelper.randomIntBetween;
import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.INCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.DOWN;
import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.NONE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.UP;
import static com.github.nhojpatrick.hamcrest.datetime.internal.after.AbstractIsAfter.SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL;
import static com.github.nhojpatrick.hamcrest.datetime.internal.after.AbstractIsAfter.SUPPLIED_EXPECTED_MUST_NOT_BE_NULL;
import static com.github.nhojpatrick.hamcrest.datetime.internal.after.AbstractIsAfter.SUPPLIED_ROUNDING_TYPE_MUST_NOT_BE_NULL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class IsAfterTime_OffsetTimeTest {

    private static final OffsetTime NOW = OffsetTime.now()
            .withHour(randomIntBetween(9, 15))
            .withNano(randomIntBetween(250_000_000, 750_000_000));

    private static final OffsetTime PAST = NOW
            .minusHours(randomIntBetween(2, 4))
            .minusNanos(randomIntBetween(100_000_000, 125_000_000));

    private static final OffsetTime FUTURE = NOW
            .plusHours(randomIntBetween(2, 4))
            .plusNanos(randomIntBetween(100_000_000, 125_000_000));

    private static final MatcherObjectTester<OffsetTime> TESTER = new MatcherObjectTester<>();

    @ParameterizedTest
    @MethodSource("noargs_exceptionSource")
    @DisplayName("OffsetTime after() exceptions")
    public <T extends Throwable> void noargs_exceptions(final Class<T> exception,
                                                        final OffsetTime expected,
                                                        final String msg) {

        final Executable testMethod = () -> afterOffsetTime(expected);
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
    @DisplayName("OffsetTime after() tests")
    public Stream<DynamicTest> noargs_tests() {

        return Stream.of(
                // after
                dynamicTest("Actual is after After()", () -> TESTER.assertValid(NOW, afterOffsetTime(PAST))),

                // before
                dynamicTest("Actual is before After()", () -> TESTER.assertFails(NOW, afterOffsetTime(FUTURE), String.format("\nExpected: after <%s>\n      but: was <%s>", FUTURE, NOW))),

                // boundary
                dynamicTest("Actual is After()", () -> TESTER.assertFails(NOW, afterOffsetTime(NOW), String.format("\nExpected: after <%s>\n      but: was <%s>", NOW, NOW)))
        );
    }

    @ParameterizedTest
    @MethodSource("CompareType_exceptionSource")
    @DisplayName("OffsetTime after(CompareType) exceptions")
    public <T extends Throwable> void CompareType_exceptions(final Class<T> exception,
                                                             final OffsetTime expected,
                                                             final CompareType compareType,
                                                             final String msg) {

        final Executable testMethod = () -> afterOffsetTime(expected, compareType);
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
    @DisplayName("OffsetTime after(CompareType) tests")
    public Stream<DynamicTest> CompareType_tests() {

        return Stream.of(
                // after
                dynamicTest("Actual is after After(EXCLUSIVE)", () -> TESTER.assertValid(NOW, afterOffsetTime(PAST, EXCLUSIVE))),
                dynamicTest("Actual is after After(INCLUSIVE)", () -> TESTER.assertValid(NOW, afterOffsetTime(PAST, INCLUSIVE))),

                // before
                dynamicTest("Actual is before After(EXCLUSIVE)", () -> TESTER.assertFails(NOW, afterOffsetTime(FUTURE, EXCLUSIVE), String.format("\nExpected: after <%s>\n      but: was <%s>", FUTURE, NOW))),
                dynamicTest("Actual is before After(INCLUSIVE)", () -> TESTER.assertFails(NOW, afterOffsetTime(FUTURE, INCLUSIVE), String.format("\nExpected: after or equal to <%s>\n      but: was <%s>", FUTURE, NOW))),

                // boundary
                dynamicTest("Actual is After(EXCLUSIVE)", () -> TESTER.assertFails(NOW, afterOffsetTime(NOW, EXCLUSIVE), String.format("\nExpected: after <%s>\n      but: was <%s>", NOW, NOW))),
                dynamicTest("Actual is After(INCLUSIVE)", () -> TESTER.assertValid(NOW, afterOffsetTime(NOW, INCLUSIVE)))
        );
    }

    @ParameterizedTest
    @MethodSource("CompareType_RoundingType_exceptionSource")
    @DisplayName("OffsetTime after(CompareType,RoundingType) exceptions")
    public <T extends Throwable> void CompareType_RoundingType_exceptions(final Class<T> exception,
                                                                          final OffsetTime expected,
                                                                          final CompareType compareType,
                                                                          final RoundingType coundingType,
                                                                          final String msg) {

        final Executable testMethod = () -> afterOffsetTime(expected, compareType, coundingType);
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
    @DisplayName("OffsetTime after(CompareType,RoundingType) tests")
    public Stream<DynamicTest> CompareType_RoundingType_tests() {

        return Stream.of(
                // after
                dynamicTest("Actual is after After(EXCLUSIVE,DOWN)", () -> TESTER.assertValid(NOW, afterOffsetTime(PAST, EXCLUSIVE, DOWN))),
                dynamicTest("Actual is after After(EXCLUSIVE,NONE)", () -> TESTER.assertValid(NOW, afterOffsetTime(PAST, EXCLUSIVE, NONE))),
                dynamicTest("Actual is after After(EXCLUSIVE,UP)", () -> TESTER.assertValid(NOW, afterOffsetTime(PAST, EXCLUSIVE, UP))),
                dynamicTest("Actual is after After(INCLUSIVE,DOWN)", () -> TESTER.assertValid(NOW, afterOffsetTime(PAST, INCLUSIVE, DOWN))),
                dynamicTest("Actual is after After(INCLUSIVE,NONE)", () -> TESTER.assertValid(NOW, afterOffsetTime(PAST, INCLUSIVE, NONE))),
                dynamicTest("Actual is after After(INCLUSIVE,UP)", () -> TESTER.assertValid(NOW, afterOffsetTime(PAST, INCLUSIVE, UP))),

                // before
                dynamicTest("Actual is before After(EXCLUSIVE,DOWN)", () -> TESTER.assertFails(NOW, afterOffsetTime(FUTURE, EXCLUSIVE, DOWN), String.format("\nExpected: after <%s>\n      but: was <%s>", FUTURE.withNano(0), NOW))),
                dynamicTest("Actual is before After(EXCLUSIVE,NONE)", () -> TESTER.assertFails(NOW, afterOffsetTime(FUTURE, EXCLUSIVE, NONE), String.format("\nExpected: after <%s>\n      but: was <%s>", FUTURE, NOW))), dynamicTest("Actual is after After(EXCLUSIVE,UP)", () -> TESTER.assertFails(NOW, afterOffsetTime(FUTURE, EXCLUSIVE, UP), String.format("\nExpected: after <%s>\n      but: was <%s>", FUTURE.withNano(999_999_999), NOW))),
                dynamicTest("Actual is before After(INCLUSIVE,DOWN)", () -> TESTER.assertFails(NOW, afterOffsetTime(FUTURE, INCLUSIVE, DOWN), String.format("\nExpected: after or equal to <%s>\n      but: was <%s>", FUTURE.withNano(0), NOW))),
                dynamicTest("Actual is before After(INCLUSIVE,NONE)", () -> TESTER.assertFails(NOW, afterOffsetTime(FUTURE, INCLUSIVE, NONE), String.format("\nExpected: after or equal to <%s>\n      but: was <%s>", FUTURE, NOW))),
                dynamicTest("Actual is before After(INCLUSIVE,UP)", () -> TESTER.assertFails(NOW, afterOffsetTime(FUTURE, INCLUSIVE, UP), String.format("\nExpected: after or equal to <%s>\n      but: was <%s>", FUTURE.withNano(999_999_999), NOW))),

                // boundary
                dynamicTest("Actual is After(EXCLUSIVE,DOWN)", () -> TESTER.assertValid(NOW, afterOffsetTime(NOW, EXCLUSIVE, DOWN))),
                dynamicTest("Actual is After(EXCLUSIVE,NONE)", () -> TESTER.assertFails(NOW, afterOffsetTime(NOW, EXCLUSIVE, NONE), String.format("\nExpected: after <%s>\n      but: was <%s>", NOW, NOW))),
                dynamicTest("Actual is After(EXCLUSIVE,UP)", () -> TESTER.assertFails(NOW, afterOffsetTime(NOW, EXCLUSIVE, UP), String.format("\nExpected: after <%s>\n      but: was <%s>", NOW.withNano(999_999_999), NOW))),
                dynamicTest("Actual is After(INCLUSIVE,DOWN)", () -> TESTER.assertValid(NOW, afterOffsetTime(NOW, INCLUSIVE, DOWN))),
                dynamicTest("Actual is After(INCLUSIVE,NONE)", () -> TESTER.assertValid(NOW, afterOffsetTime(NOW, INCLUSIVE, NONE))),
                dynamicTest("Actual is After(INCLUSIVE,UP)", () -> TESTER.assertFails(NOW, afterOffsetTime(NOW, INCLUSIVE, UP), String.format("\nExpected: after or equal to <%s>\n      but: was <%s>", NOW.withNano(999_999_999), NOW)))
                );
    }

    @ParameterizedTest
    @MethodSource("RoundingType_exceptionSource")
    @DisplayName("OffsetTime after(RoundingType) exceptions")
    public <T extends Throwable> void RoundingType_exceptions(final Class<T> exception,
                                                              final OffsetTime expected,
                                                              final RoundingType roundingType,
                                                              final String msg) {

        final Executable testMethod = () -> afterOffsetTime(expected, roundingType);
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
    @DisplayName("OffsetTime after(RoundingType) tests")
    public Stream<DynamicTest> RoundingType_tests() {

        return Stream.of(
                //after
                dynamicTest("Actual is after After(DOWN)", () -> TESTER.assertValid(NOW, afterOffsetTime(PAST, DOWN))),
                dynamicTest("Actual is after After(NONE)", () -> TESTER.assertValid(NOW, afterOffsetTime(PAST, NONE))),
                dynamicTest("Actual is after After(UP)", () -> TESTER.assertValid(NOW, afterOffsetTime(PAST, UP))),

                // before
                dynamicTest("Actual is before After(DOWN)", () -> TESTER.assertFails(NOW, afterOffsetTime(FUTURE, DOWN), String.format("\nExpected: after <%s>\n      but: was <%s>", FUTURE.withNano(0), NOW))),
                dynamicTest("Actual is before After(NONE)", () -> TESTER.assertFails(NOW, afterOffsetTime(FUTURE, NONE), String.format("\nExpected: after <%s>\n      but: was <%s>", FUTURE, NOW))),
                dynamicTest("Actual is before After(UP)", () -> TESTER.assertFails(NOW, afterOffsetTime(FUTURE, UP), String.format("\nExpected: after <%s>\n      but: was <%s>", FUTURE.withNano(999_999_999), NOW))),

                // boundary
                dynamicTest("Actual is After(DOWN)", () -> TESTER.assertValid(NOW, afterOffsetTime(NOW, DOWN))),
                dynamicTest("Actual is After(NONE)", () -> TESTER.assertFails(NOW, afterOffsetTime(NOW, NONE), String.format("\nExpected: after <%s>\n      but: was <%s>", NOW, NOW))),
                dynamicTest("Actual is After(UP)", () -> TESTER.assertFails(NOW, afterOffsetTime(NOW, UP), String.format("\nExpected: after <%s>\n      but: was <%s>", NOW.withNano(999_999_999), NOW)))
                );
    }

}
