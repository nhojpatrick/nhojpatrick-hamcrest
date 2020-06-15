package com.github.nhojpatrick.hamcrest.datetime.tests;

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

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static com.github.nhojpatrick.hamcrest.datetime.IsBetweenDateTime.betweenLocalDateTime;
import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.INCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.DOWN;
import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.NONE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.UP;
import static com.github.nhojpatrick.hamcrest.testing.util.RandomHelper.randomIntBetween;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class IsBetweenDateTime_LocalDateTimeTest {

    public static final String SUPPLIED_EXPECTED_AFTER_COMPARE_TYPE_MUST_NOT_BE_NULL = AbstractIsAfter.SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL;
    public static final String SUPPLIED_EXPECTED_AFTER_MUST_NOT_BE_NULL = AbstractIsAfter.SUPPLIED_EXPECTED_MUST_NOT_BE_NULL;
    public static final String SUPPLIED_EXPECTED_AFTER_ROUNDING_TYPE_MUST_NOT_BE_NULL = AbstractIsAfter.SUPPLIED_ROUNDING_TYPE_MUST_NOT_BE_NULL;
    public static final String SUPPLIED_EXPECTED_BEFORE_COMPARE_TYPE_MUST_NOT_BE_NULL = AbstractIsBefore.SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL;
    public static final String SUPPLIED_EXPECTED_BEFORE_MUST_NOT_BE_NULL = AbstractIsBefore.SUPPLIED_EXPECTED_MUST_NOT_BE_NULL;
    public static final String SUPPLIED_EXPECTED_BEFORE_ROUNDING_TYPE_MUST_NOT_BE_NULL = AbstractIsBefore.SUPPLIED_ROUNDING_TYPE_MUST_NOT_BE_NULL;

    private static final LocalDateTime NOW = LocalDateTime.now()
            .withHour(randomIntBetween(9, 15))
            .withNano(randomIntBetween(250_000_000, 750_000_000));

    private static final LocalDateTime PAST = NOW
            .minusHours(randomIntBetween(2, 4))
            .minusNanos(randomIntBetween(100_000_000, 125_000_000));

    private static final LocalDateTime FUTURE = NOW
            .plusHours(randomIntBetween(2, 4))
            .plusNanos(randomIntBetween(100_000_000, 125_000_000));

    private static final MatcherObjectTester<LocalDateTime> TESTER = new MatcherObjectTester<>();

    @ParameterizedTest
    @MethodSource("noargs_exceptionSource")
    @DisplayName("LocalDate between() exceptions")
    public <T extends Throwable> void noargs_exceptions(final Class<T> exception,
                                                        final LocalDateTime after,
                                                        final LocalDateTime before,
                                                        final String msg) {

        final Executable testMethod = () -> betweenLocalDateTime(after, before);
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
    @DisplayName("LocalDateTime between() tests")
    public Stream<DynamicTest> noargs_between() {

        return Stream.of(
                dynamicTest("Actual is after Before", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, NOW), String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST, NOW, NOW, FUTURE))),
                dynamicTest("Actual is After value", () -> TESTER.assertFails(NOW, betweenLocalDateTime(NOW, FUTURE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>", NOW, FUTURE, NOW, NOW))),
                dynamicTest("Actual is before After", () -> TESTER.assertFails(PAST, betweenLocalDateTime(NOW, FUTURE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>", NOW, FUTURE, NOW, PAST))),
                dynamicTest("Actual is Before value", () -> TESTER.assertFails(NOW, betweenLocalDateTime(PAST, NOW), String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST, NOW, NOW, NOW))),
                dynamicTest("Actual is between", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, FUTURE)))
        );
    }

    @ParameterizedTest
    @MethodSource("CompareType_exceptionSource")
    @DisplayName("LocalDateTime between(CompareType) exceptions")
    public <T extends Throwable> void CompareType_exceptions(final Class<T> exception,
                                                             final LocalDateTime after,
                                                             final CompareType afterCompareType,
                                                             final LocalDateTime before,
                                                             final CompareType beforeCompareType,
                                                             final String msg) {

        final Executable testMethod = () -> betweenLocalDateTime(after, afterCompareType, before, beforeCompareType);
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
                dynamicTest("Actual is between After(EXCLUSIVE) and Before(EXCLUSIVE)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, EXCLUSIVE, FUTURE, EXCLUSIVE))),
                dynamicTest("Actual is between After(EXCLUSIVE) and Before(INCLUSIVE)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, EXCLUSIVE, FUTURE, INCLUSIVE))),
                dynamicTest("Actual is between After(INCLUSIVE) and Before(EXCLUSIVE)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, INCLUSIVE, FUTURE, EXCLUSIVE))),
                dynamicTest("Actual is between After(INCLUSIVE) and Before(INCLUSIVE)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, INCLUSIVE, FUTURE, INCLUSIVE))),

                // before after
                dynamicTest("Actual is before After(EXCLUSIVE) value with Before(EXCLUSIVE)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(NOW, EXCLUSIVE, FUTURE, EXCLUSIVE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>", NOW, FUTURE, NOW, PAST))),
                dynamicTest("Actual is before After(EXCLUSIVE) value with Before(INCLUSIVE)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(NOW, EXCLUSIVE, FUTURE, INCLUSIVE), String.format("\nExpected: (after <%s> and before or equal to <%s>)\n      but: after <%s> was <%s>", NOW, FUTURE, NOW, PAST))),
                dynamicTest("Actual is before After(INCLUSIVE) value with Before(EXCLUSIVE)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(NOW, INCLUSIVE, FUTURE, EXCLUSIVE), String.format("\nExpected: (after or equal to <%s> and before <%s>)\n      but: after or equal to <%s> was <%s>", NOW, FUTURE, NOW, PAST))),
                dynamicTest("Actual is before After(INCLUSIVE) value with Before(INCLUSIVE)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(NOW, INCLUSIVE, FUTURE, INCLUSIVE), String.format("\nExpected: (after or equal to <%s> and before or equal to <%s>)\n      but: after or equal to <%s> was <%s>", NOW, FUTURE, NOW, PAST))),

                // after before
                dynamicTest("Actual is after Before(EXCLUSIVE) value with After(EXCLUSIVE)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, EXCLUSIVE, NOW, EXCLUSIVE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST, NOW, NOW, FUTURE))),
                dynamicTest("Actual is after Before(EXCLUSIVE) value with After(INCLUSIVE)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, INCLUSIVE, NOW, EXCLUSIVE), String.format("\nExpected: (after or equal to <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST, NOW, NOW, FUTURE))),
                dynamicTest("Actual is after Before(INCLUSIVE) value with After(EXCLUSIVE)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, EXCLUSIVE, NOW, INCLUSIVE), String.format("\nExpected: (after <%s> and before or equal to <%s>)\n      but: before or equal to <%s> was <%s>", PAST, NOW, NOW, FUTURE))),
                dynamicTest("Actual is after Before(INCLUSIVE) value with After(INCLUSIVE)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, INCLUSIVE, NOW, INCLUSIVE), String.format("\nExpected: (after or equal to <%s> and before or equal to <%s>)\n      but: before or equal to <%s> was <%s>", PAST, NOW, NOW, FUTURE))),

                // After Boundary
                dynamicTest("Actual is After(EXCLUSIVE) and Before(EXCLUSIVE)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(PAST, EXCLUSIVE, FUTURE, EXCLUSIVE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>", PAST, FUTURE, PAST, PAST))),
                dynamicTest("Actual is After(EXCLUSIVE) and Before(INCLUSIVE)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(PAST, EXCLUSIVE, FUTURE, INCLUSIVE), String.format("\nExpected: (after <%s> and before or equal to <%s>)\n      but: after <%s> was <%s>", PAST, FUTURE, PAST, PAST))),
                dynamicTest("Actual is After(INCLUSIVE) and Before(EXCLUSIVE)", () -> TESTER.assertValid(PAST, betweenLocalDateTime(PAST, INCLUSIVE, FUTURE, EXCLUSIVE))),
                dynamicTest("Actual is After(INCLUSIVE) and Before(INCLUSIVE)", () -> TESTER.assertValid(PAST, betweenLocalDateTime(PAST, INCLUSIVE, FUTURE, INCLUSIVE))),

                // Before Boundary
                dynamicTest("Actual is Before(EXCLUSIVE) and After(EXCLUSIVE)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, EXCLUSIVE, FUTURE, EXCLUSIVE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST, FUTURE, FUTURE, FUTURE))),
                dynamicTest("Actual is Before(EXCLUSIVE) and After(INCLUSIVE)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, INCLUSIVE, FUTURE, EXCLUSIVE), String.format("\nExpected: (after or equal to <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST, FUTURE, FUTURE, FUTURE))),
                dynamicTest("Actual is Before(INCLUSIVE) and After(EXCLUSIVE)", () -> TESTER.assertValid(FUTURE, betweenLocalDateTime(PAST, EXCLUSIVE, FUTURE, INCLUSIVE))),
                dynamicTest("Actual is Before(INCLUSIVE) and After(INCLUSIVE)", () -> TESTER.assertValid(FUTURE, betweenLocalDateTime(PAST, INCLUSIVE, FUTURE, INCLUSIVE)))
        );
    }

    @ParameterizedTest
    @MethodSource("CompareType_RoundingType_exceptionSource")
    @DisplayName("LocalDateTime between(CompareType,RoundingType) exceptions")
    public <T extends Throwable> void CompareType_RoundingType_exceptions(final Class<T> exception,
                                                                          final LocalDateTime after,
                                                                          final CompareType afterCompareType,
                                                                          final RoundingType afterRoundingType,
                                                                          final LocalDateTime before,
                                                                          final CompareType beforeCompareType,
                                                                          final RoundingType beforeRoundingType,
                                                                          final String msg) {

        final Executable testMethod = () -> betweenLocalDateTime(after, afterCompareType, afterRoundingType, before, beforeCompareType, beforeRoundingType);
        final T thrown = assertThrows(exception, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo(msg))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    public static Stream<Arguments> CompareType_RoundingType_exceptionSource() {

        // equals
        final String equalsMsg = String.format("After <%s> must not equal Before <%s>.",
                NOW,
                NOW
        );
        final Arguments equals = Arguments.of(IllegalStateException.class, NOW, EXCLUSIVE, DOWN, NOW, EXCLUSIVE, DOWN, equalsMsg);

        // wrong way around
        final String wrongWayAroundMsg = String.format("After <%s> must be Before <%s>.",
                FUTURE,
                PAST
        );
        final Arguments wrongWayAround = Arguments.of(IllegalStateException.class, FUTURE, EXCLUSIVE, DOWN, PAST, EXCLUSIVE, DOWN, wrongWayAroundMsg);

        return Stream.of(
                Arguments.of(IllegalArgumentException.class, null, EXCLUSIVE, DOWN, NOW, EXCLUSIVE, DOWN, SUPPLIED_EXPECTED_AFTER_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, null, DOWN, NOW, EXCLUSIVE, DOWN, SUPPLIED_EXPECTED_AFTER_COMPARE_TYPE_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, EXCLUSIVE, null, NOW, EXCLUSIVE, DOWN, SUPPLIED_EXPECTED_AFTER_ROUNDING_TYPE_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, EXCLUSIVE, DOWN, null, EXCLUSIVE, DOWN, SUPPLIED_EXPECTED_BEFORE_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, EXCLUSIVE, DOWN, NOW, null, DOWN, SUPPLIED_EXPECTED_BEFORE_COMPARE_TYPE_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, EXCLUSIVE, DOWN, NOW, EXCLUSIVE, null, SUPPLIED_EXPECTED_BEFORE_ROUNDING_TYPE_MUST_NOT_BE_NULL),
                equals,
                wrongWayAround
        );
    }

    @TestFactory
    @DisplayName("LocalDate between(CompareType,RoundingType) tests")
    public Stream<DynamicTest> CompareType_RoundingType_tests() {

        return Stream.of(
                // Within Boundary
                dynamicTest("Actual is between After(EXCLUSIVE,DOWN) and Before(EXCLUSIVE,DOWN)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, EXCLUSIVE, DOWN, FUTURE, EXCLUSIVE, DOWN))),
                dynamicTest("Actual is between After(EXCLUSIVE,NONE) and Before(EXCLUSIVE,NONE)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, EXCLUSIVE, NONE, FUTURE, EXCLUSIVE, NONE))),
                dynamicTest("Actual is between After(EXCLUSIVE,UP) and Before(EXCLUSIVE,UP)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, EXCLUSIVE, UP, FUTURE, EXCLUSIVE, UP))),
                dynamicTest("Actual is between After(EXCLUSIVE,DOWN) and Before(INCLUSIVE,DOWN)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, EXCLUSIVE, DOWN, FUTURE, INCLUSIVE, DOWN))),
                dynamicTest("Actual is between After(EXCLUSIVE,NONE) and Before(INCLUSIVE,NONE)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, EXCLUSIVE, NONE, FUTURE, INCLUSIVE, NONE))),
                dynamicTest("Actual is between After(EXCLUSIVE,UP) and Before(INCLUSIVE,UP)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, EXCLUSIVE, UP, FUTURE, INCLUSIVE, UP))),
                dynamicTest("Actual is between After(INCLUSIVE,DOWN) and Before(EXCLUSIVE,DOWN)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, INCLUSIVE, DOWN, FUTURE, EXCLUSIVE, DOWN))),
                dynamicTest("Actual is between After(INCLUSIVE,NONE) and Before(EXCLUSIVE,NONE)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, INCLUSIVE, NONE, FUTURE, EXCLUSIVE, NONE))),
                dynamicTest("Actual is between After(INCLUSIVE,UP) and Before(EXCLUSIVE,UP)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, INCLUSIVE, UP, FUTURE, EXCLUSIVE, UP))),
                dynamicTest("Actual is between After(INCLUSIVE,DOWN) and Before(INCLUSIVE,DOWN)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, INCLUSIVE, DOWN, FUTURE, INCLUSIVE, DOWN))),
                dynamicTest("Actual is between After(INCLUSIVE,NONE) and Before(INCLUSIVE,NONE)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, INCLUSIVE, NONE, FUTURE, INCLUSIVE, NONE))),
                dynamicTest("Actual is between After(INCLUSIVE,UP) and Before(INCLUSIVE,UP)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, INCLUSIVE, UP, FUTURE, INCLUSIVE, UP))),
                dynamicTest("Actual is between After(INCLUSIVE,DOWN) and Before(INCLUSIVE,DOWN)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, INCLUSIVE, DOWN, FUTURE, INCLUSIVE, DOWN))),
                dynamicTest("Actual is between After(INCLUSIVE,NONE) and Before(INCLUSIVE,NONE)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, INCLUSIVE, NONE, FUTURE, INCLUSIVE, NONE))),
                dynamicTest("Actual is between After(INCLUSIVE,UP) and Before(INCLUSIVE,UP)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, INCLUSIVE, UP, FUTURE, INCLUSIVE, UP))),

                // before after
                dynamicTest("Actual is before After(EXCLUSIVE,DOWN) value with Before(EXCLUSIVE,DOWN)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(NOW, EXCLUSIVE, DOWN, FUTURE, EXCLUSIVE, DOWN), String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>", NOW.withNano(0), FUTURE.withNano(0), NOW.withNano(0), PAST))),
                dynamicTest("Actual is before After(EXCLUSIVE,NONE) value with Before(EXCLUSIVE,NONE)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(NOW, EXCLUSIVE, NONE, FUTURE, EXCLUSIVE, NONE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>", NOW, FUTURE, NOW, PAST))),
                dynamicTest("Actual is before After(EXCLUSIVE,UP) value with Before(EXCLUSIVE,UP)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(NOW, EXCLUSIVE, UP, FUTURE, EXCLUSIVE, UP), String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>", NOW.withNano(999_999_999), FUTURE.withNano(999_999_999), NOW.withNano(999_999_999), PAST))),
                dynamicTest("Actual is before After(EXCLUSIVE,DOWN) value with Before(INCLUSIVE,DOWN)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(NOW, EXCLUSIVE, DOWN, FUTURE, INCLUSIVE, DOWN), String.format("\nExpected: (after <%s> and before or equal to <%s>)\n      but: after <%s> was <%s>", NOW.withNano(0), FUTURE.withNano(0), NOW.withNano(0), PAST))),
                dynamicTest("Actual is before After(EXCLUSIVE,NONE) value with Before(INCLUSIVE,NONE)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(NOW, EXCLUSIVE, NONE, FUTURE, INCLUSIVE, NONE), String.format("\nExpected: (after <%s> and before or equal to <%s>)\n      but: after <%s> was <%s>", NOW, FUTURE, NOW, PAST))),
                dynamicTest("Actual is before After(EXCLUSIVE,UP) value with Before(INCLUSIVE,UP)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(NOW, EXCLUSIVE, UP, FUTURE, INCLUSIVE, UP), String.format("\nExpected: (after <%s> and before or equal to <%s>)\n      but: after <%s> was <%s>", NOW.withNano(999_999_999), FUTURE.withNano(999_999_999), NOW.withNano(999_999_999), PAST))),
                dynamicTest("Actual is before After(INCLUSIVE,DOWN) value with Before(EXCLUSIVE,DOWN)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(NOW, INCLUSIVE, DOWN, FUTURE, EXCLUSIVE, DOWN), String.format("\nExpected: (after or equal to <%s> and before <%s>)\n      but: after or equal to <%s> was <%s>", NOW.withNano(0), FUTURE.withNano(0), NOW.withNano(0), PAST))),
                dynamicTest("Actual is before After(INCLUSIVE,NONE) value with Before(EXCLUSIVE,NONE)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(NOW, INCLUSIVE, NONE, FUTURE, EXCLUSIVE, NONE), String.format("\nExpected: (after or equal to <%s> and before <%s>)\n      but: after or equal to <%s> was <%s>", NOW, FUTURE, NOW, PAST))),
                dynamicTest("Actual is before After(INCLUSIVE,UP) value with Before(INCLUSIVE,UP)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(NOW, INCLUSIVE, UP, FUTURE, INCLUSIVE, UP), String.format("\nExpected: (after or equal to <%s> and before or equal to <%s>)\n      but: after or equal to <%s> was <%s>", NOW.withNano(999_999_999), FUTURE.withNano(999_999_999), NOW.withNano(999_999_999), PAST))),

                // after before
                dynamicTest("Actual is after Before(EXCLUSIVE,DOWN) value with After(EXCLUSIVE,DOWN)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, EXCLUSIVE, DOWN, NOW, EXCLUSIVE, DOWN), String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST.withNano(0), NOW.withNano(0), NOW.withNano(0), FUTURE))),
                dynamicTest("Actual is after Before(EXCLUSIVE,NONE) value with After(EXCLUSIVE,NONE)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, EXCLUSIVE, NONE, NOW, EXCLUSIVE, NONE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST, NOW, NOW, FUTURE))),
                dynamicTest("Actual is after Before(EXCLUSIVE,UP) value with After(EXCLUSIVE,UP)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, EXCLUSIVE, UP, NOW, EXCLUSIVE, UP), String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST.withNano(999_999_999), NOW.withNano(999_999_999), NOW.withNano(999_999_999), FUTURE))),
                dynamicTest("Actual is after Before(INCLUSIVE,DOWN) value with After(EXCLUSIVE,DOWN)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, EXCLUSIVE, DOWN, NOW, INCLUSIVE, DOWN), String.format("\nExpected: (after <%s> and before or equal to <%s>)\n      but: before or equal to <%s> was <%s>", PAST.withNano(0), NOW.withNano(0), NOW.withNano(0), FUTURE))),
                dynamicTest("Actual is after Before(INCLUSIVE,NONE) value with After(EXCLUSIVE,NONE)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, EXCLUSIVE, NONE, NOW, INCLUSIVE, NONE), String.format("\nExpected: (after <%s> and before or equal to <%s>)\n      but: before or equal to <%s> was <%s>", PAST, NOW, NOW, FUTURE))),
                dynamicTest("Actual is after Before(INCLUSIVE,UP) value with After(EXCLUSIVE,UP)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, EXCLUSIVE, UP, NOW, INCLUSIVE, UP), String.format("\nExpected: (after <%s> and before or equal to <%s>)\n      but: before or equal to <%s> was <%s>", PAST.withNano(999_999_999), NOW.withNano(999_999_999), NOW.withNano(999_999_999), FUTURE))),
                dynamicTest("Actual is after Before(EXCLUSIVE,DOWN) value with After(INCLUSIVE,DOWN)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, INCLUSIVE, DOWN, NOW, EXCLUSIVE, DOWN), String.format("\nExpected: (after or equal to <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST.withNano(0), NOW.withNano(0), NOW.withNano(0), FUTURE))),
                dynamicTest("Actual is after Before(EXCLUSIVE,NONE) value with After(INCLUSIVE,NONE)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, INCLUSIVE, NONE, NOW, EXCLUSIVE, NONE), String.format("\nExpected: (after or equal to <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST, NOW, NOW, FUTURE))),
                dynamicTest("Actual is after Before(EXCLUSIVE,UP) value with After(INCLUSIVE,UP)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, INCLUSIVE, UP, NOW, EXCLUSIVE, UP), String.format("\nExpected: (after or equal to <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST.withNano(999_999_999), NOW.withNano(999_999_999), NOW.withNano(999_999_999), FUTURE))),
                dynamicTest("Actual is after Before(INCLUSIVE,DOWN) value with After(INCLUSIVE,DOWN)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, INCLUSIVE, DOWN, NOW, INCLUSIVE, DOWN), String.format("\nExpected: (after or equal to <%s> and before or equal to <%s>)\n      but: before or equal to <%s> was <%s>", PAST.withNano(0), NOW.withNano(0), NOW.withNano(0), FUTURE))),
                dynamicTest("Actual is after Before(INCLUSIVE,NONE) value with After(INCLUSIVE,NONE)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, INCLUSIVE, NONE, NOW, INCLUSIVE, NONE), String.format("\nExpected: (after or equal to <%s> and before or equal to <%s>)\n      but: before or equal to <%s> was <%s>", PAST, NOW, NOW, FUTURE))),
                dynamicTest("Actual is after Before(INCLUSIVE,UP) value with After(INCLUSIVE,UP)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, INCLUSIVE, UP, NOW, INCLUSIVE, UP), String.format("\nExpected: (after or equal to <%s> and before or equal to <%s>)\n      but: before or equal to <%s> was <%s>", PAST.withNano(999_999_999), NOW.withNano(999_999_999), NOW.withNano(999_999_999), FUTURE))),

                // After Boundary
                dynamicTest("Actual is After(EXCLUSIVE,DOWN) and Before(EXCLUSIVE,DOWN)", () -> TESTER.assertValid(PAST, betweenLocalDateTime(PAST, EXCLUSIVE, DOWN, FUTURE, EXCLUSIVE, DOWN))),
                dynamicTest("Actual is After(EXCLUSIVE,DOWN) and Before(INCLUSIVE,DOWN)", () -> TESTER.assertValid(PAST, betweenLocalDateTime(PAST, EXCLUSIVE, DOWN, FUTURE, INCLUSIVE, DOWN))),
                dynamicTest("Actual is After(EXCLUSIVE,NONE) and Before(EXCLUSIVE,NONE)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(PAST, EXCLUSIVE, NONE, FUTURE, EXCLUSIVE, NONE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>", PAST, FUTURE, PAST, PAST))),
                dynamicTest("Actual is After(EXCLUSIVE,NONE) and Before(INCLUSIVE,NONE)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(PAST, EXCLUSIVE, NONE, FUTURE, INCLUSIVE, NONE), String.format("\nExpected: (after <%s> and before or equal to <%s>)\n      but: after <%s> was <%s>", PAST, FUTURE, PAST, PAST))),
                dynamicTest("Actual is After(EXCLUSIVE,UP) and Before(EXCLUSIVE,UP)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(PAST, EXCLUSIVE, UP, FUTURE, EXCLUSIVE, UP), String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>", PAST.withNano(999_999_999), FUTURE.withNano(999_999_999), PAST.withNano(999_999_999), PAST))),
                dynamicTest("Actual is After(EXCLUSIVE,UP) and Before(INCLUSIVE,UP)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(PAST, EXCLUSIVE, UP, FUTURE, INCLUSIVE, UP), String.format("\nExpected: (after <%s> and before or equal to <%s>)\n      but: after <%s> was <%s>", PAST.withNano(999_999_999), FUTURE.withNano(999_999_999), PAST.withNano(999_999_999), PAST))),
                dynamicTest("Actual is After(INCLUSIVE,DOWN) and Before(EXCLUSIVE,DOWN)", () -> TESTER.assertValid(PAST, betweenLocalDateTime(PAST, INCLUSIVE, DOWN, FUTURE, EXCLUSIVE, DOWN))),
                dynamicTest("Actual is After(INCLUSIVE,DOWN) and Before(INCLUSIVE,DOWN)", () -> TESTER.assertValid(PAST, betweenLocalDateTime(PAST, INCLUSIVE, DOWN, FUTURE, INCLUSIVE, DOWN))),
                dynamicTest("Actual is After(INCLUSIVE,NONE) and Before(EXCLUSIVE,NONE)", () -> TESTER.assertValid(PAST, betweenLocalDateTime(PAST, INCLUSIVE, NONE, FUTURE, EXCLUSIVE, NONE))),
                dynamicTest("Actual is After(INCLUSIVE,NONE) and Before(INCLUSIVE,NONE)", () -> TESTER.assertValid(PAST, betweenLocalDateTime(PAST, INCLUSIVE, NONE, FUTURE, INCLUSIVE, NONE))),
                dynamicTest("Actual is After(INCLUSIVE,UP) and Before(EXCLUSIVE,UP)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(PAST, INCLUSIVE, UP, FUTURE, EXCLUSIVE, UP), String.format("\nExpected: (after or equal to <%s> and before <%s>)\n      but: after or equal to <%s> was <%s>", PAST.withNano(999_999_999), FUTURE.withNano(999_999_999), PAST.withNano(999_999_999), PAST))),
                dynamicTest("Actual is After(INCLUSIVE,UP) and Before(INCLUSIVE,UP)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(PAST, INCLUSIVE, UP, FUTURE, INCLUSIVE, UP), String.format("\nExpected: (after or equal to <%s> and before or equal to <%s>)\n      but: after or equal to <%s> was <%s>", PAST.withNano(999_999_999), FUTURE.withNano(999_999_999), PAST.withNano(999_999_999), PAST))),

                // Before Boundary
                dynamicTest("Actual is Before(EXCLUSIVE,DOWN) and After(EXCLUSIVE,DOWN)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, EXCLUSIVE, DOWN, FUTURE, EXCLUSIVE, DOWN), String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST.withNano(0), FUTURE.withNano(0), FUTURE.withNano(0), FUTURE))),
                dynamicTest("Actual is Before(EXCLUSIVE,DOWN) and After(INCLUSIVE,DOWN)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, INCLUSIVE, DOWN, FUTURE, EXCLUSIVE, DOWN), String.format("\nExpected: (after or equal to <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST.withNano(0), FUTURE.withNano(0), FUTURE.withNano(0), FUTURE))),
                dynamicTest("Actual is Before(EXCLUSIVE,NONE) and After(EXCLUSIVE,NONE)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, EXCLUSIVE, NONE, FUTURE, EXCLUSIVE, NONE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST, FUTURE, FUTURE, FUTURE))),
                dynamicTest("Actual is Before(EXCLUSIVE,NONE) and After(INCLUSIVE,NONE)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, INCLUSIVE, NONE, FUTURE, EXCLUSIVE, NONE), String.format("\nExpected: (after or equal to <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST, FUTURE, FUTURE, FUTURE))),
                dynamicTest("Actual is Before(EXCLUSIVE,UP) and After(EXCLUSIVE,UP)", () -> TESTER.assertValid(FUTURE, betweenLocalDateTime(PAST, EXCLUSIVE, UP, FUTURE, EXCLUSIVE, UP))),
                dynamicTest("Actual is Before(EXCLUSIVE,UP) and After(INCLUSIVE,UP)", () -> TESTER.assertValid(FUTURE, betweenLocalDateTime(PAST, INCLUSIVE, UP, FUTURE, EXCLUSIVE, UP))),
                dynamicTest("Actual is Before(INCLUSIVE,DOWN) and After(EXCLUSIVE,DOWN)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, EXCLUSIVE, DOWN, FUTURE, INCLUSIVE, DOWN), String.format("\nExpected: (after <%s> and before or equal to <%s>)\n      but: before or equal to <%s> was <%s>", PAST.withNano(0), FUTURE.withNano(0), FUTURE.withNano(0), FUTURE))),
                dynamicTest("Actual is Before(INCLUSIVE,DOWN) and After(INCLUSIVE,DOWN)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, INCLUSIVE, DOWN, FUTURE, INCLUSIVE, DOWN), String.format("\nExpected: (after or equal to <%s> and before or equal to <%s>)\n      but: before or equal to <%s> was <%s>", PAST.withNano(0), FUTURE.withNano(0), FUTURE.withNano(0), FUTURE))),
                dynamicTest("Actual is Before(INCLUSIVE,NONE) and After(EXCLUSIVE,NONE)", () -> TESTER.assertValid(FUTURE, betweenLocalDateTime(PAST, EXCLUSIVE, NONE, FUTURE, INCLUSIVE, NONE))),
                dynamicTest("Actual is Before(INCLUSIVE,NONE) and After(INCLUSIVE,NONE)", () -> TESTER.assertValid(FUTURE, betweenLocalDateTime(PAST, INCLUSIVE, NONE, FUTURE, INCLUSIVE, NONE))),
                dynamicTest("Actual is Before(INCLUSIVE,UP) and After(EXCLUSIVE,UP)", () -> TESTER.assertValid(FUTURE, betweenLocalDateTime(PAST, EXCLUSIVE, UP, FUTURE, INCLUSIVE, UP))),
                dynamicTest("Actual is Before(INCLUSIVE,UP) and After(INCLUSIVE,UP)", () -> TESTER.assertValid(FUTURE, betweenLocalDateTime(PAST, INCLUSIVE, UP, FUTURE, INCLUSIVE, UP)))
        );
    }

    @ParameterizedTest
    @MethodSource("RoundingType_exceptionSource")
    @DisplayName("LocalDateTime between(RoundingType) exceptions")
    public <T extends Throwable> void RoundingType_exceptions(final Class<T> exception,
                                                              final LocalDateTime after,
                                                              final RoundingType afterRoundingType,
                                                              final LocalDateTime before,
                                                              final RoundingType beforeRoundingType,
                                                              final String msg) {

        final Executable testMethod = () -> betweenLocalDateTime(after, afterRoundingType, before, beforeRoundingType);
        final T thrown = assertThrows(exception, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo(msg))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    public static Stream<Arguments> RoundingType_exceptionSource() {

        // equals
        final String equalsMsg = String.format("After <%s> must not equal Before <%s>.",
                NOW,
                NOW
        );
        final Arguments equals = Arguments.of(IllegalStateException.class, NOW, DOWN, NOW, DOWN, equalsMsg);

        // wrong way around
        final String wrongWayAroundMsg = String.format("After <%s> must be Before <%s>.",
                FUTURE,
                PAST
        );
        final Arguments wrongWayAround = Arguments.of(IllegalStateException.class, FUTURE, DOWN, PAST, DOWN, wrongWayAroundMsg);

        return Stream.of(
                Arguments.of(IllegalArgumentException.class, null, DOWN, NOW, DOWN, SUPPLIED_EXPECTED_AFTER_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, null, NOW, DOWN, SUPPLIED_EXPECTED_AFTER_ROUNDING_TYPE_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, DOWN, null, DOWN, SUPPLIED_EXPECTED_BEFORE_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, NOW, DOWN, NOW, null, SUPPLIED_EXPECTED_BEFORE_ROUNDING_TYPE_MUST_NOT_BE_NULL),
                equals,
                wrongWayAround
        );
    }

    @TestFactory
    @DisplayName("LocalDate between(RoundingType) tests")
    public Stream<DynamicTest> RoundingType_tests() {

        return Stream.of(
                // Within Boundary
                dynamicTest("Actual is between After(DOWN) and Before(DOWN)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, DOWN, FUTURE, DOWN))),
                dynamicTest("Actual is between After(NONE) and Before(NONE)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, NONE, FUTURE, NONE))),
                dynamicTest("Actual is between After(UP) and Before(UP)", () -> TESTER.assertValid(NOW, betweenLocalDateTime(PAST, UP, FUTURE, UP))),

                // before after
                dynamicTest("Actual is before After(DOWN) value with Before(DOWN)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(NOW, DOWN, FUTURE, DOWN), String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>", NOW.withNano(0), FUTURE.withNano(0), NOW.withNano(0), PAST))),
                dynamicTest("Actual is before After(NONE) value with Before(NONE)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(NOW, NONE, FUTURE, NONE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>", NOW, FUTURE, NOW, PAST))),
                dynamicTest("Actual is before After(UP) value with Before(UP)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(NOW, UP, FUTURE, UP), String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>", NOW.withNano(999_999_999), FUTURE.withNano(999_999_999), NOW.withNano(999_999_999), PAST))),

                // after before
                dynamicTest("Actual is after Before(DOWN) value with After(DOWN)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, DOWN, NOW, DOWN), String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST.withNano(0), NOW.withNano(0), NOW.withNano(0), FUTURE))),
                dynamicTest("Actual is after Before(NONE) value with After(NONE)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, NONE, NOW, NONE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST, NOW, NOW, FUTURE))),
                dynamicTest("Actual is after Before(UP) value with After(UP)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, UP, NOW, UP), String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST.withNano(999_999_999), NOW.withNano(999_999_999), NOW.withNano(999_999_999), FUTURE))),

                // After Boundary
                dynamicTest("Actual is After(DOWN) and Before(DOWN)", () -> TESTER.assertValid(PAST, betweenLocalDateTime(PAST, DOWN, FUTURE, DOWN))),
                dynamicTest("Actual is After(NONE) and Before(NONE)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(PAST, NONE, FUTURE, NONE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>", PAST, FUTURE, PAST, PAST))),
                dynamicTest("Actual is After(UP) and Before(UP)", () -> TESTER.assertFails(PAST, betweenLocalDateTime(PAST, UP, FUTURE, UP), String.format("\nExpected: (after <%s> and before <%s>)\n      but: after <%s> was <%s>", PAST.withNano(999_999_999), FUTURE.withNano(999_999_999), PAST.withNano(999_999_999), PAST))),

                // Before Boundary
                dynamicTest("Actual is Before(DOWN) and After(DOWN)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, DOWN, FUTURE, DOWN), String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST.withNano(0), FUTURE.withNano(0), FUTURE.withNano(0), FUTURE))),
                dynamicTest("Actual is Before(NONE) and After(NONE)", () -> TESTER.assertFails(FUTURE, betweenLocalDateTime(PAST, NONE, FUTURE, NONE), String.format("\nExpected: (after <%s> and before <%s>)\n      but: before <%s> was <%s>", PAST, FUTURE, FUTURE, FUTURE))),
                dynamicTest("Actual is Before(UP) and After(UP)", () -> TESTER.assertValid(FUTURE, betweenLocalDateTime(PAST, UP, FUTURE, UP)))
        );
    }

}
