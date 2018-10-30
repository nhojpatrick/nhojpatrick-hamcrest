package com.github.nhojpatrick.hamcrest.datetime.internal.after;

import com.github.nhojpatrick.hamcrest.datetime.IsAfterDateTime;
import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;

import java.time.OffsetDateTime;

public class IsAfterOffsetDateTime<T extends OffsetDateTime>
        extends IsAfterDateTime<T> {

    public IsAfterOffsetDateTime(final T after, final CompareType compareType) {
        super(after, compareType);
    }

    @Override
    protected boolean matchesSafely(final T item) {

        final boolean matchesSafely = item.isAfter(this.after);
        return matchesSafely;
    }

}
