package com.github.donudon14.learn.java;

import java.util.Comparator;
import static java.util.Objects.checkFromToIndex;
import static com.github.donudon14.learn.java.Arrays.compareSwap;

public final class BubbleSort {
    public static <T> void sort(
        final T[] array,
        final Comparator<? super T> comparator
    ) {
        sort(array, 0, array.length, comparator);
    }

    public static <T> void sort(
        final T[] array,
        final int fromIndex,
        int toIndex,
        final Comparator<? super T> comparator
    ) {
        checkFromToIndex(fromIndex, toIndex, array.length);
        for (int newToIndex; toIndex > fromIndex + 1; toIndex = newToIndex) {
            newToIndex = fromIndex;
            for (int i = fromIndex + 1; i < toIndex; ++i)
                if (compareSwap(array, i - 1, i, comparator))
                    newToIndex = i;
        }
    }
}
