package com.github.donudon14.learn.java;

import java.util.Comparator;
import java.util.NoSuchElementException;
import static java.util.Objects.checkFromToIndex;

public final class Arrays {
    public static <T> int compare(
        final T[] array,
        final int i,
        final int j,
        final Comparator<? super T> comparator
    ) {
        return comparator.compare(array[i], array[j]);
    }

    public static <T> boolean compareSwap(
        final T[] array,
        final int i,
        final int j,
        final Comparator<? super T> comparator
    ) {
        if (compare(array, i, j, comparator) > 0) {
            swap(array, i, j);
            return true;
        }
        return false;
    }

    public static <T> int indexOfMax(
        final T[] array,
        final Comparator<? super T> comparator
    ) {
        return indexOfMax(array, 0, array.length, comparator);
    }

    public static <T> int indexOfMax(
        final T[] array,
        int fromIndex,
        final int toIndex,
        final Comparator<? super T> comparator
    ) {
        checkFromToIndex(fromIndex, toIndex, array.length);
        if (fromIndex == toIndex)
            throw new NoSuchElementException();
        int index = fromIndex;
        while (++fromIndex < toIndex)
            if (comparator.compare(array[index], array[fromIndex]) < 0)
                index = fromIndex;
        return index;
    }

    public static <T> int indexOfMin(
        final T[] array,
        final Comparator<? super T> comparator
    ) {
        return indexOfMin(array, 0, array.length, comparator);
    }

    public static <T> int indexOfMin(
        final T[] array,
        int fromIndex,
        final int toIndex,
        final Comparator<? super T> comparator
    ) {
        checkFromToIndex(fromIndex, toIndex, array.length);
        if (fromIndex == toIndex)
            throw new NoSuchElementException();
        int index = fromIndex;
        while (++fromIndex < toIndex)
            if (comparator.compare(array[index], array[fromIndex]) > 0)
                index = fromIndex;
        return index;
    }

    public static <T> boolean isSorted(
        final T[] array,
        final Comparator<? super T> comparator
    ) {
        return isSorted(array, 0, array.length, comparator);
    }

    public static <T> boolean isSorted(
        final T[] array,
        int fromIndex,
        final int toIndex,
        final Comparator<? super T> comparator
    ) {
        checkFromToIndex(fromIndex, toIndex, array.length);
        for (; fromIndex + 1 < toIndex; ++fromIndex)
            if (comparator.compare(array[fromIndex], array[fromIndex + 1]) > 0)
                return false;
        return true;
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
            if (comparator.compare(array[fromIndex], array[fromIndex + 1]) > 0)
                return fromIndex + 1;
        return toIndex;
    }

    public static <T> T max(
        final T[] array,
        final Comparator<? super T> comparator
    ) {
        return max(array, 0, array.length, comparator);
    }

    public static <T> T max(
        final T[] array,
        int fromIndex,
        final int toIndex,
        final Comparator<? super T> comparator
    ) {
        checkFromToIndex(fromIndex, toIndex, array.length);
        if (fromIndex == toIndex)
            throw new NoSuchElementException();
        var max = array[fromIndex];
        while (++fromIndex < toIndex)
            max = Comparators.max(max, array[fromIndex], comparator);
        return max;
    }

    public static <T> T min(
        final T[] array,
        final Comparator<? super T> comparator
    ) {
        return min(array, 0, array.length, comparator);
    }

    public static <T> T min(
        final T[] array,
        int fromIndex,
        final int toIndex,
        final Comparator<? super T> comparator
    ) {
        checkFromToIndex(fromIndex, toIndex, array.length);
        if (fromIndex == toIndex)
            throw new NoSuchElementException();
        var min = array[fromIndex];
        while (++fromIndex < toIndex)
            min = Comparators.min(min, array[fromIndex], comparator);
        return min;
    }

    public static <T> void reverse(final T[] array) {
        reverse(array, 0, array.length);
    }

    public static <T> void reverse(
        final T[] array,
        final int fromIndex,
        final int toIndex
    ) {
        checkFromToIndex(fromIndex, toIndex, array.length);
        while (fromIndex < --toIndex)
            swap(array, fromIndex++, toIndex);
    }

    public static <T> void rotate(final T[] array, final int distance) {
        rotate(array, distance)
    }

    public static <T> void rotate(
        final T[] array,
        int fromIndex,
        final int toIndex,
        final int distance
    ) {
        checkFromToIndex(fromIndex, toIndex, array.length);
        final int size = toIndex - fromIndex;
        if (size == 0)
            return;

        distance = distance % size;
        if (distance < 0)
            distance += size;
        if (distance == 0)
            return;

        final int bound = toIndex - distance;
        for (int nMoved = 0; nMoved < size; ++fromIndex) {
            int i = fromIndex;
            do {
                final int j;
                if (i >= bound)
                    j = i + (distance - size);
                else
                    j = i + distance;
                swap(array, i, j);
                i = j;
                ++nMoved;
            } while (i != fromIndex);
        }
    }

    public static <T> void swap(final T[] array, final int i, final int j) {
        final T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
