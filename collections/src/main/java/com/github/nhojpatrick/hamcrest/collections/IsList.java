package com.github.nhojpatrick.hamcrest.collections;

import com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag;
import org.hamcrest.Matcher;
import org.hamcrest.core.AnyOf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.CONTAINS;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.EMPTY;
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
