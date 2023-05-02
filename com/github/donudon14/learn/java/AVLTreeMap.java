package com.github.donudon14.learn.java;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import static java.lang.Math.abs;
import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;

public final class AVLTreeMap<K, V> extends AbstractMap<K, V>
    implements NavigableMap<K, V>, Cloneable {
    private Entry<K, V> root = null;
    private final Comparator<? super K> comparator;
    private int size = 0;

    private static final class Entry<K, V> implements Map.Entry<K, V> {
        private K key = null;
        private V value = null;
        private Entry<K, V> left = null, parent = null, right = null;
        private byte balance = 0;

        private Entry(
            final K key,
            final V value,
            final Entry<K, V> parent
        ) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        @Override
        public final K getKey() {
            return key;
        }

        @Override
        public final V getValue() {
            return value;
        }

        @Override
        public final V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public final boolean equals(final Object object) {
            if (!(object instanceof Map.Entry<?, ?>))
                return false;
            final var entry = (Map.Entry<?, ?>) object;
            return Objects.equals(key, entry.getKey()) &&
                Objects.equals(value, entry.getValue());
        }

        @Override
        public final int hashCode() {
            return hash(Objects.hashCode(key), Objects.hashCode(value));
        }

        @Override
        public final String toString() {
            return key + "=" + value;
        }
    }

    public AVLTreeMap() {
        this.comparator = null;
    }

    public AVLTreeMap(final Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    private final void addEntry(
        final K key,
        final V value,
        final Entry<K, V> parent,
        final boolean addToLeft
    ) {
        assert comparator != null || key != null;
        final var entry = new Entry<K, V>(key, value, parent);
        if (parent == null)
            root = entry;
        else if (addToLeft)
            parent.left = entry;
        else
            parent.right = entry;
        ++size;
        rebalanceInsertion(entry);
    }

    @Override
    public final void clear() {
        root = null;
        size = 0;
    }

    @Override
    public final Comparator<? super K> comparator() {
        return comparator;
    }

    @Override
    public final boolean containsKey(final Object object) {
        return getEntry(object) != null;
    }

    @Override
    public final NavigableSet<K> descendingKeySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final NavigableMap<K,V> descendingMap() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final Set<Map.Entry<K,V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final Map.Entry<K, V> firstEntry() {
        return simpleImmutableEntry(getFirstEntry());
    }

    @Override
    public final V get(final Object object) {
        final var entry = getEntry(object);
        return entry == null ? null : entry.value;
    }

    private final Entry<K, V> getEntry(final Object object) {
        if (comparator == null) {
            requireNonNull(object);
            @SuppressWarnings("unchecked")
            final var comparable = (Comparable<? super K>) object;
            for (var entry = root; entry != null; ) {
                final int result = comparable.compareTo(entry.key);
                if (result < 0)
                    entry = entry.left;
                else if (result > 0)
                    entry = entry.right;
                else
                    return entry;
            }
        } else {
            @SuppressWarnings("unchecked")
            final var key = (K) object;
            for (var entry = root; entry != null; ) {
                final int result = comparator.compare(key, entry.key);
                if (result < 0)
                    entry = entry.left;
                else if (result > 0)
                    entry = entry.right;
                else
                    return entry;
            }
        }
        return null;
    }

    private final Entry<K, V> getFirstEntry() {
        var entry = root;
        if (entry != null)
            while (entry.left != null)
                entry = entry.left;
        return entry;
    }

    private final Entry<K, V> getLastEntry() {
        var entry = root;
        if (entry != null)
            while (entry.right != null)
                entry = entry.right;
        return entry;
    }

    @Override
    public final SortedMap<K,V> headMap​(final K toKey) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final NavigableMap<K,V> headMap​(
        final K toKey,
        final boolean inclusive
    ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final Map.Entry<K, V> lastEntry() {
        return simpleImmutableEntry(getLastEntry());
    }

    @Override
    public final NavigableSet<K> navigableKeySet() {
        throw new UnsupportedOperationException();
    }

    private static <K, V> Entry<K, V> next(Entry<K, V> entry) {
        assert entry != null;
        if (entry.right != null) {
            entry = entry.right;
            while (entry.left != null)
                entry = entry.left;
            return entry;
        }
        var parent = entry.parent;
        while (parent != null && entry == parent.right) {
            entry = parent;
            parent = entry.parent;
        }
        return parent;
    }

    @Override
    public final Map.Entry<K,V> pollFirstEntry() {
        final var entry = getFirstEntry();
        final var result = simpleImmutableEntry(entry);
        if (entry != null)
            removeEntry(entry);
        return result;
    }

    @Override
    public final Map.Entry<K,V> pollLastEntry() {
        final var entry = getFirstEntry();
        final var result = simpleImmutableEntry(entry);
        if (entry != null)
            removeEntry(entry);
        return result;
    }

    private static <K, V> Entry<K, V> previous(Entry<K, V> entry) {
        assert entry != null;
        if (entry.left != null) {
            entry = entry.left;
            while (entry.right != null)
                entry = entry.right;
            return entry;
        }
        var parent = entry.parent;
        while (parent != null && entry == parent.left) {
            entry = parent;
            parent = entry.parent;
        }
        return parent;
    }

    @Override
    public final V put(final K key, final V value) {
        return put(key, value, true);
    }

    private final V put(final K key, final V value, final boolean replaceOld) {
        Entry<K, V> parent = null;
        int result = 0;
        if (comparator == null) {
            requireNonNull(key);
            @SuppressWarnings("unchecked")
            final var comparable = (Comparable<? super K>) key;
            for (var entry = root; entry != null; ) {
                parent = entry;
                result = comparable.compareTo(entry.key);
                if (result < 0)
                    entry = entry.left;
                else if (result > 0)
                    entry = entry.right;
                else {
                    final var oldValue = entry.value;
                    if (replaceOld || entry.value == null)
                        entry.value = value;
                    return oldValue;
                }
            }
        } else
            for (var entry = root; entry != null; ) {
                parent = entry;
                result = comparator.compare(key, entry.key);
                if (result < 0)
                    entry = entry.left;
                else if (result > 0)
                    entry = entry.right;
                else {
                    final var oldValue = entry.value;
                    if (replaceOld || entry.value == null)
                        entry.value = value;
                    return oldValue;
                }
            }
        addEntry(key, value, parent, result < 0);
        return null;
    }

    @Override
    public final V putIfAbsent(final K key, final V value) {
        return put(key, value, false);
    }

    private final void rebalanceInsertion(Entry<K, V> entry) {
        for (var parent = entry.parent; parent != null;
            entry = parent, parent = entry.parent
        ) {
            if (entry == parent.left)
                --parent.balance;
            else
                ++parent.balance;
            if (parent.balance < -1 || parent.balance > 1) {
                rebalance(parent);
                parent = parent.parent;
            }
            if (parent.balance == 0)
                break;
        }
    }

    private final Entry<K, V> rebalance(final Entry<K, V> entry) {
        assert abs(entry.balance) == 2;
        if (entry.balance < -1) {
            if (entry.left.balance > 0)
                rotateLeftRight(entry);
            else
                rotateRight(entry);
        } else {
            if (entry.right.balance < 0)
                rotateRightLeft(entry);
            else
                rotateLeft(entry);
        }
    }

    @Override
    public final V remove(final Object object) {
        final var node = getEntry(object);
        return node == null ? null : removeEntry(node);
    }

    private final V removeEntry(Entry<K, V> entry) {
        assert entry != null;
        final var value = entry.value;
        if (entry.left != null && entry.right != null) {
            final var next = next(entry);
            entry.key = next.key;
            entry.value = next.value;
            entry = next;
        }
        assert entry.left == null || entry.right == null;
        final var replacement = entry.left == null ? entry.right : entry.left;
        if (entry.parent == null)
            root = replacement;
        else if (entry == entry.parent.left)
            entry.parent.left = replacement;
        else
            entry.parent.right = replacement;
        if (replacement != null)
            replacement.parent = entry.parent;
        entry.key = null;
        entry.value = null;
        entry.left = entry.parent = entry.right = null;
        --size;
        return value;
    }

    private final void rotateLeft(final Entry<K, V> entry) {
        assert entry != null && entry.right != null;
        final var right = entry.right;
        entry.right = right.left;
        if (right.left != null)
            right.left.parent = entry;
        right.parent = entry.parent;
        if (entry.parent == null)
            root = right;
        else if (entry == entry.parent.left)
            entry.parent.left = right;
        else
            entry.parent.right = right;
        right.left = entry;
        entry.parent = right;
        if (right.balance == 0) {
            entry.balance = 1;
            right.balance = -1;
        } else
            entry.balance = right.balance = 0;
    }

    private final void rotateRight(final Entry<K, V> entry) {
        assert entry != null && entry.left != null;
        final var left = entry.left;
        entry.left = left.right;
        if (left.right != null)
            left.right.parent = entry;
        left.parent = entry.parent;
        if (entry.parent == null)
            root = left;
        else if (entry == entry.parent.right)
            entry.parent.right = left;
        else
            entry.parent.left = left;
        left.right = entry;
        entry.parent = left;
        if (left.balance == 0) {
            entry.balance = -1;
            left.balance = 1;
        } else
            entry.balance = left.balance = 0;
    }

    private final void rotateLeftRight(final Entry<K, V> entry) {
        rotateLeft(entry.left);
        rotateRight(entry);
    }

    private final void rotateRightLeft(final Entry<K, V> entry) {
        rotateRight(entry.right);
        rotateLeft(entry);
    }

    private static <K, V> Map.Entry<K,V> simpleImmutableEntry(
        final Entry<K, V> entry
    ) {
        return entry == null ? null : new SimpleImmutableEntry<>(entry);
    }

    @Override
    public final NavigableMap<K,V> subMap​(
        final K fromKey, final boolean fromInclusive,
        final K toKey, final boolean toInclusive
    ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final SortedMap<K,V> subMap​(final K fromKey, final K toKey) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final SortedMap<K,V> tailMap​(final K fromKey) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final NavigableMap<K,V> tailMap​(
        final K fromKey,
        final boolean inclusive
    ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final Collection<V> values() {
        throw new UnsupportedOperationException();
    }
}
