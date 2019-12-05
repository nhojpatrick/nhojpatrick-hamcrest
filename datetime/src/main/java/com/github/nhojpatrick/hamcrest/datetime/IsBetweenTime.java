package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterLocalTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterOffsetTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeLocalTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeOffsetTime;
import org.hamcrest.Matcher;
import org.hamcrest.core.CombinableMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.OffsetTime;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;
import static org.hamcrest.core.CombinableMatcher.both;

public final class IsBetweenTime {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsBetweenTime.class);

    public static <T> Matcher<T> betweenLocalTime(final LocalTime after, final LocalTime before) {
        LOGGER.debug("IsBetweenTime#betweenLocalTime((After) {}, (Before) {})", after, before);
        return betweenLocalTime(after, EXCLUSIVE, before, EXCLUSIVE);
    }

    public static <T> Matcher<T> betweenLocalTime(final LocalTime after,
                                                  final CompareType afterCompareType,
                                                  final LocalTime before,
                                                  final CompareType beforeCompareType) {
        LOGGER.debug("IsBetweenTime#betweenLocalTime((After) {}, (CompareType) {}, (Before) {}, (CompareType) {})",
                after, afterCompareType, before, beforeCompareType);

        final CombinableMatcher betweenLocalTime = both(
                new IsAfterLocalTime(after, afterCompareType)
        ).and(
                new IsBeforeLocalTime(before, beforeCompareType)
        );

        if (after.equals(before)) {
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

        return betweenLocalTime;
    }

    public static <T> Matcher<T> betweenOffsetTime(final OffsetTime after, final OffsetTime before) {
        LOGGER.debug("IsBetweenTime#betweenOffsetTime((After) {}, (Before) {})", after, before);
        return betweenOffsetTime(after, EXCLUSIVE, before, EXCLUSIVE);
    }

    public static <T> Matcher<T> betweenOffsetTime(final OffsetTime after,
                                                   final CompareType afterCompareType,
                                                   final OffsetTime before,
                                                   final CompareType beforeCompareType) {
        LOGGER.debug("IsBetweenTime#betweenOffsetTime((After) {}, (CompareType) {}, (Before) {}, (CompareType) {})",
                after, afterCompareType, before, beforeCompareType);

        final CombinableMatcher betweenOffsetTime = both(
                new IsAfterOffsetTime(after, afterCompareType)
        ).and(
                new IsBeforeOffsetTime(before, beforeCompareType)
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

        return betweenOffsetTime;
    }

    IsBetweenTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
