package com.github.nhojpatrick.hamcrest.datetime.internal.equals;

import org.hamcrest.Matcher;

import java.time.LocalTime;

public class IsLocalTime<T extends LocalTime>
        extends AbstractIsDateTime<T> {

    public IsLocalTime(final Matcher<T> matcher) {
        super(matcher, LocalTime.class.getName());
    }

    @Override
    protected boolean matchesSafely(final T item) {

        final boolean matchesSafely = this.matcher.matches(item);
        return matchesSafely;
    }

}
