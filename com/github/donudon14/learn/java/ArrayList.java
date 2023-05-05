package com.github.donudon14.learn.java;

import java.util.AbstractList;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOf;
import static java.util.Objects.checkIndex;

public class ArrayList<E> extends AbstractList<E>
    implements List<E>, RandomAccess {
    private static final int DEFAULT_CAPACITY = 10, MAX_ARRAY_LENGTH = MAX_VALUE - 2;

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

    @Override
    public final void clear() {
        for (int i = 0; i < size; ++i)
            elementData[i] = null;
        size = 0;
    }

    @Override
    public final boolean add(final E element) {
        assert size <= elementData.length;
        if (size == elementData.length)
            grow(size + 1);
        elementData[size++] = element;
        return true;
    }

    @Override
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

    @Override
    public final boolean contains(final Object object) {
        return indexOf(object) != -1;
    }

    public void ensureCapacity(final int minCapacity) {
        if (minCapacity > elementData.length)
            grow(minCapacity);
    }

    @Override
    public final E get(final int index) {
        checkIndex(index, size);
        return elementData[index];
    }

    @Override
    public final int indexOf(final Object object) {
        for (int i = 0; i < size; ++i)
            if (Objects.equals(object, elementData[i]))
                return i;
        return -1;
    }

    @Override
    public final E set(final int index, final E element) {
        checkIndex(index, size);
        final var oldElement = elementData[index];
        elementData[index] = element;
        return oldElement;
    }

    @Override
    public final int size() {
        return size;
    }

    private final void grow(final int minCapacity) {
        assert elementData.length < minCapacity;
        elementData = copyOf(elementData, newLength(minCapacity));
    }

    private final int newLength(final int minCapacity) {
        assert elementData.length < minCapacity;
        final int step = elementData.length >> 1;
        return elementData.length == MAX_ARRAY_LENGTH ? MAX_VALUE : max(
            minCapacity, step + min(elementData.length, MAX_ARRAY_LENGTH - step)
        );
    }
}
