package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.internal.equals.IsLocalTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.equals.IsOffsetTime;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import java.time.LocalTime;
import java.time.OffsetTime;

import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class IsTime {

    @Factory
    public static <T> Matcher<T> localTime() {
        return instanceOf(LocalTime.class);
    }

    @Factory
    public static <T> Matcher<T> localTime(final Matcher<T> matcher) {
        return new IsLocalTime(matcher);
    }

    @Factory
    public static <T> Matcher<T> offsetTime() {
        return instanceOf(OffsetTime.class);
    }

    @Factory
    public static <T> Matcher<T> offsetTime(final Matcher<T> matcher) {
        return new IsOffsetTime(matcher);
    }

}
