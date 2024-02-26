package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExpandRangeTest {

    private Range range;

    @Before
    public void setUp() throws Exception {
        range = new Range(0, 10);
    }

    @After
    public void tearDown() throws Exception {}

    @Test
    public void testExpandRangeWithMargins() {
        double lowerMargin = 2.0;
        double upperMargin = 3.0;
        Range expandedRange = Range.expand(range, lowerMargin, upperMargin);
        assertEquals("Expanded range lower bound should be original lower bound minus lower margin",
                     -2.0, expandedRange.getLowerBound(), 0.000001);
        assertEquals("Expanded range upper bound should be original upper bound plus upper margin",
                     13.0, expandedRange.getUpperBound(), 0.000001);
    }

    @Test
    public void testExpandRangeWithZeroMargins() {
        double lowerMargin = 0.0;
        double upperMargin = 0.0;
        Range expandedRange = Range.expand(range, lowerMargin, upperMargin);
        assertEquals("Expanded range should be the same as the original range",
                     range, expandedRange);
    }

    @Test
    public void testExpandRangeWithNegativeMargins() {
        double lowerMargin = -2.0;
        double upperMargin = 7.0;
        Range expandedRange = Range.expand(range, lowerMargin, upperMargin);
        assertEquals("Expanded range lower bound should be original lower bound plus lower margin",
                     2.0, expandedRange.getLowerBound(), 0.000001);
        assertEquals("Expanded range upper bound should be original upper bound minus upper margin",
                     3.0, expandedRange.getUpperBound(), 0.000001);
    }
}
