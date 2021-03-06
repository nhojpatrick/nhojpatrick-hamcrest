package com.github.nhojpatrick.hamcrest.collections;

import com.github.nhojpatrick.hamcrest.collections.internal.AbstractIsCollections;
import com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.hamcrest.Matcher;
import org.hamcrest.core.AnyOf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;

import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.CONTAINS;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.EMPTY;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.GREATER_THAN;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.GREATER_THAN_OR_EQUAL_TO;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.LESS_THAN;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.LESS_THAN_OR_EQUAL_TO;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsNull.nullValue;

public class IsMap<T extends Map>
        extends AbstractIsCollections<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsMap.class);

    @SuppressFBWarnings(value = "NAB_NEEDLESS_BOX_TO_CAST", justification = "Aware and accepted 2020/03/05")
    public static <T extends Map> Matcher<T> mapWithSize(final long size) {
        LOGGER.debug("IsMap#mapWithSize((long) {})", size);
        return mapWithSize(Long.valueOf(size).intValue());
    }

    public static <T extends Map> Matcher<T> mapWithSize(final int size) {
        LOGGER.debug("IsMap#mapWithSize((int) {})", size);
        return new IsMap<>(size);
    }

    @SuppressFBWarnings(value = "NAB_NEEDLESS_BOX_TO_CAST", justification = "Aware and accepted 2020/03/05")
    public static <T extends Map> Matcher<T> mapWithSizeGreaterThan(final long size) {
        LOGGER.debug("IsMap#mapWithSizeGreaterThan((long) {})", size);
        return mapWithSizeGreaterThan(Long.valueOf(size).intValue());
    }

    public static <T extends Map> Matcher<T> mapWithSizeGreaterThan(final int size) {
        LOGGER.debug("IsMap#mapWithSizeGreaterThan((int) {})", size);
        return new IsMap<>(GREATER_THAN, size);
    }

    @SuppressFBWarnings(value = "NAB_NEEDLESS_BOX_TO_CAST", justification = "Aware and accepted 2020/03/05")
    public static <T extends Map> Matcher<T> mapWithSizeGreaterThanOrEqualTo(final long size) {
        LOGGER.debug("IsMap#mapWithSizeGreaterThanOrEqualTo((long) {})", size);
        return mapWithSizeGreaterThanOrEqualTo(Long.valueOf(size).intValue());
    }

    public static <T extends Map> Matcher<T> mapWithSizeGreaterThanOrEqualTo(final int size) {
        LOGGER.debug("IsMap#mapWithSizeGreaterThanOrEqualTo((int) {})", size);
        return new IsMap<>(GREATER_THAN_OR_EQUAL_TO, size);
    }

    @SuppressFBWarnings(value = "NAB_NEEDLESS_BOX_TO_CAST", justification = "Aware and accepted 2020/03/05")
    public static <T extends Map> Matcher<T> mapWithSizeLessThan(final long size) {
        LOGGER.debug("IsMap#mapWithSizeLessThan((long) {})", size);
        return mapWithSizeLessThan(Long.valueOf(size).intValue());
    }

    public static <T extends Map> Matcher<T> mapWithSizeLessThan(final int size) {
        LOGGER.debug("IsMap#mapWithSizeLessThan((int) {})", size);
        return new IsMap<>(LESS_THAN, size);
    }

    @SuppressFBWarnings(value = "NAB_NEEDLESS_BOX_TO_CAST", justification = "Aware and accepted 2020/03/05")
    public static <T extends Map> Matcher<T> mapWithSizeLessThanOrEqualTo(final long size) {
        LOGGER.debug("IsMap#mapWithSizeLessThanOrEqualTo((long) {})", size);
        return mapWithSizeLessThanOrEqualTo(Long.valueOf(size).intValue());
    }

    public static <T extends Map> Matcher<T> mapWithSizeLessThanOrEqualTo(final int size) {
        LOGGER.debug("IsMap#mapWithSizeLessThanOrEqualTo((int) {})", size);
        return new IsMap<>(LESS_THAN_OR_EQUAL_TO, size);
    }

    public static <T extends Map> Matcher<T> emptyMap() {
        LOGGER.debug("IsMap#emptyMap()");
        return new IsMap<>(EMPTY);
    }

    @SuppressFBWarnings(value = "USBR_UNNECESSARY_STORE_BEFORE_RETURN", justification = "Useful for debugging")
    public static <T extends Map> Matcher<T> nullOrEmptyMap() {

        LOGGER.debug("IsMap#nullOrEmptyMap()");

        final AnyOf<T> nullOrEmptyCollection = anyOf(
                nullValue(),
                new IsMap<>(EMPTY)
        );

        return nullOrEmptyCollection;
    }

    private IsMap(final IsCollectionsFlag flag) {
        this(flag, null);
    }

    private IsMap(final Integer size) {
        this(CONTAINS, size);
    }

    protected IsMap(final IsCollectionsFlag flag, final Integer size) {
        this(Map.class.getName(), flag, size);
    }

    protected IsMap(final String type, final IsCollectionsFlag flag, final Integer size) {
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
