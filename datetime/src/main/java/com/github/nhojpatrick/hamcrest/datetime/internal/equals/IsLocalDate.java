package com.github.nhojpatrick.hamcrest.datetime.internal.equals;

import org.hamcrest.Matcher;

import java.time.LocalDate;

public class IsLocalDate<T extends LocalDate>
        extends AbstractIsDateTime<T> {

    public IsLocalDate(final Matcher<T> matcher) {
        super(matcher, LocalDate.class.getName());
    }

    @Override
    protected boolean matchesSafely(final T item) {

        final boolean matchesSafely = this.matcher.matches(item);
        return matchesSafely;
    }

}
