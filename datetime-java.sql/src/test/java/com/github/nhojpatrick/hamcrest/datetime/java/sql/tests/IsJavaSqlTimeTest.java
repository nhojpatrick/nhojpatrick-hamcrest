package com.github.nhojpatrick.hamcrest.datetime.java.sql.tests;

import com.github.nhojpatrick.hamcrest.datetime.java.sql.IsJavaSqlTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("IsJavaSqlTime")
public class IsJavaSqlTimeTest {

    @Test
    public void checkIs_StaticUtilityClass() {

        final Executable testMethod = IsJavaSqlTime::new;
        final AssertionError thrown = assertThrows(AssertionError.class, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo("Static utility class - cannot be instantiated."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

}
