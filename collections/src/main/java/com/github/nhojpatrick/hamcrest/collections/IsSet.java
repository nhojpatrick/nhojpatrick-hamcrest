package com.github.nhojpatrick.hamcrest.collections;

import com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag;
import org.hamcrest.Matcher;
import org.hamcrest.core.AnyOf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.CONTAINS;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.EMPTY;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.GREATER_THAN;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.GREATER_THAN_OR_EQUAL_TO;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.LESS_THAN;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.LESS_THAN_OR_EQUAL_TO;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsNull.nullValue;

public class IsSet<T extends Set>
        extends IsCollection<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsSet.class);

    public static <T extends Set> Matcher<T> setWithSize(final long size) {
        LOGGER.debug("IsSet#setWithSize((long) {})", size);
        return setWithSize(Long.valueOf(size).intValue());
    }

    public static <T extends Set> Matcher<T> setWithSize(final int size) {
        LOGGER.debug("IsSet#setWithSize((int) {})", size);
        return new IsSet<>(size);
    }

    public static <T extends Set> Matcher<T> setWithSizeGreaterThan(final long size) {
        LOGGER.debug("IsSet#setWithSizeGreaterThan((long) {})", size);
        return setWithSizeGreaterThan(Long.valueOf(size).intValue());
    }

    public static <T extends Set> Matcher<T> setWithSizeGreaterThan(final int size) {
        LOGGER.debug("IsSet#setWithSizeGreaterThan((int) {})", size);
        return new IsSet<>(GREATER_THAN, size);
    }

    public static <T extends Set> Matcher<T> setWithSizeGreaterThanOrEqualTo(final long size) {
        LOGGER.debug("IsSet#setWithSizeGreaterThanOrEqualTo((long) {})", size);
        return setWithSizeGreaterThanOrEqualTo(Long.valueOf(size).intValue());
    }

    public static <T extends Set> Matcher<T> setWithSizeGreaterThanOrEqualTo(final int size) {
        LOGGER.debug("IsSet#setWithSizeGreaterThanOrEqualTo((int) {})", size);
        return new IsSet<>(GREATER_THAN_OR_EQUAL_TO, size);
    }

    public static <T extends Set> Matcher<T> setWithSizeLessThan(final long size) {
        LOGGER.debug("IsSet#setWithSizeLessThan((long) {})", size);
        return setWithSizeLessThan(Long.valueOf(size).intValue());
    }

    public static <T extends Set> Matcher<T> setWithSizeLessThan(final int size) {
        LOGGER.debug("IsSet#setWithSizeLessThan((int) {})", size);
        return new IsSet<>(LESS_THAN, size);
    }

    public static <T extends Set> Matcher<T> setWithSizeLessThanOrEqualTo(final long size) {
        LOGGER.debug("IsSet#setWithSizeLessThanOrEqualTo((long) {})", size);
        return setWithSizeLessThanOrEqualTo(Long.valueOf(size).intValue());
    }

    public static <T extends Set> Matcher<T> setWithSizeLessThanOrEqualTo(final int size) {
        LOGGER.debug("IsSet#setWithSizeLessThanOrEqualTo((int) {})", size);
        return new IsSet<>(LESS_THAN_OR_EQUAL_TO, size);
    }

    public static <T extends Set> Matcher<T> emptySet() {
        LOGGER.debug("IsSet#emptySet()");
        return new IsSet<>(EMPTY);
    }

    public static <T extends Set> Matcher<T> nullOrEmptySet() {

        LOGGER.debug("IsSet#nullOrEmptySet()");

        final AnyOf<T> nullOrEmptyCollection = anyOf(
                nullValue(),
                new IsSet<>(EMPTY)
        );

        return nullOrEmptyCollection;
    }

    private IsSet(final IsCollectionsFlag flag) {
        this(flag, null);
    }

    private IsSet(final Integer size) {
        this(CONTAINS, size);
    }

    private IsSet(final IsCollectionsFlag flag, final Integer size) {
        super(Set.class.getName(), flag, size);
    }

}
