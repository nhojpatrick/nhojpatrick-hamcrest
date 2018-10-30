package com.github.nhojpatrick.hamcrest.datetime.internal.equals;

import org.hamcrest.Matcher;

import java.time.LocalDateTime;

public class IsLocalDateTime<T extends LocalDateTime>
        extends AbstractIsDateTime<T> {

    public IsLocalDateTime(final Matcher<T> matcher) {
        super(matcher, LocalDateTime.class.getName());
    }

    @Override
    protected boolean matchesSafely(final T item) {

        final boolean matchesSafely = this.matcher.matches(item);
        return matchesSafely;
    }

}
