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

    public static <T> Matcher<T> beforeLocalTime(final LocalTime expected) {
        LOGGER.debug("IsBeforeTime#beforeLocalTime((Before) {})", expected);
        return doBeforeLocalTime(expected, EXCLUSIVE);
    }

    public static <T> Matcher<T> beforeLocalTime(final LocalTime expected,
                                                 final CompareType compareType) {
        LOGGER.debug("IsBeforeTime#beforeLocalTime((Before) {}, (CompareType) {})", expected, compareType);
        return doBeforeLocalTime(expected, compareType);
    }

    public static <T> Matcher<T> beforeOffsetTime(final OffsetTime expected) {
        LOGGER.debug("IsBeforeTime#beforeOffsetTime((Before) {})", expected);
        return doBeforeLocalTime(expected, EXCLUSIVE);
    }

    public static <T> Matcher<T> beforeOffsetTime(final OffsetTime expected,
                                                  final CompareType compareType) {
        LOGGER.debug("IsBeforeTime#beforeOffsetTime((Before) {}, (CompareType) {})", expected, compareType);
        return doBeforeLocalTime(expected, compareType);
    }

    private static <T> Matcher<T> doBeforeLocalTime(final LocalTime expected,
                                                    final CompareType compareType) {
        return new IsBeforeLocalTime(expected, compareType);
    }

    private static <T> Matcher<T> doBeforeLocalTime(final OffsetTime expected,
                                                    final CompareType compareType) {
        return new IsBeforeOffsetTime(expected, compareType);
    }

    IsBeforeTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
