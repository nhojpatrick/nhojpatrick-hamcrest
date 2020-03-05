package com.github.nhojpatrick.hamcrest.collections;

import com.github.nhojpatrick.hamcrest.collections.internal.AbstractIsCollections;
import com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.hamcrest.Matcher;
import org.hamcrest.core.AnyOf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Objects;

import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.CONTAINS;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.EMPTY;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.GREATER_THAN;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.GREATER_THAN_OR_EQUAL_TO;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.LESS_THAN;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.LESS_THAN_OR_EQUAL_TO;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsNull.nullValue;

public class IsCollection<T extends Collection>
        extends AbstractIsCollections<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsCollection.class);

    @SuppressFBWarnings(value = "NAB_NEEDLESS_BOX_TO_CAST", justification = "Aware and accepted 2020/03/05")
    public static <T extends Collection> Matcher<T> collectionWithSize(final long size) {
        LOGGER.debug("IsCollection#collectionWithSize((long) {})", size);
        return collectionWithSize(Long.valueOf(size).intValue());
    }

    public static <T extends Collection> Matcher<T> collectionWithSize(final int size) {
        LOGGER.debug("IsCollection#collectionWithSize((int) {})", size);
        return new IsCollection<>(size);
    }

    @SuppressFBWarnings(value = "NAB_NEEDLESS_BOX_TO_CAST", justification = "Aware and accepted 2020/03/05")
    public static <T extends Collection> Matcher<T> collectionWithSizeGreaterThan(final long size) {
        LOGGER.debug("IsCollection#collectionWithSizeGreaterThan((long) {})", size);
        return collectionWithSizeGreaterThan(Long.valueOf(size).intValue());
    }

    public static <T extends Collection> Matcher<T> collectionWithSizeGreaterThan(final int size) {
        LOGGER.debug("IsCollection#collectionWithSizeGreaterThan((int) {})", size);
        return new IsCollection<>(GREATER_THAN, size);
    }

    @SuppressFBWarnings(value = "NAB_NEEDLESS_BOX_TO_CAST", justification = "Aware and accepted 2020/03/05")
    public static <T extends Collection> Matcher<T> collectionWithSizeGreaterThanOrEqualTo(final long size) {
        LOGGER.debug("IsCollection#collectionWithSizeGreaterThanOrEqualTo((long) {})", size);
        return collectionWithSizeGreaterThanOrEqualTo(Long.valueOf(size).intValue());
    }

    public static <T extends Collection> Matcher<T> collectionWithSizeGreaterThanOrEqualTo(final int size) {
        LOGGER.debug("IsCollection#collectionWithSizeGreaterThanOrEqualTo((int) {})", size);
        return new IsCollection<>(GREATER_THAN_OR_EQUAL_TO, size);
    }

    @SuppressFBWarnings(value = "NAB_NEEDLESS_BOX_TO_CAST", justification = "Aware and accepted 2020/03/05")
    public static <T extends Collection> Matcher<T> collectionWithSizeLessThan(final long size) {
        LOGGER.debug("IsCollection#collectionWithSizeLessThan((long) {})", size);
        return collectionWithSizeLessThan(Long.valueOf(size).intValue());
    }

    public static <T extends Collection> Matcher<T> collectionWithSizeLessThan(final int size) {
        LOGGER.debug("IsCollection#collectionWithSizeLessThan((int) {})", size);
        return new IsCollection<>(LESS_THAN, size);
    }

    @SuppressFBWarnings(value = "NAB_NEEDLESS_BOX_TO_CAST", justification = "Aware and accepted 2020/03/05")
    public static <T extends Collection> Matcher<T> collectionWithSizeLessThanOrEqualTo(final long size) {
        LOGGER.debug("IsCollection#collectionWithSizeLessThanOrEqualTo((long) {})", size);
        return collectionWithSizeLessThanOrEqualTo(Long.valueOf(size).intValue());
    }

    public static <T extends Collection> Matcher<T> collectionWithSizeLessThanOrEqualTo(final int size) {
        LOGGER.debug("IsCollection#collectionWithSizeLessThanOrEqualTo((int) {})", size);
        return new IsCollection<>(LESS_THAN_OR_EQUAL_TO, size);
    }

    public static <T extends Collection> Matcher<T> emptyCollection() {
        LOGGER.debug("IsCollection#emptyCollection()");
        return new IsCollection<>(EMPTY);
    }

    @SuppressFBWarnings(value = "USBR_UNNECESSARY_STORE_BEFORE_RETURN", justification = "Useful for debugging")
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
        this(Collection.class.getName(), flag, size);
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
