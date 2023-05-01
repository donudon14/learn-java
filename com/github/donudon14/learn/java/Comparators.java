package com.github.donudon14.learn.java;

import java.util.Comparator;
import static java.util.Objects.compare;

public final class Comparators {
    private Comparators() {
    }

    public static <T> T clamp(
        final T x,
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return compare(x, a, comparator) < 0 ? a :
            compare(b, x, comparator) < 0 ? b : x;
    }

    public static <T> boolean inRange(
        final T x,
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return compare(x, a, comparator) >= 0 && compare(x, b, comparator) < 0;
    }

    public static <T> boolean inRangeClosed(
        final T x,
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return compare(x, a, comparator) >= 0 && compare(b, x, comparator) >= 0;
    }

    public static <T> T max(
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return compare(a, b, comparator) < 0 ? b : a;
    }

    public static <T> T min(
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return compare(a, b, comparator) > 0 ? b : a;
    }
}
