package com.github.donudon14.learn.java;

import java.util.Comparator;
import java.util.function.BiConsumer;
import static java.util.Arrays.asList;
import static java.util.Arrays.setAll;
import static java.util.Collections.shuffle;

import static com.github.donudon14.learn.java.Arrays.isSorted;

public final class SortTest {
    private static final int LENGTH = 64, ITERATION = 4096;

    public static void main(final String[] args) {
        testSort(BubbleSort::sort);
        testSort(HeapSort::sort);
        testSort(InsertionSort::sort);
        testSort(OddEvenSort::sort);
        testSort(SelectionSort::sort);
        testSort(ShellSort::sort);
    }

    private static void testSort(
        final BiConsumer<Integer[], Comparator<Integer>> sort
    ) {
        final var array = new Integer[LENGTH];
        final var list = asList(array);
        final var comparator = Comparator.<Integer>naturalOrder();

        setAll(array, i -> i);
        for (int i = 0; i < ITERATION; ++i) {
            shuffle(list);
            sort.accept(array, comparator);
            assert isSorted(array, comparator);
        }

        setAll(array, i -> i >> 1);
        for (int i = 0; i < ITERATION; ++i) {
            shuffle(list);
            sort.accept(array, comparator);
            assert isSorted(array, comparator);
        }
    }
}
