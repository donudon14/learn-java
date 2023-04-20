package com.github.donudon14.learn.java;

import java.util.EmptyStackException;

public final class Stack<E> extends ArrayList<E> {
    public Stack() {
    }

    public final boolean empty() {
        return isEmpty();
    }

    public final E peek() {
        final int size = size();
        if (size == 0)
            throw new EmptyStackException();
        return get(size - 1);
    }

    public final E pop() {
        peek(); // throws EmptyStackException if empty
        return remove(size() - 1);
    }

    public final E push​(final E element) {
        add(element);
        return element;
    }

    public final int search​(final Object object) {
        final int index = lastIndexOf(object);
        if (index >= 0)
            return size() - index;
        return -1;
    }
}
