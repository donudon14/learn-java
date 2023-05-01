package com.github.donudon14.learn.java;

import java.util.Comparator;
import static com.github.donudon14.learn.java.Arrays.greaterThan;
import static java.util.Objects.checkFromToIndex;

public final class Sort {
    private Sort() {
    }

    public static <T> boolean isSorted(
        final T[] array,
        final Comparator<? super T> comparator
    ) {
        return isSorted(array, 0, array.length, comparator);
    }

    public static <T> boolean isSorted(
        final T[] array,
        final int fromIndex,
        final int toIndex,
        final Comparator<? super T> comparator
    ) {
        return isSortedUntil(array, fromIndex, toIndex, comparator) == toIndex;
    }

    public static <T> int isSortedUntil(
        final T[] array,
        final Comparator<? super T> comparator
    ) {
        return isSortedUntil(array, 0, array.length, comparator);
    }

    public static <T> int isSortedUntil(
        final T[] array,
        int fromIndex,
        final int toIndex,
        final Comparator<? super T> comparator
    ) {
        checkFromToIndex(fromIndex, toIndex, array.length);
        for (; fromIndex + 1 < toIndex; ++fromIndex)
            if (greaterThan(array, fromIndex, fromIndex + 1, comparator))
                return fromIndex + 1;
        return toIndex;
    }
}
