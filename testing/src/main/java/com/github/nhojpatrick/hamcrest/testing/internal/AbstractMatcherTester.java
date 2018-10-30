package com.github.nhojpatrick.hamcrest.testing.internal;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

public abstract class AbstractMatcherTester<S, T> {

    public void assertValid(final S actual,
                            final Matcher<T> matcher) {
        matcherTester(actual, matcher, true, "");
    }

    public void assertFails(final S actual,
                            final Matcher<T> matcher,
                            final String expectedDescription) {
        matcherTester(actual, matcher, false, expectedDescription);
    }

    private void matcherTester(final S actual,
                               final Matcher<T> matcher,
                               final boolean expectedValid,
                               final String expectedDescription) {

        if (Objects.isNull(matcher)) {
            throw new IllegalArgumentException("Null Matcher");
        }

        final Description description = new StringDescription();

        final boolean matches = matcher.matches(actual);
        if (!matches) {
            description.appendText("\nExpected: ")
                    .appendDescriptionOf(matcher)
                    .appendText("\n      but: ");
            matcher.describeMismatch(actual, description);
        }

        assertAll("Unexpected Matcher",
                () -> assertThat("Unexpected matches", matches, is(equalTo(expectedValid))),
                () -> assertThat("Unexpected description", description.toString(), is(equalTo(expectedDescription)))
        );
    }

}
