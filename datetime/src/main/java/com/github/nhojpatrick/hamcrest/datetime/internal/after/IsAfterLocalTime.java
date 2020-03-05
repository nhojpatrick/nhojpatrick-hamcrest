package com.github.nhojpatrick.hamcrest.datetime.internal.after;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.time.LocalTime;

public class IsAfterLocalTime<T extends LocalTime>
        extends AbstractIsAfter<T> {

    @SuppressFBWarnings(value = "OCP_OVERLY_CONCRETE_PARAMETER", justification = "Accepted will look at changing")
    public IsAfterLocalTime(final T after,
                            final CompareType compareType,
                            final RoundingType roundingType) {
        super(after, compareType, roundingType);
    }

    @Override
    protected boolean matchesSafely(final T item) {

        boolean matchesSafely = item.isAfter(this.after);

        switch (this.compareType) {
            case INCLUSIVE:
                final boolean notBefore = !item.isBefore(this.after);
                matchesSafely = notBefore || matchesSafely;
                break;
            default:
        }

        return matchesSafely;
    }

}
