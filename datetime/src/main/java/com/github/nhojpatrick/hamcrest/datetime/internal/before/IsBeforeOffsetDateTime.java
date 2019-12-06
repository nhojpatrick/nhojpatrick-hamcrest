package com.github.nhojpatrick.hamcrest.datetime.internal.before;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;

import java.time.OffsetDateTime;

public class IsBeforeOffsetDateTime<T extends OffsetDateTime>
        extends AbstractIsBefore<T> {

    public IsBeforeOffsetDateTime(final T before, final CompareType compareType) {
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
