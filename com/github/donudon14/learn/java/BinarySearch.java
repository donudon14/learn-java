package com.github.donudon14.learn.java;

import java.util.Comparator;
import static java.util.Objects.checkFromToIndex;

public final class BinarySearch {
    public static <T> int binarySearch(
        final T[] a,
        final T key,
        final Comparator<? super T> comparator
    ) {
        return binarySearch(a, 0, a.length, key, comparator);
    }

    public static <T> int binarySearch(
        final T[] array,
        int fromIndex,
        int toIndex,
        final T key,
        final Comparator<? super T> comparator
    ) {
        checkFromToIndex(fromIndex, toIndex, array.length);
        --toIndex;
        while (fromIndex <= toIndex) {
            final int middle = fromIndex + toIndex >>> 1,
                result = comparator.compare(array[middle], key);
            if (result < 0)
                fromIndex = middle + 1;
            else if (result > 0)
                toIndex = middle - 1;
            else
                return middle;
        }
        return -fromIndex - 1;
    }

    public static <T> int lowerBound(
        final T[] a,
        final T key,
        final Comparator<? super T> comparator
    ) {
        return lowerBound(a, 0, a.length, key, comparator);
    }

    public static <T> int lowerBound(
        final T[] array,
        int fromIndex,
        int toIndex,
        final T key,
        final Comparator<? super T> comparator
    ) {
        checkFromToIndex(fromIndex, toIndex, array.length);
        --toIndex;
        while (fromIndex <= toIndex) {
            final int middle = fromIndex + toIndex >>> 1,
                result = comparator.compare(array[middle], key);
            if (result < 0)
                fromIndex = middle + 1;
            else
                toIndex = middle - 1;
        }
        return fromIndex;
    }

    public static <T> int upperBound(
        final T[] a,
        final T key,
        final Comparator<? super T> comparator
    ) {
        return upperBound(a, 0, a.length, key, comparator);
    }

    public static <T> int upperBound(
        final T[] array,
        int fromIndex,
        int toIndex,
        final T key,
        final Comparator<? super T> comparator
    ) {
        checkFromToIndex(fromIndex, toIndex, array.length);
        --toIndex;
        while (fromIndex <= toIndex) {
            final int middle = fromIndex + toIndex >>> 1,
                result = comparator.compare(array[middle], key);
            if (result > 0)
                toIndex = middle - 1;
            else
                fromIndex = middle + 1;
        }
        return fromIndex;
    }
}
