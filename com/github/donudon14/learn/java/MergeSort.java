package com.github.donudon14.learn.java;

import static java.lang.System.arraycopy;

public final class MergeSort{
    public static void sort(Integer[] array, int fromIndex, int toIndex){
        sort(array, fromIndex, toIndex, new Integer[toIndex - fromIndex]);
    }

    public static void sort(
        final Integer[] array,
        int fromIndex,
        int toIndex,
        Integer[] result
    ){
        final int middle = fromIndex + (toIndex - fromIndex) / 2;
        if (fromIndex == middle)
            return;
        sort(array, fromIndex, middle, result);
        sort(array, middle, toIndex, result);
        merge(array, fromIndex, middle, toIndex, result);
    }

    public static void merge(
        final Integer[] array,
        final int fromIndex,
        final int middle,
        final int toIndex,
        Integer[] result
    ) {
        int before = fromIndex, after = middle, index = 0;
        while (before < middle || after < toIndex)
            if (before >= middle || (after < toIndex && array[before] > array[after]))
                result[index++] = array[after++];
            else
                result[index++] = array[before++];
        arraycopy(result,0, array, fromIndex, toIndex - fromIndex);
    }
}
