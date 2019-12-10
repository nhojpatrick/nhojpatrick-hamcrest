package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterLocalTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterOffsetTime;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.OffsetTime;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;

public final class IsAfterTime {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsAfterTime.class);

    public static <T> Matcher<T> afterLocalTime(final LocalTime expected) {
        LOGGER.debug("IsAfterTime#afterLocalTime((After) {})", expected);
        return doAfterLocalTime(expected, EXCLUSIVE);
    }

    public static <T> Matcher<T> afterLocalTime(final LocalTime expected,
                                                final CompareType compareType) {
        LOGGER.debug("IsAfterTime#afterLocalTime((After) {}, (CompareType) {})", expected, compareType);
        return doAfterLocalTime(expected, compareType);
    }

    public static <T> Matcher<T> afterOffsetTime(final OffsetTime expected) {
        LOGGER.debug("IsAfterTime#afterOffsetTime((After) {})", expected);
        return doAfterOffsetTime(expected, EXCLUSIVE);
    }

    public static <T> Matcher<T> afterOffsetTime(final OffsetTime expected,
                                                 final CompareType compareType) {
        LOGGER.debug("IsAfterTime#afterOffsetTime((After) {}, (CompareType) {})", expected, compareType);
        return doAfterOffsetTime(expected, compareType);
    }

    private static <T> Matcher<T> doAfterLocalTime(final LocalTime expected,
                                                   final CompareType compareType) {
        return new IsAfterLocalTime(expected, compareType);
    }

    private static <T> Matcher<T> doAfterOffsetTime(final OffsetTime expected,
                                                    final CompareType compareType) {
        return new IsAfterOffsetTime(expected, compareType);
    }

    IsAfterTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
