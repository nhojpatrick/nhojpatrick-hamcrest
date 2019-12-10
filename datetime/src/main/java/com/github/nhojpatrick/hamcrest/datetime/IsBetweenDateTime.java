package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterLocalDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterOffsetDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterZonedDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeLocalDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeOffsetDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeZonedDateTime;
import org.hamcrest.Matcher;
import org.hamcrest.core.CombinableMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;
import static org.hamcrest.core.CombinableMatcher.both;

public final class IsBetweenDateTime {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsBetweenDateTime.class);

    public static <T> Matcher<T> betweenLocalDateTime(final ChronoLocalDateTime after,
                                                      final ChronoLocalDateTime before) {
        LOGGER.debug("IsBetweenDateTime#betweenLocalDateTime((After) {}, (Before) {})", after, before);
        return doBetweenLocalDateTime(after, EXCLUSIVE, before, EXCLUSIVE);
    }

    public static <T> Matcher<T> betweenLocalDateTime(final ChronoLocalDateTime after,
                                                      final CompareType afterCompareType,
                                                      final ChronoLocalDateTime before,
                                                      final CompareType beforeCompareType) {
        LOGGER.debug(
                "IsBetweenDateTime#betweenLocalDateTime((After) {}, (CompareType) {}, (Before) {}, (CompareType) {})",
                after, afterCompareType, before, beforeCompareType);
        return doBetweenLocalDateTime(after, afterCompareType, before, beforeCompareType);
    }

    public static <T> Matcher<T> betweenOffsetDateTime(final OffsetDateTime after, final OffsetDateTime before) {
        LOGGER.debug("IsBetweenDateTime#betweenOffsetDateTime((After) {}, (Before) {})", after, before);
        return doBetweenOffsetDateTime(after, EXCLUSIVE, before, EXCLUSIVE);
    }

    public static <T> Matcher<T> betweenOffsetDateTime(final OffsetDateTime after,
                                                       final CompareType afterCompareType,
                                                       final OffsetDateTime before,
                                                       final CompareType beforeCompareType) {
        LOGGER.debug(
                "IsBetweenDateTime#betweenOffsetDateTime((After) {}, (CompareType) {}, (Before) {}, (CompareType) {})",
                after, afterCompareType, before, beforeCompareType);
        return doBetweenOffsetDateTime(after, afterCompareType, before, beforeCompareType);
    }

    public static <T> Matcher<T> betweenZonedDateTime(final ChronoZonedDateTime after,
                                                      final ChronoZonedDateTime before) {
        LOGGER.debug("IsBetweenDateTime#betweenZonedDateTime((After) {}, (Before) {})", after, before);
        return doBetweenZonedDateTime(after, EXCLUSIVE, before, EXCLUSIVE);
    }

    public static <T> Matcher<T> betweenZonedDateTime(final ChronoZonedDateTime after,
                                                      final CompareType afterCompareType,
                                                      final ChronoZonedDateTime before,
                                                      final CompareType beforeCompareType) {
        LOGGER.debug(
                "IsBetweenDateTime#betweenZonedDateTime((After) {}, (CompareType) {}, (Before) {}, (CompareType) {})",
                after, afterCompareType, before, beforeCompareType);
        return doBetweenZonedDateTime(after, afterCompareType, before, beforeCompareType);
    }

    private static <T> Matcher<T> doBetweenLocalDateTime(final ChronoLocalDateTime after,
                                                         final CompareType afterCompareType,
                                                         final ChronoLocalDateTime before,
                                                         final CompareType beforeCompareType) {

        final CombinableMatcher betweenLocalDateTime = both(
                new IsAfterLocalDateTime(after, afterCompareType)
        ).and(
                new IsBeforeLocalDateTime(before, beforeCompareType)
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

        return betweenLocalDateTime;
    }

    private static <T> Matcher<T> doBetweenOffsetDateTime(final OffsetDateTime after,
                                                          final CompareType afterCompareType,
                                                          final OffsetDateTime before,
                                                          final CompareType beforeCompareType) {

        final CombinableMatcher betweenOffsetDateTime = both(
                new IsAfterOffsetDateTime(after, afterCompareType)
        ).and(
                new IsBeforeOffsetDateTime(before, beforeCompareType)
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

        return betweenOffsetDateTime;
    }

    private static <T> Matcher<T> doBetweenZonedDateTime(final ChronoZonedDateTime after,
                                                         final CompareType afterCompareType,
                                                         final ChronoZonedDateTime before,
                                                         final CompareType beforeCompareType) {

        final CombinableMatcher betweenZonedDateTime = both(
                new IsAfterZonedDateTime(after, afterCompareType)
        ).and(
                new IsBeforeZonedDateTime(before, beforeCompareType)
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

        return betweenZonedDateTime;
    }

    IsBetweenDateTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
