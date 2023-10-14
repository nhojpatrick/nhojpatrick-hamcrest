package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterLocalDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterOffsetDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterZonedDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeLocalDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeOffsetDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeZonedDateTime;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.hamcrest.Matcher;
import org.hamcrest.core.CombinableMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;
import static com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType.NONE;
import static org.hamcrest.core.CombinableMatcher.both;

public final class IsBetweenDateTime {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsBetweenDateTime.class);

    public static <T> Matcher<T> betweenLocalDateTime(final ChronoLocalDateTime after,
                                                      final ChronoLocalDateTime before) {
        LOGGER.debug("IsBetweenTime#betweenLocalDateTime((After) {}, (Before) {})", after, before);
        return doBetweenLocalDateTime(after, EXCLUSIVE, NONE, before, EXCLUSIVE, NONE);
    }

    public static <T> Matcher<T> betweenLocalDateTime(final ChronoLocalDateTime after,
                                                      final CompareType afterCompareType,
                                                      final ChronoLocalDateTime before,
                                                      final CompareType beforeCompareType) {
        LOGGER.debug("IsBetweenTime#betweenLocalDateTime((After) {}, (CompareType) {}, (Before) {}, (CompareType) {})",
                after, afterCompareType, before, beforeCompareType);
        return doBetweenLocalDateTime(after, afterCompareType, NONE, before, beforeCompareType, NONE);
    }

    public static <T> Matcher<T> betweenLocalDateTime(final ChronoLocalDateTime after,
                                                      final CompareType afterCompareType,
                                                      final RoundingType afterRoundingType,
                                                      final ChronoLocalDateTime before,
                                                      final CompareType beforeCompareType,
                                                      final RoundingType beforeRoundingType) {
        LOGGER.debug("IsBetweenTime#betweenLocalDateTime((After) {}, (CompareType) {}, (RoundingType) {}, (Before) {}, (CompareType) {}, (RoundingType) {})",
                after, afterCompareType, afterRoundingType, before, beforeCompareType, beforeRoundingType);
        return doBetweenLocalDateTime(after, afterCompareType, afterRoundingType,
                before, beforeCompareType, beforeRoundingType);
    }

    public static <T> Matcher<T> betweenLocalDateTime(final ChronoLocalDateTime after,
                                                      final RoundingType afterRoundingType,
                                                      final ChronoLocalDateTime before,
                                                      final RoundingType beforeRoundingType) {
        LOGGER.debug("IsBetweenTime#betweenLocalDateTime((After) {}, (RoundingType) {}, (Before) {}, (RoundingType) {})",
                after, afterRoundingType, before, beforeRoundingType);
        return doBetweenLocalDateTime(after, EXCLUSIVE, afterRoundingType, before, EXCLUSIVE, beforeRoundingType);
    }

    public static <T> Matcher<T> betweenOffsetDateTime(final OffsetDateTime after,
                                                       final OffsetDateTime before) {
        LOGGER.debug("IsBetweenTime#betweenOffsetDateTime((After) {}, (Before) {})", after, before);
        return doBetweenOffsetDateTime(after, EXCLUSIVE, NONE, before, EXCLUSIVE, NONE);
    }

    public static <T> Matcher<T> betweenOffsetDateTime(final OffsetDateTime after,
                                                       final CompareType afterCompareType,
                                                       final OffsetDateTime before,
                                                       final CompareType beforeCompareType) {
        LOGGER.debug("IsBetweenTime#betweenOffsetDateTime((After) {}, (CompareType) {}, (Before) {}, (CompareType) {})",
                after, afterCompareType, before, beforeCompareType);
        return doBetweenOffsetDateTime(after, afterCompareType, NONE, before, beforeCompareType, NONE);
    }

    public static <T> Matcher<T> betweenOffsetDateTime(final OffsetDateTime after,
                                                       final CompareType afterCompareType,
                                                       final RoundingType afterRoundingType,
                                                       final OffsetDateTime before,
                                                       final CompareType beforeCompareType,
                                                       final RoundingType beforeRoundingType) {
        LOGGER.debug("IsBetweenTime#betweenOffsetDateTime((After) {}, (CompareType) {}, (RoundingType) {}, (Before) {}, (CompareType) {}, (RoundingType) {})",
                after, afterCompareType, afterRoundingType, before, beforeCompareType, beforeRoundingType);
        return doBetweenOffsetDateTime(after, afterCompareType, afterRoundingType,
                before, beforeCompareType, beforeRoundingType);
    }

    public static <T> Matcher<T> betweenOffsetDateTime(final OffsetDateTime after,
                                                       final RoundingType afterRoundingType,
                                                       final OffsetDateTime before,
                                                       final RoundingType beforeRoundingType) {
        LOGGER.debug("IsBetweenTime#betweenOffsetDateTime((After) {}, (RoundingType) {}, (Before) {}, (RoundingType) {})",
                after, afterRoundingType, before, beforeRoundingType);
        return doBetweenOffsetDateTime(after, EXCLUSIVE, afterRoundingType, before, EXCLUSIVE, beforeRoundingType);
    }

    public static <T> Matcher<T> betweenZonedDateTime(final ChronoZonedDateTime after,
                                                      final ChronoZonedDateTime before) {
        LOGGER.debug("IsBetweenTime#betweenZonedDateTime((After) {}, (Before) {})", after, before);
        return doBetweenZonedDateTime(after, EXCLUSIVE, NONE, before, EXCLUSIVE, NONE);
    }

    public static <T> Matcher<T> betweenZonedDateTime(final ChronoZonedDateTime after,
                                                      final CompareType afterCompareType,
                                                      final ChronoZonedDateTime before,
                                                      final CompareType beforeCompareType) {
        LOGGER.debug("IsBetweenTime#betweenZonedDateTime((After) {}, (CompareType) {}, (Before) {}, (CompareType) {})",
                after, afterCompareType, before, beforeCompareType);
        return doBetweenZonedDateTime(after, afterCompareType, NONE, before, beforeCompareType, NONE);
    }

    public static <T> Matcher<T> betweenZonedDateTime(final ChronoZonedDateTime after,
                                                      final CompareType afterCompareType,
                                                      final RoundingType afterRoundingType,
                                                      final ChronoZonedDateTime before,
                                                      final CompareType beforeCompareType,
                                                      final RoundingType beforeRoundingType) {
        LOGGER.debug("IsBetweenTime#betweenZonedDateTime((After) {}, (CompareType) {}, (RoundingType) {}, (Before) {}, (CompareType) {}, (RoundingType) {})",
                after, afterCompareType, afterRoundingType, before, beforeCompareType, beforeRoundingType);
        return doBetweenZonedDateTime(after, afterCompareType, afterRoundingType,
                before, beforeCompareType, beforeRoundingType);
    }

    public static <T> Matcher<T> betweenZonedDateTime(final ChronoZonedDateTime after,
                                                      final RoundingType afterRoundingType,
                                                      final ChronoZonedDateTime before,
                                                      final RoundingType beforeRoundingType) {
        LOGGER.debug("IsBetweenTime#betweenZonedDateTime((After) {}, (RoundingType) {}, (Before) {}, (RoundingType) {})",
                after, afterRoundingType, before, beforeRoundingType);
        return doBetweenZonedDateTime(after, EXCLUSIVE, afterRoundingType, before, EXCLUSIVE, beforeRoundingType);
    }

    private static <T> Matcher<T> doBetweenLocalDateTime(final ChronoLocalDateTime after,
                                                         final CompareType afterCompareType,
                                                         final RoundingType afterRoundingType,
                                                         final ChronoLocalDateTime before,
                                                         final CompareType beforeCompareType,
                                                         final RoundingType beforeRoundingType) {

        final CombinableMatcher betweenLocalDateTime = both(
                new IsAfterLocalDateTime(after, afterCompareType, afterRoundingType)
        ).and(
                new IsBeforeLocalDateTime(before, beforeCompareType, beforeRoundingType)
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

        return betweenLocalDateTime;
    }

    private static <T> Matcher<T> doBetweenOffsetDateTime(final OffsetDateTime after,
                                                          final CompareType afterCompareType,
                                                          final RoundingType afterRoundingType,
                                                          final OffsetDateTime before,
                                                          final CompareType beforeCompareType,
                                                          final RoundingType beforeRoundingType) {

        final CombinableMatcher betweenOffsetDateTime = both(
                new IsAfterOffsetDateTime(after, afterCompareType, afterRoundingType)
        ).and(
                new IsBeforeOffsetDateTime(before, beforeCompareType, beforeRoundingType)
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

        return betweenOffsetDateTime;
    }

    private static <T> Matcher<T> doBetweenZonedDateTime(final ChronoZonedDateTime after,
                                                         final CompareType afterCompareType,
                                                         final RoundingType afterRoundingType,
                                                         final ChronoZonedDateTime before,
                                                         final CompareType beforeCompareType,
                                                         final RoundingType beforeRoundingType) {

        final CombinableMatcher betweenZonedDateTime = both(
                new IsAfterZonedDateTime(after, afterCompareType, afterRoundingType)
        ).and(
                new IsBeforeZonedDateTime(before, beforeCompareType, beforeRoundingType)
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

        return betweenZonedDateTime;
    }

    @SuppressFBWarnings(value = {"CT_CONSTRUCTOR_THROW"},
            justification = "Accepted")
    public IsBetweenDateTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
