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

    public static <T> Matcher<T> afterLocalDateTime(final ChronoLocalDateTime after) {
        LOGGER.debug("IsAfterDateTime#afterLocalDateTime((After) {})", after);
        return afterLocalDateTime(after, EXCLUSIVE);
    }

    public static <T> Matcher<T> afterLocalDateTime(final ChronoLocalDateTime after, final CompareType compareType) {
        LOGGER.debug("IsAfterDateTime#afterLocalDateTime((After) {}, (CompareType) {})", after, compareType);
        return new IsAfterLocalDateTime(after, compareType);
    }

    public static <T> Matcher<T> afterOffsetDateTime(final OffsetDateTime after) {
        LOGGER.debug("IsAfterDateTime#afterOffsetDateTime((After) {})", after);
        return afterOffsetDateTime(after, EXCLUSIVE);
    }

    public static <T> Matcher<T> afterOffsetDateTime(final OffsetDateTime after, final CompareType compareType) {
        LOGGER.debug("IsAfterDateTime#afterOffsetDateTime((After) {}, (CompareType) {})", after, compareType);
        return new IsAfterOffsetDateTime(after, compareType);
    }

    public static <T> Matcher<T> afterZonedDateTime(final ChronoZonedDateTime after) {
        LOGGER.debug("IsAfterDateTime#afterZonedDateTime((After) {})", after);
        return afterZonedDateTime(after, EXCLUSIVE);
    }

    public static <T> Matcher<T> afterZonedDateTime(final ChronoZonedDateTime after, final CompareType compareType) {
        LOGGER.debug("IsAfterDateTime#afterZonedDateTime((After) {}, (CompareType) {})", after, compareType);
        return new IsAfterZonedDateTime(after, compareType);
    }

    IsAfterDateTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
