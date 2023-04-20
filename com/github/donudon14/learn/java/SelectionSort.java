package com.github.donudon14.learn.java;

import java.util.Comparator;
import static java.util.Arrays.asList;
import static java.util.Objects.checkFromToIndex;
import static com.github.donudon14.learn.java.Arrays.indexOfMin;
import static com.github.donudon14.learn.java.Arrays.swap;

public final class SelectionSort {
    public static <T> void sort(
        final T[] array,
        final Comparator<? super T> comparator
    ) {
        sort(array, 0, array.length, comparator);
    }

    public static <T> void sort(
        final T[] array,
        int fromIndex,
        final int toIndex,
        final Comparator<? super T> comparator
    ) {
        checkFromToIndex(fromIndex, toIndex, array.length);
        for (; fromIndex < toIndex - 1; ++fromIndex) {
            final int index = indexOfMin(array, fromIndex, toIndex, comparator);
            if (fromIndex != index)
                swap(array, fromIndex, index);
        }
    }
}
