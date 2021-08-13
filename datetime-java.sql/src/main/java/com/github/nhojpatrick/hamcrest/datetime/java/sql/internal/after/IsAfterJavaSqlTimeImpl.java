package com.github.nhojpatrick.hamcrest.datetime.java.sql.internal.after;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;

import static java.util.Objects.isNull;

public class IsAfterJavaSqlTimeImpl<T extends Time>
        extends TypeSafeMatcher<T> {

    public static final String SUPPLIED_COMPARE_TYPE_MUST_NOT_BE_NULL = "Supplied After CompareType must not be null";
    public static final String SUPPLIED_EXPECTED_MUST_NOT_BE_NULL = "Supplied After must not be null";

    private static final Logger LOGGER = LoggerFactory.getLogger(IsAfterJavaSqlTimeImpl.class);

    protected final T after;
    protected final CompareType compareType;

    @SuppressFBWarnings(value = "EI_EXPOSE_REP2",
            justification = "Accepted will look at changing 'this.after = after;'")
    public IsAfterJavaSqlTimeImpl(final T after,
                                  final CompareType compareType) {
        LOGGER.debug("IsAfterJavaSqlTimeImpl((After) {}, (CompareType) {})",
                after, compareType);

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

        String textDescription = "";
        switch (this.compareType) {
            case INCLUSIVE:
                textDescription = "after or equal to ";
                break;

            case EXCLUSIVE:
            default:
                textDescription = "after ";
                break;
        }

        description
                .appendText(textDescription)
                .appendValue(this.after);
    }

    @Override
    protected boolean matchesSafely(final T item) {

        boolean matchesSafely = item.after(this.after);

        switch (this.compareType) {
            case INCLUSIVE:
                final boolean notBefore = !item.before(this.after);
                matchesSafely |= notBefore;
                break;
            default:
        }

        return matchesSafely;
    }

}
