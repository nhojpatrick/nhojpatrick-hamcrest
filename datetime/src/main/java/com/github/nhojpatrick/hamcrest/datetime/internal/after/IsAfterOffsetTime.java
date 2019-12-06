package com.github.nhojpatrick.hamcrest.datetime.internal.after;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;

import java.time.OffsetTime;

public class IsAfterOffsetTime<T extends OffsetTime>
        extends AbstractIsAfter<T> {

    public IsAfterOffsetTime(final T after, final CompareType compareType) {
        super(after, compareType);
    }

    @Override
    protected boolean matchesSafely(final T item) {

        boolean matchesSafely = item.isAfter(this.after);

        switch (this.compareType) {
            case INCLUSIVE:
                final boolean isEqual = item.isEqual(this.after);
                matchesSafely = isEqual || matchesSafely;
                break;
        }

        return matchesSafely;
    }

}
