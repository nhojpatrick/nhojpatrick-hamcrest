package com.github.nhojpatrick.hamcrest.datetime.internal.equals;

import org.hamcrest.Matcher;

import java.time.OffsetDateTime;

public class IsOffsetDateTime<T extends OffsetDateTime>
        extends AbstractIsDateTime<T> {

    public IsOffsetDateTime(final Matcher<T> matcher) {
        super(matcher, OffsetDateTime.class.getName());
    }

    @Override
    protected boolean matchesSafely(final T item) {

        final boolean matchesSafely = this.matcher.matches(item);
        return matchesSafely;
    }

}
