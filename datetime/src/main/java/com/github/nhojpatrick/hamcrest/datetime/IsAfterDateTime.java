package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.AbstractIsAfter;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterLocalDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterOffsetDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterZonedDateTime;
import org.hamcrest.Matcher;

import java.time.OffsetDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;

public abstract class IsAfterDateTime<T>
        extends AbstractIsAfter<T> {

    public static <T> Matcher<T> afterLocalDateTime(final ChronoLocalDateTime after) {
        return afterLocalDateTime(after, EXCLUSIVE);
    }

    public static <T> Matcher<T> afterLocalDateTime(final ChronoLocalDateTime after, final CompareType compareType) {
        return new IsAfterLocalDateTime(after, compareType);
    }

    public static <T> Matcher<T> afterOffsetDateTime(final OffsetDateTime after) {
        return afterOffsetDateTime(after, EXCLUSIVE);
    }

    public static <T> Matcher<T> afterOffsetDateTime(final OffsetDateTime after, final CompareType compareType) {
        return new IsAfterOffsetDateTime(after, compareType);
    }

    public static <T> Matcher<T> afterZonedDateTime(final ChronoZonedDateTime after) {
        return afterZonedDateTime(after, EXCLUSIVE);
    }

    public static <T> Matcher<T> afterZonedDateTime(final ChronoZonedDateTime after, final CompareType compareType) {
        return new IsAfterZonedDateTime(after, compareType);
    }

    protected IsAfterDateTime(final T after, final CompareType compareType) {
        super(after, compareType);
    }

}
