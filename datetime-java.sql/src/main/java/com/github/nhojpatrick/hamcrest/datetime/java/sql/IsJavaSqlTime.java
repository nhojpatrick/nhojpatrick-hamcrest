package com.github.nhojpatrick.hamcrest.datetime.java.sql;

import com.github.nhojpatrick.hamcrest.datetime.java.sql.internal.equals.IsEqualJavaSqlTimeImpl;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;

import static org.hamcrest.core.IsInstanceOf.instanceOf;

public final class IsJavaSqlTime {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsJavaSqlTime.class);

    public static <T> Matcher<T> javaSqlTime() {
        LOGGER.debug("IsJavaSqlTime#javaSqlTime()");
        return instanceOf(Time.class);
    }

    public static <T extends Time> Matcher<T> javaSqlTime(final Matcher<T> matcher) {
        LOGGER.debug("IsJavaSqlTime#javaSqlTime(Matcher<T> {})", matcher);
        return new IsEqualJavaSqlTimeImpl(matcher);
    }

    IsJavaSqlTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
