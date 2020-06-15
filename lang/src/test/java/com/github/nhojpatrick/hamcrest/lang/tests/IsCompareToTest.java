package com.github.nhojpatrick.hamcrest.lang.tests;

import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.Collection;

import static com.github.nhojpatrick.hamcrest.lang.IsCompareTo.SUPPLIED_COMPARABLE_MUST_NOT_BE_NULL;
import static com.github.nhojpatrick.hamcrest.lang.IsCompareTo.compareToObject;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsCompareToTest {

    public static class ICompareToObject
            implements Comparable {

        private final int compareTo;

        public ICompareToObject(final int compareTo) {
            this.compareTo = compareTo;
        }

        @Override
        public int compareTo(final Object obj) {
            return this.compareTo;
        }

    }

    @TestFactory
    public Collection<DynamicTest> compareTo() {

        final MatcherObjectTester<Object> TESTER = new MatcherObjectTester<>();

        final ICompareToObject expected = new ICompareToObject(4321);

        return Arrays.asList(
                DynamicTest.dynamicTest("Actual \"qwerty\", Comparable, expected 1234",
                        () -> TESTER.assertFails("qwerty", compareToObject(1234, expected),
                                "\nExpected: Object compareTo(Object) <1234>\n      but: was <4321>")),


                DynamicTest.dynamicTest("Actual new Object(), Comparable, expected 1234",
                        () -> TESTER.assertFails(new Object(), compareToObject(1234, expected),
                                "\nExpected: Object compareTo(Object) <1234>\n      but: was <4321>")),

                DynamicTest.dynamicTest("Actual new Object(), Comparable, expected 4321",
                        () -> TESTER.assertValid(new Object(), compareToObject(4321, expected)))

        );
    }

    @Test
    public void compareTo_exception() {

        final Executable testMethod = () -> compareToObject(0, null);
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo(SUPPLIED_COMPARABLE_MUST_NOT_BE_NULL))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

}
