package com.github.nhojpatrick.hamcrest.datetime.internal.equals;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static java.util.Objects.isNull;

public abstract class AbstractIsDateTime<T>
        extends TypeSafeMatcher<T> {

    protected final Matcher<T> matcher;
    private final String type;

    protected AbstractIsDateTime(final Matcher<T> matcher, final String type) {

        if (isNull(matcher)) {
            throw new IllegalArgumentException("Supplied Matcher must not be null");
        }

        this.matcher = matcher;
        this.type = type;
    }

    @Override
    public void describeTo(final Description description) {
        description
                .appendText(type)
                .appendText(" ")
                .appendDescriptionOf(this.matcher);
    }

}
