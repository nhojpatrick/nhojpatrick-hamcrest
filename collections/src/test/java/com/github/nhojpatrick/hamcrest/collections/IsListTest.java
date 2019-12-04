package com.github.nhojpatrick.hamcrest.collections;

import com.github.nhojpatrick.hamcrest.testing.MatcherTypedTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.nhojpatrick.hamcrest.collections.IsList.emptyList;
import static com.github.nhojpatrick.hamcrest.collections.IsList.listWithSize;
import static com.github.nhojpatrick.hamcrest.collections.IsList.nullOrEmptyList;

public class IsListTest {

    private static final MatcherTypedTester<List> TESTER = new MatcherTypedTester<>();

    private static final List<String> TYPED_NULL = null;
    private static final List<String> EMPTY = new ArrayList<>();
    private static final List<String> ONE = Arrays.asList("alpha");
    private static final List<String> TWO = Arrays.asList("alpha", "bravo");

    @Nested
    @DisplayName("List Size tests")
    class listWithSize {

        @Nested
        @DisplayName("Empty input")
        class listWithSizeEmpty {

            @Test
            public void expectedEmptyInt() {
                TESTER.assertValid(EMPTY, listWithSize(0));
            }

            @Test
            public void expectedEmptyLong() {
                TESTER.assertValid(EMPTY, listWithSize(0L));
            }

            @Test
            public void expectedOneInt() {
                TESTER.assertFails(EMPTY, listWithSize(1), "\nExpected: java.util.List size <1>\n      but: was java.util.List size <0> <[]>");
            }

            @Test
            public void expectedOneLong() {
                TESTER.assertFails(EMPTY, listWithSize(1L), "\nExpected: java.util.List size <1>\n      but: was java.util.List size <0> <[]>");
            }

        }

        @Nested
        @DisplayName("One entry input")
        class listWithSizeOne {

            @Test
            public void expectedEmptyInt() {
                TESTER.assertFails(ONE, listWithSize(0), "\nExpected: java.util.List size <0>\n      but: was java.util.List size <1> <[alpha]>");
            }

            @Test
            public void expectedEmptyLong() {
                TESTER.assertFails(ONE, listWithSize(0L), "\nExpected: java.util.List size <0>\n      but: was java.util.List size <1> <[alpha]>");
            }

            @Test
            public void expectedOneInt() {
                TESTER.assertValid(ONE, listWithSize(1));
            }

            @Test
            public void expectedOneLong() {
                TESTER.assertValid(ONE, listWithSize(1L));
            }

            @Test
            public void expectedTwoInt() {
                TESTER.assertFails(ONE, listWithSize(2), "\nExpected: java.util.List size <2>\n      but: was java.util.List size <1> <[alpha]>");
            }

            @Test
            public void expectedTwoLong() {
                TESTER.assertFails(ONE, listWithSize(2L), "\nExpected: java.util.List size <2>\n      but: was java.util.List size <1> <[alpha]>");
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class listWithSizeTwo {

            @Test
            public void expectedEmptyInt() {
                TESTER.assertFails(TWO, listWithSize(0), "\nExpected: java.util.List size <0>\n      but: was java.util.List size <2> <[alpha, bravo]>");
            }

            @Test
            public void expectedEmptyLong() {
                TESTER.assertFails(TWO, listWithSize(0L), "\nExpected: java.util.List size <0>\n      but: was java.util.List size <2> <[alpha, bravo]>");
            }

            @Test
            public void expectedOneInt() {
                TESTER.assertFails(TWO, listWithSize(1), "\nExpected: java.util.List size <1>\n      but: was java.util.List size <2> <[alpha, bravo]>");
            }

            @Test
            public void expectedOneLong() {
                TESTER.assertFails(TWO, listWithSize(1L), "\nExpected: java.util.List size <1>\n      but: was java.util.List size <2> <[alpha, bravo]>");
            }

            @Test
            public void expectedTwoInt() {
                TESTER.assertValid(TWO, listWithSize(2));
            }

            @Test
            public void expectedTwoLong() {
                TESTER.assertValid(TWO, listWithSize(2L));
            }

            @Test
            public void expectedThreeInt() {
                TESTER.assertFails(TWO, listWithSize(3), "\nExpected: java.util.List size <3>\n      but: was java.util.List size <2> <[alpha, bravo]>");
            }

            @Test
            public void expectedThreeLong() {
                TESTER.assertFails(TWO, listWithSize(3L), "\nExpected: java.util.List size <3>\n      but: was java.util.List size <2> <[alpha, bravo]>");
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class listWithSizeTypedNull {

            @Test
            public void expectedEmptyInt() {
                TESTER.assertFails(TYPED_NULL, listWithSize(0), "\nExpected: java.util.List size <0>\n      but: was null");
            }

            @Test
            public void expectedEmptyLong() {
                TESTER.assertFails(TYPED_NULL, listWithSize(0L), "\nExpected: java.util.List size <0>\n      but: was null");
            }

            @Test
            public void expectedOneInt() {
                TESTER.assertFails(TYPED_NULL, listWithSize(1), "\nExpected: java.util.List size <1>\n      but: was null");
            }

            @Test
            public void expectedOneLong() {
                TESTER.assertFails(TYPED_NULL, listWithSize(1L), "\nExpected: java.util.List size <1>\n      but: was null");
            }

        }

    }

    @Nested
    @DisplayName("Empty List tests")
    class emptyList {

        @Test
        public void empty() {
            TESTER.assertValid(EMPTY, emptyList());
        }

        @Test
        public void nullTyped() {
            TESTER.assertFails(TYPED_NULL, emptyList(), "\nExpected: empty java.util.List\n      but: was null");
        }

        @Test
        public void one() {
            TESTER.assertFails(ONE, emptyList(), "\nExpected: empty java.util.List\n      but: was java.util.List size <1> <[alpha]>");
        }

    }

    @Nested
    @DisplayName("Null Or Empty List tests")
    class nullOrEmptyList {

        @Test
        public void empty() {
            TESTER.assertValid(EMPTY, nullOrEmptyList());
        }

        @Test
        public void nullTyped() {
            TESTER.assertValid(TYPED_NULL, nullOrEmptyList());
        }

        @Test
        public void one() {
            TESTER.assertFails(ONE, nullOrEmptyList(), "\nExpected: (null or empty java.util.List)\n      but: was <[alpha]>");
        }

    }

}
