package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.internal.equals.IsLocalTime;
import com.github.nhojpatrick.hamcrest.datetime.internal.equals.IsOffsetTime;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.OffsetTime;

import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class IsTime {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsTime.class);

    public static <T> Matcher<T> localTime() {
        LOGGER.debug("IsTime#localTime()");
        return instanceOf(LocalTime.class);
    }

    public static <T> Matcher<T> localTime(final Matcher<T> matcher) {
        LOGGER.debug("IsTime#localTime((Matcher<T>) {})", matcher);
        return new IsLocalTime(matcher);
    }

    public static <T> Matcher<T> offsetTime() {
        LOGGER.debug("IsTime#offsetTime()");
        return instanceOf(OffsetTime.class);
    }

    public static <T> Matcher<T> offsetTime(final Matcher<T> matcher) {
        LOGGER.debug("IsTime#offsetTime((Matcher<T>) {})", matcher);
        return new IsOffsetTime(matcher);
    }

}
