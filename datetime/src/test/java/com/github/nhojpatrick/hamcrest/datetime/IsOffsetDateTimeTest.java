package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.testing.MatcherTypedTester;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class IsOffsetDateTimeTest {

    private static final MatcherTypedTester<OffsetDateTime> TESTER = new MatcherTypedTester<>();

    private static final OffsetDateTime TYPED_NULL = null;
    private static final OffsetDateTime DATE_HARD_CODED = OffsetDateTime.of(
            LocalDateTime.of(2018, 05, 28, 01, 02, 03),
            ZoneOffset.UTC);
//    private static final Optional<String> POPULATED = Optional.of("alpha");
//    private static final Optional<String> MISMATCH = Optional.of("bravo");

//    @Nested
//    @DisplayName("Optional Contains tests")
//    class optionalContains {
//
//        @Test
//        public void empty() {
//            TESTER.assertFails(EMPTY, optionalContains("alpha"), "\nExpected: Optional containing \"alpha\"\n      but: <2018-05-28>");
//        }
//
//        @Test
//        public void populatedMatch() {
//            TESTER.assertValid(POPULATED, optionalContains("alpha"));
//        }
//
//        @Test
//        public void populatedMismatch() {
//            TESTER.assertFails(MISMATCH, optionalContains("alpha"), "\nExpected: Optional containing \"alpha\"\n      but: <Optional[bravo]>");
//        }
//
//        @Test
//        public void typedNull() {
//            TESTER.assertFails(TYPED_NULL, optionalContains("alpha"), "\nExpected: Optional containing \"alpha\"\n      but: null");
//        }
//
//    }

//    @Nested
//    @DisplayName("Optional Empty tests")
//    class optionalIsEmpty {
//
//        @Test
//        public void empty() {
//            TESTER.assertValid(EMPTY, optionalIsEmpty());
//        }
//
//        @Test
//        public void populated() {
//            TESTER.assertFails(POPULATED, optionalIsEmpty(), "\nExpected: Optional.empty\n      but: <Optional[alpha]>");
//        }
//
//        @Test
//        public void typedNull() {
//            TESTER.assertValid(TYPED_NULL, optionalIsEmpty());
//        }
//
//    }

//    @Nested
//    @DisplayName("Optional Present tests")
//    class optionalIsPresent {
//
//        @Test
//        public void empty() {
//            TESTER.assertFails(EMPTY, optionalIsPresent(), "\nExpected: Optional.isPresent\n      but: <2018-05-28>");
//        }
//
//        @Test
//        public void populated() {
//            TESTER.assertValid(POPULATED, optionalIsPresent());
//        }
//
//        @Test
//        public void typedNull() {
//            TESTER.assertFails(TYPED_NULL, optionalIsPresent(), "\nExpected: Optional.isPresent\n      but: null");
//        }
//
//    }

//    @Nested
//    @DisplayName("Null OffsetDateTime tests")
//    class nullOffsetDateTime {
//
//        @Test
//        public void date() {
//            TESTER.assertFails(DATE_HARD_CODED, nullOffsetDateTime(), "\nExpected: null\n      but: <2018-05-28T01:02:03Z>");
//        }
//
//        @Test
//        public void typedNull() {
//            TESTER.assertValid(TYPED_NULL, nullOffsetDateTime());
//        }
//
//    }

}
