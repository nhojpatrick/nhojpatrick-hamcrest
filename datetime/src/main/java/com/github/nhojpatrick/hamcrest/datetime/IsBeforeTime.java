package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.AbstractIsBefore;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeLocalTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeOffsetTime;
import org.hamcrest.Matcher;

import java.time.LocalTime;
import java.time.OffsetTime;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;

public abstract class IsBeforeTime<T>
        extends AbstractIsBefore<T> {

    public static <T> Matcher<T> beforeLocalTime( final LocalTime before) {
        return beforeLocalTime(before, EXCLUSIVE);
    }

    public static <T> Matcher<T> beforeLocalTime( final LocalTime before, final CompareType compareType) {
        return new IsBeforeLocalTime(before, compareType);
    }

    public static <T> Matcher<T> beforeOffsetTime(final OffsetTime before) {
        return beforeOffsetTime(before, EXCLUSIVE);
    }

    public static <T> Matcher<T> beforeOffsetTime(final OffsetTime before, final CompareType compareType) {
        return new IsBeforeOffsetTime(before, compareType);
    }

    protected IsBeforeTime(final T before, final CompareType compareType) {
        super(before, compareType);
    }

}
