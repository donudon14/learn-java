package com.github.donudon14.learn.java;

import java.util.Comparator;
import com.github.donudon14.learn.java.Heap;
import static java.util.Objects.checkFromToIndex;

public final class HeapSort {
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
        Heap.make(array, fromIndex, toIndex, comparator);
        Heap.sort(array, fromIndex, toIndex, comparator);
    }
}
