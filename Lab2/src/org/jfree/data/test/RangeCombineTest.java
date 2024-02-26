package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RangeCombineTest {

    private Range firstRange;
    private Range secondRange;

    @Before
    public void setUp() throws Exception {
        firstRange = new Range(0, 1);
        secondRange = new Range(-2,-1 );
    }

    @After
    public void tearDown() throws Exception {}

    @Test
    public void testCombineRanges() {
        Range combinedRange = Range.combine(firstRange, secondRange);
        assertEquals("Combined range lower bound should be minimum of both ranges' lower bounds", 1.0, combinedRange.getLowerBound(), 0.000001);
        assertEquals("Combined range upper bound should be maximum of both ranges' upper bounds", 1.0, combinedRange.getUpperBound(), 0.000001);
    }
}
