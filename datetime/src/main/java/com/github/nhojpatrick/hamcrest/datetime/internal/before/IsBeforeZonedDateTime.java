package com.github.nhojpatrick.hamcrest.datetime.internal.before;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;

import java.time.chrono.ChronoZonedDateTime;

public class IsBeforeZonedDateTime<T extends ChronoZonedDateTime>
        extends AbstractIsBefore<T> {

    public IsBeforeZonedDateTime(final T before,
                                 final CompareType compareType) {
        super(before, compareType);
    }

    @Override
    protected boolean matchesSafely(final T item) {

        boolean matchesSafely = item.isBefore(this.before);

        switch (this.compareType) {
            case INCLUSIVE:
                final boolean isEqual = item.isEqual(this.before);
                matchesSafely = isEqual || matchesSafely;
                break;
        }

        return matchesSafely;
    }

}
