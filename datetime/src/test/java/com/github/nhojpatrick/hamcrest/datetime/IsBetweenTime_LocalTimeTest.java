package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.AbstractIsAfter;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.AbstractIsBefore;
import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalTime;
import java.util.stream.Stream;

import static com.github.nhojpatrick.hamcrest.datetime.IsBetweenTime.betweenLocalTime;
import static com.github.nhojpatrick.hamcrest.datetime.RandomHelper.randomIntBetween;
import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.INCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.DOWN;
import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.NONE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.UP;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class IsBetweenTime_LocalTimeTest {

    public static final String SUPPLIED_EXPECTED_AFTER_COMPARE_TYPE_MUST_NOT_BE_NULL = AbstractIsAfter.SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL;
    public static final String SUPPLIED_EXPECTED_AFTER_MUST_NOT_BE_NULL = AbstractIsAfter.SUPPLIED_EXPECTED_MUST_NOT_BE_NULL;
    public static final String SUPPLIED_EXPECTED_BEFORE_COMPARE_TYPE_MUST_NOT_BE_NULL = AbstractIsBefore.SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL;
    public static final String SUPPLIED_EXPECTED_BEFORE_MUST_NOT_BE_NULL = AbstractIsBefore.SUPPLIED_EXPECTED_MUST_NOT_BE_NULL;

    private static final LocalTime NOW = LocalTime.now()
            .withHour(randomIntBetween(9, 15))
            .withNano(randomIntBetween(250_000_000, 750_000_000));

    private static final LocalTime PAST = NOW
            .minusHours(randomIntBetween(2, 4))
            .minusNanos(randomIntBetween(100_000_000, 125_000_000));

    private static final LocalTime FUTURE = NOW
            .plusHours(randomIntBetween(2, 4))
            .plusNanos(randomIntBetween(100_000_000, 125_000_000));

    private static final MatcherObjectTester<LocalTime> TESTER = new MatcherObjectTester<>();

    @ParameterizedTest
    @MethodSource("noargs_exceptionSource")
    @DisplayName("LocalDate between() exceptions")
    public <T extends Throwable> void noargs_exceptions(final Class<T> exception,
                                                        final LocalTime after,
                                                        final LocalTime before,
                                                        final String msg) {

        final Executable testMethod = () -> betweenLocalTime(after, before);
        final T thrown = assertThrows(exception, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo(msg))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    public static Stream<Arguments> noargs_exceptionSource() {

        // equals
        final String equalsMsg = String.format("After <%s> must not equal Before <%s>.",
                NOW,
                NOW
        );
        final Arguments equals = Arguments.of(IllegalStateException.class, NOW, NOW, equalsMsg);

        // wrong way around
        final String wrongWayAroundMsg = String.format("After <%s> must be Before <%s>.",
                FUTURE,
                PAST
        );
        final Arguments wrongWayAround = Arguments.of(IllegalStateException.class, FUTURE, PAST, wrongWayAroundMsg);

        return Stream.of(
                Arguments.of(IllegalArgumentException.class, null, NOW, SUPPLIED_EXPECTED_AFTER_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, null, SUPPLIED_EXPECTED_BEFORE_MUST_NOT_BE_NULL),
                equals,
                wrongWayAround
        );
    }

    @TestFactory
    @DisplayName("LocalTime between() tests")
    public Stream<DynamicTest> noargs_between() {

        return Stream.of(
                dynamicTest("Actual is after Before", () -> TESTER.assertFails(FUTURE, betweenLocalTime(PAST, NOW), String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST, NOW, NOW, FUTURE))),
                dynamicTest("Actual is After value", () -> TESTER.assertFails(NOW, betweenLocalTime(NOW, FUTURE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>", NOW, FUTURE, NOW, NOW))),
                dynamicTest("Actual is before After", () -> TESTER.assertFails(PAST, betweenLocalTime(NOW, FUTURE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>", NOW, FUTURE, NOW, PAST))),
                dynamicTest("Actual is Before value", () -> TESTER.assertFails(NOW, betweenLocalTime(PAST, NOW), String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST, NOW, NOW, NOW))),
                dynamicTest("Actual is between", () -> TESTER.assertValid(NOW, betweenLocalTime(PAST, FUTURE)))
        );
    }

    @ParameterizedTest
    @MethodSource("CompareType_exceptionSource")
    @DisplayName("LocalTime between(CompareType) exceptions")
    public <T extends Throwable> void CompareType_exceptions(final Class<T> exception,
                                                             final LocalTime after,
                                                             final CompareType afterCompareType,
                                                             final LocalTime before,
                                                             final CompareType beforeCompareType,
                                                             final String msg) {

        final Executable testMethod = () -> betweenLocalTime(after, afterCompareType, before, beforeCompareType);
        final T thrown = assertThrows(exception, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo(msg))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    public static Stream<Arguments> CompareType_exceptionSource() {

        // equals
        final String equalsMsg = String.format("After <%s> must not equal Before <%s>.",
                NOW,
                NOW
        );
        final Arguments equals = Arguments.of(IllegalStateException.class, NOW, EXCLUSIVE, NOW, EXCLUSIVE, equalsMsg);

        // wrong way around
        final String wrongWayAroundMsg = String.format("After <%s> must be Before <%s>.",
                FUTURE,
                PAST
        );
        final Arguments wrongWayAround = Arguments.of(IllegalStateException.class, FUTURE, EXCLUSIVE, PAST, EXCLUSIVE, wrongWayAroundMsg);

        return Stream.of(
                Arguments.of(IllegalArgumentException.class, null, EXCLUSIVE, NOW, EXCLUSIVE, SUPPLIED_EXPECTED_AFTER_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, null, NOW, EXCLUSIVE, SUPPLIED_EXPECTED_AFTER_COMPARE_TYPE_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, EXCLUSIVE, null, EXCLUSIVE, SUPPLIED_EXPECTED_BEFORE_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, EXCLUSIVE, NOW, null, SUPPLIED_EXPECTED_BEFORE_COMPARE_TYPE_MUST_NOT_BE_NULL),
                equals,
                wrongWayAround
        );
    }

    @TestFactory
    @DisplayName("LocalDate between(CompareType) tests")
    public Stream<DynamicTest> CompareType_tests() {

        return Stream.of(
                // Within Boundary
                dynamicTest("Actual is between After(EXCLUSIVE) and Before(EXCLUSIVE)", () -> TESTER.assertValid(NOW, betweenLocalTime(PAST, EXCLUSIVE, FUTURE, EXCLUSIVE))),
                dynamicTest("Actual is between After(EXCLUSIVE) and Before(INCLUSIVE)", () -> TESTER.assertValid(NOW, betweenLocalTime(PAST, EXCLUSIVE, FUTURE, INCLUSIVE))),
                dynamicTest("Actual is between After(INCLUSIVE) and Before(EXCLUSIVE)", () -> TESTER.assertValid(NOW, betweenLocalTime(PAST, INCLUSIVE, FUTURE, EXCLUSIVE))),
                dynamicTest("Actual is between After(INCLUSIVE) and Before(INCLUSIVE)", () -> TESTER.assertValid(NOW, betweenLocalTime(PAST, INCLUSIVE, FUTURE, INCLUSIVE))),

                // before after
                dynamicTest("Actual is before After(EXCLUSIVE) value with Before(EXCLUSIVE)", () -> TESTER.assertFails(PAST, betweenLocalTime(NOW, EXCLUSIVE, FUTURE, EXCLUSIVE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>", NOW, FUTURE, NOW, PAST))),
                dynamicTest("Actual is before After(EXCLUSIVE) value with Before(INCLUSIVE)", () -> TESTER.assertFails(PAST, betweenLocalTime(NOW, EXCLUSIVE, FUTURE, INCLUSIVE), String.format("\nExpected: (after <%s> and before or equal to <%s>)\n      but: after <%s> was <%s>", NOW, FUTURE, NOW, PAST))),
                dynamicTest("Actual is before After(INCLUSIVE) value with Before(EXCLUSIVE)", () -> TESTER.assertFails(PAST, betweenLocalTime(NOW, INCLUSIVE, FUTURE, EXCLUSIVE), String.format("\nExpected: (after or equal to <%s> and before <%s>)\n      but: after or equal to <%s> was <%s>", NOW, FUTURE, NOW, PAST))),
                dynamicTest("Actual is before After(INCLUSIVE) value with Before(INCLUSIVE)", () -> TESTER.assertFails(PAST, betweenLocalTime(NOW, INCLUSIVE, FUTURE, INCLUSIVE), String.format("\nExpected: (after or equal to <%s> and before or equal to <%s>)\n      but: after or equal to <%s> was <%s>", NOW, FUTURE, NOW, PAST))),

                // after before
                dynamicTest("Actual is after Before(EXCLUSIVE) value with After(EXCLUSIVE)", () -> TESTER.assertFails(FUTURE, betweenLocalTime(PAST, EXCLUSIVE, NOW, EXCLUSIVE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST, NOW, NOW, FUTURE))),
                dynamicTest("Actual is after Before(EXCLUSIVE) value with After(INCLUSIVE)", () -> TESTER.assertFails(FUTURE, betweenLocalTime(PAST, INCLUSIVE, NOW, EXCLUSIVE), String.format("\nExpected: (after or equal to <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST, NOW, NOW, FUTURE))),
                dynamicTest("Actual is after Before(INCLUSIVE) value with After(EXCLUSIVE)", () -> TESTER.assertFails(FUTURE, betweenLocalTime(PAST, EXCLUSIVE, NOW, INCLUSIVE), String.format("\nExpected: (after <%s> and before or equal to <%s>)\n      but: before or equal to <%s> was <%s>", PAST, NOW, NOW, FUTURE))),
                dynamicTest("Actual is after Before(INCLUSIVE) value with After(INCLUSIVE)", () -> TESTER.assertFails(FUTURE, betweenLocalTime(PAST, INCLUSIVE, NOW, INCLUSIVE), String.format("\nExpected: (after or equal to <%s> and before or equal to <%s>)\n      but: before or equal to <%s> was <%s>", PAST, NOW, NOW, FUTURE))),

                // After Boundary
                dynamicTest("Actual is After(EXCLUSIVE) and Before(EXCLUSIVE)", () -> TESTER.assertFails(PAST, betweenLocalTime(PAST, EXCLUSIVE, FUTURE, EXCLUSIVE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>", PAST, FUTURE, PAST, PAST))),
                dynamicTest("Actual is After(EXCLUSIVE) and Before(INCLUSIVE)", () -> TESTER.assertFails(PAST, betweenLocalTime(PAST, EXCLUSIVE, FUTURE, INCLUSIVE), String.format("\nExpected: (after <%s> and before or equal to <%s>)\n      but: after <%s> was <%s>", PAST, FUTURE, PAST, PAST))),
                dynamicTest("Actual is After(INCLUSIVE) and Before(EXCLUSIVE)", () -> TESTER.assertValid(PAST, betweenLocalTime(PAST, INCLUSIVE, FUTURE, EXCLUSIVE))),
                dynamicTest("Actual is After(INCLUSIVE) and Before(INCLUSIVE)", () -> TESTER.assertValid(PAST, betweenLocalTime(PAST, INCLUSIVE, FUTURE, INCLUSIVE))),

                // Before Boundary
                dynamicTest("Actual is Before(EXCLUSIVE) and After(EXCLUSIVE)", () -> TESTER.assertFails(FUTURE, betweenLocalTime(PAST, EXCLUSIVE, FUTURE, EXCLUSIVE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST, FUTURE, FUTURE, FUTURE))),
                dynamicTest("Actual is Before(EXCLUSIVE) and After(INCLUSIVE)", () -> TESTER.assertFails(FUTURE, betweenLocalTime(PAST, INCLUSIVE, FUTURE, EXCLUSIVE), String.format("\nExpected: (after or equal to <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST, FUTURE, FUTURE, FUTURE))),
                dynamicTest("Actual is Before(INCLUSIVE) and After(EXCLUSIVE)", () -> TESTER.assertValid(FUTURE, betweenLocalTime(PAST, EXCLUSIVE, FUTURE, INCLUSIVE))),
                dynamicTest("Actual is Before(INCLUSIVE) and After(INCLUSIVE)", () -> TESTER.assertValid(FUTURE, betweenLocalTime(PAST, INCLUSIVE, FUTURE, INCLUSIVE)))
        );
    }

}
