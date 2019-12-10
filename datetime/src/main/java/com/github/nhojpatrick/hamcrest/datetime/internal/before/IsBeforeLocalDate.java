package com.github.nhojpatrick.hamcrest.datetime.internal.before;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;

import java.time.chrono.ChronoLocalDate;

import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.NONE;

public class IsBeforeLocalDate<T extends ChronoLocalDate>
        extends AbstractIsBefore<T> {

    public IsBeforeLocalDate(final T before,
                             final CompareType compareType) {
        super(before, compareType, NONE);
    }

    @Override
    protected boolean matchesSafely(final T item) {

        boolean matchesSafely = item.isBefore(this.before);

        switch (this.compareType) {
            case INCLUSIVE:
                final boolean isEqual = item.isEqual(this.before);
                matchesSafely = isEqual || matchesSafely;
                break;
            default:
        }

        return matchesSafely;
    }

}
