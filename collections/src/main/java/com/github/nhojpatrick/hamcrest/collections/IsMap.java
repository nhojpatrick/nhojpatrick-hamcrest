package com.github.nhojpatrick.hamcrest.collections;

import com.github.nhojpatrick.hamcrest.collections.internal.AbstractIsCollections;
import com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag;
import org.hamcrest.Matcher;
import org.hamcrest.core.AnyOf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;

import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.CONTAINS;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.EMPTY;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsNull.nullValue;

public class IsMap<T extends Map>
        extends AbstractIsCollections<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsMap.class);

    public static <T extends Map> Matcher<T> mapWithSize(final long size) {
        LOGGER.debug("IsMap#mapWithSize((long) {})", size);
        return mapWithSize(Long.valueOf(size).intValue());
    }

    public static <T extends Map> Matcher<T> mapWithSize(final int size) {
        LOGGER.debug("IsMap#mapWithSize((int) {})", size);
        return new IsMap<>(size);
    }

    public static <T extends Map> Matcher<T> emptyMap() {
        LOGGER.debug("IsMap#emptyMap()");
        return new IsMap<>(EMPTY);
    }

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
