package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeLocalTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeOffsetTime;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.OffsetTime;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;

public final class IsBeforeTime {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsBeforeTime.class);

    public static <T> Matcher<T> beforeLocalTime(final LocalTime before) {
        LOGGER.debug("IsBeforeTime#beforeLocalTime((Before) {})", before);
        return beforeLocalTime(before, EXCLUSIVE);
    }

    public static <T> Matcher<T> beforeLocalTime(final LocalTime before,
                                                 final CompareType compareType) {
        LOGGER.debug("IsBeforeTime#beforeLocalTime((Before) {}, (CompareType) {})", before, compareType);
        return new IsBeforeLocalTime(before, compareType);
    }

    public static <T> Matcher<T> beforeOffsetTime(final OffsetTime before) {
        LOGGER.debug("IsBeforeTime#beforeOffsetTime((Before) {})", before);
        return beforeOffsetTime(before, EXCLUSIVE);
    }

    public static <T> Matcher<T> beforeOffsetTime(final OffsetTime before,
                                                  final CompareType compareType) {
        LOGGER.debug("IsBeforeTime#beforeOffsetTime((Before) {}, (CompareType) {})", before, compareType);
        return new IsBeforeOffsetTime(before, compareType);
    }

    IsBeforeTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
