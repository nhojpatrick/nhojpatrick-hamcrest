package com.github.nhojpatrick.hamcrest.collections;

import com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.AnyOf;

import java.util.Map;
import java.util.Objects;

import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.CONTAINS;
import static com.github.nhojpatrick.hamcrest.collections.internal.IsCollectionsFlag.EMPTY;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsNull.nullValue;

public class IsMap<T extends Map>
        extends TypeSafeMatcher<T> {

    @Factory
    public static <T extends Map> Matcher<T> mapWithSize(final long size) {
        return mapWithSize(Long.valueOf(size).intValue());
    }

    @Factory
    public static <T extends Map> Matcher<T> mapWithSize(final int size) {
        return new IsMap<>(size);
    }

    @Factory
    public static <T extends Map> Matcher<T> emptyMap() {
        return new IsMap<>(EMPTY);
    }

    @Factory
    public static <T extends Map> Matcher<T> nullOrEmptyMap() {

        final AnyOf<T> nullOrEmptyCollection = anyOf(
                nullValue(),
                new IsMap<>(EMPTY)
        );

        return nullOrEmptyCollection;
    }

    private Integer actualSize;
    private final Integer expectedSize;
    private final IsCollectionsFlag flag;
    private final String type;

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

        final boolean isNull = Objects.isNull(item);

        final boolean isEmpty = Objects.nonNull(item)
                && item.isEmpty();

        final boolean isEmptyOrNull = isNull
                || isEmpty;

        switch (this.flag) {
            case EMPTY:
                return isEmpty;

            case CONTAINS:
            default:
                return this.expectedSize.equals(this.actualSize);
        }
    }

}
