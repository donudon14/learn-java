package com.github.donudon14.learn.java;

import java.util.Comparator;
import static java.util.Arrays.setAll;
import static com.github.donudon14.learn.java.BinarySearch.binarySearch;
import static com.github.donudon14.learn.java.BinarySearch.lowerBound;
import static com.github.donudon14.learn.java.BinarySearch.upperBound;

public final class BinarySearchTest {
    private static final int LENGTH = 64;

    public static void main(final String[] args) {
        testBinarySearch();
        testLowerBound();
        testUpperBound();
    }

    private static void testBinarySearch() {
        final var array = new Integer[LENGTH];
        final var comparator = Comparator.<Integer>naturalOrder();

        setAll(array, i -> i * 2);
        assert -1 == binarySearch(array, 0, array.length, -1, comparator);
        for (int i = 0; i < LENGTH; ++i) {
            assert i ==
                binarySearch(array, 0, array.length, i * 2, comparator);
            assert -i - 2 ==
                binarySearch(array, 0, array.length, i * 2 + 1, comparator);
        }
    }

    private static void testLowerBound() {
        final var array = new Integer[LENGTH];
        final var comparator = Comparator.<Integer>naturalOrder();

        setAll(array, i -> i * 2);
        assert 0 == lowerBound(array, 0, array.length, -1, comparator);
        for (int i = 0; i < LENGTH; ++i) {
            assert i ==
                lowerBound(array, 0, array.length, i * 2, comparator);
            assert i + 1 ==
                lowerBound(array, 0, array.length, i * 2 + 1, comparator);
        }
    }

    private static void testUpperBound() {
        final var array = new Integer[LENGTH];
        final var comparator = Comparator.<Integer>naturalOrder();

        setAll(array, i -> i * 2);
        assert 0 == upperBound(array, 0, array.length, -1, comparator);
        for (int i = 0; i < LENGTH; ++i) {
            assert i + 1 ==
                upperBound(array, 0, array.length, i * 2, comparator);
            assert i + 1 ==
                upperBound(array, 0, array.length, i * 2 + 1, comparator);
        }
    }
}
