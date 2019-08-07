package com.github.nhojpatrick.hamcrest.collections.internal;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Collection;
import java.util.Objects;

public abstract class AbstractIsCollections<T>
        extends TypeSafeMatcher<T> {

    private Integer actualSize;
    private final Integer expectedSize;
    private final IsCollectionsFlag flag;
    private final String type;

    protected AbstractIsCollections(final String type, final IsCollectionsFlag flag, final Integer size) {
        this.expectedSize = size;
        this.flag = flag;
        this.type = type;
    }

    @Override
    public void describeMismatchSafely(final T item, final Description description) {

        description.appendText("was ");
        description.appendText(this.type);
        description.appendText(" size ");
        description.appendValue(this.actualSize);
        description.appendText(" ");
        description.appendValue(item);
    }

    @Override
    public void describeTo(final Description description) {

        switch (this.flag) {
            case EMPTY:
                description.appendText("empty ");
                description.appendText(this.type);
                break;

            case CONTAINS:
            default:
                description.appendText(this.type);
                description.appendText(" size ");
                description.appendValue(this.expectedSize);
                break;
        }
    }

    protected boolean matchesSafely(final T item,
                                    final Integer size,
                                    final boolean empty) {

        if (item != null) {
            this.actualSize = size;
        }

        final boolean isNull = Objects.isNull(item);

        final boolean isEmpty = Objects.nonNull(item)
                && empty;

        final boolean isEmptyOrNull = isNull
                || isEmpty;

        switch (this.flag) {
            case EMPTY:
                return isEmptyOrNull;

            case CONTAINS:
            default:
                return this.expectedSize.equals(this.actualSize);
        }
    }

}
