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
import static com.github.nhojpatrick.hamcrest.collections.IsSet.setWithSizeGreaterThan;
import static com.github.nhojpatrick.hamcrest.collections.IsSet.setWithSizeGreaterThanOrEqualTo;
import static com.github.nhojpatrick.hamcrest.collections.IsSet.setWithSizeLessThan;
import static com.github.nhojpatrick.hamcrest.collections.IsSet.setWithSizeLessThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("IsSet")
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
    @DisplayName("Size")
    class size {

        @Nested
        @DisplayName("Empty input")
        class sizeEmpty {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(EMPTY, setWithSize(0)),
                        () -> TESTER.assertValid(EMPTY, setWithSize(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(EMPTY, setWithSize(1), "\nExpected: java.util.Set size <1>\n      but: was java.util.Set size <0> <[]>"),
                        () -> TESTER.assertFails(EMPTY, setWithSize(1L), "\nExpected: java.util.Set size <1>\n      but: was java.util.Set size <0> <[]>")
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(ONE, setWithSize(0), "\nExpected: java.util.Set size <0>\n      but: was java.util.Set size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, setWithSize(0L), "\nExpected: java.util.Set size <0>\n      but: was java.util.Set size <1> <[alpha]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(ONE, setWithSize(1)),
                        () -> TESTER.assertValid(ONE, setWithSize(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(ONE, setWithSize(2), "\nExpected: java.util.Set size <2>\n      but: was java.util.Set size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, setWithSize(2L), "\nExpected: java.util.Set size <2>\n      but: was java.util.Set size <1> <[alpha]>")
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TWO, setWithSize(0), "\nExpected: java.util.Set size <0>\n      but: was java.util.Set size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, setWithSize(0L), "\nExpected: java.util.Set size <0>\n      but: was java.util.Set size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TWO, setWithSize(1), "\nExpected: java.util.Set size <1>\n      but: was java.util.Set size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, setWithSize(1L), "\nExpected: java.util.Set size <1>\n      but: was java.util.Set size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(TWO, setWithSize(2)),
                        () -> TESTER.assertValid(TWO, setWithSize(2L))
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertFails(TWO, setWithSize(3), "\nExpected: java.util.Set size <3>\n      but: was java.util.Set size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, setWithSize(3L), "\nExpected: java.util.Set size <3>\n      but: was java.util.Set size <2> <[alpha, bravo]>")
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, setWithSize(0), "\nExpected: java.util.Set size <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, setWithSize(0L), "\nExpected: java.util.Set size <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, setWithSize(1), "\nExpected: java.util.Set size <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, setWithSize(1L), "\nExpected: java.util.Set size <1>\n      but: was null")
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
                        () -> TESTER.assertFails(EMPTY, setWithSizeGreaterThan(0), "\nExpected: java.util.Set size greater than <0>\n      but: was java.util.Set size <0> <[]>"),
                        () -> TESTER.assertFails(EMPTY, setWithSizeGreaterThan(0L), "\nExpected: java.util.Set size greater than <0>\n      but: was java.util.Set size <0> <[]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(EMPTY, setWithSizeGreaterThan(1), "\nExpected: java.util.Set size greater than <1>\n      but: was java.util.Set size <0> <[]>"),
                        () -> TESTER.assertFails(EMPTY, setWithSizeGreaterThan(1L), "\nExpected: java.util.Set size greater than <1>\n      but: was java.util.Set size <0> <[]>")
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeGreaterThanOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(ONE, setWithSizeGreaterThan(0)),
                        () -> TESTER.assertValid(ONE, setWithSizeGreaterThan(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(ONE, setWithSizeGreaterThan(1), "\nExpected: java.util.Set size greater than <1>\n      but: was java.util.Set size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, setWithSizeGreaterThan(1L), "\nExpected: java.util.Set size greater than <1>\n      but: was java.util.Set size <1> <[alpha]>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(ONE, setWithSizeGreaterThan(2), "\nExpected: java.util.Set size greater than <2>\n      but: was java.util.Set size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, setWithSizeGreaterThan(2L), "\nExpected: java.util.Set size greater than <2>\n      but: was java.util.Set size <1> <[alpha]>")
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeGreaterThanTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(TWO, setWithSizeGreaterThan(0)),
                        () -> TESTER.assertValid(TWO, setWithSizeGreaterThan(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(TWO, setWithSizeGreaterThan(1)),
                        () -> TESTER.assertValid(TWO, setWithSizeGreaterThan(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(TWO, setWithSizeGreaterThan(2), "\nExpected: java.util.Set size greater than <2>\n      but: was java.util.Set size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, setWithSizeGreaterThan(2L), "\nExpected: java.util.Set size greater than <2>\n      but: was java.util.Set size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertFails(TWO, setWithSizeGreaterThan(3), "\nExpected: java.util.Set size greater than <3>\n      but: was java.util.Set size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, setWithSizeGreaterThan(3L), "\nExpected: java.util.Set size greater than <3>\n      but: was java.util.Set size <2> <[alpha, bravo]>")
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeGreaterThanTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, setWithSizeGreaterThan(0), "\nExpected: java.util.Set size greater than <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, setWithSizeGreaterThan(0L), "\nExpected: java.util.Set size greater than <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, setWithSizeGreaterThan(1), "\nExpected: java.util.Set size greater than <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, setWithSizeGreaterThan(1L), "\nExpected: java.util.Set size greater than <1>\n      but: was null")
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
                        () -> TESTER.assertValid(EMPTY, setWithSizeGreaterThanOrEqualTo(0)),
                        () -> TESTER.assertValid(EMPTY, setWithSizeGreaterThanOrEqualTo(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(EMPTY, setWithSizeGreaterThanOrEqualTo(1), "\nExpected: java.util.Set size greater than or equal to <1>\n      but: was java.util.Set size <0> <[]>"),
                        () -> TESTER.assertFails(EMPTY, setWithSizeGreaterThanOrEqualTo(1L), "\nExpected: java.util.Set size greater than or equal to <1>\n      but: was java.util.Set size <0> <[]>")
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeGreaterThanOrEqualToOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(ONE, setWithSizeGreaterThanOrEqualTo(0)),
                        () -> TESTER.assertValid(ONE, setWithSizeGreaterThanOrEqualTo(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(ONE, setWithSizeGreaterThanOrEqualTo(1)),
                        () -> TESTER.assertValid(ONE, setWithSizeGreaterThanOrEqualTo(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(ONE, setWithSizeGreaterThanOrEqualTo(2), "\nExpected: java.util.Set size greater than or equal to <2>\n      but: was java.util.Set size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, setWithSizeGreaterThanOrEqualTo(2L), "\nExpected: java.util.Set size greater than or equal to <2>\n      but: was java.util.Set size <1> <[alpha]>")
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeGreaterThanOrEqualToTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(TWO, setWithSizeGreaterThanOrEqualTo(0)),
                        () -> TESTER.assertValid(TWO, setWithSizeGreaterThanOrEqualTo(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(TWO, setWithSizeGreaterThanOrEqualTo(1)),
                        () -> TESTER.assertValid(TWO, setWithSizeGreaterThanOrEqualTo(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(TWO, setWithSizeGreaterThanOrEqualTo(2)),
                        () -> TESTER.assertValid(TWO, setWithSizeGreaterThanOrEqualTo(2L))
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertFails(TWO, setWithSizeGreaterThanOrEqualTo(3), "\nExpected: java.util.Set size greater than or equal to <3>\n      but: was java.util.Set size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, setWithSizeGreaterThanOrEqualTo(3L), "\nExpected: java.util.Set size greater than or equal to <3>\n      but: was java.util.Set size <2> <[alpha, bravo]>")
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeGreaterThanOrEqualToTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, setWithSizeGreaterThanOrEqualTo(0), "\nExpected: java.util.Set size greater than or equal to <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, setWithSizeGreaterThanOrEqualTo(0L), "\nExpected: java.util.Set size greater than or equal to <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, setWithSizeGreaterThanOrEqualTo(1), "\nExpected: java.util.Set size greater than or equal to <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, setWithSizeGreaterThanOrEqualTo(1L), "\nExpected: java.util.Set size greater than or equal to <1>\n      but: was null")
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
                        () -> TESTER.assertFails(EMPTY, setWithSizeLessThan(0), "\nExpected: java.util.Set size less than <0>\n      but: was java.util.Set size <0> <[]>"),
                        () -> TESTER.assertFails(EMPTY, setWithSizeLessThan(0L), "\nExpected: java.util.Set size less than <0>\n      but: was java.util.Set size <0> <[]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(EMPTY, setWithSizeLessThan(1)),
                        () -> TESTER.assertValid(EMPTY, setWithSizeLessThan(1L))
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeLessThanOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(ONE, setWithSizeLessThan(0), "\nExpected: java.util.Set size less than <0>\n      but: was java.util.Set size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, setWithSizeLessThan(0L), "\nExpected: java.util.Set size less than <0>\n      but: was java.util.Set size <1> <[alpha]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(ONE, setWithSizeLessThan(1), "\nExpected: java.util.Set size less than <1>\n      but: was java.util.Set size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, setWithSizeLessThan(1L), "\nExpected: java.util.Set size less than <1>\n      but: was java.util.Set size <1> <[alpha]>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(ONE, setWithSizeLessThan(2)),
                        () -> TESTER.assertValid(ONE, setWithSizeLessThan(2L))
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeLessThanTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TWO, setWithSizeLessThan(0), "\nExpected: java.util.Set size less than <0>\n      but: was java.util.Set size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, setWithSizeLessThan(0L), "\nExpected: java.util.Set size less than <0>\n      but: was java.util.Set size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TWO, setWithSizeLessThan(1), "\nExpected: java.util.Set size less than <1>\n      but: was java.util.Set size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, setWithSizeLessThan(1L), "\nExpected: java.util.Set size less than <1>\n      but: was java.util.Set size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(TWO, setWithSizeLessThan(2), "\nExpected: java.util.Set size less than <2>\n      but: was java.util.Set size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, setWithSizeLessThan(2L), "\nExpected: java.util.Set size less than <2>\n      but: was java.util.Set size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertValid(TWO, setWithSizeLessThan(3)),
                        () -> TESTER.assertValid(TWO, setWithSizeLessThan(3L))
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeLessThanTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, setWithSizeLessThan(0), "\nExpected: java.util.Set size less than <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, setWithSizeLessThan(0L), "\nExpected: java.util.Set size less than <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, setWithSizeLessThan(1), "\nExpected: java.util.Set size less than <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, setWithSizeLessThan(1L), "\nExpected: java.util.Set size less than <1>\n      but: was null")
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
                        () -> TESTER.assertValid(EMPTY, setWithSizeLessThanOrEqualTo(0)),
                        () -> TESTER.assertValid(EMPTY, setWithSizeLessThanOrEqualTo(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(EMPTY, setWithSizeLessThanOrEqualTo(1)),
                        () -> TESTER.assertValid(EMPTY, setWithSizeLessThanOrEqualTo(1L))
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeLessThanOrEqualToOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(ONE, setWithSizeLessThanOrEqualTo(0), "\nExpected: java.util.Set size less than or equal to <0>\n      but: was java.util.Set size <1> <[alpha]>"),
                        () -> TESTER.assertFails(ONE, setWithSizeLessThanOrEqualTo(0L), "\nExpected: java.util.Set size less than or equal to <0>\n      but: was java.util.Set size <1> <[alpha]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(ONE, setWithSizeLessThanOrEqualTo(1)),
                        () -> TESTER.assertValid(ONE, setWithSizeLessThanOrEqualTo(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(ONE, setWithSizeLessThanOrEqualTo(2)),
                        () -> TESTER.assertValid(ONE, setWithSizeLessThanOrEqualTo(2L))
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeLessThanOrEqualToTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TWO, setWithSizeLessThanOrEqualTo(0), "\nExpected: java.util.Set size less than or equal to <0>\n      but: was java.util.Set size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, setWithSizeLessThanOrEqualTo(0L), "\nExpected: java.util.Set size less than or equal to <0>\n      but: was java.util.Set size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TWO, setWithSizeLessThanOrEqualTo(1), "\nExpected: java.util.Set size less than or equal to <1>\n      but: was java.util.Set size <2> <[alpha, bravo]>"),
                        () -> TESTER.assertFails(TWO, setWithSizeLessThanOrEqualTo(1L), "\nExpected: java.util.Set size less than or equal to <1>\n      but: was java.util.Set size <2> <[alpha, bravo]>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(TWO, setWithSizeLessThanOrEqualTo(2)),
                        () -> TESTER.assertValid(TWO, setWithSizeLessThanOrEqualTo(2L))
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertValid(TWO, setWithSizeLessThanOrEqualTo(3)),
                        () -> TESTER.assertValid(TWO, setWithSizeLessThanOrEqualTo(3L))
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeLessThanOrEqualToTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, setWithSizeLessThanOrEqualTo(0), "\nExpected: java.util.Set size less than or equal to <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, setWithSizeLessThanOrEqualTo(0L), "\nExpected: java.util.Set size less than or equal to <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, setWithSizeLessThanOrEqualTo(1), "\nExpected: java.util.Set size less than or equal to <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, setWithSizeLessThanOrEqualTo(1L), "\nExpected: java.util.Set size less than or equal to <1>\n      but: was null")
                );
            }

        }

    }

    @Nested
    @DisplayName("Empty")
    class empty {

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
    @DisplayName("Null Or Empty")
    class nullOrEmpty {

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
