package com.github.nhojpatrick.hamcrest.datetime.internal.before;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;

import java.time.chrono.ChronoLocalDate;

public class IsBeforeLocalDate<T extends ChronoLocalDate>
        extends AbstractIsBefore<T> {

    public IsBeforeLocalDate(final T before, final CompareType compareType) {
        super(before, compareType);
    }

    @Override
    protected boolean matchesSafely(final T item) {

        final boolean matchesSafely = item.isBefore(this.before);
        return matchesSafely;
    }

}
