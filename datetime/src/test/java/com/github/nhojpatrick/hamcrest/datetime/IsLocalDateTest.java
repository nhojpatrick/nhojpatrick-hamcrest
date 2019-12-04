package com.github.nhojpatrick.hamcrest.datetime;

import com.github.nhojpatrick.hamcrest.testing.MatcherTypedTester;

import java.time.LocalDate;

public class IsLocalDateTest {

    private static final MatcherTypedTester<LocalDate> TESTER = new MatcherTypedTester<>();

    private static final LocalDate TYPED_NULL = null;
    private static final LocalDate DATE_HARD_CODED = LocalDate.of(2018, 05, 28);
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
//    @DisplayName("Null LocalDate tests")
//    class nullLocalDate {
//
//        @Test
//        public void date() {
//            TESTER.assertFails(DATE_HARD_CODED, nullLocalDate(), "\nExpected: null\n      but: <2018-05-28>");
//        }
//
//        @Test
//        public void typedNull() {
//            TESTER.assertValid(TYPED_NULL, nullLocalDate());
//        }
//
//    }

}
