package com.github.donudon14.learn.java;

import java.util.Comparator;
import static com.github.donudon14.learn.java.BinarySearch.upperBound;
import static java.lang.System.arraycopy;
import static java.util.Objects.checkFromToIndex;

public final class InsertionSort {
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
        for (int last = fromIndex + 1; last < toIndex; ++last) {
            final int index = upperBound(
                array,
                fromIndex, last,
                array[last],
                comparator
            );
            if (last != index) {
                final var element = array[last];
                arraycopy(
                    array, index,
                    array, index + 1,
                    last - index
                );
                array[index] = element;
            }
        }
    }
}
