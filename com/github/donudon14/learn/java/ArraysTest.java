package com.github.donudon14.learn.java;

import java.util.Arrays;
import static com.github.donudon14.learn.java.Arrays.rotate;
import static java.util.Arrays.setAll;

public class ArraysTest{
    private static final int LENGTH = 64;

    public static void main(String[] args) {
        final var expected = new Integer[LENGTH];
        final var actual = new Integer[LENGTH];
        for (int i = -LENGTH; i < LENGTH << 1; ++i) {
            setAll(actual, j -> j);
            for (int j = 0; j < LENGTH; ++j)
                expected[j] = (i + j + LENGTH) % LENGTH;
            rotate(actual, -i);
            assert Arrays.equals(expected, actual);
        }
    }
}
