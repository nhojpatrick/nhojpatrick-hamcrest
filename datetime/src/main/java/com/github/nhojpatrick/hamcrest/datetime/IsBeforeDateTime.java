package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.AbstractIsBefore;
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

public abstract class IsBeforeDateTime<T>
        extends AbstractIsBefore<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsBeforeDateTime.class);

    public static <T> Matcher<T> beforeLocalDateTime(final ChronoLocalDateTime before) {
        LOGGER.debug("IsBeforeDateTime#beforeLocalDateTime((ChronoLocalDateTime) {})", before);
        return beforeLocalDateTime(before, EXCLUSIVE);
    }

    public static <T> Matcher<T> beforeLocalDateTime(final ChronoLocalDateTime before, final CompareType compareType) {
        LOGGER.debug("IsBeforeDateTime#beforeLocalDateTime((ChronoLocalDateTime) {}, (CompareType) {})", before, compareType);
        return new IsBeforeLocalDateTime(before, compareType);
    }

    public static <T> Matcher<T> beforeOffsetDateTime(final OffsetDateTime before) {
        LOGGER.debug("IsBeforeDateTime#beforeOffsetDateTime((OffsetDateTime) {})", before);
        return beforeOffsetDateTime(before, EXCLUSIVE);
    }

    public static <T> Matcher<T> beforeOffsetDateTime(final OffsetDateTime before, final CompareType compareType) {
        LOGGER.debug("IsBeforeDateTime#beforeOffsetDateTime((OffsetDateTime) {}, (CompareType) {})", before, compareType);
        return new IsBeforeOffsetDateTime(before, compareType);
    }

    public static <T> Matcher<T> beforeZonedDateTime(final ChronoZonedDateTime before) {
        LOGGER.debug("IsBeforeDateTime#beforeZonedDateTime((ChronoZonedDateTime) {})", before);
        return beforeZonedDateTime(before, EXCLUSIVE);
    }

    public static <T> Matcher<T> beforeZonedDateTime(final ChronoZonedDateTime before, final CompareType compareType) {
        LOGGER.debug("IsBeforeDateTime#beforeZonedDateTime((ChronoZonedDateTime) {}, (CompareType) {})", before, compareType);
        return new IsBeforeZonedDateTime(before, compareType);
    }

    protected IsBeforeDateTime(final T before, final CompareType compareType) {
        super(before, compareType);
    }

}
