package com.github.nhojpatrick.hamcrest.collections;

import com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag;
import org.hamcrest.Matcher;
import org.hamcrest.core.AnyOf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.CONTAINS;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.EMPTY;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.GREATER_THAN;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.GREATER_THAN_OR_EQUAL_TO;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.LESS_THAN;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.LESS_THAN_OR_EQUAL_TO;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsNull.nullValue;

public class IsList<T extends List>
        extends IsCollection<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsList.class);

    public static <T extends List> Matcher<T> listWithSize(final long size) {
        LOGGER.debug("IsList#listWithSize((long) {})", size);
        return listWithSize(Long.valueOf(size).intValue());
    }

    public static <T extends List> Matcher<T> listWithSize(final int size) {
        LOGGER.debug("IsList#listWithSize((int) {})", size);
        return new IsList<>(size);
    }

    public static <T extends List> Matcher<T> listWithSizeGreaterThan(final long size) {
        LOGGER.debug("IsList#listWithSizeGreaterThan((long) {})", size);
        return listWithSizeGreaterThan(Long.valueOf(size).intValue());
    }

    public static <T extends List> Matcher<T> listWithSizeGreaterThan(final int size) {
        LOGGER.debug("IsList#listWithSizeGreaterThan((int) {})", size);
        return new IsList<>(GREATER_THAN, size);
    }

    public static <T extends List> Matcher<T> listWithSizeGreaterThanOrEqualTo(final long size) {
        LOGGER.debug("IsList#listWithSizeGreaterThanOrEqualTo((long) {})", size);
        return listWithSizeGreaterThanOrEqualTo(Long.valueOf(size).intValue());
    }

    public static <T extends List> Matcher<T> listWithSizeGreaterThanOrEqualTo(final int size) {
        LOGGER.debug("IsList#listWithSizeGreaterThanOrEqualTo((int) {})", size);
        return new IsList<>(GREATER_THAN_OR_EQUAL_TO, size);
    }

    public static <T extends List> Matcher<T> listWithSizeLessThan(final long size) {
        LOGGER.debug("IsList#listWithSizeLessThan((long) {})", size);
        return listWithSizeLessThan(Long.valueOf(size).intValue());
    }

    public static <T extends List> Matcher<T> listWithSizeLessThan(final int size) {
        LOGGER.debug("IsList#listWithSizeLessThan((int) {})", size);
        return new IsList<>(LESS_THAN, size);
    }

    public static <T extends List> Matcher<T> listWithSizeLessThanOrEqualTo(final long size) {
        LOGGER.debug("IsList#listWithSizeLessThanOrEqualTo((long) {})", size);
        return listWithSizeLessThanOrEqualTo(Long.valueOf(size).intValue());
    }

    public static <T extends List> Matcher<T> listWithSizeLessThanOrEqualTo(final int size) {
        LOGGER.debug("IsList#listWithSizeLessThanOrEqualTo((int) {})", size);
        return new IsList<>(LESS_THAN_OR_EQUAL_TO, size);
    }

    public static <T extends List> Matcher<T> emptyList() {
        LOGGER.debug("IsList#emptyList()");
        return new IsList<>(EMPTY);
    }

    public static <T extends List> Matcher<T> nullOrEmptyList() {

        LOGGER.debug("IsList#nullOrEmptyList()");

        final AnyOf nullOrEmptyCollection = anyOf(
                nullValue(),
                new IsList<>(EMPTY)
        );

        return nullOrEmptyCollection;
    }

    private IsList(final IsCollectionsFlag flag) {
        this(flag, null);
    }

    private IsList(final Integer size) {
        this(CONTAINS, size);
    }

    private IsList(final IsCollectionsFlag flag, final Integer size) {
        super(List.class.getName(), flag, size);
    }

}
