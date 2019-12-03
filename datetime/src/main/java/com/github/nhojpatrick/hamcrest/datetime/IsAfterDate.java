package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.AbstractIsAfter;
import com.github.nhojpatrick.hamcrest.datetime.internal.after.IsAfterLocalDate;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;

public abstract class IsAfterDate<T>
        extends AbstractIsAfter<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsAfterDate.class);

    public static <T> Matcher<T> afterLocalDate(final LocalDate after) {
        LOGGER.debug("IsAfterDate#afterLocalDate((After) {})", after);
        return afterLocalDate(after, EXCLUSIVE);
    }

    public static <T> Matcher<T> afterLocalDate(final LocalDate after, final CompareType compareType) {
        LOGGER.debug("IsAfterDate#afterLocalDate((After) {}, (CompareType) {} )", after, compareType);
        return new IsAfterLocalDate(after, compareType);
    }

    protected IsAfterDate(final T after, final CompareType compareType) {
        super(after, compareType);
    }

}
