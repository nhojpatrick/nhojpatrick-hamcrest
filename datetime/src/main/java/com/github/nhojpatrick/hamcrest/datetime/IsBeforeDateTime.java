package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.AbstractIsBefore;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeLocalDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeOffsetDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeZonedDateTime;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import java.time.OffsetDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;

public abstract class IsBeforeDateTime<T>
        extends AbstractIsBefore<T> {

    @Factory
    public static <T> Matcher<T> beforeLocalDateTime(final ChronoLocalDateTime before) {
        return beforeLocalDateTime(before, EXCLUSIVE);
    }

    @Factory
    public static <T> Matcher<T> beforeLocalDateTime(final ChronoLocalDateTime before, final CompareType compareType) {
        return new IsBeforeLocalDateTime(before, compareType);
    }

    @Factory
    public static <T> Matcher<T> beforeOffsetDateTime(final OffsetDateTime before) {
        return beforeOffsetDateTime(before, EXCLUSIVE);
    }

    @Factory
    public static <T> Matcher<T> beforeOffsetDateTime(final OffsetDateTime before, final CompareType compareType) {
        return new IsBeforeOffsetDateTime(before, compareType);
    }

    @Factory
    public static <T> Matcher<T> beforeZonedDateTime(final ChronoZonedDateTime before) {
        return beforeZonedDateTime(before, EXCLUSIVE);
    }

    @Factory
    public static <T> Matcher<T> beforeZonedDateTime(final ChronoZonedDateTime before, final CompareType compareType) {
        return new IsBeforeZonedDateTime(before, compareType);
    }

    protected IsBeforeDateTime(final T before, final CompareType compareType) {
        super(before, compareType);
    }

}
