package com.github.donudon14.learn.java;

import java.util.Comparator;
import static java.util.Objects.checkFromToIndex;
import static com.github.donudon14.learn.java.Arrays.compare;
import static com.github.donudon14.learn.java.Arrays.compareSwap;
import static com.github.donudon14.learn.java.Arrays.lessThan;
import static com.github.donudon14.learn.java.Arrays.swap;

public final class Heap {
    private Heap() {
    }

    public static <T> boolean isHeap(
        final T[] heap,
        final Comparator<? super T> comparator
    ) {
        return isHeap(heap, 0, heap.length, comparator);
    }

    public static <T> boolean isHeap(
        final T[] heap,
        final int fromIndex,
        final int toIndex,
        final Comparator<? super T> comparator
    ) {
        return isHeapUntil(heap, fromIndex, toIndex, comparator) == toIndex;
    }

    public static <T> int isHeapUntil(
        final T[] heap,
        final Comparator<? super T> comparator
    ) {
        return isHeapUntil(heap, 0, heap.length, comparator);
    }

    public static <T> int isHeapUntil(
        final T[] heap,
        final int fromIndex,
        final int toIndex,
        final Comparator<? super T> comparator
    ) {
        checkFromToIndex(fromIndex, toIndex, heap.length);
        for (int parent = 0, child = 1; child < toIndex; ++parent, ++child)
            if (lessThan(heap, parent, child, comparator) ||
                ++child < toIndex && lessThan(heap, parent, child, comparator))
                return child;
        return toIndex;
    }

    public static <T> void make(
        final T[] heap,
        final Comparator<? super T> comparator
    ) {
        make(heap, 0, heap.length, comparator);
    }

    public static <T> void make(
        final T[] heap,
        final int fromIndex,
        final int toIndex,
        final Comparator<? super T> comparator
    ) {
        checkFromToIndex(fromIndex, toIndex, heap.length);
        for (int index = fromIndex + 2; index <= toIndex; ++index) // ???
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
                lessThan(heap, child, child + 1, comparator))
                ++child;
            if (!compareSwap(heap, child, parent, comparator))
                break;
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
        }
    }

    public static <T> void sort(
        final T[] heap,
        final Comparator<? super T> comparator
    ) {
        sort(heap, 0, heap.length, comparator);
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
