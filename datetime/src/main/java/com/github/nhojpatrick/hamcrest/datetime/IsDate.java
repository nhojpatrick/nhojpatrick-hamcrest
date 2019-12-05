package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.internal.equals.IsLocalDate;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static org.hamcrest.core.IsInstanceOf.instanceOf;

public final class IsDate {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsDate.class);

    public static <T> Matcher<T> localDate() {
        LOGGER.debug("IsDate#localDate()");
        return instanceOf(LocalDate.class);
    }

    public static <T> Matcher<T> localDate(final Matcher<T> matcher) {
        LOGGER.debug("IsDate#localDate((Matcher<T>) {})", matcher);
        return new IsLocalDate(matcher);
    }

    IsDate() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
