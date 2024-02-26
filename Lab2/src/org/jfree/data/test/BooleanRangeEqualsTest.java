package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BooleanRangeEqualsTest {

    private Range range1;
    private Range range2;

    @Before
    public void setUp() throws Exception {
        range1 = new Range(0, 10);
        range2 = new Range(0, 10);
    }

    @After
    public void tearDown() throws Exception {}

    @Test
    public void testEqualsSameRangeObject() {
        assertTrue("Same Range objects should be equal", range1.equals(range1));
    }

    @Test
    public void testEqualsIdenticalRanges() {
        assertTrue("Ranges with same lower and upper bounds should be equal", range1.equals(range2));
    }

    @Test
    public void testEqualsDifferentObjectTypes() {
        assertFalse("Range should not be equal to a different type of object", range1.equals("string"));
    }

    @Test
    public void testEqualsNullObject() {
        assertFalse("Range should not be equal to null", range1.equals(null));
    }

    @Test
    public void testEqualsDifferentLowerBound() {
        Range differentRange = new Range(-5, 10);
        assertFalse("Ranges with different lower bounds should not be equal", range1.equals(differentRange));
    }

    @Test
    public void testEqualsDifferentUpperBound() {
        Range differentRange = new Range(0, 20);
        assertFalse("Ranges with different upper bounds should not be equal", range1.equals(differentRange));
    }
}
