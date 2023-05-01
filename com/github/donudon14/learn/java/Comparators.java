package com.github.donudon14.learn.java;

import java.util.Comparator;

public final class Comparators {
    private Comparators() {
    }

    public static <T> T clamp(
        final T x,
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return lessThan(x, a, comparator) ? a :
            lessThan(b, x, comparator) ? b : x;
    }

    /**
     * a.compareTo(b) == 0 doesn't implie a.equals(b)
     * assert BigDecimal.ONE.equals(BigDecimal.valueOf(1.0)) == false;
     * assert BigDecimal.ONE.compareTo(BigDecimal.valueOf(1.0)) == 0;
     */
    public static <T> boolean equalTo(
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return comparator.compare(a, b) == 0;
    }

    public static <T> boolean greaterThan(
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return comparator.compare(a, b) > 0;
    }

    public static <T> boolean greaterThanEqual(
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return comparator.compare(a, b) >= 0;
    }

    public static <T> boolean inRange(
        final T x,
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return greaterThanEqual(x, a, comparator) && lessThan(x, b, comparator);
    }

    public static <T> boolean inRangeClosed(
        final T x,
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return greaterThanEqual(x, a, comparator) &&
            greaterThanEqual(b, x, comparator);
    }

    public static <T> boolean lessThan(
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return comparator.compare(a, b) < 0;
    }

    public static <T> boolean lessThanEqual(
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return comparator.compare(a, b) <= 0;
    }

    public static <T> T max(
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return lessThan(a, b, comparator) ? b : a;
    }

    public static <T> T min(
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return greaterThan(a, b, comparator) ? b : a;
    }

    /**
     * a.compareTo(b) == 0 doesn't imply a.equals(b)
     * assert BigDecimal.ONE.equals(BigDecimal.valueOf(1.0)) == false;
     * assert BigDecimal.ONE.compareTo(BigDecimal.valueOf(1.0)) == 0;
     */
    public static <T> boolean notEqualTo(
        final T a,
        final T b,
        final Comparator<? super T> comparator
    ) {
        return comparator.compare(a, b) != 0;
    }
}
