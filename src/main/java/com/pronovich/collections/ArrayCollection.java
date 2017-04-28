package com.pronovich.collections;

/*
    The implementation of the Collection interface array-based.
*/

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ArrayCollection<T> implements Collection<T> {

    private T[] m = (T[])new Object[1];

    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(final Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (m[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(m[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator<>();
    }

    @Override
    public Object[] toArray() {
        Object[] newM = new Object[size];
        System.arraycopy(m, 0, newM, 0, size);
        return newM;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        T1[] newA = a;
        if (a.length < size) {
            newA = (T1[]) new Object[size];
        }
        System.arraycopy(m, 0, newA, 0, size);
        return newA;
    }

    @Override
    public boolean add(final T t) {
        if (m.length == size) {
            T[] newM = (T[]) new Object[size * 2];
            System.arraycopy(m, 0 , newM, 0, size);
            m = newM;
        }
        m[size++] = t;
        return true;
    }

    @Override
    public boolean remove(final Object o) {
        if (o == null) {
            for (int i=0; i < size; i++) {
                if (m[i] == null) {
                    System.arraycopy(m, i+1, m, i, size-i-1);
                    size--;
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(m[i])) {
                    System.arraycopy(m, i + 1, m, i, size - i - 1);
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (Object item: c) {
            if (!this.contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (T item: c) {
            this.add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        boolean modified = false;
        for (Object item: c) {
            if (this.remove(item)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        T[] newM = (T[]) new Object[size];
        int index = 0;
        for (T item: this) {
            if (c.contains(item)) {
                newM[index++] = item;
            }
        }
        m = newM;
        this.size = index;
        return true;
    }

    @Override
    public void clear() {
        m = (T[]) new Object[1];
        size = 0;
    }

    private class ElementsIterator<T> implements Iterator<T> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return size > index;
        }

        @Override
        public T next() {
            return (T) m[index++];
        }
    }
}
