package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.AbstractIsBefore;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeLocalDate;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import java.time.LocalDate;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;

public abstract class IsBeforeDate<T>
        extends AbstractIsBefore<T> {

    @Factory
    public static <T> Matcher<T> beforeLocalDate(final LocalDate before) {
        return beforeLocalDate(before, EXCLUSIVE);
    }

    @Factory
    public static <T> Matcher<T> beforeLocalDate(final LocalDate before, final CompareType compareType) {
        return new IsBeforeLocalDate(before, compareType);
    }

    protected IsBeforeDate(final T before, final CompareType compareType) {
        super(before, compareType);
    }

}
