package com.github.nhojpatrick.hamcrest.collections;

import com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.core.AnyOf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.CONTAINS;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.EMPTY;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsNull.nullValue;

public class IsSet<T extends Set>
        extends IsCollection<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsSet.class);

    @Factory
    public static <T extends Set> Matcher<T> setWithSize(final long size) {
        LOGGER.debug("IsSet#setWithSize((long) {})", size);
        return setWithSize(Long.valueOf(size).intValue());
    }

    @Factory
    public static <T extends Set> Matcher<T> setWithSize(final int size) {
        LOGGER.debug("IsSet#setWithSize((int) {})", size);
        return new IsSet<>(size);
    }

    @Factory
    public static <T extends Set> Matcher<T> emptySet() {
        LOGGER.debug("IsSet#emptySet()");
        return new IsSet<>(EMPTY);
    }

    @Factory
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
