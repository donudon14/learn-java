package com.github.donudon14.learn.java;

import java.util.Comparator;
import java.util.NoSuchElementException;
import static java.util.Objects.checkFromToIndex;

public final class Arrays {
    private Arrays() {
    }

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
        if (greaterThan(array, i, j, comparator)) {
            swap(array, i, j);
            return true;
        }
        return false;
    }

    public static <T> T[] copyOfRange(
        final T[] array,
        final int fromIndex,
        final int toIndex,
        final int length
    ) {
        return copyOfRange(array, fromIndex, toIndex, length,
            (Class<? extends T[]>) array.getClass()
        );
    }

    public static <T,U> T[] copyOfRange(
        final U[] array,
        final int fromIndex,
        final int toFrom,
        final int length,
        final Class<? extends T[]> newType
    ) {
        return copyOfRange(array, fromIndex, max(toIndex, fromIndex + length),
            newType
        );
    }

    /**
     * a.compareTo(b) == 0 doesn't implie a.equals(b)
     * assert BigDecimal.ONE.equals(BigDecimal.valueOf(1.0)) == false;
     * assert BigDecimal.ONE.compareTo(BigDecimal.valueOf(1.0)) == 0;
     */
    public static <T> boolean equalTo(
        final T[] array,
        final int i,
        final int j,
        final Comparator<? super T> comparator
    ) {
        return compare(array, i, j, comparator) == 0;
    }

    public static <T> boolean greaterThan(
        final T[] array,
        final int i,
        final int j,
        final Comparator<? super T> comparator
    ) {
        return compare(array, i, j, comparator) > 0;
    }

    public static <T> boolean greaterThanEqual(
        final T[] array,
        final int i,
        final int j,
        final Comparator<? super T> comparator
    ) {
        return compare(array, i, j, comparator) >= 0;
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
            if (lessThan(array, index, fromIndex, comparator))
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
            if (greaterThan(array, index, fromIndex, comparator))
                index = fromIndex;
        return index;
    }

    public static <T> boolean lessThan(
        final T[] array,
        final int i,
        final int j,
        final Comparator<? super T> comparator
    ) {
        return compare(array, i, j, comparator) < 0;
    }

    public static <T> boolean lessThanEqual(
        final T[] array,
        final int i,
        final int j,
        final Comparator<? super T> comparator
    ) {
        return compare(array, i, j, comparator) <= 0;
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

    /**
     * a.compareTo(b) == 0 doesn't implie a.equals(b)
     * assert BigDecimal.ONE.equals(BigDecimal.valueOf(1.0)) == false;
     * assert BigDecimal.ONE.compareTo(BigDecimal.valueOf(1.0)) == 0;
     */
    public static <T> boolean notEqualTo(
        final T[] array,
        final int i,
        final int j,
        final Comparator<? super T> comparator
    ) {
        return compare(array, i, j, comparator) != 0;
    }

    public static <T> void permute(
        final T[] array,
        final int[] permutation
    ) {
        permute(array, 0, array.length, permutation);
    }

    public static <T> void permute(
        final T[] array,
        final int fromIndex,
        final int toIndex,
        final int[] permutation
    ) {
        checkFromToIndex(fromIndex, toIndex, array.length);
        final int length = toIndex - fromIndex;
        if (length != permutation.length)
            throw new IllegalArgumentException(
                "array and permutation should have same size"
            );
        for (int i = 0; i < length; ++i)
            swap(array, fromIndex + i, fromIndex + permutation[i]);
    }

    /* public static <T> void reversePermutation(final int[] permutation) {
        for (int i = 0; i < length; ++i)
            result[permutation[i]] = i;
    } */

    public static <T> void reverse(final T[] array) {
        reverse(array, 0, array.length);
    }

    public static <T> void reverse(
        final T[] array,
        int fromIndex,
        int toIndex
    ) {
        checkFromToIndex(fromIndex, toIndex, array.length);
        while (fromIndex < --toIndex)
            swap(array, fromIndex++, toIndex);
    }

    public static <T> void rotate(final T[] array, final int distance) {
        rotate(array, 0, array.length, distance);
    }

    public static <T> void rotate(
        final T[] array,
        int fromIndex,
        final int toIndex,
        int distance
    ) {
        checkFromToIndex(fromIndex, toIndex, array.length);
        final int size = toIndex - fromIndex;
        if (size == 0)
            return;

        distance %= size;
        if (distance == 0)
            return;
        if (distance < 0)
            distance += size;

        final int bound = toIndex - distance;
        for (int nMoved = 0; nMoved < size; ++fromIndex) {
            int i = fromIndex;
            var value = array[i];
            do {
                i += i >= bound ? distance - size : distance;
                final var temp = array[i];
                array[i] = value;
                value = temp;
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
