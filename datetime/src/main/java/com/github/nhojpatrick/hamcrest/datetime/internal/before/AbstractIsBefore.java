package com.github.nhojpatrick.hamcrest.datetime.internal.before;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import static java.util.Objects.isNull;

public abstract class AbstractIsBefore<T>
        extends TypeSafeMatcher<T> {

    public static final String SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL = "Supplied Before CompareType must not be null";
    public static final String SUPPLIED_EXPECTED_MUST_NOT_BE_NULL = "Supplied Before must not be null";

    protected final T before;
    protected final CompareType compareType;

    protected AbstractIsBefore(final T before,
                               final CompareType compareType) {

        if (isNull(before)) {
            throw new IllegalArgumentException(SUPPLIED_EXPECTED_MUST_NOT_BE_NULL);
        }
        if (isNull(compareType)) {
            throw new IllegalArgumentException(SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL);
        }

        this.before = before;
        this.compareType = compareType;
    }

    @Override
    public void describeTo(final Description description) {

        switch (this.compareType) {
            case INCLUSIVE:
                description
                        .appendText("before or equal to ")
                        .appendValue(this.before);
                break;

            case EXCLUSIVE:
            default:
                description
                        .appendText("before ")
                        .appendValue(this.before);
                break;
        }
    }

}
