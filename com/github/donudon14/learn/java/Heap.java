package com.github.donudon14.learn.java;

import java.util.Comparator;
import static java.util.Objects.checkFromToIndex;
import static com.github.donudon14.learn.java.Arrays.compare;
import static com.github.donudon14.learn.java.Arrays.compareSwap;
import static com.github.donudon14.learn.java.Arrays.swap;

public final class Heap {
    public static <T> void make(
        final T[] heap,
        final int fromIndex,
        final int toIndex,
        final Comparator<? super T> comparator
    ) {
        checkFromToIndex(fromIndex, toIndex, heap.length);
        for (int index = fromIndex + 2; index <= toIndex; ++index)
            push(heap, fromIndex, index, comparator);
    }

    public static <T> void pop(
        final T[] heap,
        final int fromIndex,
        int toIndex,
        final Comparator<? super T> comparator
    ) {
        checkFromToIndex(fromIndex, toIndex, heap.length);
        swap(heap, fromIndex, --toIndex);
        final int half = fromIndex + (toIndex - fromIndex >> 1);
        for (int parent = fromIndex, child = fromIndex;
            parent < half; parent = child
        ) {
            child += (child - fromIndex) + 1;
            if (child + 1 < toIndex &&
                compare(heap, child, child + 1, comparator) < 0)
                ++child;
            if (!compareSwap(heap, child, parent, comparator))
                break;
            assert compare(heap, parent, child, comparator) >= 0;
        }
    }

    public static <T> void push(
        final T[] heap,
        final int fromIndex,
        int toIndex,
        final Comparator<? super T> comparator
    ) {
        checkFromToIndex(fromIndex, toIndex, heap.length);
        --toIndex;
        for (int parent; toIndex > fromIndex; toIndex = parent) {
            parent = fromIndex + (toIndex - fromIndex - 1 >> 1);
            if (!compareSwap(heap, toIndex, parent, comparator))
                break;
            assert compare(heap, parent, toIndex, comparator) >= 0;
        }
    }

    public static <T> void sort(
        final T[] heap,
        final int fromIndex,
        int toIndex,
        final Comparator<? super T> comparator
    ) {
        checkFromToIndex(fromIndex, toIndex, heap.length);
        for (; toIndex - 1 > fromIndex; --toIndex)
            pop(heap, fromIndex, toIndex, comparator);
    }
}
