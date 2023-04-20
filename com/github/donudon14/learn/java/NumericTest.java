package com.github.donudon14.learn.java;

import static com.github.donudon14.learn.java.Comparables.inRangeClosed;
import static com.github.donudon14.learn.java.Numeric.pow;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Integer.SIZE;
import static java.lang.Integer.max;
import static java.lang.Integer.min;

public final class NumericTest {
    public static void main(final String[] args) {
        testPow();
    }

    private static void testPow() {
        for (int base = -4096; base < 4096; ++base) {
            int result = 1;
            for (int exp = 0; exp < SIZE; result *= base, ++exp) {
                assert result == pow(base, exp);
                if (!inRangeClosed(base, -1, 0) && !inRangeClosed(result,
                    min(MAX_VALUE / base, MIN_VALUE / base),
                    max(MAX_VALUE / base, MIN_VALUE / base)
                )) break;
            }
        }
    }
}
