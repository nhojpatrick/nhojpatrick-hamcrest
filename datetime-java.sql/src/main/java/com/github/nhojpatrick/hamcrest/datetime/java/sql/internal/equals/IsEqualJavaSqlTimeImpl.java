package com.github.nhojpatrick.hamcrest.datetime.java.sql.internal.equals;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;

import static java.util.Objects.isNull;

public class IsEqualJavaSqlTimeImpl<T extends Time>
        extends TypeSafeMatcher<T> {

    public static final String SUPPLIED_MATCHER_MUST_NOT_BE_NULL = "Supplied Matcher must not be null";

    private static final Logger LOGGER = LoggerFactory.getLogger(IsEqualJavaSqlTimeImpl.class);

    protected final Matcher<T> matcher;
    private final String type;

        @SuppressFBWarnings(value = {"CT_CONSTRUCTOR_THROW"},
                justification = "Accepted")
    public IsEqualJavaSqlTimeImpl(final Matcher<T> matcher) {
        LOGGER.debug("IsEqualJavaSqlTimeImpl(Matcher<T> {})", matcher);

        if (isNull(matcher)) {
            throw new IllegalArgumentException(SUPPLIED_MATCHER_MUST_NOT_BE_NULL);
        }

        this.matcher = matcher;
        this.type = Time.class.getName();
    }

    @Override
    public void describeTo(final Description description) {
        description
                .appendText(this.type)
                .appendText(" ")
                .appendDescriptionOf(this.matcher);
    }

    @Override
    protected boolean matchesSafely(final T item) {
        LOGGER.debug("IsEqualJavaSqlTimeImpl.matchesSafely(<T> {})", item);

        final boolean matchesSafely = this.matcher.matches(item);
        LOGGER.debug("IsEqualJavaSqlTimeImpl.matchesSafely(<T> {}) return {}", item, matchesSafely);
        return matchesSafely;
    }

}
