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
import static com.github.nhojpatrick.hamcrest.collections.IsMap.mapWithSizeGreaterThan;
import static com.github.nhojpatrick.hamcrest.collections.IsMap.mapWithSizeGreaterThanOrEqualTo;
import static com.github.nhojpatrick.hamcrest.collections.IsMap.mapWithSizeLessThan;
import static com.github.nhojpatrick.hamcrest.collections.IsMap.mapWithSizeLessThanOrEqualTo;
import static com.github.nhojpatrick.hamcrest.collections.IsMap.nullOrEmptyMap;
import static java.util.stream.Collectors.toMap;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("IsMap")
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
    @DisplayName("Size")
    class size {

        @Nested
        @DisplayName("Empty input")
        class sizeEmpty {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(EMPTY, mapWithSize(0)),
                        () -> TESTER.assertValid(EMPTY, mapWithSize(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(EMPTY, mapWithSize(1), "\nExpected: java.util.Map size <1>\n      but: was java.util.Map size <0> <{}>"),
                        () -> TESTER.assertFails(EMPTY, mapWithSize(1L), "\nExpected: java.util.Map size <1>\n      but: was java.util.Map size <0> <{}>")
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(ONE, mapWithSize(0), "\nExpected: java.util.Map size <0>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>"),
                        () -> TESTER.assertFails(ONE, mapWithSize(0L), "\nExpected: java.util.Map size <0>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(ONE, mapWithSize(1)),
                        () -> TESTER.assertValid(ONE, mapWithSize(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(ONE, mapWithSize(2), "\nExpected: java.util.Map size <2>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>"),
                        () -> TESTER.assertFails(ONE, mapWithSize(2L), "\nExpected: java.util.Map size <2>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>")
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TWO, mapWithSize(0), "\nExpected: java.util.Map size <0>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>"),
                        () -> TESTER.assertFails(TWO, mapWithSize(0L), "\nExpected: java.util.Map size <0>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TWO, mapWithSize(1), "\nExpected: java.util.Map size <1>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>"),
                        () -> TESTER.assertFails(TWO, mapWithSize(1L), "\nExpected: java.util.Map size <1>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(TWO, mapWithSize(2)),
                        () -> TESTER.assertValid(TWO, mapWithSize(2L))
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertFails(TWO, mapWithSize(3), "\nExpected: java.util.Map size <3>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>"),
                        () -> TESTER.assertFails(TWO, mapWithSize(3L), "\nExpected: java.util.Map size <3>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>")
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSize(0), "\nExpected: java.util.Map size <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSize(0L), "\nExpected: java.util.Map size <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSize(1), "\nExpected: java.util.Map size <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSize(1L), "\nExpected: java.util.Map size <1>\n      but: was null")
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
                        () -> TESTER.assertFails(EMPTY, mapWithSizeGreaterThan(0), "\nExpected: java.util.Map size greater than <0>\n      but: was java.util.Map size <0> <{}>"),
                        () -> TESTER.assertFails(EMPTY, mapWithSizeGreaterThan(0L), "\nExpected: java.util.Map size greater than <0>\n      but: was java.util.Map size <0> <{}>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(EMPTY, mapWithSizeGreaterThan(1), "\nExpected: java.util.Map size greater than <1>\n      but: was java.util.Map size <0> <{}>"),
                        () -> TESTER.assertFails(EMPTY, mapWithSizeGreaterThan(1L), "\nExpected: java.util.Map size greater than <1>\n      but: was java.util.Map size <0> <{}>")
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeGreaterThanOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(ONE, mapWithSizeGreaterThan(0)),
                        () -> TESTER.assertValid(ONE, mapWithSizeGreaterThan(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(ONE, mapWithSizeGreaterThan(1), "\nExpected: java.util.Map size greater than <1>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>"),
                        () -> TESTER.assertFails(ONE, mapWithSizeGreaterThan(1L), "\nExpected: java.util.Map size greater than <1>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(ONE, mapWithSizeGreaterThan(2), "\nExpected: java.util.Map size greater than <2>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>"),
                        () -> TESTER.assertFails(ONE, mapWithSizeGreaterThan(2L), "\nExpected: java.util.Map size greater than <2>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>")
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeGreaterThanTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(TWO, mapWithSizeGreaterThan(0)),
                        () -> TESTER.assertValid(TWO, mapWithSizeGreaterThan(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(TWO, mapWithSizeGreaterThan(1)),
                        () -> TESTER.assertValid(TWO, mapWithSizeGreaterThan(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(TWO, mapWithSizeGreaterThan(2), "\nExpected: java.util.Map size greater than <2>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>"),
                        () -> TESTER.assertFails(TWO, mapWithSizeGreaterThan(2L), "\nExpected: java.util.Map size greater than <2>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>")
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertFails(TWO, mapWithSizeGreaterThan(3), "\nExpected: java.util.Map size greater than <3>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>"),
                        () -> TESTER.assertFails(TWO, mapWithSizeGreaterThan(3L), "\nExpected: java.util.Map size greater than <3>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>")
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeGreaterThanTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSizeGreaterThan(0), "\nExpected: java.util.Map size greater than <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSizeGreaterThan(0L), "\nExpected: java.util.Map size greater than <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSizeGreaterThan(1), "\nExpected: java.util.Map size greater than <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSizeGreaterThan(1L), "\nExpected: java.util.Map size greater than <1>\n      but: was null")
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
                        () -> TESTER.assertValid(EMPTY, mapWithSizeGreaterThanOrEqualTo(0)),
                        () -> TESTER.assertValid(EMPTY, mapWithSizeGreaterThanOrEqualTo(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(EMPTY, mapWithSizeGreaterThanOrEqualTo(1), "\nExpected: java.util.Map size greater than or equal to <1>\n      but: was java.util.Map size <0> <{}>"),
                        () -> TESTER.assertFails(EMPTY, mapWithSizeGreaterThanOrEqualTo(1L), "\nExpected: java.util.Map size greater than or equal to <1>\n      but: was java.util.Map size <0> <{}>")
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeGreaterThanOrEqualToOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(ONE, mapWithSizeGreaterThanOrEqualTo(0)),
                        () -> TESTER.assertValid(ONE, mapWithSizeGreaterThanOrEqualTo(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(ONE, mapWithSizeGreaterThanOrEqualTo(1)),
                        () -> TESTER.assertValid(ONE, mapWithSizeGreaterThanOrEqualTo(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(ONE, mapWithSizeGreaterThanOrEqualTo(2), "\nExpected: java.util.Map size greater than or equal to <2>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>"),
                        () -> TESTER.assertFails(ONE, mapWithSizeGreaterThanOrEqualTo(2L), "\nExpected: java.util.Map size greater than or equal to <2>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>")
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeGreaterThanOrEqualToTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertValid(TWO, mapWithSizeGreaterThanOrEqualTo(0)),
                        () -> TESTER.assertValid(TWO, mapWithSizeGreaterThanOrEqualTo(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(TWO, mapWithSizeGreaterThanOrEqualTo(1)),
                        () -> TESTER.assertValid(TWO, mapWithSizeGreaterThanOrEqualTo(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(TWO, mapWithSizeGreaterThanOrEqualTo(2)),
                        () -> TESTER.assertValid(TWO, mapWithSizeGreaterThanOrEqualTo(2L))
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertFails(TWO, mapWithSizeGreaterThanOrEqualTo(3), "\nExpected: java.util.Map size greater than or equal to <3>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>"),
                        () -> TESTER.assertFails(TWO, mapWithSizeGreaterThanOrEqualTo(3L), "\nExpected: java.util.Map size greater than or equal to <3>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>")
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeGreaterThanOrEqualToTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSizeGreaterThanOrEqualTo(0), "\nExpected: java.util.Map size greater than or equal to <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSizeGreaterThanOrEqualTo(0L), "\nExpected: java.util.Map size greater than or equal to <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSizeGreaterThanOrEqualTo(1), "\nExpected: java.util.Map size greater than or equal to <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSizeGreaterThanOrEqualTo(1L), "\nExpected: java.util.Map size greater than or equal to <1>\n      but: was null")
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
                        () -> TESTER.assertFails(EMPTY, mapWithSizeLessThan(0), "\nExpected: java.util.Map size less than <0>\n      but: was java.util.Map size <0> <{}>"),
                        () -> TESTER.assertFails(EMPTY, mapWithSizeLessThan(0L), "\nExpected: java.util.Map size less than <0>\n      but: was java.util.Map size <0> <{}>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(EMPTY, mapWithSizeLessThan(1)),
                        () -> TESTER.assertValid(EMPTY, mapWithSizeLessThan(1L))
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeLessThanOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(ONE, mapWithSizeLessThan(0), "\nExpected: java.util.Map size less than <0>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>"),
                        () -> TESTER.assertFails(ONE, mapWithSizeLessThan(0L), "\nExpected: java.util.Map size less than <0>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(ONE, mapWithSizeLessThan(1), "\nExpected: java.util.Map size less than <1>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>"),
                        () -> TESTER.assertFails(ONE, mapWithSizeLessThan(1L), "\nExpected: java.util.Map size less than <1>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(ONE, mapWithSizeLessThan(2)),
                        () -> TESTER.assertValid(ONE, mapWithSizeLessThan(2L))
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeLessThanTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TWO, mapWithSizeLessThan(0), "\nExpected: java.util.Map size less than <0>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>"),
                        () -> TESTER.assertFails(TWO, mapWithSizeLessThan(0L), "\nExpected: java.util.Map size less than <0>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TWO, mapWithSizeLessThan(1), "\nExpected: java.util.Map size less than <1>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>"),
                        () -> TESTER.assertFails(TWO, mapWithSizeLessThan(1L), "\nExpected: java.util.Map size less than <1>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertFails(TWO, mapWithSizeLessThan(2), "\nExpected: java.util.Map size less than <2>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>"),
                        () -> TESTER.assertFails(TWO, mapWithSizeLessThan(2L), "\nExpected: java.util.Map size less than <2>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>")
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertValid(TWO, mapWithSizeLessThan(3)),
                        () -> TESTER.assertValid(TWO, mapWithSizeLessThan(3L))
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeLessThanTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSizeLessThan(0), "\nExpected: java.util.Map size less than <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSizeLessThan(0L), "\nExpected: java.util.Map size less than <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSizeLessThan(1), "\nExpected: java.util.Map size less than <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSizeLessThan(1L), "\nExpected: java.util.Map size less than <1>\n      but: was null")
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
                        () -> TESTER.assertValid(EMPTY, mapWithSizeLessThanOrEqualTo(0)),
                        () -> TESTER.assertValid(EMPTY, mapWithSizeLessThanOrEqualTo(0L))
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(EMPTY, mapWithSizeLessThanOrEqualTo(1)),
                        () -> TESTER.assertValid(EMPTY, mapWithSizeLessThanOrEqualTo(1L))
                );
            }

        }

        @Nested
        @DisplayName("One entry input")
        class sizeLessThanOrEqualToOne {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(ONE, mapWithSizeLessThanOrEqualTo(0), "\nExpected: java.util.Map size less than or equal to <0>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>"),
                        () -> TESTER.assertFails(ONE, mapWithSizeLessThanOrEqualTo(0L), "\nExpected: java.util.Map size less than or equal to <0>\n      but: was java.util.Map size <1> <{alphaKey=alphaValue}>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertValid(ONE, mapWithSizeLessThanOrEqualTo(1)),
                        () -> TESTER.assertValid(ONE, mapWithSizeLessThanOrEqualTo(1L))
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(ONE, mapWithSizeLessThanOrEqualTo(2)),
                        () -> TESTER.assertValid(ONE, mapWithSizeLessThanOrEqualTo(2L))
                );
            }

        }

        @Nested
        @DisplayName("Two entries input")
        class sizeLessThanOrEqualToTwo {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TWO, mapWithSizeLessThanOrEqualTo(0), "\nExpected: java.util.Map size less than or equal to <0>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>"),
                        () -> TESTER.assertFails(TWO, mapWithSizeLessThanOrEqualTo(0L), "\nExpected: java.util.Map size less than or equal to <0>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TWO, mapWithSizeLessThanOrEqualTo(1), "\nExpected: java.util.Map size less than or equal to <1>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>"),
                        () -> TESTER.assertFails(TWO, mapWithSizeLessThanOrEqualTo(1L), "\nExpected: java.util.Map size less than or equal to <1>\n      but: was java.util.Map size <2> <{alphaKey=alphaValue, bravoKey=bravoValue}>")
                );
            }

            @Test
            public void expectedTwo() {
                assertAll(
                        () -> TESTER.assertValid(TWO, mapWithSizeLessThanOrEqualTo(2)),
                        () -> TESTER.assertValid(TWO, mapWithSizeLessThanOrEqualTo(2L))
                );
            }

            @Test
            public void expectedThree() {
                assertAll(
                        () -> TESTER.assertValid(TWO, mapWithSizeLessThanOrEqualTo(3)),
                        () -> TESTER.assertValid(TWO, mapWithSizeLessThanOrEqualTo(3L))
                );
            }

        }

        @Nested
        @DisplayName("Typed Null input")
        class sizeLessThanOrEqualToTypedNull {

            @Test
            public void expectedEmpty() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSizeLessThanOrEqualTo(0), "\nExpected: java.util.Map size less than or equal to <0>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSizeLessThanOrEqualTo(0L), "\nExpected: java.util.Map size less than or equal to <0>\n      but: was null")
                );
            }

            @Test
            public void expectedOne() {
                assertAll(
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSizeLessThanOrEqualTo(1), "\nExpected: java.util.Map size less than or equal to <1>\n      but: was null"),
                        () -> TESTER.assertFails(TYPED_NULL, mapWithSizeLessThanOrEqualTo(1L), "\nExpected: java.util.Map size less than or equal to <1>\n      but: was null")
                );
            }

        }

    }

    @Nested
    @DisplayName("Empty")
    class empty {

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
    @DisplayName("Null Or Empty")
    class nullOrEmpty {

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
