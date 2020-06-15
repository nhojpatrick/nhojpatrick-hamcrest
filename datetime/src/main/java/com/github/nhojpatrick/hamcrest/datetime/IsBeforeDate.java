package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeLocalDate;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;

public final class IsBeforeDate {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsBeforeDate.class);

    public static <T> Matcher<T> beforeLocalDate(final LocalDate before) {
        LOGGER.debug("IsBeforeDate#beforeLocalDate((Before) {})", before);
        return doBeforeLocalDate(before, EXCLUSIVE);
    }

    public static <T> Matcher<T> beforeLocalDate(final LocalDate before,
                                                 final CompareType compareType) {
        LOGGER.debug("IsBeforeDate#beforeLocalDate((Before) {}, (CompareType) {})", before, compareType);
        return doBeforeLocalDate(before, compareType);
    }

    @SuppressFBWarnings(value = "OCP_OVERLY_CONCRETE_PARAMETER", justification = "Accepted will look at changing")
    private static <T> Matcher<T> doBeforeLocalDate(final LocalDate before,
                                                    final CompareType compareType) {
        return new IsBeforeLocalDate(before, compareType);
    }

    public IsBeforeDate() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
