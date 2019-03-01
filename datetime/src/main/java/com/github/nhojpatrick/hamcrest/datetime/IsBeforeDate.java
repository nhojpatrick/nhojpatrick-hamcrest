package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.flags.CompareType;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.AbstractIsBefore;
import com.github.nhojpatrick.hamcrest.datetime.internal.before.IsBeforeLocalDate;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static com.github.nhojpatrick.hamcrest.datetime.flags.CompareType.EXCLUSIVE;

public abstract class IsBeforeDate<T>
        extends AbstractIsBefore<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsBeforeDate.class);

    public static <T> Matcher<T> beforeLocalDate(final LocalDate before) {
        LOGGER.debug("IsBeforeDate#beforeLocalDate((LocalDate) {})", before);
        return beforeLocalDate(before, EXCLUSIVE);
    }

    public static <T> Matcher<T> beforeLocalDate(final LocalDate before, final CompareType compareType) {
        LOGGER.debug("IsBeforeDate#beforeLocalDate((LocalDate) {}, (CompareType) {})", before, compareType);
        return new IsBeforeLocalDate(before, compareType);
    }

    protected IsBeforeDate(final T before, final CompareType compareType) {
        super(before, compareType);
    }

}
