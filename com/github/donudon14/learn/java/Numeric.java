package com.github.donudon14.learn.java;

import static com.github.donudon14.learn.java.Comparables.inRangeClosed;

public final class Numeric {
    public static int gcd(int a, int b) {
        while (b != 0) {
            a %= b;
            final int temp = a;
            a = b;
            b = temp;
        }
        return a;
    }

    public static int lcm(final int a, final int b) {
        return a == 0 && b == 0 ? 0 : a / gcd(a, b) * b;
    }

    public static int pow(int base, int exp) {
        if (exp < 0)
            throw new ArithmeticException("Negative exponent");
        if (base == -1 && (exp & 1) != 0)
            return -1;
        if (base == 0 && exp != 0)
            return 0;
        if (inRangeClosed(base, -1, 1) || exp == 0)
            return 1;
        int result = 1;
        for (; exp > 1; base *= base, exp >>= 1)
            if ((exp & 1) != 0)
                result *= base;
        return base * result;
    }

    /* public static int modPow(final int a, final int b) {
        if (b < 0)
            throw new ArithmeticException("Negative exponent");
        if (a == -1 && (b & 1) != 0)
            return -1;
        if (a == 0 && b != 0)
            return 0;
        if ((a >= -1 && a <= 1) || b == 0)
            return 1;
        int result = 1;
        for (; b > 1; a *= a, b >>= 1)
            if ((b & 1) != 0)
                result *= a;
        return a * result;
    } */

    public static int[] extendedGcd(int a, int b) {
        int x = 1, y = 0, nextX = 0, nextY = 1;
        while (b != 0) {
            final int q = a / b;
            x -= q * nextX; x ^= nextX; nextX ^= x; x ^= nextX;
            y -= q * nextY; y ^= nextY; nextY ^= y; y ^= nextY;
            a -= q * b; a ^= b; b ^= a; a ^= b;
        }
        return new int[] { a, x, y };
    }

    public static int modInverse(final int a, final int b) {
        final int[] gXY = extendedGcd(a, m);
        if (gXY[0] != 1)
            throw new ArithmeticException(); // ???
        return (gXY[1] % m + m) % m;
    }
}
