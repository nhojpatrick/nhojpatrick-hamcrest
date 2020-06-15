package com.github.nhojpatrick.hamcrest.collections.tests;

import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.github.nhojpatrick.hamcrest.collections.IsCollection.collectionWithSize;
import static com.github.nhojpatrick.hamcrest.collections.IsCollection.collectionWithSizeGreaterThan;
import static com.github.nhojpatrick.hamcrest.collections.IsCollection.collectionWithSizeGreaterThanOrEqualTo;
import static com.github.nhojpatrick.hamcrest.collections.IsCollection.collectionWithSizeLessThan;
import static com.github.nhojpatrick.hamcrest.collections.IsCollection.collectionWithSizeLessThanOrEqualTo;
import static com.github.nhojpatrick.hamcrest.collections.IsCollection.emptyCollection;
import static com.github.nhojpatrick.hamcrest.collections.IsCollection.nullOrEmptyCollection;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("IsCollection")
public class IsCollectionTest {

    private static final MatcherObjectTester<Collection> TESTER = new MatcherObjectTester<>();

    private static final Collection<String> TYPED_NULL = null;
    private static final Collection<String> EMPTY = new ArrayList<>();
    private static final Collection<String> ONE = Arrays.asList("alpha");
    private static final Collection<String> TWO = Arrays.asList("alpha", "bravo");

    @Nested
    @DisplayName("Size")
    class size {

        @Nested
        @DisplayName("Empty input")
        class sizeEmpty {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(EMPTY, collectionWithSize(0)),
                        () -> TESTER.assertValid(EMPTY, collectionWithSize(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(EMPTY, collectionWithSize(1), "\nExpected: java.util.Collection size <1>\n      but: was java.util.Collection size <0> <[]>"),
                        () -> TESTER.assertFails(EMPTY, collectionWithSize(1L), "\nExpected: java.util.Collection size <1>\n      but: was java.util.Collection size <0> <[]>")
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(ONE, collectionWithSize(0), "\nExpected: java.util.Collection size <0>\n      but: was java.util.Collection size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, collectionWithSize(0L), "\nExpected: java.util.Collection size <0>\n      but: was java.util.Collection size <1> <[alpha]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(ONE, collectionWithSize(1)),
                        () -> TESTER.assertValid(ONE, collectionWithSize(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(ONE, collectionWithSize(2), "\nExpected: java.util.Collection size <2>\n      but: was java.util.Collection size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, collectionWithSize(2L), "\nExpected: java.util.Collection size <2>\n      but: was java.util.Collection size <1> <[alpha]>")
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TWO, collectionWithSize(0), "\nExpected: java.util.Collection size <0>\n      but: was java.util.Collection size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, collectionWithSize(0L), "\nExpected: java.util.Collection size <0>\n      but: was java.util.Collection size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TWO, collectionWithSize(1), "\nExpected: java.util.Collection size <1>\n      but: was java.util.Collection size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, collectionWithSize(1L), "\nExpected: java.util.Collection size <1>\n      but: was java.util.Collection size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(TWO, collectionWithSize(2)),
                        () -> TESTER.assertValid(TWO, collectionWithSize(2L))
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertFails(TWO, collectionWithSize(3), "\nExpected: java.util.Collection size <3>\n      but: was java.util.Collection size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, collectionWithSize(3L), "\nExpected: java.util.Collection size <3>\n      but: was java.util.Collection size <2> <[alpha, bravo]>")
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSize(0), "\nExpected: java.util.Collection size <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSize(0L), "\nExpected: java.util.Collection size <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSize(1), "\nExpected: java.util.Collection size <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSize(1L), "\nExpected: java.util.Collection size <1>\n      but: was null")
                );
            }

        }

    }

    @Nested
    @DisplayName("Size Greater Than")
    class sizeGreaterThan {

        @Nested
        @DisplayName("Empty input")
        class sizeGreaterThanEmpty {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(EMPTY, collectionWithSizeGreaterThan(0), "\nExpected: java.util.Collection size greater than <0>\n      but: was java.util.Collection size <0> <[]>"),
                        () -> TESTER.assertFails(EMPTY, collectionWithSizeGreaterThan(0L), "\nExpected: java.util.Collection size greater than <0>\n      but: was java.util.Collection size <0> <[]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(EMPTY, collectionWithSizeGreaterThan(1), "\nExpected: java.util.Collection size greater than <1>\n      but: was java.util.Collection size <0> <[]>"),
                        () -> TESTER.assertFails(EMPTY, collectionWithSizeGreaterThan(1L), "\nExpected: java.util.Collection size greater than <1>\n      but: was java.util.Collection size <0> <[]>")
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeGreaterThanOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(ONE, collectionWithSizeGreaterThan(0)),
                        () -> TESTER.assertValid(ONE, collectionWithSizeGreaterThan(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(ONE, collectionWithSizeGreaterThan(1), "\nExpected: java.util.Collection size greater than <1>\n      but: was java.util.Collection size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, collectionWithSizeGreaterThan(1L), "\nExpected: java.util.Collection size greater than <1>\n      but: was java.util.Collection size <1> <[alpha]>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(ONE, collectionWithSizeGreaterThan(2), "\nExpected: java.util.Collection size greater than <2>\n      but: was java.util.Collection size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, collectionWithSizeGreaterThan(2L), "\nExpected: java.util.Collection size greater than <2>\n      but: was java.util.Collection size <1> <[alpha]>")
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeGreaterThanTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(TWO, collectionWithSizeGreaterThan(0)),
                        () -> TESTER.assertValid(TWO, collectionWithSizeGreaterThan(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(TWO, collectionWithSizeGreaterThan(1)),
                        () -> TESTER.assertValid(TWO, collectionWithSizeGreaterThan(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(TWO, collectionWithSizeGreaterThan(2), "\nExpected: java.util.Collection size greater than <2>\n      but: was java.util.Collection size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, collectionWithSizeGreaterThan(2L), "\nExpected: java.util.Collection size greater than <2>\n      but: was java.util.Collection size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertFails(TWO, collectionWithSizeGreaterThan(3), "\nExpected: java.util.Collection size greater than <3>\n      but: was java.util.Collection size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, collectionWithSizeGreaterThan(3L), "\nExpected: java.util.Collection size greater than <3>\n      but: was java.util.Collection size <2> <[alpha, bravo]>")
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeGreaterThanTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSizeGreaterThan(0), "\nExpected: java.util.Collection size greater than <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSizeGreaterThan(0L), "\nExpected: java.util.Collection size greater than <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSizeGreaterThan(1), "\nExpected: java.util.Collection size greater than <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSizeGreaterThan(1L), "\nExpected: java.util.Collection size greater than <1>\n      but: was null")
                );
            }

        }

    }

    @Nested
    @DisplayName("Size Greater Than Or Equal To")
    class sizeGreaterThanOrEqualTo {

        @Nested
        @DisplayName("Empty input")
        class sizeGreaterThanOrEqualToEmpty {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(EMPTY, collectionWithSizeGreaterThanOrEqualTo(0)),
                        () -> TESTER.assertValid(EMPTY, collectionWithSizeGreaterThanOrEqualTo(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(EMPTY, collectionWithSizeGreaterThanOrEqualTo(1), "\nExpected: java.util.Collection size greater than or equal to <1>\n      but: was java.util.Collection size <0> <[]>"),
                        () -> TESTER.assertFails(EMPTY, collectionWithSizeGreaterThanOrEqualTo(1L), "\nExpected: java.util.Collection size greater than or equal to <1>\n      but: was java.util.Collection size <0> <[]>")
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeGreaterThanOrEqualToOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(ONE, collectionWithSizeGreaterThanOrEqualTo(0)),
                        () -> TESTER.assertValid(ONE, collectionWithSizeGreaterThanOrEqualTo(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(ONE, collectionWithSizeGreaterThanOrEqualTo(1)),
                        () -> TESTER.assertValid(ONE, collectionWithSizeGreaterThanOrEqualTo(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(ONE, collectionWithSizeGreaterThanOrEqualTo(2), "\nExpected: java.util.Collection size greater than or equal to <2>\n      but: was java.util.Collection size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, collectionWithSizeGreaterThanOrEqualTo(2L), "\nExpected: java.util.Collection size greater than or equal to <2>\n      but: was java.util.Collection size <1> <[alpha]>")
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeGreaterThanOrEqualToTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(TWO, collectionWithSizeGreaterThanOrEqualTo(0)),
                        () -> TESTER.assertValid(TWO, collectionWithSizeGreaterThanOrEqualTo(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(TWO, collectionWithSizeGreaterThanOrEqualTo(1)),
                        () -> TESTER.assertValid(TWO, collectionWithSizeGreaterThanOrEqualTo(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(TWO, collectionWithSizeGreaterThanOrEqualTo(2)),
                        () -> TESTER.assertValid(TWO, collectionWithSizeGreaterThanOrEqualTo(2L))
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertFails(TWO, collectionWithSizeGreaterThanOrEqualTo(3), "\nExpected: java.util.Collection size greater than or equal to <3>\n      but: was java.util.Collection size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, collectionWithSizeGreaterThanOrEqualTo(3L), "\nExpected: java.util.Collection size greater than or equal to <3>\n      but: was java.util.Collection size <2> <[alpha, bravo]>")
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeGreaterThanOrEqualToTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSizeGreaterThanOrEqualTo(0), "\nExpected: java.util.Collection size greater than or equal to <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSizeGreaterThanOrEqualTo(0L), "\nExpected: java.util.Collection size greater than or equal to <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSizeGreaterThanOrEqualTo(1), "\nExpected: java.util.Collection size greater than or equal to <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSizeGreaterThanOrEqualTo(1L), "\nExpected: java.util.Collection size greater than or equal to <1>\n      but: was null")
                );
            }

        }

    }

    @Nested
    @DisplayName("Size Less Than")
    class sizeLessThan {

        @Nested
        @DisplayName("Empty input")
        class sizeLessThanEmpty {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(EMPTY, collectionWithSizeLessThan(0), "\nExpected: java.util.Collection size less than <0>\n      but: was java.util.Collection size <0> <[]>"),
                        () -> TESTER.assertFails(EMPTY, collectionWithSizeLessThan(0L), "\nExpected: java.util.Collection size less than <0>\n      but: was java.util.Collection size <0> <[]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(EMPTY, collectionWithSizeLessThan(1)),
                        () -> TESTER.assertValid(EMPTY, collectionWithSizeLessThan(1L))
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeLessThanOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(ONE, collectionWithSizeLessThan(0), "\nExpected: java.util.Collection size less than <0>\n      but: was java.util.Collection size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, collectionWithSizeLessThan(0L), "\nExpected: java.util.Collection size less than <0>\n      but: was java.util.Collection size <1> <[alpha]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(ONE, collectionWithSizeLessThan(1), "\nExpected: java.util.Collection size less than <1>\n      but: was java.util.Collection size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, collectionWithSizeLessThan(1L), "\nExpected: java.util.Collection size less than <1>\n      but: was java.util.Collection size <1> <[alpha]>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(ONE, collectionWithSizeLessThan(2)),
                        () -> TESTER.assertValid(ONE, collectionWithSizeLessThan(2L))
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeLessThanTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TWO, collectionWithSizeLessThan(0), "\nExpected: java.util.Collection size less than <0>\n      but: was java.util.Collection size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, collectionWithSizeLessThan(0L), "\nExpected: java.util.Collection size less than <0>\n      but: was java.util.Collection size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TWO, collectionWithSizeLessThan(1), "\nExpected: java.util.Collection size less than <1>\n      but: was java.util.Collection size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, collectionWithSizeLessThan(1L), "\nExpected: java.util.Collection size less than <1>\n      but: was java.util.Collection size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(TWO, collectionWithSizeLessThan(2), "\nExpected: java.util.Collection size less than <2>\n      but: was java.util.Collection size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, collectionWithSizeLessThan(2L), "\nExpected: java.util.Collection size less than <2>\n      but: was java.util.Collection size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertValid(TWO, collectionWithSizeLessThan(3)),
                        () -> TESTER.assertValid(TWO, collectionWithSizeLessThan(3L))
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeLessThanTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSizeLessThan(0), "\nExpected: java.util.Collection size less than <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSizeLessThan(0L), "\nExpected: java.util.Collection size less than <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSizeLessThan(1), "\nExpected: java.util.Collection size less than <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSizeLessThan(1L), "\nExpected: java.util.Collection size less than <1>\n      but: was null")
                );
            }

        }

    }

    @Nested
    @DisplayName("Size Less Than Or Equal To")
    class sizeLessThanOrEqualTo {

        @Nested
        @DisplayName("Empty input")
        class sizeLessThanOrEqualToEmpty {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(EMPTY, collectionWithSizeLessThanOrEqualTo(0)),
                        () -> TESTER.assertValid(EMPTY, collectionWithSizeLessThanOrEqualTo(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(EMPTY, collectionWithSizeLessThanOrEqualTo(1)),
                        () -> TESTER.assertValid(EMPTY, collectionWithSizeLessThanOrEqualTo(1L))
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeLessThanOrEqualToOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(ONE, collectionWithSizeLessThanOrEqualTo(0), "\nExpected: java.util.Collection size less than or equal to <0>\n      but: was java.util.Collection size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, collectionWithSizeLessThanOrEqualTo(0L), "\nExpected: java.util.Collection size less than or equal to <0>\n      but: was java.util.Collection size <1> <[alpha]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(ONE, collectionWithSizeLessThanOrEqualTo(1)),
                        () -> TESTER.assertValid(ONE, collectionWithSizeLessThanOrEqualTo(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(ONE, collectionWithSizeLessThanOrEqualTo(2)),
                        () -> TESTER.assertValid(ONE, collectionWithSizeLessThanOrEqualTo(2L))
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeLessThanOrEqualToTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TWO, collectionWithSizeLessThanOrEqualTo(0), "\nExpected: java.util.Collection size less than or equal to <0>\n      but: was java.util.Collection size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, collectionWithSizeLessThanOrEqualTo(0L), "\nExpected: java.util.Collection size less than or equal to <0>\n      but: was java.util.Collection size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TWO, collectionWithSizeLessThanOrEqualTo(1), "\nExpected: java.util.Collection size less than or equal to <1>\n      but: was java.util.Collection size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, collectionWithSizeLessThanOrEqualTo(1L), "\nExpected: java.util.Collection size less than or equal to <1>\n      but: was java.util.Collection size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(TWO, collectionWithSizeLessThanOrEqualTo(2)),
                        () -> TESTER.assertValid(TWO, collectionWithSizeLessThanOrEqualTo(2L))
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertValid(TWO, collectionWithSizeLessThanOrEqualTo(3)),
                        () -> TESTER.assertValid(TWO, collectionWithSizeLessThanOrEqualTo(3L))
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeLessThanOrEqualToTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSizeLessThanOrEqualTo(0), "\nExpected: java.util.Collection size less than or equal to <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSizeLessThanOrEqualTo(0L), "\nExpected: java.util.Collection size less than or equal to <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSizeLessThanOrEqualTo(1), "\nExpected: java.util.Collection size less than or equal to <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, collectionWithSizeLessThanOrEqualTo(1L), "\nExpected: java.util.Collection size less than or equal to <1>\n      but: was null")
                );
            }

        }

    }

    @Nested
    @DisplayName("Empty")
    class empty {

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
    @DisplayName("Null Or Empty")
    class nullOrEmpty {

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
