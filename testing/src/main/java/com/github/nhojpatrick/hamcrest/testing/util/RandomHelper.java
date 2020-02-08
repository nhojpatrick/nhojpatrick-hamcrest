package com.github.nhojpatrick.hamcrest.testing.util;

import java.util.Random;

public class RandomHelper {

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
