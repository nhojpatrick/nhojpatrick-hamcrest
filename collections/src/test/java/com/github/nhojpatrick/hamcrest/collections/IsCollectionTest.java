package com.github.nhojpatrick.hamcrest.collections;

import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.github.nhojpatrick.hamcrest.collections.IsCollection.collectionWithSize;
import static com.github.nhojpatrick.hamcrest.collections.IsCollection.emptyCollection;
import static com.github.nhojpatrick.hamcrest.collections.IsCollection.nullOrEmptyCollection;

public class IsCollectionTest {

    private static final MatcherObjectTester<Collection> TESTER = new MatcherObjectTester<>();

    private static final Collection<String> TYPED_NULL = null;
    private static final Collection<String> EMPTY = new ArrayList<>();
    private static final Collection<String> ONE = Arrays.asList("alpha");
    private static final Collection<String> TWO = Arrays.asList("alpha", "bravo");

    @Nested
    @DisplayName("Collection Size tests")
    class collectionWithSize {

        @Nested
        @DisplayName("Empty input")
        class collectionWithSizeEmpty {

            @Test
            public void expectedEmptyInt() {
                TESTER.assertValid(EMPTY, collectionWithSize(0));
            }

            @Test
            public void expectedEmptyLong() {
                TESTER.assertValid(EMPTY, collectionWithSize(0L));
            }

            @Test
            public void expectedOneInt() {
                TESTER.assertFails(EMPTY, collectionWithSize(1), "\nExpected: java.util.Collection size <1>\n      but: was java.util.Collection size <0> <[]>");
            }

            @Test
            public void expectedOneLong() {
                TESTER.assertFails(EMPTY, collectionWithSize(1L), "\nExpected: java.util.Collection size <1>\n      but: was java.util.Collection size <0> <[]>");
            }

        }

        @Nested
        @DisplayName("One entry input")
        class collectionWithSizeOne {

            @Test
            public void expectedEmptyInt() {
                TESTER.assertFails(ONE, collectionWithSize(0), "\nExpected: java.util.Collection size <0>\n      but: was java.util.Collection size <1> <[alpha]>");
            }

            @Test
            public void expectedEmptyLong() {
                TESTER.assertFails(ONE, collectionWithSize(0L), "\nExpected: java.util.Collection size <0>\n      but: was java.util.Collection size <1> <[alpha]>");
            }

            @Test
            public void expectedOneInt() {
                TESTER.assertValid(ONE, collectionWithSize(1));
            }

            @Test
            public void expectedOneLong() {
                TESTER.assertValid(ONE, collectionWithSize(1L));
            }

            @Test
            public void expectedTwoInt() {
                TESTER.assertFails(ONE, collectionWithSize(2), "\nExpected: java.util.Collection size <2>\n      but: was java.util.Collection size <1> <[alpha]>");
            }

            @Test
            public void expectedTwoLong() {
                TESTER.assertFails(ONE, collectionWithSize(2L), "\nExpected: java.util.Collection size <2>\n      but: was java.util.Collection size <1> <[alpha]>");
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class collectionWithSizeTwo {

            @Test
            public void expectedEmptyInt() {
                TESTER.assertFails(TWO, collectionWithSize(0), "\nExpected: java.util.Collection size <0>\n      but: was java.util.Collection size <2> <[alpha, bravo]>");
            }

            @Test
            public void expectedEmptyLong() {
                TESTER.assertFails(TWO, collectionWithSize(0L), "\nExpected: java.util.Collection size <0>\n      but: was java.util.Collection size <2> <[alpha, bravo]>");
            }

            @Test
            public void expectedOneInt() {
                TESTER.assertFails(TWO, collectionWithSize(1), "\nExpected: java.util.Collection size <1>\n      but: was java.util.Collection size <2> <[alpha, bravo]>");
            }

            @Test
            public void expectedOneLong() {
                TESTER.assertFails(TWO, collectionWithSize(1L), "\nExpected: java.util.Collection size <1>\n      but: was java.util.Collection size <2> <[alpha, bravo]>");
            }

            @Test
            public void expectedTwoInt() {
                TESTER.assertValid(TWO, collectionWithSize(2));
            }

            @Test
            public void expectedTwoLong() {
                TESTER.assertValid(TWO, collectionWithSize(2L));
            }

            @Test
            public void expectedThreeInt() {
                TESTER.assertFails(TWO, collectionWithSize(3), "\nExpected: java.util.Collection size <3>\n      but: was java.util.Collection size <2> <[alpha, bravo]>");
            }

            @Test
            public void expectedThreeLong() {
                TESTER.assertFails(TWO, collectionWithSize(3L), "\nExpected: java.util.Collection size <3>\n      but: was java.util.Collection size <2> <[alpha, bravo]>");
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class collectionWithSizeTypedNull {

            @Test
            public void expectedEmptyInt() {
                TESTER.assertFails(TYPED_NULL, collectionWithSize(0), "\nExpected: java.util.Collection size <0>\n      but: was null");
            }

            @Test
            public void expectedEmptyLong() {
                TESTER.assertFails(TYPED_NULL, collectionWithSize(0L), "\nExpected: java.util.Collection size <0>\n      but: was null");
            }

            @Test
            public void expectedOneInt() {
                TESTER.assertFails(TYPED_NULL, collectionWithSize(1), "\nExpected: java.util.Collection size <1>\n      but: was null");
            }

            @Test
            public void expectedOneLong() {
                TESTER.assertFails(TYPED_NULL, collectionWithSize(1L), "\nExpected: java.util.Collection size <1>\n      but: was null");
            }

        }

    }

    @Nested
    @DisplayName("Empty Collection tests")
    class emptyCollection {

        @Test
        public void empty() {
            TESTER.assertValid(EMPTY, emptyCollection());
        }

        @Test
        public void nullTyped() {
            TESTER.assertFails(TYPED_NULL, emptyCollection(), "\nExpected: empty java.util.Collection\n      but: was null");
        }

        @Test
        public void one() {
            TESTER.assertFails(ONE, emptyCollection(), "\nExpected: empty java.util.Collection\n      but: was java.util.Collection size <1> <[alpha]>");
        }

    }

    @Nested
    @DisplayName("Null Or Empty Collection tests")
    class nullOrEmptyCollection {

        @Test
        public void empty() {
            TESTER.assertValid(EMPTY, nullOrEmptyCollection());
        }

        @Test
        public void nullTyped() {
            TESTER.assertValid(TYPED_NULL, nullOrEmptyCollection());
        }

        @Test
        public void one() {
            TESTER.assertFails(ONE, nullOrEmptyCollection(), "\nExpected: (null or empty java.util.Collection)\n      but: was <[alpha]>");
        }

    }

}
