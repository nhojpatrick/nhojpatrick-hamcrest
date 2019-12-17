package com.github.nhojpatrick.hamcrest.datetime.internal.equals;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractIsDateTimeTest {

    static class TestingAbstractIsDateTime<T extends LocalDateTime>
            extends AbstractIsDateTime<T> {

        public TestingAbstractIsDateTime(final Matcher<T> matcher, final String type) {
            super(matcher, type);
        }

        @Override
        protected boolean matchesSafely(LocalDateTime localDateTime) {
            return false;
        }

    }

    @Test
    public void constructorNullMatcher() {

        final Executable testMethod = () -> new TestingAbstractIsDateTime(null, "type");
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo("Supplied Matcher must not be null"))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

}
