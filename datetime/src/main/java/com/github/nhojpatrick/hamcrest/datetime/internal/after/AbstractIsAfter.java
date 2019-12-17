package com.github.nhojpatrick.hamcrest.datetime.internal.after;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;

import static java.util.Objects.isNull;

public abstract class AbstractIsAfter<T extends Temporal>
        extends TypeSafeMatcher<T> {

    public static final String SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL = "Supplied After CompareType must not be null";
    public static final String SUPPLIED_EXPECTED_MUST_NOT_BE_NULL = "Supplied After must not be null";
    public static final String SUPPLIED_ROUNDING_TYPE_MUST_NOT_BE_NULL = "Supplied RoundingType must not be null";

    protected final T after;
    protected final CompareType compareType;

    protected AbstractIsAfter(final T after,
                              final CompareType compareType,
                              final RoundingType roundingType) {

        if (isNull(after)) {
            throw new IllegalArgumentException(SUPPLIED_EXPECTED_MUST_NOT_BE_NULL);
        }
        if (isNull(compareType)) {
            throw new IllegalArgumentException(SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL);
        }
        if (isNull(roundingType)) {
            throw new IllegalArgumentException(SUPPLIED_ROUNDING_TYPE_MUST_NOT_BE_NULL);
        }

        switch (roundingType) {
            case DOWN:
                this.after = (T) after.with(ChronoField.NANO_OF_SECOND, 0);
                break;

            case UP:
                this.after = (T) after.with(ChronoField.NANO_OF_SECOND, 999_999_999);
                break;

            case NONE:
            default:
                this.after = after;
        }
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
