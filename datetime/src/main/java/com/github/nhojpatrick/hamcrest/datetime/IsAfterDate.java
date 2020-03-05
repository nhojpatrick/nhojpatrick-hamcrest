package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterLocalDate;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;

public final class IsAfterDate {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsAfterDate.class);

    public static <T> Matcher<T> afterLocalDate(final LocalDate expected) {
        LOGGER.debug("IsAfterDate#afterLocalDate((After) {})", expected);
        return doAfterLocalDate(expected, EXCLUSIVE);
    }

    public static <T> Matcher<T> afterLocalDate(final LocalDate expected,
                                                final CompareType compareType) {
        LOGGER.debug("IsAfterDate#afterLocalDate((After) {}, (CompareType) {})", expected, compareType);
        return doAfterLocalDate(expected, compareType);
    }

    @SuppressFBWarnings(value = "OCP_OVERLY_CONCRETE_PARAMETER", justification = "Accepted will look at changing")
    private static <T> Matcher<T> doAfterLocalDate(final LocalDate expected,
                                                   final CompareType compareType) {
        return new IsAfterLocalDate(expected, compareType);
    }

    IsAfterDate() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
