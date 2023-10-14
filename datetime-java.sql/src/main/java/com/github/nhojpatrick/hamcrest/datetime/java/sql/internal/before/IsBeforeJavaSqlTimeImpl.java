package com.github.nhojpatrick.hamcrest.datetime.java.sql.internal.before;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;

import static java.util.Objects.isNull;

public class IsBeforeJavaSqlTimeImpl<T extends Time>
        extends TypeSafeMatcher<T> {

    public static final String SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL = "Supplied Before CompareType must not be null";
    public static final String SUPPLIED_EXPECTED_MUST_NOT_BE_NULL = "Supplied Before must not be null";

    private static final Logger LOGGER = LoggerFactory.getLogger(IsBeforeJavaSqlTimeImpl.class);

    protected final T before;
    protected final CompareType compareType;

    @SuppressFBWarnings(value = {"CT_CONSTRUCTOR_THROW", "EI_EXPOSE_REP2"},
            justification = "Accepted will look at changing 'this.before = before;'")
    public IsBeforeJavaSqlTimeImpl(final T before,
                                   final CompareType compareType) {
        LOGGER.debug("IsBeforeJavaSqlTimeImpl((Before) {}, (CompareType) {})",
                before, compareType);

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

        String textDescription = "";
        switch (this.compareType) {
            case INCLUSIVE:
                textDescription = "before or equal to ";
                break;

            case EXCLUSIVE:
            default:
                textDescription = "before ";
                break;
        }

        description
                .appendText(textDescription)
                .appendValue(this.before);
    }

    @Override
    protected boolean matchesSafely(final T item) {

        boolean matchesSafely = item.before(this.before);

        switch (this.compareType) {
            case INCLUSIVE:
                final boolean notAfter = !item.after(this.before);
                matchesSafely |= notAfter;
                break;
            default:
        }

        return matchesSafely;
    }

}
