package com.github.nhojpatrick.hamcrest.lang;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Objects;

public class IsHashCode<T extends Object>
        extends TypeSafeMatcher<T> {

    public static <T> Matcher<T> hashCodeGenerated(final int expected) {
        return new IsHashCode(expected);
    }

    private Integer actual;
    private final Integer expected;

    private IsHashCode(final int expected) {
        this.expected = expected;
    }

    @Override
    public void describeMismatchSafely(final T item, final Description description) {

        description.appendText("was ");
        description.appendValue(this.actual);
    }

    @Override
    public void describeTo(final Description description) {

        description.appendText("Object hashCode ");
        description.appendValue(this.expected);
    }

    @Override
    protected boolean matchesSafely(final T item) {

        if (Objects.nonNull(item)) {
            this.actual = item.hashCode();
        }

        return this.expected.equals(this.actual);
    }

}
