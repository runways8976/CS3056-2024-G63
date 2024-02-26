package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BooleanRangeContainsTest {

    private Range range;

    @Before
    public void setUp() throws Exception {
        range = new Range(0, 10);
    }

    @After
    public void tearDown() throws Exception {}

    @Test
    public void testContainsValueInsideRange() {
        double value = 5.0;
        assertTrue("Value inside range should return true", range.contains(value));
    }

    @Test
    public void testContainsValueBelowRange() {
        double value = -5.0;
        assertFalse("Value below range should return false", range.contains(value));
    }

    @Test
    public void testContainsValueAboveRange() {
        double value = 15.0;
        assertFalse("Value above range should return false", range.contains(value));
    }

    @Test
    public void testContainsValueEqualToLowerBound() {
        double value = 0.0;
        assertTrue("Value equal to lower bound should return true", range.contains(value));
    }

    @Test
    public void testContainsValueEqualToUpperBound() {
        double value = 10.0;
        assertTrue("Value equal to upper bound should return true", range.contains(value));
    }
}
