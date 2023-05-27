package com.github.donudon14.learn.java;

import java.util.Arrays;
import static com.github.donudon14.learn.java.Arrays.permute;
import static com.github.donudon14.learn.java.Arrays.rotate;
import static java.util.Arrays.asList;
import static java.util.Arrays.setAll;
import static java.util.Collections.shuffle;

public final class ArraysTest {
    private static final int ITERATION = 4096, LENGTH = 64;

    public static void main(String[] args) {
        testPermute();
        testRotate();
    }

    private static void testRotate() {
        final var expected = new Integer[LENGTH];
        final var actual = new Integer[LENGTH];
        for (int i = -LENGTH; i < LENGTH << 1; ++i) {
            setAll(actual, j -> j);
            for (int j = 0; j < LENGTH; ++j)
                expected[j] = ((i % LENGTH + j) % LENGTH + LENGTH) % LENGTH;
            rotate(actual, -i);
            assert Arrays.equals(expected, actual);
        }
    }

    private static void testPermute() {
        final var expected = new Integer[8];
        final var actual = new Integer[8];
        final var permutation = new int[8];
        final var list = asList(permutation);
        setAll(permutation, i -> i);
        for (int i = 0; i < ITERATION; ++i) {
            setAll(actual, j -> ~j);
            shuffle(list);
            setAll(expected, j -> actual[permutation[j]]);
            permute(actual, permutation);
            assert Arrays.equals(expected, actual);
        }
    }
}
