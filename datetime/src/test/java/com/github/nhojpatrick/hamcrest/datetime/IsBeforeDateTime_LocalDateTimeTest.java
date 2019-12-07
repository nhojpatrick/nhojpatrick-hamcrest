package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
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

import static com.github.nhojpatrick.hamcrest.datetime.IsBeforeDateTime.beforeLocalDateTime;
import static com.github.nhojpatrick.hamcrest.datetime.RandomHelper.randomIntBetween;
import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.INCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.internal.before.AbstractIsBefore.SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL;
import static com.github.nhojpatrick.hamcrest.datetime.internal.before.AbstractIsBefore.SUPPLIED_EXPECTED_MUST_NOT_BE_NULL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class IsBeforeDateTime_LocalDateTimeTest {

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
    @DisplayName("LocalDateTime before() exceptions")
    public <T extends Throwable> void noargs_exceptions(final Class<T> exception,
                                                        final LocalDateTime expected,
                                                        final String msg) {

        final Executable testMethod = () -> beforeLocalDateTime(expected);
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
    @DisplayName("LocalDateTime before() tests")
    public Stream<DynamicTest> noargs_tests() {

        return Stream.of(
                // after
                dynamicTest("Actual is after Before()", () -> TESTER.assertFails(NOW, beforeLocalDateTime(PAST), String.format("\nExpected: before <%s>\n      but: was <%s>", PAST, NOW))),

                // before
                dynamicTest("Actual is before Before()", () -> TESTER.assertValid(NOW, beforeLocalDateTime(FUTURE))),

                // boundary
                dynamicTest("Actual is Before()", () -> TESTER.assertFails(NOW, beforeLocalDateTime(NOW), String.format("\nExpected: before <%s>\n      but: was <%s>", NOW, NOW)))
        );
    }

    @ParameterizedTest
    @MethodSource("CompareType_exceptionSource")
    @DisplayName("LocalDateTime before(CompareType) exceptions")
    public <T extends Throwable> void CompareType_exceptions(final Class<T> exception,
                                                             final LocalDateTime expected,
                                                             final CompareType compareType,
                                                             final String msg) {

        final Executable testMethod = () -> beforeLocalDateTime(expected, compareType);
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
    @DisplayName("LocalDateTime before(CompareType) tests")
    public Stream<DynamicTest> CompareType_tests() {

        return Stream.of(
                // after
                dynamicTest("Actual is after Before(EXCLUSIVE)", () -> TESTER.assertFails(NOW, beforeLocalDateTime(PAST, EXCLUSIVE), String.format("\nExpected: before <%s>\n      but: was <%s>", PAST, NOW))),
                dynamicTest("Actual is after Before(INCLUSIVE)", () -> TESTER.assertFails(NOW, beforeLocalDateTime(PAST, INCLUSIVE), String.format("\nExpected: before or equal to <%s>\n      but: was <%s>", PAST, NOW))),

                // before
                dynamicTest("Actual is before Before(EXCLUSIVE)", () -> TESTER.assertValid(NOW, beforeLocalDateTime(FUTURE, EXCLUSIVE))),
                dynamicTest("Actual is before Before(INCLUSIVE)", () -> TESTER.assertValid(NOW, beforeLocalDateTime(FUTURE, INCLUSIVE))),

                // boundary
                dynamicTest("Actual is Before(EXCLUSIVE)", () -> TESTER.assertFails(NOW, beforeLocalDateTime(NOW, EXCLUSIVE), String.format("\nExpected: before <%s>\n      but: was <%s>", NOW, NOW))),
                dynamicTest("Actual is Before(INCLUSIVE)", () -> TESTER.assertValid(NOW, beforeLocalDateTime(NOW, INCLUSIVE)))
        );
    }

}
