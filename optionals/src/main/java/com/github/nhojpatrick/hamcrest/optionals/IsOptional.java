package com.github.nhojpatrick.hamcrest.optionals;

import com.github.nhojpatrick.hamcrest.optionals.internal.IsOptionalFlag;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Objects;
import java.util.Optional;

import static com.github.nhojpatrick.hamcrest.optionals.internal.IsOptionalFlag.CONTAINS;
import static com.github.nhojpatrick.hamcrest.optionals.internal.IsOptionalFlag.EMPTY;
import static com.github.nhojpatrick.hamcrest.optionals.internal.IsOptionalFlag.PRESENT;

public class IsOptional<T extends Optional<?>>
        extends TypeSafeMatcher<T> {

    public static <T> Matcher<T> optionalContains(final Object expected) {
        return new IsOptional(expected);
    }

    public static <T> Matcher<T> optionalIsEmpty() {
        return new IsOptional(EMPTY);
    }

    public static <T> Matcher<T> optionalIsPresent() {
        return new IsOptional(PRESENT);
    }

    private final Object expected;
    private final IsOptionalFlag flag;

    private IsOptional(final IsOptionalFlag flag) {
        this(flag, null);
    }

    private IsOptional(final Object expected) {
        this(CONTAINS, expected);
    }

    private IsOptional(final IsOptionalFlag flag, final Object expected) {
        this.flag = flag;
        this.expected = expected;
    }

    @Override
    public void describeTo(final Description description) {

        switch (this.flag) {
            case EMPTY:
                description.appendText("Optional.empty()");
                break;

            case PRESENT:
                description.appendText("Optional.isPresent()");
                break;

            case CONTAINS:
            default:
                description.appendText("Optional containing ");
                description.appendValue(this.expected);
                break;
        }
    }

    @Override
    protected boolean matchesSafely(final T item) {

        final boolean isPresent = Objects.nonNull(item)
                && item.isPresent();

        switch (this.flag) {
            case EMPTY:
                return !isPresent;

            case PRESENT:
                return isPresent;

            case CONTAINS:
            default:
                final Optional<Object> expectedOptional = Optional.ofNullable(this.expected);
                final boolean equals = expectedOptional.equals(item);
                return equals;
        }
    }

}
