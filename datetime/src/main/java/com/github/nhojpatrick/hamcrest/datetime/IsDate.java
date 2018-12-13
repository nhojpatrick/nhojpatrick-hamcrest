package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.datetime.internal.equals.IsLocalDate;
import org.hamcrest.Matcher;

import java.time.LocalDate;

import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class IsDate {

    public static <T> Matcher<T> localDate() {
        return instanceOf(LocalDate.class);
    }

    public static <T> Matcher<T> localDate(final Matcher<T> matcher) {
        return new IsLocalDate(matcher);
    }

}
