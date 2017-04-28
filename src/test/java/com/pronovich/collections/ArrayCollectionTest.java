package com.pronovich.collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class ArrayCollectionTest {

    @Test
    public void testSizeWhenSizeIs0() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        assertEquals(0, testInstance.size());
    }

    @Test
    public void testSizeWhenSizeIs1() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(10);
        assertEquals(1, testInstance.size());
    }

    @Test
    public void testIsEmptyWhenEmpty() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        assertTrue(testInstance.isEmpty());
    }

    @Test
    public void testIsEmptyWhenIsNoEmpty() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(10);
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testContains() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);

        assertTrue(testInstance.contains(1));
        assertFalse(testInstance.contains(0));
    }

    @Test
    public void testContainsNull() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(null);

        assertTrue(testInstance.contains(1));
        assertTrue("Collection contains null", testInstance.contains(null));
        assertFalse(testInstance.contains(0));
    }

    @Test
    public void testIterator() throws RuntimeException {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        for (int i = 3; i < 15; i++) {
            testInstance.add(i);
        }
        int i = 0;
        System.out.println("\n testIterator");
        System.out.format("Size: %s\n", testInstance.size());
        for (final Integer iT : testInstance) {
            System.out.format("Element %s: %s \n", i++, iT);
        }
        if (i != testInstance.size()) {
            throw new RuntimeException("ElementsIterator don't working!!!!");
        }
    }

    @Test
    public void testToArray() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        for (int i = 1; i < 15; i++) {
            testInstance.add(i);
        }
        testInstance.remove(10);
        testInstance.remove(8);
        if (testInstance.toArray().length != testInstance.size()) {
            throw new RuntimeException("The returned array does not match the length of the collection.");
        }
    }

    @Test
    public void testToArrayLessLengthWithType() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        for (int i = 1; i < 4; i++) {
            testInstance.add(i);
        }
        System.out.println( Arrays.toString(testInstance.toArray(new Integer[4])) );
    }

    @Test
    public void testToArrayWithType() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        for (int i = 1; i < 15; i++) {
            testInstance.add(i);
        }
        System.out.println( Arrays.toString(testInstance.toArray(new Integer[2])) );
    }

    @Test
    public void testToArrayWhenInputArrayHaveSizeOne() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[1];

        final Integer[] result = testInstance.toArray(input);
        assertNotEquals(input, result);
        assertEquals((Integer)1, result[0]);
        assertEquals((Integer)2, result[1]);
        assertEquals((Integer)3, result[2]);
        assertEquals(3, result.length);
    }

    @Test
    public void testToArrayWhenInputArrayHaveCorrectSize() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[3];

        final Integer[] result = testInstance.toArray(input);
        assertEquals(input, result);
    }

    @Test
    public void testAdd() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        for (int i = -10; i < 15; i++) {
            testInstance.add(i);
        }

        assertEquals(25, testInstance.size());
        assertFalse(testInstance.isEmpty());
        int i = 0;
        System.out.println("\n testAdd");
        System.out.format("Size: %s\n", testInstance.size());
        for (final Integer iT : testInstance) {
            System.out.format("Element %s: %s \n", i++, iT);
        }
    }

    @Test
    public void testRemoveFirstElement() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.remove(1);

        assertEquals(1, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveCenterElement() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        testInstance.remove(3);

        assertEquals(4, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveLastElement() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.remove(4);

        assertEquals(3, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveNull() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(2);
        testInstance.add(4);
        testInstance.add(null);
        testInstance.add(4);
        testInstance.remove(null);

        assertEquals(3, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testContainsAll() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(1);

        assertTrue(testInstance.containsAll(testInstance2));
    }

    @Test
    public void testContainsAllWithNull() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(null);
        testInstance.add(7);

        testInstance2.add(2);
        testInstance2.add(null);

        assertTrue(testInstance.containsAll(testInstance2));
    }

    @Test
    public void testAddAll() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(3);
        testInstance2.add(4);
        testInstance.addAll(testInstance2);

        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(4));
    }

    @Test
    public void testRemoveAll() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);

        testInstance2.add(2);
        testInstance2.add(3);

        boolean modified = testInstance.removeAll(testInstance2);

        assertEquals(2, testInstance.size());
        assertTrue(testInstance.contains(1));
        assertFalse(testInstance.contains(2));
        assertTrue("Has been modified", modified);
    }

    @Test
    public void testRemoveAllWithoutRemoving() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);

        testInstance2.add(9);
        testInstance2.add(8);

        boolean modified = testInstance.removeAll(testInstance2);

        assertEquals(4, testInstance.size());
        assertTrue(testInstance.contains(1));
        assertFalse(testInstance.contains(8));
        assertFalse("Hasn't been modified", modified);
    }


    @Test
    public void testRetainAll() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(3);
        testInstance.add(3);
        testInstance.add(6);
        testInstance.add(7);
        testInstance.add(8);
        testInstance.add(9);

        testInstance2.add(3); //0 size1
        testInstance2.add(4); //1 size2
        testInstance2.add(5); //2 size3
        testInstance2.add(6); //3 size4

        testInstance.retainAll(testInstance2);
        int i = 0;
        System.out.println("\n testRetainAll");
        System.out.format("the current size: %s \n", testInstance.size());
        for (final Integer iT : testInstance) {
            System.out.format("Element %s: %s \n", i++, iT);
        }
        assertEquals(4, testInstance.size());
        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(6));
    }

    @Test
    public void testClear() throws Exception {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(1);

        testInstance.clear();

        assertTrue(testInstance.isEmpty());
        assertEquals(0, testInstance.size());
    }

}