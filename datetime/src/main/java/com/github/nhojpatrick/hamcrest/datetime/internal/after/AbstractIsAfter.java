package com.github.nhojpatrick.hamcrest.datetime.internal.after;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import static java.util.Objects.isNull;

public abstract class AbstractIsAfter<T>
        extends TypeSafeMatcher<T> {

    public static final String SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL = "Supplied After CompareType must not be null";
    public static final String SUPPLIED_EXPECTED_MUST_NOT_BE_NULL = "Supplied After must not be null";

    protected final T after;
    protected final CompareType compareType;

    protected AbstractIsAfter(final T after,
                              final CompareType compareType) {

        if (isNull(after)) {
            throw new IllegalArgumentException(SUPPLIED_EXPECTED_MUST_NOT_BE_NULL);
        }
        if (isNull(compareType)) {
            throw new IllegalArgumentException(SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL);
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
