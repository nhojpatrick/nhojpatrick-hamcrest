package com.github.nhojpatrick.hamcrest.lang;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class IsToString<T extends Object>
        extends TypeSafeMatcher<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsToString.class);

    public static <T> Matcher<T> toStringGenerated(final String expected) {
        LOGGER.debug("IsToString#toStringGenerated((String) {})", expected);
        return new IsToString(expected);
    }

    private String actual;
    private final String expected;

    private IsToString(final String expected) {
        this.expected = expected;
    }

    @Override
    public void describeMismatchSafely(final T item, final Description description) {

        description.appendText("was ");
        description.appendValue(this.actual);
    }

    @Override
    public void describeTo(final Description description) {

        description.appendText("Object toString ");
        description.appendValue(this.expected);
    }

    @Override
    protected boolean matchesSafely(final T item) {

        if (Objects.nonNull(item)) {
            this.actual = item.toString();
        }

        return this.expected.equals(this.actual);
    }

}
