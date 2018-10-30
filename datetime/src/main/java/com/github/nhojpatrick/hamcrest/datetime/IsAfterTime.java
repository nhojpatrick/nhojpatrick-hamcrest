package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.AbstractIsAfter;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterLocalTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterOffsetTime;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import java.time.LocalTime;
import java.time.OffsetTime;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;

public abstract class IsAfterTime<T>
        extends AbstractIsAfter<T> {

    @Factory
    public static <T> Matcher<T> afterLocalTime(final LocalTime after) {
        return afterLocalTime(after, EXCLUSIVE);
    }

    @Factory
    public static <T> Matcher<T> afterLocalTime(final LocalTime after, final CompareType compareType) {
        return new IsAfterLocalTime(after, compareType);
    }

    @Factory
    public static <T> Matcher<T> afterOffsetTime(final OffsetTime after) {
        return afterOffsetTime(after, EXCLUSIVE);
    }

    @Factory
    public static <T> Matcher<T> afterOffsetTime(final OffsetTime after, final CompareType compareType) {
        return new IsAfterOffsetTime(after, compareType);
    }

    protected IsAfterTime(final T after, final CompareType compareType) {
        super(after, compareType);
    }

}
