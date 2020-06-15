package com.github.nhojpatrick.hamcrest.datetime.java.sql.tests;

import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static com.github.nhojpatrick.hamcrest.datetime.java.sql.IsJavaSqlTime.javaSqlTime;
import static com.github.nhojpatrick.hamcrest.testing.util.RandomHelper.randomIntBetween;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@DisplayName("IsJavaSqlTime.javaSqlTime")
public class IsJavaSqlTime_javaSqlTimeTest {

    private static final Time FUTURE = new Time(LocalDateTime.now()
            .atZone(java.time.ZoneId.systemDefault())
            .withHour(15)
            .withMinute(34)
            .withSecond(15)
            .withNano(randomIntBetween(250_000_000, 750_000_000))
            .toInstant()
            .toEpochMilli());

    private static final Time NOW = new Time(LocalDateTime.now()
            .atZone(java.time.ZoneId.systemDefault())
            .withHour(12)
            .withMinute(34)
            .withSecond(30)
            .withNano(randomIntBetween(250_000_000, 750_000_000))
            .toInstant()
            .toEpochMilli());

    private static final Time PAST = new Time(LocalDateTime.now()
            .atZone(java.time.ZoneId.systemDefault())
            .withHour(9)
            .withMinute(34)
            .withSecond(45)
            .withNano(randomIntBetween(250_000_000, 750_000_000))
            .toInstant()
            .toEpochMilli());

    private static final MatcherObjectTester<Time> TESTER = new MatcherObjectTester<>();

    @TestFactory
    @DisplayName("javaSqlTime() tests")
    public Stream<DynamicTest> javaSqlTimeTests() {

        return Stream.of(
                // match
                dynamicTest("Match", () -> TESTER.assertValid(NOW, javaSqlTime())),

                // mismatch
                dynamicTest("LocalDate", () -> TESTER.assertFails(LocalDate.of(2019, 12, 1), javaSqlTime(), "\nExpected: an instance of java.sql.Time\n      but: <2019-12-01> is a java.time.LocalDate")),
                dynamicTest("Object", () -> {
                    final Object object = new Object();
                    TESTER.assertFails(object, javaSqlTime(), String.format(
                            "\nExpected: an instance of java.sql.Time\n      but: <%s> is a java.lang.Object",
                            object.toString()
                    ));
                }),
                dynamicTest("String", () -> TESTER.assertFails("AString", javaSqlTime(), "\nExpected: an instance of java.sql.Time\n      but: \"AString\" is a java.lang.String"))
        );
    }

    @TestFactory
    @DisplayName("javaSqlTime(<Matcher>) tests")
    public Stream<DynamicTest> javaSqlTimeMatcherTests() {

        return Stream.of(
                // before
                dynamicTest("Past", () -> TESTER.assertFails(NOW, javaSqlTime(equalTo(PAST)), "\nExpected: java.sql.Time <09:34:45>\n      but: was <12:34:30>")),

                // now
                dynamicTest("Now", () -> TESTER.assertValid(NOW, javaSqlTime(is(NOW)))),

                // after
                dynamicTest("Future", () -> TESTER.assertFails(NOW, javaSqlTime(equalTo(FUTURE)), "\nExpected: java.sql.Time <15:34:15>\n      but: was <12:34:30>"))
        );
    }

}
