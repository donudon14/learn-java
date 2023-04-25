package com.github.donudon14.learn.java;

import java.util.Comparator;
import static com.github.donudon14.learn.java.Arrays.indexOfMin;
import static com.github.donudon14.learn.java.Arrays.swap;
import static java.lang.Math.min;
import static java.util.Arrays.asList;
import static java.util.Objects.checkFromToIndex;

public final class ShellSort {
    private static final int[] GAPS = new int[328];

    static {
        GAPS[0] = 1;
        for (int i = 1, i2 = 0, i3 = 0; i < GAPS.length; ++i) {
            final int n2 = 2 * GAPS[i2], n3 = 3 * GAPS[i3];
            GAPS[i] = min(n2, n3);
            if (n2 <= n3)
                ++i2;
            if (n2 >= n3)
                ++i3;
        }
    }

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
        for (int step = toIndex - fromIndex >> 1; step > 0; step >>= 1) {
            for (int i = fromIndex + step; i < toIndex; ++i)
                for (int j = i - step;
                    j >= 0 && comparator.compare(array[j], array[j + step]) > 0;
                    j -= step
                )
                    swap(array, j, j + step);
        }
    }
}
