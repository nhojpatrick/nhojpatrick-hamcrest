package com.github.nhojpatrick.hamcrest.datetime.java.sql.internal.equals;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static com.github.nhojpatrick.hamcrest.datetime.java.sql.internal.equals.IsEqualJavaSqlTimeImpl.SUPPLIED_MATCHER_MUST_NOT_BE_NULL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("IsEqualJavaSqlTimeImpl tests")
public class IsEqualJavaSqlTimeImplTest {

    @ParameterizedTest
    @MethodSource("constructor_exceptionSource")
    @DisplayName("IsEqualJavaSqlTimeImpl() exceptions tests")
    public <T extends Throwable, S extends Time> void constructor_exception(final Class<T> exception,
                                                                            final Matcher<S> matcher,
                                                                            final String msg) {

        final Executable testMethod = () -> new IsEqualJavaSqlTimeImpl(matcher);
        final T thrown = assertThrows(exception, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo(msg))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    public static Stream<Arguments> constructor_exceptionSource() {

        return Stream.of(
                Arguments.of(IllegalArgumentException.class, null, SUPPLIED_MATCHER_MUST_NOT_BE_NULL)
        );
    }

    @ParameterizedTest
    @MethodSource("constructor_source")
    @DisplayName("IsEqualJavaSqlTimeImpl() tests")
    public <T extends Time> void constructor(final Matcher<T> matcher) {

        final IsEqualJavaSqlTimeImpl classUnderTest = new IsEqualJavaSqlTimeImpl(matcher);

        assertThat(classUnderTest, is(notNullValue()));
    }

    public static Stream<Arguments> constructor_source() {

        final Time now = new Time(LocalDateTime.now()
                .atZone(java.time.ZoneId.systemDefault())
                .withNano(0)
                .toInstant()
                .toEpochMilli());

        return Stream.of(
                Arguments.of(equalTo(now))
        );
    }

}
