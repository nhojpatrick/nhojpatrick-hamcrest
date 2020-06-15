package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterLocalTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterOffsetTime;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.OffsetTime;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.NONE;

public final class IsAfterTime {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsAfterTime.class);

    public static <T> Matcher<T> afterLocalTime(final LocalTime expected) {
        LOGGER.debug("IsAfterTime#afterLocalTime((After) {})", expected);
        return doAfterLocalTime(expected, EXCLUSIVE, NONE);
    }

    public static <T> Matcher<T> afterLocalTime(final LocalTime expected,
                                                final CompareType compareType) {
        LOGGER.debug("IsAfterTime#afterLocalTime((After) {}, (CompareType) {})", expected, compareType);
        return doAfterLocalTime(expected, compareType, NONE);
    }

    public static <T> Matcher<T> afterLocalTime(final LocalTime expected,
                                                final CompareType compareType,
                                                final RoundingType roundingType) {
        LOGGER.debug("IsAfterTime#afterLocalTime((After) {}, (CompareType) {}, (RoundingType) {})",
                expected, compareType, roundingType);
        return doAfterLocalTime(expected, compareType, roundingType);
    }

    public static <T> Matcher<T> afterLocalTime(final LocalTime expected,
                                                final RoundingType roundingType) {
        LOGGER.debug("IsAfterTime#afterLocalTime((After) {}, (RoundingType) {})",
                expected, roundingType);
        return doAfterLocalTime(expected, EXCLUSIVE, roundingType);
    }

    public static <T> Matcher<T> afterOffsetTime(final OffsetTime expected) {
        LOGGER.debug("IsAfterTime#afterOffsetTime((After) {})", expected);
        return doAfterOffsetTime(expected, EXCLUSIVE, NONE);
    }

    public static <T> Matcher<T> afterOffsetTime(final OffsetTime expected,
                                                 final CompareType compareType) {
        LOGGER.debug("IsAfterTime#afterOffsetTime((After) {}, (CompareType) {})", expected, compareType);
        return doAfterOffsetTime(expected, compareType, NONE);
    }

    public static <T> Matcher<T> afterOffsetTime(final OffsetTime expected,
                                                 final CompareType compareType,
                                                 final RoundingType roundingType) {
        LOGGER.debug("IsAfterTime#afterOffsetTime((After) {}, (CompareType) {}, (RoundingType) {})",
                expected, compareType, roundingType);
        return doAfterOffsetTime(expected, compareType, roundingType);
    }

    public static <T> Matcher<T> afterOffsetTime(final OffsetTime expected,
                                                 final RoundingType roundingType) {
        LOGGER.debug("IsAfterTime#afterOffsetTime((After) {}, (RoundingType) {})",
                expected, roundingType);
        return doAfterOffsetTime(expected, EXCLUSIVE, roundingType);
    }

    @SuppressFBWarnings(value = "OCP_OVERLY_CONCRETE_PARAMETER", justification = "Accepted will look at changing")
    private static <T> Matcher<T> doAfterLocalTime(final LocalTime expected,
                                                   final CompareType compareType,
                                                   final RoundingType roundingType) {
        return new IsAfterLocalTime(expected, compareType, roundingType);
    }

    @SuppressFBWarnings(value = "OCP_OVERLY_CONCRETE_PARAMETER", justification = "Accepted will look at changing")
    private static <T> Matcher<T> doAfterOffsetTime(final OffsetTime expected,
                                                    final CompareType compareType,
                                                    final RoundingType roundingType) {
        return new IsAfterOffsetTime(expected, compareType, roundingType);
    }

    public IsAfterTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
