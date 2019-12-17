package com.github.nhojpatrick.hamcrest.lang;

import com.github.nhojpatrick.hamcrest.testing.MatcherObjectTester;
import org.junit.jupiter.api.Test;

import static com.github.nhojpatrick.hamcrest.lang.IsHashCode.hashCodeGenerated;

public class IsHashCodeTest {

    public static class IHashCodeObject {

        private final int hashCode;

        public IHashCodeObject(final int hashCode) {
            this.hashCode = hashCode;
        }

        @Override
        public int hashCode() {
            return this.hashCode;
        }

    }

    private static final MatcherObjectTester<Object> TESTER = new MatcherObjectTester<>();

    @Test
    public void nullActual() {
        TESTER.assertFails(null, hashCodeGenerated(1234),
                "\nExpected: Object hashCode <1234>\n      but: was null");
    }

    @Test
    public void match() {
        final IHashCodeObject actual = new IHashCodeObject(1234);
        TESTER.assertValid(actual, hashCodeGenerated(1234));
    }

    @Test
    public void misMatch() {
        final IHashCodeObject actual = new IHashCodeObject(4321);
        TESTER.assertFails(actual, hashCodeGenerated(1234),
                "\nExpected: Object hashCode <1234>\n      but: was <4321>");
    }

}
