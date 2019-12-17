package com.github.nhojpatrick.hamcrest.lang;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.isNull;

public class IsCompareTo<T extends Object>
        extends TypeSafeMatcher<T> {

    public static final String SUPPLIED_COMPARABLE_MUST_NOT_BE_NULL = "Supplied Comparable must not be null";

    private static final Logger LOGGER = LoggerFactory.getLogger(IsCompareTo.class);

    public static <T> Matcher<T> compareToObject(final int expected,
                                                 final Comparable comparable) {
        LOGGER.debug("IsCompareTo#compareToObject((int) {}, (Comparable) {})", expected, comparable);
        return new IsCompareTo(expected, comparable);
    }

    private Integer actualCompareToValue;
    private final Integer expectedCompareToValue;
    private final Comparable compareToObject;

    private IsCompareTo(final int expectedCompareToValue, final Comparable compareToObject) {

        if (isNull(compareToObject)) {
            throw new IllegalArgumentException(SUPPLIED_COMPARABLE_MUST_NOT_BE_NULL);
        }

        this.expectedCompareToValue = expectedCompareToValue;
        this.compareToObject = compareToObject;
    }

    @Override
    public void describeMismatchSafely(final T item, final Description description) {

        description.appendText("was ");
        description.appendValue(this.actualCompareToValue);
    }

    @Override
    public void describeTo(final Description description) {

        description.appendText("Object compareTo(Object) ");
        description.appendValue(this.expectedCompareToValue);
    }

    @Override
    protected boolean matchesSafely(final T item) {

        this.actualCompareToValue = this.compareToObject.compareTo(item);

        return this.expectedCompareToValue.equals(this.actualCompareToValue);
    }

}
