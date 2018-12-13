package com.github.nhojpatrick.hamcrest.collections;

import com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.AnyOf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Objects;

import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.CONTAINS;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.EMPTY;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsNull.nullValue;

public class IsCollection<T extends Collection>
        extends TypeSafeMatcher<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsCollection.class);

    public static <T extends Collection> Matcher<T> collectionWithSize(final long size) {
        LOGGER.debug("IsCollection#collectionWithSize((long) {})", size);
        return collectionWithSize(Long.valueOf(size).intValue());
    }

    public static <T extends Collection> Matcher<T> collectionWithSize(final int size) {
        LOGGER.debug("IsCollection#collectionWithSize((int) {})", size);
        return new IsCollection<>(size);
    }

    public static <T extends Collection> Matcher<T> emptyCollection() {
        LOGGER.debug("IsCollection#emptyCollection()");
        return new IsCollection<>(EMPTY);
    }

    public static <T extends Collection> Matcher<T> nullOrEmptyCollection() {

        LOGGER.debug("IsCollection#nullOrEmptyCollection()");

        final AnyOf nullOrEmptyCollection = anyOf(
                nullValue(),
                new IsCollection<>(EMPTY)
        );

        return nullOrEmptyCollection;
    }

    private Integer actualSize;
    private final Integer expectedSize;
    private final IsCollectionsFlag flag;
    private final String type;

    private IsCollection(final IsCollectionsFlag flag) {
        this(flag, null);
    }

    private IsCollection(final Integer size) {
        this(CONTAINS, size);
    }

    protected IsCollection(final IsCollectionsFlag flag, final Integer size) {
        this(Collection.class.getName(), flag, size);
    }

    protected IsCollection(final String type, final IsCollectionsFlag flag, final Integer size) {
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

    @Override
    protected boolean matchesSafely(final T item) {

        if (item != null) {
            this.actualSize = item.size();
        }

        final boolean isEmpty = Objects.nonNull(item)
                && item.isEmpty();

        switch (this.flag) {
            case EMPTY:
                return isEmpty;

            case CONTAINS:
            default:
                return this.expectedSize.equals(this.actualSize);
        }
    }

}
