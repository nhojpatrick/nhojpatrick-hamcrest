package com.github.nhojpatrick.hamcrest.datetime.internal.equals;

import org.hamcrest.Matcher;

import java.time.ZonedDateTime;

public class IsZonedDateTime<T extends ZonedDateTime>
        extends AbstractIsDateTime<T> {

    public IsZonedDateTime(final Matcher<T> matcher) {
        super(matcher, ZonedDateTime.class.getName());
    }

    @Override
    protected boolean matchesSafely(final T item) {

        final boolean matchesSafely = this.matcher.matches(item);
        return matchesSafely;
    }

}
