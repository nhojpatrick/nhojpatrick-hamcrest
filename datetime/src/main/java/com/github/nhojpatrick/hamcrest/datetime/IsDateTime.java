package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.internal.equals.IsLocalDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.equals.IsOffsetDateTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.equals.IsZonedDateTime;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import static org.hamcrest.core.IsInstanceOf.instanceOf;

public final class IsDateTime {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsDateTime.class);

    public static <T> Matcher<T> localDateTime() {
        LOGGER.debug("IsDateTime#localDateTime()");
        return instanceOf(LocalDateTime.class);
    }

    @SuppressFBWarnings(value = "OCP_OVERLY_CONCRETE_PARAMETER", justification = "Accepted will look at changing")
    public static <T> Matcher<T> localDateTime(final Matcher<T> matcher) {
        LOGGER.debug("IsDateTime#localDateTime((Matcher<T>) {})", matcher);
        return new IsLocalDateTime(matcher);
    }

    public static <T> Matcher<T> offsetDateTime() {
        LOGGER.debug("IsDateTime#offsetDateTime()");
        return instanceOf(OffsetDateTime.class);
    }

    @SuppressFBWarnings(value = "OCP_OVERLY_CONCRETE_PARAMETER", justification = "Accepted will look at changing")
    public static <T> Matcher<T> offsetDateTime(final Matcher<T> matcher) {
        LOGGER.debug("IsDateTime#offsetDateTime((Matcher<T>) {})", matcher);
        return new IsOffsetDateTime(matcher);
    }

    public static <T> Matcher<T> zonedDateTime() {
        LOGGER.debug("IsDateTime#zonedDateTime()");
        return instanceOf(ZonedDateTime.class);
    }

    @SuppressFBWarnings(value = "OCP_OVERLY_CONCRETE_PARAMETER", justification = "Accepted will look at changing")
    public static <T> Matcher<T> zonedDateTime(final Matcher<T> matcher) {
        LOGGER.debug("IsDateTime#zonedDateTime((Matcher<T>) {})", matcher);
        return new IsZonedDateTime(matcher);
    }

    @SuppressFBWarnings(value = {"CT_CONSTRUCTOR_THROW"},
            justification = "Accepted")
    public IsDateTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
