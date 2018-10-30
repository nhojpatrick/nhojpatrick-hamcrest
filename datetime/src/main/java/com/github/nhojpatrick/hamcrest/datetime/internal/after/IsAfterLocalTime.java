package com.github.nhojpatrick.hamcrest.datetime.internal.after;

import com.github.nhojpatrick.hamcrest.datetime.IsAfterDateTime;
import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;

import java.time.LocalTime;

public class IsAfterLocalTime<T extends LocalTime>
        extends IsAfterDateTime<T> {

    public IsAfterLocalTime(final T after, final CompareType compareType) {
        super(after, compareType);
    }

    @Override
    protected boolean matchesSafely(final T item) {

        final boolean matchesSafely = item.isAfter(this.after);
        return matchesSafely;
    }

}
