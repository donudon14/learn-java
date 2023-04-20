package com.github.donudon14.learn.java;

import java.util.Comparator;

public final class Comparators {
    public static <T> T clamp(
        final T x,
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return comparator.compare(x, a) < 0 ? a : comparator.compare(b, x) < 0 ? b : x;
    }

    public static <T> boolean inRange(
        final T x,
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return comparator.compare(x, a) >= 0 && comparator.compare(x, b) < 0;
    }

    public static <T> boolean inRangeClosed(
        final T x,
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return comparator.compare(x, a) >= 0 && comparator.compare(b, x) >= 0;
    }

    public static <T> T max(
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return comparator.compare(a, b) < 0 ? b : a;
    }

    public static <T> T min(
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return comparator.compare(a, b) > 0 ? b : a;
    }
}
