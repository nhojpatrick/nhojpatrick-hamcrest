package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType;
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
import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.NONE;
import static org.hamcrest.core.CombinableMatcher.both;

public final class IsBetweenTime {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsBetweenTime.class);

    public static <T> Matcher<T> betweenLocalTime(final LocalTime after,
                                                  final LocalTime before) {
        LOGGER.debug("IsBetweenTime#betweenLocalTime((After) {}, (Before) {})", after, before);
        return doBetweenLocalTime(after, EXCLUSIVE, NONE, before, EXCLUSIVE, NONE);
    }

    public static <T> Matcher<T> betweenLocalTime(final LocalTime after,
                                                  final CompareType afterCompareType,
                                                  final LocalTime before,
                                                  final CompareType beforeCompareType) {
        LOGGER.debug("IsBetweenTime#betweenLocalTime((After) {}, (CompareType) {}, (Before) {}, (CompareType) {})",
                after, afterCompareType, before, beforeCompareType);
        return doBetweenLocalTime(after, afterCompareType, NONE, before, beforeCompareType, NONE);
    }

    public static <T> Matcher<T> betweenLocalTime(final LocalTime after,
                                                  final CompareType afterCompareType,
                                                  final RoundingType afterRoundingType,
                                                  final LocalTime before,
                                                  final CompareType beforeCompareType,
                                                  final RoundingType beforeRoundingType) {
        LOGGER.debug("IsBetweenTime#betweenLocalTime((After) {}, (CompareType) {}, (RoundingType) {}, (Before) {}, (CompareType) {}, (RoundingType) {})",
                after, afterCompareType, afterRoundingType, before, beforeCompareType, beforeRoundingType);
        return doBetweenLocalTime(after, afterCompareType, afterRoundingType, before, beforeCompareType, beforeRoundingType);
    }

    public static <T> Matcher<T> betweenLocalTime(final LocalTime after,
                                                  final RoundingType afterRoundingType,
                                                  final LocalTime before,
                                                  final RoundingType beforeRoundingType) {
        LOGGER.debug("IsBetweenTime#betweenLocalTime((After) {}, (RoundingType) {}, (Before) {}, (RoundingType) {})",
                after, afterRoundingType, before, beforeRoundingType);
        return doBetweenLocalTime(after, EXCLUSIVE, afterRoundingType, before, EXCLUSIVE, beforeRoundingType);
    }

    public static <T> Matcher<T> betweenOffsetTime(final OffsetTime after,
                                                   final OffsetTime before) {
        LOGGER.debug("IsBetweenTime#betweenOffsetTime((After) {}, (Before) {})",
                after, before);
        return doBetweenOffsetTime(after, EXCLUSIVE, NONE, before, EXCLUSIVE, NONE);
    }

    public static <T> Matcher<T> betweenOffsetTime(final OffsetTime after,
                                                   final CompareType afterCompareType,
                                                   final OffsetTime before,
                                                   final CompareType beforeCompareType) {
        LOGGER.debug("IsBetweenTime#betweenOffsetTime((After) {}, (CompareType) {}, (Before) {}, (CompareType) {})",
                after, afterCompareType, before, beforeCompareType);
        return doBetweenOffsetTime(after, afterCompareType, NONE, before, beforeCompareType, NONE);
    }

    public static <T> Matcher<T> betweenOffsetTime(final OffsetTime after,
                                                   final CompareType afterCompareType,
                                                   final RoundingType afterRoundingType,
                                                   final OffsetTime before,
                                                   final CompareType beforeCompareType,
                                                   final RoundingType beforeRoundingType) {
        LOGGER.debug("IsBetweenTime#betweenOffsetTime((After) {}, (CompareType) {}, (RoundingType) {}, (Before) {}, (CompareType) {}, (RoundingType) {})",
                after, afterCompareType, afterRoundingType, before, beforeCompareType, beforeRoundingType);
        return doBetweenOffsetTime(after, afterCompareType, afterRoundingType, before, beforeCompareType, beforeRoundingType);
    }

    public static <T> Matcher<T> betweenOffsetTime(final OffsetTime after,
                                                   final RoundingType afterRoundingType,
                                                   final OffsetTime before,
                                                   final RoundingType beforeRoundingType) {
        LOGGER.debug("IsBetweenTime#betweenOffsetTime((After) {}, (RoundingType) {}, (Before) {}, (RoundingType) {})",
                after, afterRoundingType, before, beforeRoundingType);
        return doBetweenOffsetTime(after, EXCLUSIVE, afterRoundingType, before, EXCLUSIVE, beforeRoundingType);
    }

    private static <T> Matcher<T> doBetweenLocalTime(final LocalTime after,
                                                     final CompareType afterCompareType,
                                                     final RoundingType afterRoundingType,
                                                     final LocalTime before,
                                                     final CompareType beforeCompareType,
                                                     final RoundingType beforeRoundingType) {

        final CombinableMatcher betweenLocalTime = both(
                new IsAfterLocalTime(after, afterCompareType, afterRoundingType)
        ).and(
                new IsBeforeLocalTime(before, beforeCompareType, beforeRoundingType)
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

    private static <T> Matcher<T> doBetweenOffsetTime(final OffsetTime after,
                                                      final CompareType afterCompareType,
                                                      final RoundingType afterRoundingType,
                                                      final OffsetTime before,
                                                      final CompareType beforeCompareType,
                                                      final RoundingType beforeRoundingType) {

        final CombinableMatcher betweenOffsetTime = both(
                new IsAfterOffsetTime(after, afterCompareType, afterRoundingType)
        ).and(
                new IsBeforeOffsetTime(before, beforeCompareType, beforeRoundingType)
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

        return betweenOffsetTime;
    }

    IsBetweenTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
