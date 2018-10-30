package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.internal.equals.IsLocalDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.equals.IsOffsetDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.equals.IsZonedDateTime;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class IsDateTime {

    @Factory
    public static <T> Matcher<T> localDateTime() {
        return instanceOf(LocalDateTime.class);
    }

    @Factory
    public static <T> Matcher<T> localDateTime(final Matcher<T> matcher) {
        return new IsLocalDateTime(matcher);
    }

    @Factory
    public static <T> Matcher<T> offsetDateTime() {
        return instanceOf(OffsetDateTime.class);
    }

    @Factory
    public static <T> Matcher<T> offsetDateTime(final Matcher<T> matcher) {
        return new IsOffsetDateTime(matcher);
    }

    @Factory
    public static <T> Matcher<T> zonedDateTime() {
        return instanceOf(ZonedDateTime.class);
    }

    @Factory
    public static <T> Matcher<T> zonedDateTime(final Matcher<T> matcher) {
        return new IsZonedDateTime(matcher);
    }

}
