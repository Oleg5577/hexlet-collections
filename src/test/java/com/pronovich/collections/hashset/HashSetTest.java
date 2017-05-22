package com.pronovich.collections.hashset;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HashSetTest {

    private Set<String> s;

    @Before
    public void setUp() {
        s = new HashSet<>();
    }

    @Test
    public void addTheSameTest() {
        s.add("1");
        s.add("1");
        assertEquals("set size does not return 1 after adding same element twice", 1, s.size());
        assertTrue("set contains do not return true for the element that was added twice", s.contains("1"));
    }

    @Test
    public void addDifferentTest() {

        s.add("2");
        s.add("3");
        assertEquals("set size is not returning the correct size", 2, s.size());
        assertTrue("set contains is not working correctly", s.contains("2"));
        assertTrue("set contains is not working correctly", s.contains("3"));
    }

    @Test
    public void removeTest() {
        s.add("1");
        s.remove("1");
        assertEquals("set size does not return 0 when all elements are removed from the set", 0, s.size());
        assertTrue("set isEmpty do not return true when the set size is 0", s.isEmpty());
    }

    @Test
    public void addAllTest() {
        s.add("2");
        s.add("3");

        final Set<String> s2 = new HashSet<>();
        s2.add("1");

        s.addAll(s2);
        assertEquals("addAll is not working correctly", 3, s.size());
        assertTrue("addAll is not working correctly", s.contains("1"));
    }
}