package com.github.donudon14.learn.java;

public final class Comparables {
    public static byte clamp(
        final byte x,
        final byte a,
        final byte b
    ) {
        return x < a ? a : b < x ? b : x;
    }

    public static short clamp(
        final short x,
        final short a,
        final short b
    ) {
        return x < a ? a : b < x ? b : x;
    }

    public static int clamp(
        final int x,
        final int a,
        final int b
    ) {
        return x < a ? a : b < x ? b : x;
    }

    public static long clamp(
        final long x,
        final long a,
        final long b
    ) {
        return x < a ? a : b < x ? b : x;
    }

    public static float clamp(
        final float x,
        final float a,
        final float b
    ) {
        return x < a ? a : b < x ? b : x;
    }

    public static double clamp(
        final double x,
        final double a,
        final double b
    ) {
        return x < a ? a : b < x ? b : x;
    }

    public static <T extends Comparable<? super T>> T clamp(
        final T x,
        final T a,
        final T b
    ) {
        return lessThan(x, a) ? a : lessThan(b, x) ? b : x;
    }

    /**
     * a.compareTo(b) == 0 doesn't implie a.equals(b)
     * assert BigDecimal.ONE.equals(BigDecimal.valueOf(1.0)) == false;
     * assert BigDecimal.ONE.compareTo(BigDecimal.valueOf(1.0)) == 0;
     */
    public static <T extends Comparable<? super T>> boolean equalTo(
        final T a,
        final T b
    ) {
        return a.compareTo(b) == 0;
    }

    public static <T extends Comparable<? super T>> boolean greaterThan(
        final T a,
        final T b
    ) {
        return a.compareTo(b) > 0;
    }

    public static <T extends Comparable<? super T>> boolean greaterThanEqual(
        final T a,
        final T b
    ) {
        return a.compareTo(b) >= 0;
    }

    public static boolean inRange(
        final byte x,
        final byte a,
        final byte b
    ) {
        return x >= a && x < b;
    }

    public static boolean inRange(
        final short x,
        final short a,
        final short b
    ) {
        return x >= a && x < b;
    }

    public static boolean inRange(
        final int x,
        final int a,
        final int b
    ) {
        return x >= a && x < b;
    }

    public static boolean inRange(
        final long x,
        final long a,
        final long b
    ) {
        return x >= a && x < b;
    }

    public static boolean inRange(
        final float x,
        final float a,
        final float b
    ) {
        return x >= a && x < b;
    }

    public static boolean inRange(
        final double x,
        final double a,
        final double b
    ) {
        return x >= a && x < b;
    }

    public static <T extends Comparable<? super T>> boolean inRange(
        final T x,
        final T a,
        final T b
    ) {
        return greaterThanEqual(x, a) && lessThan(x, b);
    }

    public static boolean inRangeClosed(
        final byte x,
        final byte a,
        final byte b
    ) {
        return x >= a && b >= x;
    }

    public static boolean inRangeClosed(
        final short x,
        final short a,
        final short b
    ) {
        return x >= a && b >= x;
    }

    public static boolean inRangeClosed(
        final int x,
        final int a,
        final int b
    ) {
        return x >= a && b >= x;
    }

    public static boolean inRangeClosed(
        final long x,
        final long a,
        final long b
    ) {
        return x >= a && b >= x;
    }

    public static boolean inRangeClosed(
        final float x,
        final float a,
        final float b
    ) {
        return x >= a && b >= x;
    }

    public static boolean inRangeClosed(
        final double x,
        final double a,
        final double b
    ) {
        return x >= a && b >= x;
    }

    public static <T extends Comparable<? super T>> boolean inRangeClosed(
        final T x,
        final T a,
        final T b
    ) {
        return greaterThanEqual(x, a) && greaterThanEqual(b, x);
    }

    public static <T extends Comparable<? super T>> boolean lessThan(
        final T a,
        final T b
    ) {
        return a.compareTo(b) < 0;
    }

    public static <T extends Comparable<? super T>> boolean lessThanEqual(
        final T a,
        final T b
    ) {
        return a.compareTo(b) <= 0;
    }

    public static <T extends Comparable<? super T>> T max(
        final T a,
        final T b
    ) {
        return lessThan(a, b) ? b : a;
    }

    public static <T extends Comparable<? super T>> T min(
        final T a,
        final T b
    ) {
        return greaterThan(a, b) ? b : a;
    }

    /**
     * a.compareTo(b) == 0 doesn't implie a.equals(b)
     * assert BigDecimal.ONE.equals(BigDecimal.valueOf(1.0)) == false;
     * assert BigDecimal.ONE.compareTo(BigDecimal.valueOf(1.0)) == 0;
     */
    public static <T extends Comparable<? super T>> boolean notEqualTo(
        final T a,
        final T b
    ) {
        return a.compareTo(b) != 0;
    }
}
