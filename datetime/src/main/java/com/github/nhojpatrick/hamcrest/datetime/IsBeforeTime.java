package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.AbstractIsBefore;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeLocalTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeOffsetTime;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.OffsetTime;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;

public abstract class IsBeforeTime<T>
        extends AbstractIsBefore<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsBeforeTime.class);

    public static <T> Matcher<T> beforeLocalTime( final LocalTime before) {
        LOGGER.debug("IsBeforeTime#beforeLocalTime((LocalTime) {})", before);
        return beforeLocalTime(before, EXCLUSIVE);
    }

    public static <T> Matcher<T> beforeLocalTime( final LocalTime before, final CompareType compareType) {
        LOGGER.debug("IsBeforeTime#beforeLocalTime((LocalTime) {}, (CompareType) {})", before, compareType);
        return new IsBeforeLocalTime(before, compareType);
    }

    public static <T> Matcher<T> beforeOffsetTime(final OffsetTime before) {
        LOGGER.debug("IsBeforeTime#beforeOffsetTime((OffsetTime) {})", before);
        return beforeOffsetTime(before, EXCLUSIVE);
    }

    public static <T> Matcher<T> beforeOffsetTime(final OffsetTime before, final CompareType compareType) {
        LOGGER.debug("IsBeforeTime#beforeOffsetTime((OffsetTime) {}, (CompareType) {})", before, compareType);
        return new IsBeforeOffsetTime(before, compareType);
    }

    protected IsBeforeTime(final T before, final CompareType compareType) {
        super(before, compareType);
    }

}
