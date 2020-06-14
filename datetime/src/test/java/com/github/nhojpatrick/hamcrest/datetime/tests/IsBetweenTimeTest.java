package com.github.nhojpatrick.hamcrest.datetime.tests;

import com.github.nhojpatrick.hamcrest.datetime.IsBetweenTime;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsBetweenTimeTest {

    @Test
    public void checkIs_StaticUtilityClass() {

        final Executable testMethod = IsBetweenTime::new;
        final AssertionError thrown = assertThrows(AssertionError.class, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo("Static utility class - cannot be instantiated."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

}
