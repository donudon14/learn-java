package com.github.donudon14.learn.java;

import java.io.Serializable;
import static java.lang.Long.SIZE;
import static java.lang.Long.numberOfLeadingZeros;

public class BitSet implements Cloneable, Serializable {
    private static final int SHIFT = numberOfLeadingZeros(SIZE);
    private long[] bits;
    private int wordsInUse = 0;

    public BitSet() {
    }

    public void clear(final int bitIndex) {
    }

    public final int length() {
        if (wordsInUse == 0)
            return 0;

        // return SIZE * wordsInUse - numberOfLeadingZeros(words[wordsInUse - 1]);
        return SIZE * (wordsInUse - 1) +
            SIZE - numberOfLeadingZeros(words[wordsInUse - 1]);
    }

    public final boolean get(final int bitIndex) {
        if (bitIndex < 0)
            throw new IndexOutOfBoundsException("bitIndex < 0: " + bitIndex);

        final int wordIndex = wordIndex(bitIndex);
        return (wordIndex < wordsInUse) &&
            ((words[wordIndex] & (1L << bitIndex)) != 0);
    }

    public final boolean set(final int bitIndex) {
        ;
    }

    public final boolean set(final int bitIndex, final boolean value) {
        ;
    }

    public final int size() {
        return words.length * SIZE;
    }

    private static int wordIndex(final int bitIndex) {
        assert bitIndex >= 0;
        return bitIndex >> ADDRESS_BITS_PER_WORD;
    }
}
