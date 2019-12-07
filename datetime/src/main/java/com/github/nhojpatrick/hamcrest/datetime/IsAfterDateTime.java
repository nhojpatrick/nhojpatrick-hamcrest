package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterLocalDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterOffsetDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterZonedDateTime;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;

public final class IsAfterDateTime {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsAfterDateTime.class);

    public static <T> Matcher<T> afterLocalDateTime(final ChronoLocalDateTime expected) {
        LOGGER.debug("IsAfterDateTime#afterLocalDateTime((After) {})", expected);
        return doAfterLocalDateTime(expected, EXCLUSIVE);
    }

    public static <T> Matcher<T> afterLocalDateTime(final ChronoLocalDateTime expected,
                                                    final CompareType compareType) {
        LOGGER.debug("IsAfterDateTime#afterLocalDateTime((After) {}, (CompareType) {})", expected, compareType);
        return doAfterLocalDateTime(expected, compareType);
    }

    public static <T> Matcher<T> afterOffsetDateTime(final OffsetDateTime expected) {
        LOGGER.debug("IsAfterDateTime#afterOffsetDateTime((After) {})", expected);
        return doAfterOffsetDateTime(expected, EXCLUSIVE);
    }

    public static <T> Matcher<T> afterOffsetDateTime(final OffsetDateTime expected,
                                                     final CompareType compareType) {
        LOGGER.debug("IsAfterDateTime#afterOffsetDateTime((After) {}, (CompareType) {})", expected, compareType);
        return doAfterOffsetDateTime(expected, compareType);
    }

    public static <T> Matcher<T> afterZonedDateTime(final ChronoZonedDateTime expected) {
        LOGGER.debug("IsAfterDateTime#afterZonedDateTime((After) {})", expected);
        return doAfterZonedDateTime(expected, EXCLUSIVE);
    }

    public static <T> Matcher<T> afterZonedDateTime(final ChronoZonedDateTime expected,
                                                    final CompareType compareType) {
        LOGGER.debug("IsAfterDateTime#afterZonedDateTime((After) {}, (CompareType) {})", expected, compareType);
        return doAfterZonedDateTime(expected, compareType);
    }

    private static <T> Matcher<T> doAfterLocalDateTime(final ChronoLocalDateTime expected,
                                                       final CompareType compareType) {
        return new IsAfterLocalDateTime(expected, compareType);
    }

    private static <T> Matcher<T> doAfterOffsetDateTime(final OffsetDateTime expected,
                                                        final CompareType compareType) {
        return new IsAfterOffsetDateTime(expected, compareType);
    }

    private static <T> Matcher<T> doAfterZonedDateTime(final ChronoZonedDateTime expected,
                                                       final CompareType compareType) {
        return new IsAfterZonedDateTime(expected, compareType);
    }

    IsAfterDateTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
