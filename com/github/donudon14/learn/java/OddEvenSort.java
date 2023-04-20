package com.github.donudon14.learn.java;

import java.util.Comparator;
import static java.util.Arrays.asList;
import static java.util.Objects.checkFromToIndex;
import static com.github.donudon14.learn.java.Arrays.compareSwap;

public final class OddEvenSort {
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
        for (boolean sorted = true; sorted; ) {
            sorted = false;
            for (int j = fromIndex + 1; j < toIndex; j += 2)
                if (compareSwap(array, j - 1, j, comparator))
                    sorted = true;
            for (int j = fromIndex + 2; j < toIndex; j += 2)
                if (compareSwap(array, j - 1, j, comparator))
                    sorted = true;
        }
    }
}
