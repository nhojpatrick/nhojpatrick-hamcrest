package com.github.nhojpatrick.hamcrest.datetime.internal.before;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Objects;

public abstract class AbstractIsBefore<T>
        extends TypeSafeMatcher<T> {

    protected final T before;
    protected final CompareType compareType;

    protected AbstractIsBefore(final T before, CompareType compareType) {

        if (Objects.isNull(before)) {
            throw new IllegalArgumentException("Supplied Before must not be null");
        }

        this.before = before;
        this.compareType = compareType;
    }

    @Override
    public void describeTo(final Description description) {

        description
                .appendText("before ")
                .appendValue(this.before)
        ;
    }

}
