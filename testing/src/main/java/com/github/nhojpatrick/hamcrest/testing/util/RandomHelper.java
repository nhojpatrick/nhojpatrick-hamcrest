package com.github.nhojpatrick.hamcrest.testing.util;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.Random;

public class RandomHelper {

    @SuppressFBWarnings(value = "PREDICTABLE_RANDOM",
            justification = "Accepted issue and just for datetime testing currently")
    public static int randomIntBetween(final int low,
                                       final int high) {
        return randomIntBetween(new Random(), low, high);
    }

    public static int randomIntBetween(final Random r,
                                       final int low,
                                       final int high) {
        final int l = Math.min(low, high);
        final int h = Math.max(low, high);
        final int diff = h - l;
        return l + r.nextInt(diff + 1);
    }

}
