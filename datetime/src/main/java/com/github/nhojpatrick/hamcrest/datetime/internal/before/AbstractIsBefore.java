package com.github.nhojpatrick.hamcrest.datetime.internal.before;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.flags.RoundingType;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;

import static java.util.Objects.isNull;

public abstract class AbstractIsBefore<T extends Temporal>
        extends TypeSafeMatcher<T> {

    public static final String SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL = "Supplied Before CompareType must not be null";
    public static final String SUPPLIED_EXPECTED_MUST_NOT_BE_NULL = "Supplied Before must not be null";
    public static final String SUPPLIED_ROUNDING_TYPE_MUST_NOT_BE_NULL = "Supplied RoundingType must not be null";

    protected final T before;
    protected final CompareType compareType;

    @SuppressFBWarnings(value = {"CT_CONSTRUCTOR_THROW"},
            justification = "Accepted")
    protected AbstractIsBefore(final T before,
                               final CompareType compareType,
                               final RoundingType roundingType) {

        if (isNull(before)) {
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
                this.before = (T) before.with(ChronoField.NANO_OF_SECOND, 0);
                break;

            case UP:
                this.before = (T) before.with(ChronoField.NANO_OF_SECOND, 999_999_999);
                break;

            case NONE:
            default:
                this.before = before;
        }
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
