package com.github.donudon14.learn.java;

import java.util.AbstractSequentialList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import static java.util.Objects.checkIndex;

public final class LinkedList<E> extends AbstractSequentialList<E>
    implements List<E>, Deque<E> {
    private static final class Node<E> {
        private E element;
        private Node<E> prev, next;

        public Node(final E element, final Node<E> prev, final Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private Node<E> first, last;

    @Override
    public final boolean add(final E element) {
        addLast(element);
        return true;
    }

    @Override
    public final void add(final int index, final E element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(
                "Index: " + index + ", Size: " + size);
        if (index == size)
            addLast(element);
        else {
            final var current = find(index);
            final var node = new Node<>(element, current.prev, current.next);
            current.prev.next = node;
            current.prev = node;
        }
    }

    @Override
    public final void addFirst(final E element) {
        final var node = new Node<>(element, null, first);
        if (size == 0)
            last = node;
        else
            first.prev = node;
        ++size;
        first = node;
    }

    @Override
    public final void addLast(final E element) {
        final var node = new Node<>(element, last, null);
        if (size == 0)
            first = node;
        else
            last.next = node;
        ++size;
        last = node;
    }

    @Override
    public final void clear() {
        for (var node = first; node != null; ) {
            final var next = node.next;
            node.element = null;
            node.prev = node.next = null;
            node = next;
        }
        size = 0;
        first = last = null;
    }
/*
    @Override
    public final Object clone() {
    }
*/
    @Override
    public final boolean contains(final Object object) {
        return indexOf(object) >= 0;
    }

    @Override
    public final Iterator<E> descendingIterator() {
        throw new UnsupportedOperationException(); // TODO
    }

    @Override
    public final E element() {
        return getFirst();
    }

    private final Node<E> find(final int index) {
        assert index >= 0 && index < size;
        if (index < (size >> 1)) {
            var node = first;
            for (int i = 0; i < index; i++)
                node = node.next;
            return node;
        } else {
            var node = last;
            for (int i = size - 1; i > index; i--)
                node = node.prev;
            return node;
        }
    }

    @Override
    public final E get(final int index) {
        checkIndex(index, size);
        return find(index).element;
    }

    @Override
    public final E getFirst() {
        if (first == null)
            throw new NoSuchElementException();
        return first.element;
    }

    @Override
    public final E getLast() {
        if (last == null)
            throw new NoSuchElementException();
        return last.element;
    }

    @Override
    public final int indexOf(final Object object) {
        int index = 0;
        if (object == null) {
            for (var node = first; node != null; ++index, node = node.next)
                if (node.element == null)
                    return index;
        } else
           for (var node = first; node != null; ++index, node = node.next)
                if (object.equals(node.element))
                    return index;
        return -1;
    }

    @Override
    public final ListIterator<E> listIterator(final int index) {
        throw new UnsupportedOperationException(); // TODO
    }

    @Override
    public final boolean offer(final E element) {
        return add(element);
    }

    @Override
    public final E peek() {
        return peekFirst();
    }

    @Override
    public final E peekFirst() {
        return (first == null) ? null : first.element;
    }

    @Override
    public final E peekLast() {
        return (last == null) ? null : last.element;
    }

    @Override
    public final E poll() {
        return (first == null) ? null : unlink(first);
    }

    @Override
    public final E pop() {
        return removeFirst();
    }

    @Override
    public final void push(final E element) {
        addFirst(element);
    }

    @Override
    public final E remove() {
        return removeFirst();
    }

    @Override
    public final E remove(final int index) {
        checkIndex(index, size);
        return unlink(find(index));
    }

    @Override
    public final boolean remove(final Object object) {
        if (object == null) {
            for (var node = first; node != null; node = node.next)
                if (node.element == null) {
                    unlink(node);
                    return true;
                }
        } else
           for (var node = first; node != null; node = node.next)
                if (object.equals(node.element)) {
                    unlink(node);
                    return true;
                }
        return false;
    }

    @Override
    public final E removeFirst() {
        if (first == null)
            throw new NoSuchElementException();
        return unlink(first);
    }

    @Override
    public final boolean removeFirstOccurrence(final Object object) {
        return remove(object);
    }

    @Override
    public final E removeLast() {
        if (last == null)
            throw new NoSuchElementException();
        return unlink(last);
    }

    @Override
    public final boolean removeLastOccurrence(final Object object) {
        if (object == null) {
            for (Node<E> node = last; node != null; node = node.prev)
                if (node.element == null) {
                    unlink(node);
                    return true;
                }
        } else
            for (Node<E> node = last; node != null; node = node.prev)
                if (object.equals(node.element)) {
                    unlink(node);
                    return true;
                }
        return false;
    }

    @Override
    public final E set(final int index, E element) {
        checkIndex(index, size);
        final var node = find(index);
        final var oldValue = node.element;
        node.element = element;
        return oldValue;
    }

    @Override
    public final int size() {
        return size;
    }

    @Override
    public final String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        var node = first;
        if (node != null) {
            stringBuilder.append(node.element);
            node = node.next;
        }
        for (; node != null; node = node.next) {
            stringBuilder.append(", ");
            stringBuilder.append(node.element);
        }
        return stringBuilder.append(']').toString();
    }

    private final E unlink(final Node<E> node) {
        assert node != null;
        final var element = node.element;
        final var prev = node.prev;
        final var next = node.next;

        --size;
        node.element = null;
        if (prev == null)
            first = next;
        else {
            prev.next = next;
            node.prev = null;
        }
        if (next == null)
            last = prev;
        else {
            next.prev = prev;
            node.next = null;
        }
        return element;
    }
}
