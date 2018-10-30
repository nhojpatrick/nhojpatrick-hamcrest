package com.github.nhojpatrick.hamcrest.datetime.internal.equals;

import org.hamcrest.Matcher;

import java.time.OffsetTime;

public class IsOffsetTime<T extends OffsetTime>
        extends AbstractIsDateTime<T> {

    public IsOffsetTime(final Matcher<T> matcher) {
        super(matcher, OffsetTime.class.getName());
    }

    @Override
    protected boolean matchesSafely(final T item) {

        final boolean matchesSafely = this.matcher.matches(item);
        return matchesSafely;
    }

}
