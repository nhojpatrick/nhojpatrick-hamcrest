package com.github.nhojpatrick.hamcrest.collections;

import com.github.nhojpatrick.hamcrest.testing.MatcherTypedTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static com.github.nhojpatrick.hamcrest.collections.IsMap.emptyMap;
import static com.github.nhojpatrick.hamcrest.collections.IsMap.mapWithSize;
import static com.github.nhojpatrick.hamcrest.collections.IsMap.nullOrEmptyMap;
import static java.util.stream.Collectors.toMap;

public class IsMapTest {

    private static final MatcherTypedTester<Map> TESTER = new MatcherTypedTester<>();

    private static final Map<String, String> TYPED_NULL = null;
    private static final Map<String, String> EMPTY = new HashMap<>();
    private static final Map<String, String> ONE;
    private static final Map<String, String> TWO;

    static {
        ONE = Arrays.asList("alpha")
                .stream()
                .collect(toMap(p -> p + "Key", p -> p + "Value"));

        final Map<String, String> tmpTwo = Arrays.asList("alpha", "bravo")
                .stream()
                .collect(toMap(p -> p + "Key", p -> p + "Value"));
        TWO = new TreeMap<>(tmpTwo);
    }

    @Nested
    @DisplayName("Map Size tests")
    class mapWithSize {

        @Nested
        @DisplayName("Empty input")
        class mapWithSizeEmpty {

            @Test
            public void expectedEmptyInt() {
                TESTER.assertValid(EMPTY, mapWithSize(0));
            }

            @Test
            public void expectedEmptyLong() {
                TESTER.assertValid(EMPTY, mapWithSize(0L));
            }

            @Test
            public void expectedOneInt() {
                TESTER.assertFails(EMPTY, mapWithSize(1), "\nExpected: java.util.Map size <1>\n      but: was java.util.Map size <0> <{}>");
            }

            @Test
            public void expectedOneLong() {
                TESTER.assertFails(EMPTY, mapWithSize(1L), "\nExpected: java.util.Map size <1>\n      but: was java.util.Map size <0> <{}>");
            }

        }

        @Nested
        @DisplayName("One entry input")
        class mapWithSizeOne {

            @Test
            public void expectedEmptyInt() {
                TESTER.assertFails(ONE, mapWithSize(0), "\nExpected: java.util.Map size <0>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>");
            }

            @Test
            public void expectedEmptyLong() {
                TESTER.assertFails(ONE, mapWithSize(0L), "\nExpected: java.util.Map size <0>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>");
            }

            @Test
            public void expectedOneInt() {
                TESTER.assertValid(ONE, mapWithSize(1));
            }

            @Test
            public void expectedOneLong() {
                TESTER.assertValid(ONE, mapWithSize(1L));
            }

            @Test
            public void expectedTwoInt() {
                TESTER.assertFails(ONE, mapWithSize(2), "\nExpected: java.util.Map size <2>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>");
            }

            @Test
            public void expectedTwoLong() {
                TESTER.assertFails(ONE, mapWithSize(2L), "\nExpected: java.util.Map size <2>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>");
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class mapWithSizeTwo {

            @Test
            public void expectedEmptyInt() {
                TESTER.assertFails(TWO, mapWithSize(0), "\nExpected: java.util.Map size <0>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>");
            }

            @Test
            public void expectedEmptyLong() {
                TESTER.assertFails(TWO, mapWithSize(0L), "\nExpected: java.util.Map size <0>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>");
            }

            @Test
            public void expectedOneInt() {
                TESTER.assertFails(TWO, mapWithSize(1), "\nExpected: java.util.Map size <1>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>");
            }

            @Test
            public void expectedOneLong() {
                TESTER.assertFails(TWO, mapWithSize(1L), "\nExpected: java.util.Map size <1>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>");
            }

            @Test
            public void expectedTwoInt() {
                TESTER.assertValid(TWO, mapWithSize(2));
            }

            @Test
            public void expectedTwoLong() {
                TESTER.assertValid(TWO, mapWithSize(2L));
            }

            @Test
            public void expectedThreeInt() {
                TESTER.assertFails(TWO, mapWithSize(3), "\nExpected: java.util.Map size <3>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>");
            }

            @Test
            public void expectedThreeLong() {
                TESTER.assertFails(TWO, mapWithSize(3L), "\nExpected: java.util.Map size <3>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>");
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class mapWithSizeTypedNull {

            @Test
            public void expectedEmptyInt() {
                TESTER.assertFails(TYPED_NULL, mapWithSize(0), "\nExpected: java.util.Map size <0>\n      but: was null");
            }

            @Test
            public void expectedEmptyLong() {
                TESTER.assertFails(TYPED_NULL, mapWithSize(0L), "\nExpected: java.util.Map size <0>\n      but: was null");
            }

            @Test
            public void expectedOneInt() {
                TESTER.assertFails(TYPED_NULL, mapWithSize(1), "\nExpected: java.util.Map size <1>\n      but: was null");
            }

            @Test
            public void expectedOneLong() {
                TESTER.assertFails(TYPED_NULL, mapWithSize(1L), "\nExpected: java.util.Map size <1>\n      but: was null");
            }

        }

    }

    @Nested
    @DisplayName("Empty Map tests")
    class emptyMap {

        @Test
        public void empty() {
            TESTER.assertValid(EMPTY, emptyMap());
        }

        @Test
        public void nullTyped() {
            TESTER.assertFails(TYPED_NULL, emptyMap(), "\nExpected: empty java.util.Map\n      but: was null");
        }

        @Test
        public void one() {
            TESTER.assertFails(ONE, emptyMap(), "\nExpected: empty java.util.Map\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>");
        }

    }

    @Nested
    @DisplayName("Null Or Empty Map tests")
    class nullOrEmptyMap {

        @Test
        public void empty() {
            TESTER.assertValid(EMPTY, nullOrEmptyMap());
        }

        @Test
        public void nullTyped() {
            TESTER.assertValid(TYPED_NULL, nullOrEmptyMap());
        }

        @Test
        public void one() {
            TESTER.assertFails(ONE, nullOrEmptyMap(), "\nExpected: (null or empty java.util.Map)\n      but: was <{alphaKey=alphaValue}>");
        }

    }

}
