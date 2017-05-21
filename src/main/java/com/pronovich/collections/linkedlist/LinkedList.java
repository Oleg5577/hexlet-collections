package com.pronovich.collections.linkedlist;

 /**
    LinkedList implementation
 */

import java.util.List;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;

public class LinkedList<T> implements List<T> {

    private Item<T> first = null;

    private Item<T> last = null;

    private int size = 0;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        Iterator<T> it = this.iterator();
        for (int i = 0; i < size; i++) {
            arr[i] = it.next();
        }
        return arr;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            a = (T1[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = a;
        for (Item<T> x = first; x != null; x = x.next) {
            result[i++] = x.element;
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(final T t) {
        Item<T> item = new Item<T>(t, last, null);
        if (last != null) {
            last.next = item;
        }
        last = item;
        if (first == null) {
            first = item;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(final Object o) {
        if (o == null) {
            for (Item<T> item = first; item != null; item = item.next) {
                if (item.element == null) {
                    remove(item);
                    return true;
                }
            }
        } else {
            for (Item<T> item = first; item != null; item = item.next) {
                if (o.equals(item.element)) {
                    remove(item);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        boolean modified = false;
        Iterator<T> it = this.listIterator();
        while (it.hasNext()) {
            if (c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        for (final Object item : this) {
            if (!c.contains(item)) {
                this.remove(item);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        Item<T> e = first;
        while (e != first) {
            Item<T> next = e.next;
            e.next = e.prev = null;
            e.element = null;
            e = next;
        }
        first.next = first.prev = first;
        size = 0;
    }

    @Override
    public T remove(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return remove(entry(index));
    }

    private Item<T> entry(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index +
                    ", Size: " + size);
        }
        Item<T> e = first;
        if (index < (size >> 1)) {
            for (int i = 0; i <= index; i++) {
                e = e.next;
            }
        } else {
            for (int i = size; i > index; i--) {
                e = e.prev;
            }
        }
        return e;
    }

    @Override
    public List<T> subList(final int start, final int end) {
        return null;
    }

    @Override
    public ListIterator listIterator() {
        return new ElementsIterator(0);
    }

    @Override
    public ListIterator listIterator(final int index) {
        return new ElementsIterator(index);
    }

    @Override
    public int lastIndexOf(final Object target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(final Object target) {
        int index = 0;
        for (T item : this) {
            if (item.equals(target)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public void add(final int index, final T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(final int index, final Collection elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(final int index, final T element) {
        Item<T> e = entry(index);
        T oldVal = e.element;
        e.element = element;
        return oldVal;
    }

    @Override
    public T get(final int index) {
        ListIterator<T> it = this.listIterator();
        while (it.hasNext()) {
            T item = it.next();
            if (it.previousIndex() == index) {
                return item;
            }
        }
        return null;
    }

    private T remove(Item<T> item) {
        if (item == null) {
            throw new NoSuchElementException();
        }
        final T result = item.element;
        final Item<T> next = item.next;
        final Item<T> prev = item.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            item.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            item.next = null;
        }
        item.element = null;
        size--;
        return result;
    }

    private class ElementsIterator implements ListIterator<T> {

        private Item<T> current;

        private Item<T> previous;

        private int index = 0;

        private int previousIndex = -1;

        public ElementsIterator() {
            this(0);
        }

        public ElementsIterator(final int index) {
            this.current = LinkedList.this.first;
            if (!LinkedList.this.isEmpty()) {
                this.previous = LinkedList.this.first.getPrev();
            }
            this.index = index;
            previousIndex = index - 1;
            while (this.index != index) {
                this.next();
            }
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T element = current.getElement();
            previousIndex = index;
            index++;
            previous = current;
            current = current.getNext();
            return element;
        }

        @Override
        public void add(final T element) {
            LinkedList.this.add(element);
        }

        @Override
        public void set(final T element) {
            if (previousIndex < 0) {
                throw new IllegalStateException();
            }
            previous.element = element;
        }

        @Override
        public int previousIndex() {
            return previousIndex;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public boolean hasPrevious() {
            return previous != null;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            T element = previous.getElement();
            index = previousIndex;
            previousIndex--;
            current = previous;
            previous = current.getPrev();
            return element;
        }

        //TODO
        @Override
        public void remove() {
            if (previousIndex < 0) {
                throw new IllegalStateException();
            }
            Item<T> lastNext = previous.next;
            try {
                LinkedList.this.remove(previous);
            } catch (NoSuchElementException e) {
                throw new IllegalStateException();
            }
            if (current == previous) {
                current = lastNext;
            } else {
                index--;
            }
            previous = null;
            previousIndex = -1;
        }

    }

    private static class Item<T> {

        private T element;

        private Item<T> next;

        private Item<T> prev;

        public Item(final T element, final Item<T> prev, final Item<T> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public T getElement() {
            return element;
        }

        public Item<T> getNext() {
            return next;
        }

        public Item<T> getPrev() {
            return prev;
        }
    }
}
