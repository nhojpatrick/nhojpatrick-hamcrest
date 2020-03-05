package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeLocalDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeOffsetDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeZonedDateTime;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.NONE;

public final class IsBeforeDateTime {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsBeforeDateTime.class);

    public static <T> Matcher<T> beforeLocalDateTime(final ChronoLocalDateTime expected) {
        LOGGER.debug("IsBeforeTime#beforeLocalDateTime((Before) {})", expected);
        return doBeforeLocalDateTime(expected, EXCLUSIVE, NONE);
    }

    public static <T> Matcher<T> beforeLocalDateTime(final ChronoLocalDateTime expected,
                                                     final CompareType compareType) {
        LOGGER.debug("IsBeforeTime#beforeLocalDateTime((Before) {}, (CompareType) {})", expected, compareType);
        return doBeforeLocalDateTime(expected, compareType, NONE);
    }

    public static <T> Matcher<T> beforeLocalDateTime(final ChronoLocalDateTime expected,
                                                     final CompareType compareType,
                                                     final RoundingType roundingType) {
        LOGGER.debug("IsBeforeTime#beforeLocalDateTime((Before) {}, (CompareType) {}, (RoundingType) {})",
                expected, compareType, roundingType);
        return doBeforeLocalDateTime(expected, compareType, roundingType);
    }

    public static <T> Matcher<T> beforeLocalDateTime(final ChronoLocalDateTime expected,
                                                     final RoundingType roundingType) {
        LOGGER.debug("IsBeforeTime#beforeLocalDateTime((Before) {}, (RoundingType) {})",
                expected, roundingType);
        return doBeforeLocalDateTime(expected, EXCLUSIVE, roundingType);
    }

    public static <T> Matcher<T> beforeOffsetDateTime(final OffsetDateTime expected) {
        LOGGER.debug("IsBeforeTime#beforeOffsetDateTime((Before) {})", expected);
        return doBeforeOffsetDateTime(expected, EXCLUSIVE, NONE);
    }

    public static <T> Matcher<T> beforeOffsetDateTime(final OffsetDateTime expected,
                                                      final CompareType compareType) {
        LOGGER.debug("IsBeforeTime#beforeOffsetDateTime((Before) {}, (CompareType) {})", expected, compareType);
        return doBeforeOffsetDateTime(expected, compareType, NONE);
    }

    public static <T> Matcher<T> beforeOffsetDateTime(final OffsetDateTime expected,
                                                      final CompareType compareType,
                                                      final RoundingType roundingType) {
        LOGGER.debug("IsBeforeTime#beforeOffsetDateTime((Before) {}, (CompareType) {}, (RoundingType) {})",
                expected, compareType, roundingType);
        return doBeforeOffsetDateTime(expected, compareType, roundingType);
    }

    public static <T> Matcher<T> beforeOffsetDateTime(final OffsetDateTime expected,
                                                      final RoundingType roundingType) {
        LOGGER.debug("IsBeforeTime#beforeOffsetDateTime((Before) {}, (RoundingType) {})",
                expected, roundingType);
        return doBeforeOffsetDateTime(expected, EXCLUSIVE, roundingType);
    }

    @SuppressFBWarnings(value = "OCP_OVERLY_CONCRETE_PARAMETER", justification = "Accepted will look at changing")
    private static <T> Matcher<T> doBeforeOffsetDateTime(final OffsetDateTime expected,
                                                         final CompareType compareType,
                                                         final RoundingType roundingType) {
        return new IsBeforeOffsetDateTime(expected, compareType, roundingType);
    }

    public static <T> Matcher<T> beforeZonedDateTime(final ChronoZonedDateTime expected) {
        LOGGER.debug("IsBeforeTime#beforeZonedDateTime((Before) {})", expected);
        return doBeforeZonedDateTime(expected, EXCLUSIVE, NONE);
    }

    public static <T> Matcher<T> beforeZonedDateTime(final ChronoZonedDateTime expected,
                                                     final CompareType compareType) {
        LOGGER.debug("IsBeforeTime#beforeZonedDateTime((Before) {}, (CompareType) {})", expected, compareType);
        return doBeforeZonedDateTime(expected, compareType, NONE);
    }

    public static <T> Matcher<T> beforeZonedDateTime(final ChronoZonedDateTime expected,
                                                     final CompareType compareType,
                                                     final RoundingType roundingType) {
        LOGGER.debug("IsBeforeTime#beforeZonedDateTime((Before) {}, (CompareType) {}, (RoundingType) {})",
                expected, compareType, roundingType);
        return doBeforeZonedDateTime(expected, compareType, roundingType);
    }

    public static <T> Matcher<T> beforeZonedDateTime(final ChronoZonedDateTime expected,
                                                     final RoundingType roundingType) {
        LOGGER.debug("IsBeforeTime#beforeZonedDateTime((Before) {}, (RoundingType) {})",
                expected, roundingType);
        return doBeforeZonedDateTime(expected, EXCLUSIVE, roundingType);
    }

    @SuppressFBWarnings(value = "OCP_OVERLY_CONCRETE_PARAMETER", justification = "Accepted will look at changing")
    private static <T> Matcher<T> doBeforeLocalDateTime(final ChronoLocalDateTime expected,
                                                        final CompareType compareType,
                                                        final RoundingType roundingType) {
        return new IsBeforeLocalDateTime(expected, compareType, roundingType);
    }

    @SuppressFBWarnings(value = "OCP_OVERLY_CONCRETE_PARAMETER", justification = "Accepted will look at changing")
    private static <T> Matcher<T> doBeforeZonedDateTime(final ChronoZonedDateTime expected,
                                                        final CompareType compareType,
                                                        final RoundingType roundingType) {
        return new IsBeforeZonedDateTime(expected, compareType, roundingType);
    }

    IsBeforeDateTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
