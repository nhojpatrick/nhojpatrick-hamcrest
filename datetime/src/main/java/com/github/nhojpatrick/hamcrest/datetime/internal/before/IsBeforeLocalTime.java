package com.github.nhojpatrick.hamcrest.datetime.internal.before;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;

import java.time.LocalTime;

public class IsBeforeLocalTime<T extends LocalTime>
        extends AbstractIsBefore<T> {

    public IsBeforeLocalTime(final T before,
                             final CompareType compareType) {
        super(before, compareType);
    }

    @Override
    protected boolean matchesSafely(final T item) {

        boolean matchesSafely = item.isBefore(this.before);

        switch (this.compareType) {
            case INCLUSIVE:
                final boolean notAfter = !item.isAfter(this.before);
                matchesSafely = notAfter || matchesSafely;
                break;
            default:
        }

        return matchesSafely;
    }

}
