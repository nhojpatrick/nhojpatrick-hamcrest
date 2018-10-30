package com.github.nhojpatrick.hamcrest.collections.tests;

import com.github.nhojpatrick.hamcrest.testing.MatcherTypedTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static com.github.nhojpatrick.hamcrest.collections.IsSet.emptySet;
import static com.github.nhojpatrick.hamcrest.collections.IsSet.nullOrEmptySet;
import static com.github.nhojpatrick.hamcrest.collections.IsSet.setWithSize;

public class IsSetTest {

    private static final MatcherTypedTester<Set> TESTER = new MatcherTypedTester<>();

    private static final Set<String> TYPED_NULL = null;
    private static final Set<String> EMPTY = new HashSet<>();
    private static final Set<String> ONE;
    private static final Set<String> TWO;

    static {
        final List<String> oneTwp = Arrays.asList("alpha");
        ONE = new TreeSet(oneTwp);

        final List<String> twoTmp = Arrays.asList("alpha", "bravo");
        TWO = new TreeSet(twoTmp);
    }

    @Nested
    @DisplayName("Set Size tests")
    class setWithSize {

        @Nested
        @DisplayName("Empty input")
        class setWithSizeEmpty {

            @Test
            public void expectedEmptyInt() {
                TESTER.assertValid(EMPTY, setWithSize(0));
            }

            @Test
            public void expectedEmptyLong() {
                TESTER.assertValid(EMPTY, setWithSize(0L));
            }

            @Test
            public void expectedOneInt() {
                TESTER.assertFails(EMPTY, setWithSize(1), "\nExpected: java.util.Set size <1>\n      but: was java.util.Set size <0> <[]>");
            }

            @Test
            public void expectedOneLong() {
                TESTER.assertFails(EMPTY, setWithSize(1L), "\nExpected: java.util.Set size <1>\n      but: was java.util.Set size <0> <[]>");
            }

        }

        @Nested
        @DisplayName("One entry input")
        class setWithSizeOne {

            @Test
            public void expectedEmptyInt() {
                TESTER.assertFails(ONE, setWithSize(0), "\nExpected: java.util.Set size <0>\n      but: was java.util.Set size <1> <[alpha]>");
            }

            @Test
            public void expectedEmptyLong() {
                TESTER.assertFails(ONE, setWithSize(0L), "\nExpected: java.util.Set size <0>\n      but: was java.util.Set size <1> <[alpha]>");
            }

            @Test
            public void expectedOneInt() {
                TESTER.assertValid(ONE, setWithSize(1));
            }

            @Test
            public void expectedOneLong() {
                TESTER.assertValid(ONE, setWithSize(1L));
            }

            @Test
            public void expectedTwoInt() {
                TESTER.assertFails(ONE, setWithSize(2), "\nExpected: java.util.Set size <2>\n      but: was java.util.Set size <1> <[alpha]>");
            }

            @Test
            public void expectedTwoLong() {
                TESTER.assertFails(ONE, setWithSize(2L), "\nExpected: java.util.Set size <2>\n      but: was java.util.Set size <1> <[alpha]>");
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class setWithSizeTwo {

            @Test
            public void expectedEmptyInt() {
                TESTER.assertFails(TWO, setWithSize(0), "\nExpected: java.util.Set size <0>\n      but: was java.util.Set size <2> <[alpha, bravo]>");
            }

            @Test
            public void expectedEmptyLong() {
                TESTER.assertFails(TWO, setWithSize(0L), "\nExpected: java.util.Set size <0>\n      but: was java.util.Set size <2> <[alpha, bravo]>");
            }

            @Test
            public void expectedOneInt() {
                TESTER.assertFails(TWO, setWithSize(1), "\nExpected: java.util.Set size <1>\n      but: was java.util.Set size <2> <[alpha, bravo]>");
            }

            @Test
            public void expectedOneLong() {
                TESTER.assertFails(TWO, setWithSize(1L), "\nExpected: java.util.Set size <1>\n      but: was java.util.Set size <2> <[alpha, bravo]>");
            }

            @Test
            public void expectedTwoInt() {
                TESTER.assertValid(TWO, setWithSize(2));
            }

            @Test
            public void expectedTwoLong() {
                TESTER.assertValid(TWO, setWithSize(2L));
            }

            @Test
            public void expectedThreeInt() {
                TESTER.assertFails(TWO, setWithSize(3), "\nExpected: java.util.Set size <3>\n      but: was java.util.Set size <2> <[alpha, bravo]>");
            }

            @Test
            public void expectedThreeLong() {
                TESTER.assertFails(TWO, setWithSize(3L), "\nExpected: java.util.Set size <3>\n      but: was java.util.Set size <2> <[alpha, bravo]>");
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class setWithSizeTypedNull {

            @Test
            public void expectedEmptyInt() {
                TESTER.assertFails(TYPED_NULL, setWithSize(0), "\nExpected: java.util.Set size <0>\n      but: was null");
            }

            @Test
            public void expectedEmptyLong() {
                TESTER.assertFails(TYPED_NULL, setWithSize(0L), "\nExpected: java.util.Set size <0>\n      but: was null");
            }

            @Test
            public void expectedOneInt() {
                TESTER.assertFails(TYPED_NULL, setWithSize(1), "\nExpected: java.util.Set size <1>\n      but: was null");
            }

            @Test
            public void expectedOneLong() {
                TESTER.assertFails(TYPED_NULL, setWithSize(1L), "\nExpected: java.util.Set size <1>\n      but: was null");
            }

        }

    }

    @Nested
    @DisplayName("Empty Set tests")
    class emptySet {

        @Test
        public void empty() {
            TESTER.assertValid(EMPTY, emptySet());
        }

        @Test
        public void nullTyped() {
            TESTER.assertFails(TYPED_NULL, emptySet(), "\nExpected: empty java.util.Set\n      but: was null");
        }

        @Test
        public void one() {
            TESTER.assertFails(ONE, emptySet(), "\nExpected: empty java.util.Set\n      but: was java.util.Set size <1> <[alpha]>");
        }

    }

    @Nested
    @DisplayName("Null Or Empty Set tests")
    class nullOrEmptySet {

        @Test
        public void empty() {
            TESTER.assertValid(EMPTY, nullOrEmptySet());
        }

        @Test
        public void nullTyped() {
            TESTER.assertValid(TYPED_NULL, nullOrEmptySet());
        }

        @Test
        public void one() {
            TESTER.assertFails(ONE, nullOrEmptySet(), "\nExpected: (null or empty java.util.Set)\n      but: was <[alpha]>");
        }

    }

}
