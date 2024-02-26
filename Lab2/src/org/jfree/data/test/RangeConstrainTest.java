package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RangeConstrainTest {

    private Range range;

    @Before
    public void setUp() throws Exception {
        range = new Range(0, 10);
    }

    @After
    public void tearDown() throws Exception {}

    @Test
    public void testConstrainValueInsideRange() {
        double value = 5.0;
        double constrainedValue = range.constrain(value);
        assertEquals("Constrained value inside range should be equal to original value", value, constrainedValue, 0.000001);
    }

    @Test
    public void testConstrainValueBelowRange() {
        double value = -5.0;
        double constrainedValue = range.constrain(value);
        assertEquals("Constrained value below range should be equal to lower bound of range", range.getLowerBound(), constrainedValue, 0.000001);
    }

    @Test
    public void testConstrainValueAboveRange() {
        double value = 15.0;
        double constrainedValue = range.constrain(value);
        assertEquals("Constrained value above range should be equal to upper bound of range", range.getUpperBound(), constrainedValue, 0.000001);
    }

    @Test
    public void testConstrainValueInBetweenRange() {
        double value = 7.5;
        double constrainedValue = range.constrain(value);
        assertEquals("Constrained value in between range should be equal to original value", value, constrainedValue, 0.000001);
    }
}
