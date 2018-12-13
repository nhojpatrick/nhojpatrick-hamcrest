package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterLocalDate;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeLocalDate;
import org.hamcrest.Matcher;
import org.hamcrest.core.CombinableMatcher;

import java.time.LocalDate;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;
import static org.hamcrest.core.CombinableMatcher.both;

public class IsBetweenDate {

    public static <T> Matcher<T> betweenLocalDate(final LocalDate after, final LocalDate before) {
        return betweenLocalDate(after, EXCLUSIVE, before, EXCLUSIVE);
    }

    public static <T> Matcher<T> betweenLocalDate(final LocalDate after, final CompareType afterCompareType, final LocalDate before, final CompareType beforeCompareType) {

        final CombinableMatcher betweenLocalDate = both(
                new IsAfterLocalDate(after, afterCompareType)
        ).and(
                new IsBeforeLocalDate(before, beforeCompareType)
        );

        if (after.isEqual(before)) {
            throw new IllegalStateException(
                    String.format("After <%s> must not equal Before <%s>.",
                            after,
                            before
                    ));
        }

        if (after.isAfter(before)
                || before.isBefore(after)) {
            throw new IllegalStateException(
                    String.format("After <%s> must be Before <%s>.",
                            after,
                            before
                    ));
        }

        return betweenLocalDate;
    }

}
