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
import java.util.function.ToIntFunction;
import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;

public final class TreeMap<K, V> extends AbstractMap<K, V>
    implements NavigableMap<K, V>, Cloneable {
    private Entry<K, V> root = null;
    private final Comparator<? super K> comparator;
    private int size = 0;

    private static final class Entry<K, V> implements Map.Entry<K, V> {
        private K key = null;
        private V value = null;
        private Entry<K, V> left = null, parent = null, right = null;

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

    public TreeMap() {
        this.comparator = null;
    }

    public TreeMap(final Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    private final Entry<K, V> addEntry(
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
        return entry;
    }

    @Override
    public final Map.Entry<K, V> ceilingEntry(final K key) {
        return simpleImmutableEntry(getCeilingEntry(key));
    }

    @Override
    public final K ceilingKey(final K key) {
        return keyOrNull(getCeilingEntry(key));
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

    private final ToIntFunction<K> comparator(final Object object) {
        if (comparator == null) {
            requireNonNull(object);
            @SuppressWarnings("unchecked")
            final var comparable = (Comparable<? super K>) object;
            return (final K entryKey) -> comparable.compareTo(entryKey);
        }
        @SuppressWarnings("unchecked")
        final var key = (K) object;
        return (final K entryKey) -> comparator.compare(key, entryKey);
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
    public final NavigableMap<K, V> descendingMap() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final Map.Entry<K, V> firstEntry() {
        return simpleImmutableEntry(getFirstEntry());
    }

    @Override
    public final Map.Entry<K, V> floorEntry(final K key) {
        return simpleImmutableEntry(getFloorEntry(key));
    }

    @Override
    public final K floorKey(final K key) {
        return keyOrNull(getFloorEntry(key));
    }

    @Override
    public final V get(final Object object) {
        final var entry = getEntry(object);
        return entry == null ? null : entry.value;
    }

    private final Entry<K, V> getCeilingEntry(final K key) {
        final var comparator = comparator(key);
        for (var entry = root; entry != null; ) {
            final int result = comparator.applyAsInt(entry.key);
            if (result < 0) {
                if (entry.left == null)
                    return entry;
                entry = entry.left;
            } else if (result > 0) {
                if (entry.right == null) {
                    var parent = entry.parent;
                    while (parent != null && entry == parent.right) {
                        entry = parent;
                        parent = parent.parent;
                    }
                    return parent;
                }
                entry = entry.right;
            } else
                return entry;
        }
        return null;
    }

    private final Entry<K, V> getEntry(final Object object) {
        final var comparator = comparator(object);
        for (var entry = root; entry != null; ) {
            final int result = comparator.applyAsInt(entry.key);
            if (result < 0)
                entry = entry.left;
            else if (result > 0)
                entry = entry.right;
            else
                return entry;
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

    @Override
    public final K firstKey() {
        return keyOrNull(getFirstEntry());
    }

    private final Entry<K, V> getFloorEntry(final K key) {
        final var comparator = comparator(key);
        for (var entry = root; entry != null; ) {
            final int result = comparator.applyAsInt(entry.key);
            if (result > 0) {
                if (entry.right == null)
                    return entry;
                entry = entry.right;
            } else if (result < 0) {
                if (entry.left == null) {
                    var parent = entry.parent;
                    while (parent != null && entry == parent.left) {
                        entry = parent;
                        parent = parent.parent;
                    }
                    return parent;
                }
                entry = entry.left;
            } else
                return entry;
        }
        return null;
    }

    private final Entry<K, V> getHigherEntry(final K key) {
        final var comparator = comparator(key);
        for (var entry = root; entry != null; ) {
            final int result = comparator.applyAsInt(entry.key);
            if (result < 0) {
                if (entry.left == null)
                    return entry;
                entry = entry.left;
            } else {
                if (entry.right == null) {
                    var parent = entry.parent;
                    while (parent != null && entry == parent.right) {
                        entry = parent;
                        parent = entry.parent;
                    }
                    return parent;
                }
                entry = entry.right;
            }
        }
        return null;
    }

    private final Entry<K, V> getLastEntry() {
        var entry = root;
        if (entry != null)
            while (entry.right != null)
                entry = entry.right;
        return entry;
    }

    private final Entry<K, V> getLowerEntry(final K key) {
        final var comparator = comparator(key);
        for (var entry = root; entry != null; ) {
            final int result = comparator.applyAsInt(entry.key);
            if (result > 0) {
                if (entry.right == null)
                    return entry;
                entry = entry.right;
            } else {
                if (entry.left == null) {
                    var parent = entry.parent;
                    while (parent != null && entry == parent.left) {
                        entry = parent;
                        parent = entry.parent;
                    }
                    return parent;
                }
                entry = entry.left;
            }
        }
        return null;
    }

    @Override
    public final SortedMap<K, V> headMap​(final K toKey) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final NavigableMap<K, V> headMap​(
        final K toKey,
        final boolean inclusive
    ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final Map.Entry<K, V> higherEntry(final K key) {
        return simpleImmutableEntry(getHigherEntry(key));
    }

    @Override
    public final K higherKey(final K key) {
        return keyOrNull(getHigherEntry(key));
    }

    private static <K, V> K keyOrNull(final Entry<K, V> entry) {
        return entry == null ? null : entry.key;
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
    public final K lastKey() {
        return keyOrNull(getLastEntry());
    }

    @Override
    public final Map.Entry<K, V> lowerEntry(final K key) {
        return simpleImmutableEntry(getLowerEntry(key));
    }

    @Override
    public final K lowerKey(final K key) {
        return keyOrNull(getLowerEntry(key));
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
    public final Map.Entry<K, V> pollFirstEntry() {
        final var entry = getFirstEntry();
        final var result = simpleImmutableEntry(entry);
        if (entry != null)
            removeEntry(entry);
        return result;
    }

    @Override
    public final Map.Entry<K, V> pollLastEntry() {
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
        final var comparator = comparator(key);
        for (var entry = root; entry != null; ) {
            parent = entry;
            result = comparator.applyAsInt(entry.key);
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

    private static <K, V> Map.Entry<K, V> simpleImmutableEntry(
        final Entry<K, V> entry
    ) {
        return entry == null ? null : new SimpleImmutableEntry<>(entry);
    }

    @Override
    public final NavigableMap<K, V> subMap​(
        final K fromKey, final boolean fromInclusive,
        final K toKey, final boolean toInclusive
    ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final SortedMap<K, V> subMap​(final K fromKey, final K toKey) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final SortedMap<K, V> tailMap​(final K fromKey) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final NavigableMap<K, V> tailMap​(
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
