package com.github.donudon14.learn.java;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOf;
import static java.util.Objects.checkIndex;

public class ArrayList<E> extends AbstractList<E>
    implements List<E>, RandomAccess {
    private static final int DEFAULT_CAPACITY = 10, MAX_LENGTH = MAX_VALUE - 2;

    private E[] elementData;
    private int size;

    public ArrayList() {
        @SuppressWarnings("unchecked")
        final var elementData = (E[]) new Object[DEFAULT_CAPACITY];
        this.elementData = elementData;
    }

    public ArrayList(final int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        @SuppressWarnings("unchecked")
        final var elementData = (E[]) new Object[capacity];
        this.elementData = elementData;
    }

    public final void clear() {
        for (int i = 0; i < size; ++i)
            elementData[i] = null;
        size = 0;
    }

    public final boolean add(final E element) {
        assert size <= elementData.length;
        if (size == elementData.length)
            grow(size + 1);
        elementData[size++] = element;
        return true;
    }

    public final void add(final int index, final E element) {
        assert size <= elementData.length;
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(
                "Index: " + index + ", Size: " + size);
        if (size == elementData.length)
            grow(size + 1);
        arraycopy(
            elementData, index,
            elementData, index + 1,
            size - index
        );
        elementData[index] = element;
        ++size;
    }

    public final boolean contains(final Object object) {
        return indexOf(object) != -1;
    }

    public final E get(final int index) {
        checkIndex(index, size);
        return elementData[index];
    }

    public final int indexOf(final Object object) {
        if (object == null) {
            for (int i = 0; i < size; ++i)
                if (elementData[i] == null)
                    return i;
        } else
            for (int i = 0; i < size; ++i)
                if (object.equals(elementData[i]))
                    return i;
        return -1;
    }

    public final E set(final int index, final E element) {
        checkIndex(index, size);
        final var oldElement = elementData[index];
        elementData[index] = element;
        return oldElement;
    }

    public final int size() {
        return size;
    }

    private final void grow(final int minCapacity) {
        assert elementData.length < minCapacity;
        elementData = copyOf(elementData, newCapacity(minCapacity));
    }

    private final int newCapacity(final int minCapacity) {
        assert elementData.length < minCapacity;
        final int step = elementData.length >> 1;
        return elementData.length == MAX_LENGTH ? MAX_VALUE : max(
            minCapacity,
            step + min(elementData.length, MAX_LENGTH - step)
        );
    }
}
