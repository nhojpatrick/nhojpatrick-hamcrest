package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeLocalTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeOffsetTime;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.OffsetTime;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.NONE;

public final class IsBeforeTime {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsBeforeTime.class);

    public static <T> Matcher<T> beforeLocalTime(final LocalTime expected) {
        LOGGER.debug("IsBeforeTime#beforeLocalTime((Before) {})", expected);
        return doBeforeLocalTime(expected, EXCLUSIVE, NONE);
    }

    public static <T> Matcher<T> beforeLocalTime(final LocalTime expected,
                                                final CompareType compareType) {
        LOGGER.debug("IsBeforeTime#beforeLocalTime((Before) {}, (CompareType) {})", expected, compareType);
        return doBeforeLocalTime(expected, compareType, NONE);
    }

    public static <T> Matcher<T> beforeLocalTime(final LocalTime expected,
                                                final CompareType compareType,
                                                final RoundingType roundingType) {
        LOGGER.debug("IsBeforeTime#beforeLocalTime((Before) {}, (CompareType) {}, (RoundingType) {})",
                expected, compareType, roundingType);
        return doBeforeLocalTime(expected, compareType, roundingType);
    }

    public static <T> Matcher<T> beforeLocalTime(final LocalTime expected,
                                                final RoundingType roundingType) {
        LOGGER.debug("IsBeforeTime#beforeLocalTime((Before) {}, (RoundingType) {})",
                expected, roundingType);
        return doBeforeLocalTime(expected, EXCLUSIVE, roundingType);
    }

    public static <T> Matcher<T> beforeOffsetTime(final OffsetTime expected) {
        LOGGER.debug("IsBeforeTime#beforeOffsetTime((Before) {})", expected);
        return doBeforeOffsetTime(expected, EXCLUSIVE, NONE);
    }

    public static <T> Matcher<T> beforeOffsetTime(final OffsetTime expected,
                                                 final CompareType compareType) {
        LOGGER.debug("IsBeforeTime#beforeOffsetTime((Before) {}, (CompareType) {})", expected, compareType);
        return doBeforeOffsetTime(expected, compareType, NONE);
    }

    public static <T> Matcher<T> beforeOffsetTime(final OffsetTime expected,
                                                 final CompareType compareType,
                                                 final RoundingType roundingType) {
        LOGGER.debug("IsBeforeTime#beforeOffsetTime((Before) {}, (CompareType) {}, (RoundingType) {})",
                expected, compareType, roundingType);
        return doBeforeOffsetTime(expected, compareType, roundingType);
    }

    public static <T> Matcher<T> beforeOffsetTime(final OffsetTime expected,
                                                 final RoundingType roundingType) {
        LOGGER.debug("IsBeforeTime#beforeOffsetTime((Before) {}, (RoundingType) {})",
                expected, roundingType);
        return doBeforeOffsetTime(expected, EXCLUSIVE, roundingType);
    }

    private static <T> Matcher<T> doBeforeLocalTime(final LocalTime expected,
                                                    final CompareType compareType,
                                                    final RoundingType roundingType) {
        return new IsBeforeLocalTime(expected, compareType, roundingType);
    }

    private static <T> Matcher<T> doBeforeOffsetTime(final OffsetTime expected,
                                                     final CompareType compareType,
                                                     final RoundingType roundingType) {
        return new IsBeforeOffsetTime(expected, compareType, roundingType);
    }

    IsBeforeTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
