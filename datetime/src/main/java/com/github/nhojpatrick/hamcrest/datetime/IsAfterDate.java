package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.AbstractIsAfter;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterLocalDate;
import org.hamcrest.Matcher;

import java.time.LocalDate;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;

public abstract class IsAfterDate<T>
        extends AbstractIsAfter<T> {

    public static <T> Matcher<T> afterLocalDate(final LocalDate after) {
        return afterLocalDate(after, EXCLUSIVE);
    }

    public static <T> Matcher<T> afterLocalDate(final LocalDate after, final CompareType compareType) {
        return new IsAfterLocalDate(after, compareType);
    }

    protected IsAfterDate(final T after, final CompareType compareType) {
        super(after, compareType);
    }

}
