package com.github.nhojpatrick.hamcrest.datetime.internal.equals;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.hamcrest.Matcher;

import java.time.LocalDateTime;

public class IsLocalDateTime<T extends LocalDateTime>
        extends AbstractIsDateTime<T> {

    public IsLocalDateTime(final Matcher<T> matcher) {
        super(matcher, LocalDateTime.class.getName());
    }

    @Override
    @SuppressFBWarnings(value = "USBR_UNNECESSARY_STORE_BEFORE_RETURN", justification = "Useful for debugging")
    protected boolean matchesSafely(final T item) {

        final boolean matchesSafely = this.matcher.matches(item);
        return matchesSafely;
    }

}
