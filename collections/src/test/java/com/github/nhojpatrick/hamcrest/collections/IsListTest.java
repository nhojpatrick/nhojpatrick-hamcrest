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
import static com.github.nhojpatrick.hamcrest.collections.IsList.listWithSizeGreaterThan;
import static com.github.nhojpatrick.hamcrest.collections.IsList.listWithSizeGreaterThanOrEqualTo;
import static com.github.nhojpatrick.hamcrest.collections.IsList.listWithSizeLessThan;
import static com.github.nhojpatrick.hamcrest.collections.IsList.listWithSizeLessThanOrEqualTo;
import static com.github.nhojpatrick.hamcrest.collections.IsList.nullOrEmptyList;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("IsList")
public class IsListTest {

    private static final MatcherTypedTester<List> TESTER = new MatcherTypedTester<>();

    private static final List<String> TYPED_NULL = null;
    private static final List<String> EMPTY = new ArrayList<>();
    private static final List<String> ONE = Arrays.asList("alpha");
    private static final List<String> TWO = Arrays.asList("alpha", "bravo");

    @Nested
    @DisplayName("Size")
    class size {

        @Nested
        @DisplayName("Empty input")
        class sizeEmpty {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(EMPTY, listWithSize(0)),
                        () -> TESTER.assertValid(EMPTY, listWithSize(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(EMPTY, listWithSize(1), "\nExpected: java.util.List size <1>\n      but: was java.util.List size <0> <[]>"),
                        () -> TESTER.assertFails(EMPTY, listWithSize(1L), "\nExpected: java.util.List size <1>\n      but: was java.util.List size <0> <[]>")
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(ONE, listWithSize(0), "\nExpected: java.util.List size <0>\n      but: was java.util.List size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, listWithSize(0L), "\nExpected: java.util.List size <0>\n      but: was java.util.List size <1> <[alpha]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(ONE, listWithSize(1)),
                        () -> TESTER.assertValid(ONE, listWithSize(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(ONE, listWithSize(2), "\nExpected: java.util.List size <2>\n      but: was java.util.List size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, listWithSize(2L), "\nExpected: java.util.List size <2>\n      but: was java.util.List size <1> <[alpha]>")
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TWO, listWithSize(0), "\nExpected: java.util.List size <0>\n      but: was java.util.List size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, listWithSize(0L), "\nExpected: java.util.List size <0>\n      but: was java.util.List size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TWO, listWithSize(1), "\nExpected: java.util.List size <1>\n      but: was java.util.List size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, listWithSize(1L), "\nExpected: java.util.List size <1>\n      but: was java.util.List size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(TWO, listWithSize(2)),
                        () -> TESTER.assertValid(TWO, listWithSize(2L))
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertFails(TWO, listWithSize(3), "\nExpected: java.util.List size <3>\n      but: was java.util.List size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, listWithSize(3L), "\nExpected: java.util.List size <3>\n      but: was java.util.List size <2> <[alpha, bravo]>")
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, listWithSize(0), "\nExpected: java.util.List size <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, listWithSize(0L), "\nExpected: java.util.List size <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, listWithSize(1), "\nExpected: java.util.List size <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, listWithSize(1L), "\nExpected: java.util.List size <1>\n      but: was null")
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
                        () -> TESTER.assertFails(EMPTY, listWithSizeGreaterThan(0), "\nExpected: java.util.List size greater than <0>\n      but: was java.util.List size <0> <[]>"),
                        () -> TESTER.assertFails(EMPTY, listWithSizeGreaterThan(0L), "\nExpected: java.util.List size greater than <0>\n      but: was java.util.List size <0> <[]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(EMPTY, listWithSizeGreaterThan(1), "\nExpected: java.util.List size greater than <1>\n      but: was java.util.List size <0> <[]>"),
                        () -> TESTER.assertFails(EMPTY, listWithSizeGreaterThan(1L), "\nExpected: java.util.List size greater than <1>\n      but: was java.util.List size <0> <[]>")
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeGreaterThanOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(ONE, listWithSizeGreaterThan(0)),
                        () -> TESTER.assertValid(ONE, listWithSizeGreaterThan(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(ONE, listWithSizeGreaterThan(1), "\nExpected: java.util.List size greater than <1>\n      but: was java.util.List size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, listWithSizeGreaterThan(1L), "\nExpected: java.util.List size greater than <1>\n      but: was java.util.List size <1> <[alpha]>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(ONE, listWithSizeGreaterThan(2), "\nExpected: java.util.List size greater than <2>\n      but: was java.util.List size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, listWithSizeGreaterThan(2L), "\nExpected: java.util.List size greater than <2>\n      but: was java.util.List size <1> <[alpha]>")
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeGreaterThanTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(TWO, listWithSizeGreaterThan(0)),
                        () -> TESTER.assertValid(TWO, listWithSizeGreaterThan(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(TWO, listWithSizeGreaterThan(1)),
                        () -> TESTER.assertValid(TWO, listWithSizeGreaterThan(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(TWO, listWithSizeGreaterThan(2), "\nExpected: java.util.List size greater than <2>\n      but: was java.util.List size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, listWithSizeGreaterThan(2L), "\nExpected: java.util.List size greater than <2>\n      but: was java.util.List size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertFails(TWO, listWithSizeGreaterThan(3), "\nExpected: java.util.List size greater than <3>\n      but: was java.util.List size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, listWithSizeGreaterThan(3L), "\nExpected: java.util.List size greater than <3>\n      but: was java.util.List size <2> <[alpha, bravo]>")
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeGreaterThanTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, listWithSizeGreaterThan(0), "\nExpected: java.util.List size greater than <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, listWithSizeGreaterThan(0L), "\nExpected: java.util.List size greater than <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, listWithSizeGreaterThan(1), "\nExpected: java.util.List size greater than <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, listWithSizeGreaterThan(1L), "\nExpected: java.util.List size greater than <1>\n      but: was null")
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
                        () -> TESTER.assertValid(EMPTY, listWithSizeGreaterThanOrEqualTo(0)),
                        () -> TESTER.assertValid(EMPTY, listWithSizeGreaterThanOrEqualTo(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(EMPTY, listWithSizeGreaterThanOrEqualTo(1), "\nExpected: java.util.List size greater than or equal to <1>\n      but: was java.util.List size <0> <[]>"),
                        () -> TESTER.assertFails(EMPTY, listWithSizeGreaterThanOrEqualTo(1L), "\nExpected: java.util.List size greater than or equal to <1>\n      but: was java.util.List size <0> <[]>")
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeGreaterThanOrEqualToOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(ONE, listWithSizeGreaterThanOrEqualTo(0)),
                        () -> TESTER.assertValid(ONE, listWithSizeGreaterThanOrEqualTo(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(ONE, listWithSizeGreaterThanOrEqualTo(1)),
                        () -> TESTER.assertValid(ONE, listWithSizeGreaterThanOrEqualTo(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(ONE, listWithSizeGreaterThanOrEqualTo(2), "\nExpected: java.util.List size greater than or equal to <2>\n      but: was java.util.List size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, listWithSizeGreaterThanOrEqualTo(2L), "\nExpected: java.util.List size greater than or equal to <2>\n      but: was java.util.List size <1> <[alpha]>")
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeGreaterThanOrEqualToTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(TWO, listWithSizeGreaterThanOrEqualTo(0)),
                        () -> TESTER.assertValid(TWO, listWithSizeGreaterThanOrEqualTo(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(TWO, listWithSizeGreaterThanOrEqualTo(1)),
                        () -> TESTER.assertValid(TWO, listWithSizeGreaterThanOrEqualTo(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(TWO, listWithSizeGreaterThanOrEqualTo(2)),
                        () -> TESTER.assertValid(TWO, listWithSizeGreaterThanOrEqualTo(2L))
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertFails(TWO, listWithSizeGreaterThanOrEqualTo(3), "\nExpected: java.util.List size greater than or equal to <3>\n      but: was java.util.List size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, listWithSizeGreaterThanOrEqualTo(3L), "\nExpected: java.util.List size greater than or equal to <3>\n      but: was java.util.List size <2> <[alpha, bravo]>")
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeGreaterThanOrEqualToTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, listWithSizeGreaterThanOrEqualTo(0), "\nExpected: java.util.List size greater than or equal to <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, listWithSizeGreaterThanOrEqualTo(0L), "\nExpected: java.util.List size greater than or equal to <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, listWithSizeGreaterThanOrEqualTo(1), "\nExpected: java.util.List size greater than or equal to <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, listWithSizeGreaterThanOrEqualTo(1L), "\nExpected: java.util.List size greater than or equal to <1>\n      but: was null")
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
                        () -> TESTER.assertFails(EMPTY, listWithSizeLessThan(0), "\nExpected: java.util.List size less than <0>\n      but: was java.util.List size <0> <[]>"),
                        () -> TESTER.assertFails(EMPTY, listWithSizeLessThan(0L), "\nExpected: java.util.List size less than <0>\n      but: was java.util.List size <0> <[]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(EMPTY, listWithSizeLessThan(1)),
                        () -> TESTER.assertValid(EMPTY, listWithSizeLessThan(1L))
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeLessThanOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(ONE, listWithSizeLessThan(0), "\nExpected: java.util.List size less than <0>\n      but: was java.util.List size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, listWithSizeLessThan(0L), "\nExpected: java.util.List size less than <0>\n      but: was java.util.List size <1> <[alpha]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(ONE, listWithSizeLessThan(1), "\nExpected: java.util.List size less than <1>\n      but: was java.util.List size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, listWithSizeLessThan(1L), "\nExpected: java.util.List size less than <1>\n      but: was java.util.List size <1> <[alpha]>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(ONE, listWithSizeLessThan(2)),
                        () -> TESTER.assertValid(ONE, listWithSizeLessThan(2L))
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeLessThanTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TWO, listWithSizeLessThan(0), "\nExpected: java.util.List size less than <0>\n      but: was java.util.List size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, listWithSizeLessThan(0L), "\nExpected: java.util.List size less than <0>\n      but: was java.util.List size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TWO, listWithSizeLessThan(1), "\nExpected: java.util.List size less than <1>\n      but: was java.util.List size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, listWithSizeLessThan(1L), "\nExpected: java.util.List size less than <1>\n      but: was java.util.List size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(TWO, listWithSizeLessThan(2), "\nExpected: java.util.List size less than <2>\n      but: was java.util.List size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, listWithSizeLessThan(2L), "\nExpected: java.util.List size less than <2>\n      but: was java.util.List size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertValid(TWO, listWithSizeLessThan(3)),
                        () -> TESTER.assertValid(TWO, listWithSizeLessThan(3L))
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeLessThanTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, listWithSizeLessThan(0), "\nExpected: java.util.List size less than <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, listWithSizeLessThan(0L), "\nExpected: java.util.List size less than <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, listWithSizeLessThan(1), "\nExpected: java.util.List size less than <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, listWithSizeLessThan(1L), "\nExpected: java.util.List size less than <1>\n      but: was null")
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
                        () -> TESTER.assertValid(EMPTY, listWithSizeLessThanOrEqualTo(0)),
                        () -> TESTER.assertValid(EMPTY, listWithSizeLessThanOrEqualTo(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(EMPTY, listWithSizeLessThanOrEqualTo(1)),
                        () -> TESTER.assertValid(EMPTY, listWithSizeLessThanOrEqualTo(1L))
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeLessThanOrEqualToOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(ONE, listWithSizeLessThanOrEqualTo(0), "\nExpected: java.util.List size less than or equal to <0>\n      but: was java.util.List size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, listWithSizeLessThanOrEqualTo(0L), "\nExpected: java.util.List size less than or equal to <0>\n      but: was java.util.List size <1> <[alpha]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(ONE, listWithSizeLessThanOrEqualTo(1)),
                        () -> TESTER.assertValid(ONE, listWithSizeLessThanOrEqualTo(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(ONE, listWithSizeLessThanOrEqualTo(2)),
                        () -> TESTER.assertValid(ONE, listWithSizeLessThanOrEqualTo(2L))
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeLessThanOrEqualToTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TWO, listWithSizeLessThanOrEqualTo(0), "\nExpected: java.util.List size less than or equal to <0>\n      but: was java.util.List size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, listWithSizeLessThanOrEqualTo(0L), "\nExpected: java.util.List size less than or equal to <0>\n      but: was java.util.List size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TWO, listWithSizeLessThanOrEqualTo(1), "\nExpected: java.util.List size less than or equal to <1>\n      but: was java.util.List size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, listWithSizeLessThanOrEqualTo(1L), "\nExpected: java.util.List size less than or equal to <1>\n      but: was java.util.List size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(TWO, listWithSizeLessThanOrEqualTo(2)),
                        () -> TESTER.assertValid(TWO, listWithSizeLessThanOrEqualTo(2L))
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertValid(TWO, listWithSizeLessThanOrEqualTo(3)),
                        () -> TESTER.assertValid(TWO, listWithSizeLessThanOrEqualTo(3L))
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeLessThanOrEqualToTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, listWithSizeLessThanOrEqualTo(0), "\nExpected: java.util.List size less than or equal to <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, listWithSizeLessThanOrEqualTo(0L), "\nExpected: java.util.List size less than or equal to <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, listWithSizeLessThanOrEqualTo(1), "\nExpected: java.util.List size less than or equal to <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, listWithSizeLessThanOrEqualTo(1L), "\nExpected: java.util.List size less than or equal to <1>\n      but: was null")
                );
            }

        }

    }

    @Nested
    @DisplayName("Empty")
    class empty {

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
    @DisplayName("Null Or Empty")
    class nullOrEmpty {

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
