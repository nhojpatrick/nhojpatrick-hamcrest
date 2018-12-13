package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.internal.equals.IsLocalDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.equals.IsOffsetDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.equals.IsZonedDateTime;
import org.hamcrest.Matcher;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class IsDateTime {

    public static <T> Matcher<T> localDateTime() {
        return instanceOf(LocalDateTime.class);
    }

    public static <T> Matcher<T> localDateTime(final Matcher<T> matcher) {
        return new IsLocalDateTime(matcher);
    }

    public static <T> Matcher<T> offsetDateTime() {
        return instanceOf(OffsetDateTime.class);
    }

    public static <T> Matcher<T> offsetDateTime(final Matcher<T> matcher) {
        return new IsOffsetDateTime(matcher);
    }

    public static <T> Matcher<T> zonedDateTime() {
        return instanceOf(ZonedDateTime.class);
    }

    public static <T> Matcher<T> zonedDateTime(final Matcher<T> matcher) {
        return new IsZonedDateTime(matcher);
    }

}
