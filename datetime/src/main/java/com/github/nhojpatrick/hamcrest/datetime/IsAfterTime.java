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

    public static <T> Matcher<T> afterLocalTime(final LocalTime after) {
        LOGGER.debug("IsAfterTime#afterLocalTime((After) {})", after);
        return afterLocalTime(after, EXCLUSIVE);
    }

    public static <T> Matcher<T> afterLocalTime(final LocalTime after,
                                                final CompareType compareType) {
        LOGGER.debug("IsAfterTime#afterLocalTime((After) {}, (CompareType) {})", after, compareType);
        return new IsAfterLocalTime(after, compareType);
    }

    public static <T> Matcher<T> afterOffsetTime(final OffsetTime after) {
        LOGGER.debug("IsAfterTime#afterOffsetTime((After) {})", after);
        return afterOffsetTime(after, EXCLUSIVE);
    }

    public static <T> Matcher<T> afterOffsetTime(final OffsetTime after,
                                                 final CompareType compareType) {
        LOGGER.debug("IsAfterTime#afterOffsetTime((After) {}, (CompareType) {})", after, compareType);
        return new IsAfterOffsetTime(after, compareType);
    }

    IsAfterTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
