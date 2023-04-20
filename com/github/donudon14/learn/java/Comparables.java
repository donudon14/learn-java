package com.github.donudon14.learn.java;

import com.github.donudon14.learn.java.Comparators;
import java.util.Comparator;

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
        return Comparators.clamp(x, a, b, Comparator.<T>naturalOrder());
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
        return Comparators.inRange(x, a, b, Comparator.<T>naturalOrder());
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
        return Comparators.inRangeClosed(x, a, b, Comparator.<T>naturalOrder());
    }

    public static <T extends Comparable<? super T>> T max(
        final T a,
        final T b
    ) {
        return Comparators.max(a, b, Comparator.<T>naturalOrder());
    }

    public static <T extends Comparable<? super T>> T min(
        final T a,
        final T b
    ) {
        return Comparators.min(a, b, Comparator.<T>naturalOrder());
    }
}
