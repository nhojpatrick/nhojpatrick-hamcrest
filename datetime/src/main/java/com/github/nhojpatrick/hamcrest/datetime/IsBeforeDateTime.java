package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeLocalDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeOffsetDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeZonedDateTime;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;

public final class IsBeforeDateTime {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsBeforeDateTime.class);

    public static <T> Matcher<T> beforeLocalDateTime(final ChronoLocalDateTime expected) {
        LOGGER.debug("IsBeforeDateTime#beforeLocalDateTime((Before) {})", expected);
        return doBeforeLocalDateTime(expected, EXCLUSIVE);
    }

    public static <T> Matcher<T> beforeLocalDateTime(final ChronoLocalDateTime expected,
                                                     final CompareType compareType) {
        LOGGER.debug("IsBeforeDateTime#beforeLocalDateTime((Before) {}, (CompareType) {})", expected, compareType);
        return doBeforeLocalDateTime(expected, compareType);
    }

    public static <T> Matcher<T> beforeOffsetDateTime(final OffsetDateTime expected) {
        LOGGER.debug("IsBeforeDateTime#beforeOffsetDateTime((Before) {})", expected);
        return doBeforeOffsetDateTime(expected, EXCLUSIVE);
    }

    public static <T> Matcher<T> beforeOffsetDateTime(final OffsetDateTime expected,
                                                      final CompareType compareType) {
        LOGGER.debug("IsBeforeDateTime#beforeOffsetDateTime((Before) {}, (CompareType) {})", expected, compareType);
        return doBeforeOffsetDateTime(expected, compareType);
    }

    public static <T> Matcher<T> beforeZonedDateTime(final ChronoZonedDateTime expected) {
        LOGGER.debug("IsBeforeDateTime#beforeZonedDateTime((Before) {})", expected);
        return doBeforeZonedDateTime(expected, EXCLUSIVE);
    }

    public static <T> Matcher<T> beforeZonedDateTime(final ChronoZonedDateTime expected,
                                                     final CompareType compareType) {
        LOGGER.debug("IsBeforeDateTime#beforeZonedDateTime((Before) {}, (CompareType) {})", expected, compareType);
        return doBeforeZonedDateTime(expected, compareType);
    }

    private static <T> Matcher<T> doBeforeLocalDateTime(final ChronoLocalDateTime expected,
                                                        final CompareType compareType) {
        return new IsBeforeLocalDateTime(expected, compareType);
    }

    private static <T> Matcher<T> doBeforeOffsetDateTime(final OffsetDateTime expected,
                                                         final CompareType compareType) {
        return new IsBeforeOffsetDateTime(expected, compareType);
    }

    private static <T> Matcher<T> doBeforeZonedDateTime(final ChronoZonedDateTime expected,
                                                        final CompareType compareType) {
        return new IsBeforeZonedDateTime(expected, compareType);
    }

    IsBeforeDateTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
