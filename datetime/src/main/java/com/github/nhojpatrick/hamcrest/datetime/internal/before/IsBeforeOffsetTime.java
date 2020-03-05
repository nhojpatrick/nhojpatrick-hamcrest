package com.github.nhojpatrick.hamcrest.datetime.internal.before;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.time.OffsetTime;

public class IsBeforeOffsetTime<T extends OffsetTime>
        extends AbstractIsBefore<T> {

    @SuppressFBWarnings(value = "OCP_OVERLY_CONCRETE_PARAMETER", justification = "Accepted will look at changing")
    public IsBeforeOffsetTime(final T before,
                              final CompareType compareType,
                              final RoundingType roundingType) {
        super(before, compareType, roundingType);
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
