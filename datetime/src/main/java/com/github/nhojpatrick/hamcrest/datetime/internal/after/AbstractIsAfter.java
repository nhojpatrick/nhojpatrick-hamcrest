package com.github.nhojpatrick.hamcrest.datetime.internal.after;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import static java.util.Objects.isNull;

public abstract class AbstractIsAfter<T>
        extends TypeSafeMatcher<T> {

    protected final T after;
    protected final CompareType compareType;

    protected AbstractIsAfter(final T after,
                              final CompareType compareType) {

        if (isNull(after)) {
            throw new IllegalArgumentException("Supplied After must not be null");
        }
        if (isNull(compareType)) {
            throw new IllegalArgumentException("Supplied CompareType must not be null");
        }

        this.after = after;
        this.compareType = compareType;
    }

    @Override
    public void describeTo(final Description description) {

        switch (this.compareType) {
            case INCLUSIVE:
                description
                        .appendText("after or equal to ")
                        .appendValue(this.after);
                break;

            case EXCLUSIVE:
            default:
                description
                        .appendText("after ")
                        .appendValue(this.after);
                break;
        }
    }

}
