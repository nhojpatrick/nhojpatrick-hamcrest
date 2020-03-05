package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.internal.equals.IsLocalTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.equals.IsOffsetTime;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.OffsetTime;

import static org.hamcrest.core.IsInstanceOf.instanceOf;

public final class IsTime {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsTime.class);

    public static <T> Matcher<T> localTime() {
        LOGGER.debug("IsTime#localTime()");
        return instanceOf(LocalTime.class);
    }

    @SuppressFBWarnings(value = "OCP_OVERLY_CONCRETE_PARAMETER", justification = "Accepted will look at changing")
    public static <T> Matcher<T> localTime(final Matcher<T> matcher) {
        LOGGER.debug("IsTime#localTime((Matcher<T>) {})", matcher);
        return new IsLocalTime(matcher);
    }

    public static <T> Matcher<T> offsetTime() {
        LOGGER.debug("IsTime#offsetTime()");
        return instanceOf(OffsetTime.class);
    }

    @SuppressFBWarnings(value = "OCP_OVERLY_CONCRETE_PARAMETER", justification = "Accepted will look at changing")
    public static <T> Matcher<T> offsetTime(final Matcher<T> matcher) {
        LOGGER.debug("IsTime#offsetTime((Matcher<T>) {})", matcher);
        return new IsOffsetTime(matcher);
    }

    IsTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
