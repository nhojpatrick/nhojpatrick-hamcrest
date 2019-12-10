package com.github.nhojpatrick.hamcrest.datetime.internal.after;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType;

import java.time.OffsetDateTime;

public class IsAfterOffsetDateTime<T extends OffsetDateTime>
        extends AbstractIsAfter<T> {

    public IsAfterOffsetDateTime(final T after,
                                 final CompareType compareType,
                                 final RoundingType roundingType) {
        super(after, compareType, roundingType);
    }

    @Override
    protected boolean matchesSafely(final T item) {

        boolean matchesSafely = item.isAfter(this.after);

        switch (this.compareType) {
            case INCLUSIVE:
                final boolean isEqual = item.isEqual(this.after);
                matchesSafely = isEqual || matchesSafely;
                break;
            default:
        }

        return matchesSafely;
    }

}
