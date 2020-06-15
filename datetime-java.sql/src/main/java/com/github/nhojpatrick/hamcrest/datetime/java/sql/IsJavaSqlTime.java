package com.github.nhojpatrick.hamcrest.datetime.java.sql;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.java.sql.internal.after.IsAfterJavaSqlTimeImpl;
import com.github.nhojpatrick.hamcrest.datetime.java.sql.internal.before.IsBeforeJavaSqlTimeImpl;
import com.github.nhojpatrick.hamcrest.datetime.java.sql.internal.equals.IsEqualJavaSqlTimeImpl;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public final class IsJavaSqlTime {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsJavaSqlTime.class);

    public static <T extends Time> Matcher<T> afterJavaSqlTime(final T after) {
        LOGGER.debug("IsJavaSqlTime#afterJavaSqlTime(<T> {})", after);
        return doAfterJavaSqlTime(after, EXCLUSIVE);
    }

    public static <T extends Time> Matcher<T> afterJavaSqlTime(final T after,
                                                               final CompareType compareType) {
        LOGGER.debug("IsJavaSqlTime#afterJavaSqlTime(<T> {}, (CompareType) {})",
                after, compareType);
        return doAfterJavaSqlTime(after, compareType);
    }

    public static <T extends Time> Matcher<T> beforeJavaSqlTime(final T before) {
        LOGGER.debug("IsJavaSqlTime#beforeJavaSqlTime(<T> {})", before);
        return doBeforeJavaSqlTime(before, EXCLUSIVE);
    }

    public static <T extends Time> Matcher<T> beforeJavaSqlTime(final T before,
                                                                final CompareType compareType) {
        LOGGER.debug("IsJavaSqlTime#beforeJavaSqlTime(<T> {}, (CompareType) {})",
                before, compareType);
        return doBeforeJavaSqlTime(before, compareType);
    }

    public static <T> Matcher<T> javaSqlTime() {
        LOGGER.debug("IsJavaSqlTime#javaSqlTime()");
        return instanceOf(Time.class);
    }

    public static <T extends Time> Matcher<T> javaSqlTime(final Matcher<T> matcher) {
        LOGGER.debug("IsJavaSqlTime#javaSqlTime(Matcher<T> {})", matcher);
        return new IsEqualJavaSqlTimeImpl(matcher);
    }

    private static <T extends Time> Matcher<T> doAfterJavaSqlTime(final T before,
                                                                  final CompareType compareType) {
        LOGGER.debug("IsJavaSqlTime#doAfterJavaSqlTime((After) {}, (CompareType) {})",
                before, compareType);
        return new IsAfterJavaSqlTimeImpl(before, compareType);
    }

    private static <T extends Time> Matcher<T> doBeforeJavaSqlTime(final T before,
                                                                   final CompareType compareType) {
        LOGGER.debug("IsJavaSqlTime#doBeforeJavaSqlTime((Before) {}, (CompareType) {})",
                before, compareType);
        return new IsBeforeJavaSqlTimeImpl(before, compareType);
    }

    public IsJavaSqlTime() {
        throw new AssertionError("Static utility class - cannot be instantiated.");
    }

}
