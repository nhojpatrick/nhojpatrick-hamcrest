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

import java.time.LocalDate;
import java.util.stream.Stream;

import static com.github.nhojpatrick.hamcrest.datetime.IsAfterDate.afterLocalDate;
import static com.github.nhojpatrick.hamcrest.datetime.RandomHelper.randomIntBetween;
import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.INCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.internal.after.AbstractIsAfter.SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL;
import static com.github.nhojpatrick.hamcrest.datetime.internal.after.AbstractIsAfter.SUPPLIED_EXPECTED_MUST_NOT_BE_NULL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class IsAfterDate_LocalDateTest {

    private static final LocalDate NOW = LocalDate.now();

    private static final LocalDate PAST = NOW
            .minusDays(randomIntBetween(2, 3));

    private static final LocalDate FUTURE = NOW
            .plusDays(randomIntBetween(2, 3));

    private static final MatcherObjectTester<LocalDate> TESTER = new MatcherObjectTester<>();

    @ParameterizedTest
    @MethodSource("noargs_exceptionSource")
    @DisplayName("LocalDate after() exceptions")
    public <T extends Throwable> void noargs_exceptions(final Class<T> exception,
                                                 final LocalDate expected,
                                                 final String msg) {

        final Executable testMethod = () -> afterLocalDate(expected);
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
    @DisplayName("LocalDate after() fails")
    public Stream<DynamicTest> noargs_fails() {

        return Stream.of(
                dynamicTest("Actual is after future value",
                        () -> TESTER.assertFails(NOW, afterLocalDate(FUTURE),
                                String.format("\nExpected: after <%s>\n      but: was <%s>",
                                        FUTURE,
                                        NOW
                                ))),

                // before boundary
                dynamicTest("Actual is After value",
                        () -> TESTER.assertFails(NOW, afterLocalDate(NOW),
                                String.format("\nExpected: after <%s>\n      but: was <%s>",
                                        NOW,
                                        NOW
                                )))
        );
    }

    @TestFactory
    @DisplayName("LocalDate after() valid")
    public Stream<DynamicTest> noargs_valids() {

        return Stream.of(
                dynamicTest("Actual is before past value",
                        () -> TESTER.assertValid(NOW,
                                afterLocalDate(PAST)))
        );
    }

    @ParameterizedTest
    @MethodSource("CompareType_exceptionSource")
    @DisplayName("LocalDate after(CompareType) exceptions")
    public <T extends Throwable> void CompareTypeexceptions(final Class<T> exception,
                                                            final LocalDate expected,
                                                            final CompareType compareType,
                                                            final String msg) {

        final Executable testMethod = () -> afterLocalDate(expected, compareType);
        final T thrown = assertThrows(exception, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo(msg))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    public static Stream<Arguments> CompareType_exceptionSource() {

        return Stream.of(
                Arguments.of(IllegalArgumentException.class, null, EXCLUSIVE, SUPPLIED_EXPECTED_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, LocalDate.now(), null, SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL)
        );
    }

    @TestFactory
    @DisplayName("LocalDate after(CompareType) fails")
    public Stream<DynamicTest> CompareType_fails() {

        return Stream.of(
                dynamicTest("Actual is after future(EXCLUSIVE) value",
                        () -> TESTER.assertFails(NOW, afterLocalDate(FUTURE, EXCLUSIVE),
                                String.format("\nExpected: after <%s>\n      but: was <%s>",
                                        FUTURE,
                                        NOW
                                ))),
                dynamicTest("Actual is after future(INCLUSIVE) value",
                        () -> TESTER.assertFails(NOW, afterLocalDate(FUTURE, INCLUSIVE),
                                String.format("\nExpected: after or equal to <%s>\n      but: was <%s>",
                                        FUTURE,
                                        NOW
                                ))),
                dynamicTest("Actual is After(EXCLUSIVE) value",
                        () -> TESTER.assertFails(NOW, afterLocalDate(NOW, EXCLUSIVE),
                                String.format("\nExpected: after <%s>\n      but: was <%s>",
                                        NOW,
                                        NOW
                                )))
        );
    }

    @TestFactory
    @DisplayName("LocalDate after(CompareType) valid")
    public Stream<DynamicTest> CompareType_valids() {

        return Stream.of(
                dynamicTest("Actual is before(EXCLUSIVE) past value",
                        () -> TESTER.assertValid(NOW,
                                afterLocalDate(PAST, EXCLUSIVE))),
                dynamicTest("Actual is before(INCLUSIVE) past value",
                        () -> TESTER.assertValid(NOW,
                                afterLocalDate(PAST, INCLUSIVE))),

                // after boundary
                dynamicTest("Actual is After(INCLUSIVE) value",
                        () -> TESTER.assertValid(NOW, afterLocalDate(NOW, INCLUSIVE)))
        );
    }

}
