package com.github.nhojpatrick.hamcrest.datetime.internal.after;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Objects;

public abstract class AbstractIsAfter<T>
        extends TypeSafeMatcher<T> {

    protected final T after;
    protected final CompareType compareType;

    protected AbstractIsAfter(final T after, CompareType compareType) {

        if (Objects.isNull(after)) {
            throw new IllegalArgumentException("Supplied After must not be null");
        }

        this.after = after;
        this.compareType = compareType;
    }

    @Override
    public void describeTo(final Description description) {

        description
                .appendText("after ")
                .appendValue(this.after)
        ;
    }

}
