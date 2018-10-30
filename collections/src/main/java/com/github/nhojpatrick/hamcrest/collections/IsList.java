package com.github.nhojpatrick.hamcrest.collections;

import com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.core.AnyOf;

import java.util.List;

import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.CONTAINS;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.EMPTY;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsNull.nullValue;

public class IsList<T extends List>
        extends IsCollection<T> {

    @Factory
    public static <T extends List> Matcher<T> listWithSize(final long size) {
        return listWithSize(Long.valueOf(size).intValue());
    }

    @Factory
    public static <T extends List> Matcher<T> listWithSize(final int size) {
        return new IsList<>(size);
    }

    @Factory
    public static <T extends List> Matcher<T> emptyList() {
        return new IsList<>(EMPTY);
    }

    @Factory
    public static <T extends List> Matcher<T> nullOrEmptyList() {

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
