package com.github.nhojpatrick.hamcrest.datetime.java.sql.internal.before.tests;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.java.sql.internal.before.IsBeforeJavaSqlTimeImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.INCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.java.sql.internal.before.IsBeforeJavaSqlTimeImpl.SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL;
import static com.github.nhojpatrick.hamcrest.datetime.java.sql.internal.before.IsBeforeJavaSqlTimeImpl.SUPPLIED_EXPECTED_MUST_NOT_BE_NULL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("IsBeforeJavaSqlTimeImpl tests")
public class IsBeforeJavaSqlTimeImplTest {

    @ParameterizedTest
    @MethodSource("constructor_exceptionSource")
    @DisplayName("IsBeforeJavaSqlTimeImpl() exceptions tests")
    public <T extends Throwable> void constructor_exception(final Class<T> exception,
                                                            final Time before,
                                                            final CompareType compareType,
                                                            final String msg) {

        final Executable testMethod = () -> new IsBeforeJavaSqlTimeImpl(before, compareType);
        final T thrown = assertThrows(exception, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo(msg))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    public static Stream<Arguments> constructor_exceptionSource() {

        return Stream.of(
                Arguments.of(IllegalArgumentException.class, null, null, SUPPLIED_EXPECTED_MUST_NOT_BE_NULL),
                Arguments.of(IllegalArgumentException.class, new Time(1), null, SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL)
        );
    }

    @ParameterizedTest
    @MethodSource("constructor_source")
    @DisplayName("IsBeforeJavaSqlTimeImpl() tests")
    public void constructor(final Time after,
                            final CompareType compareType) {

        final IsBeforeJavaSqlTimeImpl classUnderTest = new IsBeforeJavaSqlTimeImpl(after, compareType);

        assertThat(classUnderTest, is(notNullValue()));
    }

    public static Stream<Arguments> constructor_source() {

        final Time now = new Time(LocalDateTime.now()
                .atZone(java.time.ZoneId.systemDefault())
                .withNano(0)
                .toInstant()
                .toEpochMilli());

        return Stream.of(
                Arguments.of(now, EXCLUSIVE),
                Arguments.of(now, INCLUSIVE)
        );
    }

}
