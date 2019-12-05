package com.github.nhojpatrick.hamcrest.datetime.internal.before;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;

import java.time.OffsetTime;

public class IsBeforeOffsetTime<T extends OffsetTime>
        extends AbstractIsBefore<T> {

    public IsBeforeOffsetTime(final T before, final CompareType compareType) {
        super(before, compareType);
    }

    @Override
    protected boolean matchesSafely(final T item) {

        final boolean matchesSafely = item.isBefore(this.before);
        return matchesSafely;
    }

}
