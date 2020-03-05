package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterLocalDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterOffsetDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterZonedDateTime;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.NONE;

public final class IsAfterDateTime {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsAfterDateTime.class);

    public static <T> Matcher<T> afterLocalDateTime(final ChronoLocalDateTime expected) {
        LOGGER.debug("IsAfterTime#afterLocalDateTime((After) {})", expected);
        return doAfterLocalDateTime(expected, EXCLUSIVE, NONE);
    }

    public static <T> Matcher<T> afterLocalDateTime(final ChronoLocalDateTime expected,
                                                    final CompareType compareType) {
        LOGGER.debug("IsAfterTime#afterLocalDateTime((After) {}, (CompareType) {})", expected, compareType);
        return doAfterLocalDateTime(expected, compareType, NONE);
    }

    public static <T> Matcher<T> afterLocalDateTime(final ChronoLocalDateTime expected,
                                                    final CompareType compareType,
                                                    final RoundingType roundingType) {
        LOGGER.debug("IsAfterTime#afterLocalDateTime((After) {}, (CompareType) {}, (RoundingType) {})",
                expected, compareType, roundingType);
        return doAfterLocalDateTime(expected, compareType, roundingType);
    }

    public static <T> Matcher<T> afterLocalDateTime(final ChronoLocalDateTime expected,
                                                    final RoundingType roundingType) {
        LOGGER.debug("IsAfterTime#afterLocalDateTime((After) {}, (RoundingType) {})",
                expected, roundingType);
        return doAfterLocalDateTime(expected, EXCLUSIVE, roundingType);
    }

    public static <T> Matcher<T> afterOffsetDateTime(final OffsetDateTime expected) {
        LOGGER.debug("IsAfterTime#afterOffsetDateTime((After) {})", expected);
        return doAfterOffsetDateTime(expected, EXCLUSIVE, NONE);
    }

    public static <T> Matcher<T> afterOffsetDateTime(final OffsetDateTime expected,
                                                     final CompareType compareType) {
        LOGGER.debug("IsAfterTime#afterOffsetDateTime((After) {}, (CompareType) {})", expected, compareType);
        return doAfterOffsetDateTime(expected, compareType, NONE);
    }

    public static <T> Matcher<T> afterOffsetDateTime(final OffsetDateTime expected,
                                                     final CompareType compareType,
                                                     final RoundingType roundingType) {
        LOGGER.debug("IsAfterTime#afterOffsetDateTime((After) {}, (CompareType) {}, (RoundingType) {})",
                expected, compareType, roundingType);
        return doAfterOffsetDateTime(expected, compareType, roundingType);
    }

    public static <T> Matcher<T> afterOffsetDateTime(final OffsetDateTime expected,
                                                     final RoundingType roundingType) {
        LOGGER.debug("IsAfterTime#afterOffsetDateTime((After) {}, (RoundingType) {})",
                expected, roundingType);
        return doAfterOffsetDateTime(expected, EXCLUSIVE, roundingType);
    }

    @SuppressFBWarnings(value = "OCP_OVERLY_CONCRETE_PARAMETER", justification = "Accepted will look at changing")
    private static <T> Matcher<T> doAfterOffsetDateTime(final OffsetDateTime expected,
                                                        final CompareType compareType,
                                                        final RoundingType roundingType) {
        return new IsAfterOffsetDateTime(expected, compareType, roundingType);
    }

    public static <T> Matcher<T> afterZonedDateTime(final ChronoZonedDateTime expected) {
        LOGGER.debug("IsAfterTime#afterZonedDateTime((After) {})", expected);
        return doAfterZonedDateTime(expected, EXCLUSIVE, NONE);
    }

    public static <T> Matcher<T> afterZonedDateTime(final ChronoZonedDateTime expected,
                                                    final CompareType compareType) {
        LOGGER.debug("IsAfterTime#afterZonedDateTime((After) {}, (CompareType) {})", expected, compareType);
        return doAfterZonedDateTime(expected, compareType, NONE);
    }

    public static <T> Matcher<T> afterZonedDateTime(final ChronoZonedDateTime expected,
                                                    final CompareType compareType,
                                                    final RoundingType roundingType) {
        LOGGER.debug("IsAfterTime#afterZonedDateTime((After) {}, (CompareType) {}, (RoundingType) {})",
                expected, compareType, roundingType);
        return doAfterZonedDateTime(expected, compareType, roundingType);
    }

    public static <T> Matcher<T> afterZonedDateTime(final ChronoZonedDateTime expected,
                                                    final RoundingType roundingType) {
        LOGGER.debug("IsAfterTime#afterZonedDateTime((After) {}, (RoundingType) {})",
                expected, roundingType);
        return doAfterZonedDateTime(expected, EXCLUSIVE, roundingType);
    }

    @SuppressFBWarnings(value = "OCP_OVERLY_CONCRETE_PARAMETER", justification = "Accepted will look at changing")
    private static <T> Matcher<T> doAfterLocalDateTime(final ChronoLocalDateTime expected,
                                                       final CompareType compareType,
                                                       final RoundingType roundingType) {
        return new IsAfterLocalDateTime(expected, compareType, roundingType);
    }

    @SuppressFBWarnings(value = "OCP_OVERLY_CONCRETE_PARAMETER", justification = "Accepted will look at changing")
    private static <T> Matcher<T> doAfterZonedDateTime(final ChronoZonedDateTime expected,
                                                       final CompareType compareType,
                                                       final RoundingType roundingType) {
        return new IsAfterZonedDateTime(expected, compareType, roundingType);
    }

    IsAfterDateTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
