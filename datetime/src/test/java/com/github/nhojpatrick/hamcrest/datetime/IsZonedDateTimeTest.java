package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class IsZonedDateTimeTest {

    private static final MatcherObjectTester<ZonedDateTime> TESTER = new MatcherObjectTester<>();

    private static final ZonedDateTime TYPED_NULL = null;
    private static final ZonedDateTime DATE_HARD_CODED = ZonedDateTime.of(
            LocalDateTime.of(2018, 05, 28, 01, 02, 03),
            ZoneId.systemDefault());
    //
//    @Nested
//    @DisplayName("zonedDateTime matcher tests")
//    class check_zonedDateTime_matcher {
//
//        @Test
//        public void valid() {
//
//            TESTER.assertValid(DATE_HARD_CODED, zonedDateTime(equalTo(DATE_HARD_CODED)));
//        }
//
//        @Test
//        public void nullCheck() {
//
//            final Executable testMethod = () -> zonedDateTime(null);
//            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, testMethod);
//            assertThat(exception.getMessage(), is(equalTo("Supplied Matcher must not be null")));
//        }
//
//        @Test
//        public void string() {
//
//            TESTER.assertFails("", zonedDateTime(null), "\nExpected: an instance of java.time.ZonedDateTime\n      but: \"\" is a java.lang.String");
//        }
//
//    }
//
//    @Nested
//    @DisplayName("zonedDateTime type tests")
//    class check_zonedDateTime_object {
//
//        @Test
//        public void valid() {
//
//            TESTER.assertValid(DATE_HARD_CODED, zonedDateTime());
//        }
//
//        @Test
//        public void nullCheck() {
//
//            TESTER.assertFails(null, zonedDateTime(), "\nExpected: an instance of java.time.ZonedDateTime\n      but: null");
//        }
//
//        @Test
//        public void string() {
//
//            TESTER.assertFails("", zonedDateTime(), "\nExpected: an instance of java.time.ZonedDateTime\n      but: \"\" is a java.lang.String");
//        }
//
//    }
//
    @Nested
    @DisplayName("ZonedDateTime betweenDateTime tests")
    class betweenZonedDateTimeTest {

        @Test
        public void valid() {

//            final ZonedDateTime now = ZonedDateTime.now();
//            final ZonedDateTime after = now.minusMinutes(10);
//            final ZonedDateTime before = now.plusMinutes(10);
//
//            TESTER.assertValid(now, is(zonedDateTime(betweenZonedDateTime(after, before))));
        }
//
//        @Test
//        public void after() {
//
//            final ZonedDateTime now = ZonedDateTime.now();
//            final ZonedDateTime after = now.minusMinutes(20);
//            final ZonedDateTime before = now.minusMinutes(10);
//
//            TESTER.assertFails(now, is(zonedDateTime(betweenZonedDateTime(after, before))),
//                    String.format("\nExpected: is %s after <%s> and before <%s>\n      but: was <%s>",
//                            ZonedDateTime.class.getName(),
//                            after,
//                            before,
//                            now
//                    ));
//        }
//
//        @Test
//        public void before() {
//
//            final ZonedDateTime now = ZonedDateTime.now();
//            final ZonedDateTime after = now.plusMinutes(10);
//            final ZonedDateTime before = now.plusMinutes(20);
//
//            TESTER.assertFails(now, is(zonedDateTime(betweenZonedDateTime(after, before))),
//                    String.format("\nExpected: is %s after <%s> and before <%s>\n      but: was <%s>",
//                            ZonedDateTime.class.getName(),
//                            after,
//                            before,
//                            now
//                            ));
//        }

    }

}
