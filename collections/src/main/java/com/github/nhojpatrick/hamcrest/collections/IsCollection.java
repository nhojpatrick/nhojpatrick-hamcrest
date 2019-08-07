package com.github.nhojpatrick.hamcrest.collections;

import com.github.nhojpatrick.hamcrest.collections.internal.AbstractIsCollections;
import com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag;
import org.hamcrest.Matcher;
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
        extends AbstractIsCollections<T> {

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

    private IsCollection(final IsCollectionsFlag flag) {
        this(flag, null);
    }

    private IsCollection(final Integer size) {
        this(CONTAINS, size);
    }

    protected IsCollection(final IsCollectionsFlag flag, final Integer size) {
        super(Collection.class.getName(), flag, size);
    }

    protected IsCollection(final String type, final IsCollectionsFlag flag, final Integer size) {
        super(type, flag, size);
    }

    @Override
    protected boolean matchesSafely(final T item) {
        return super.matchesSafely(item,
                Objects.isNull(item) ? null : item.size(),
                Objects.nonNull(item) && item.isEmpty()
        );
    }

}
