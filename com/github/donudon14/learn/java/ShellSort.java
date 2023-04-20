package com.github.donudon14.learn.java;

import java.util.Comparator;

import static java.util.Arrays.asList;
import static java.util.Objects.checkFromToIndex;

import static com.github.donudon14.learn.java.Arrays.indexOfMin;
import static com.github.donudon14.learn.java.Arrays.swap;

public final class ShellSort {
    public static <T> void sort(
        final T[] array,
        final Comparator<? super T> comparator
    ) {
        sort(array, 0, array.length, comparator);
    }

    public static <T> void sort(
        final T[] array,
        final int fromIndex,
        final int toIndex,
        final Comparator<? super T> comparator
    ) {
        checkFromToIndex(fromIndex, toIndex, array.length);
        for (int step = toIndex - fromIndex >> 1; step > 0; step >>= 1) {
            for (int i = fromIndex + step; i < toIndex; ++i)
                for (int j = i - step;
                    j >= 0 && comparator.compare(array[j], array[j + step]) > 0;
                    j -= step
                )
                    swap(array, j, j + step);
        }
    }
}
