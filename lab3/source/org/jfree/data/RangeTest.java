package org.jfree.data;

import org.junit.Test;
import static org.junit.Assert.*;

public class RangeTest {
    
	private Range range;

    @Test
    public void testConstructorAndGetters() {
        Range range = new Range(0.0, 10.0);
        assertEquals(0.0, range.getLowerBound(), 0.0001);
        assertEquals(10.0, range.getUpperBound(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConstructor() {
        // This should throw IllegalArgumentException because lower > upper
        new Range(10.0, 0.0);
    }

    @Test
    public void testContains() {
        Range range = new Range(0.0, 10.0);
        assertTrue(range.contains(5.0));
        assertFalse(range.contains(-1.0));
        assertFalse(range.contains(15.0));
    }

    @Test
    public void testIntersectsBelow() {
        Range range = new Range(5, 10);
        assertFalse(range.intersects(2, 4)); 
    }

    @Test
    public void testIntersectsAbove() {
        Range range = new Range(5, 10);
        assertFalse(range.intersects(11, 15)); 
    }

    @Test
    public void testIntersectsPartialLower() {
        Range range = new Range(5, 10);
        assertTrue(range.intersects(4, 6)); 
    }

    @Test
    public void testIntersectsPartialUpper() {
        Range range = new Range(5, 10);
        assertTrue(range.intersects(9, 12)); 
    }

    @Test
    public void testIntersectsInside() {
        Range range = new Range(5, 10);
        assertTrue(range.intersects(6, 9)); 
    }

    @Test
    public void testIntersectsEncapsulates() {
        Range range = new Range(5, 10);
        assertTrue(range.intersects(4, 11)); 
    }
    @Test
    public void testIntersectsStartsWithinEndsBeyond() {
        Range range = new Range(5, 10);
        assertTrue(range.intersects(7, 12)); 
    }

  


    @Test
    public void testConstrain() {
        Range range = new Range(0.0, 10.0);
        assertEquals(5.0, range.constrain(5.0), 0.0001);
        assertEquals(0.0, range.constrain(-5.0), 0.0001);
        assertEquals(10.0, range.constrain(15.0), 0.0001);
    }
   

    @Test
    public void testCombine() {
        Range range1 = new Range(0.0, 5.0);
        Range range2 = new Range(5.0, 10.0);
        Range combinedRange = Range.combine(range1, range2);
        assertEquals(0.0, combinedRange.getLowerBound(), 0.0001);
        assertEquals(10.0, combinedRange.getUpperBound(), 0.0001);
    }
    @Test
    public void testCombineWithBothRangesNull() {
        assertNull(Range.combine(null, null));
    }

    @Test
    public void testCombineWithFirstRangeNull() {
        Range range2 = new Range(5, 10);
        assertEquals(range2, Range.combine(null, range2));
    }

    @Test
    public void testCombineWithSecondRangeNull() {
        Range range1 = new Range(1, 4);
        assertEquals(range1, Range.combine(range1, null));
    }

    @Test
    public void testCombineWithBothRangesNonNull() {
        Range range1 = new Range(1, 4);
        Range range2 = new Range(3, 7);
        Range combined = Range.combine(range1, range2);
        assertEquals(1.0, combined.getLowerBound(), 0.0001);
        assertEquals(7.0, combined.getUpperBound(), 0.0001);
    }

    
    @Test
    public void testExpandToInclude() {
        // Case 1: Range is null
        Range result = Range.expandToInclude(null, 5.0);
        assertEquals("Range should start at 5.0", 5.0, result.getLowerBound(), 0.0);
        assertEquals("Range should end at 5.0", 5.0, result.getUpperBound(), 0.0);

        // Case 2: Value is less than the lower bound
        result = Range.expandToInclude(new Range(2.0, 3.0), 1.0);
        assertEquals("Lower bound should be adjusted to 1.0", 1.0, result.getLowerBound(), 0.0);

        // Case 3: Value is greater than the upper bound
        result = Range.expandToInclude(new Range(1.0, 2.0), 3.0);
        assertEquals("Upper bound should be adjusted to 3.0", 3.0, result.getUpperBound(), 0.0);

        // Case 4: Value is within the range
        Range initialRange = new Range(1.0, 3.0);
        result = Range.expandToInclude(initialRange, 2.0);
        assertSame("Range should remain unchanged", initialRange, result);
    }
    @Test
    public void testExpand() {
        Range range = new Range(2.0, 6.0);
        Range expandedRange = Range.expand(range, 0.25, 0.5);
        assertEquals(1.0, expandedRange.getLowerBound(), 0.0001);
        assertEquals(8.0, expandedRange.getUpperBound(), 0.0001);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testExpandWithNullRange() {
        Range.expand(null, 0.25, 0.5);
    }


   
    


    @Test
    public void testShiftWithNoZeroCrossing() {
        // Test case 1: Value is positive, delta is positive
        assertEquals(15.0, Range.shiftWithNoZeroCrossing(10.0, 5.0), 0.001);

        // Test case 2: Value is positive, delta is negative, not crossing zero
        assertEquals(5.0, Range.shiftWithNoZeroCrossing(10.0, -5.0), 0.001);

        // Test case 3: Value is positive, delta is negative, crossing to zero
        assertEquals(0.0, Range.shiftWithNoZeroCrossing(5.0, -10.0), 0.001);

        // Test case 4: Value is negative, delta is negative
        assertEquals(-15.0, Range.shiftWithNoZeroCrossing(-10.0, -5.0), 0.001);

        // Test case 5: Value is negative, delta is positive, not crossing zero
        assertEquals(-5.0, Range.shiftWithNoZeroCrossing(-10.0, 5.0), 0.001);

        // Test case 6: Value is negative, delta is positive, crossing to zero
        assertEquals(0.0, Range.shiftWithNoZeroCrossing(-5.0, 10.0), 0.001);

        // Test case 7: Value is zero, delta is positive
        assertEquals(5.0, Range.shiftWithNoZeroCrossing(0.0, 5.0), 0.001);

        // Test case 8: Value is zero, delta is negative
        assertEquals(-5.0, Range.shiftWithNoZeroCrossing(0.0, -5.0), 0.001);
    }

	@Test
    public void testEqualsSelf() {
        Range range = new Range(1, 2);
        assertTrue(range.equals(range));
    }

    @Test
    public void testEqualsDifferentClass() {
        Range range = new Range(1, 2);
        Object obj = new Object();
        assertFalse(range.equals(obj));
    }

    @Test
    public void testEqualsNull() {
        Range range = new Range(1, 2);
        assertFalse(range.equals(null));
    }

    @Test
    public void testEqualsDifferentRanges() {
        Range range1 = new Range(1, 2);
        Range range2 = new Range(2, 3);
        assertFalse(range1.equals(range2));
    }

    @Test
    public void testEqualsIdenticalRanges() {
        Range range1 = new Range(1, 2);
        Range range2 = new Range(1, 2);
        assertTrue(range1.equals(range2));
    }

	@Test
	public void testShiftAllowZeroCrossing() {
	    // Allowing zero crossing
	    Range original = new Range(-1.0, 1.0);
	    double delta = 2.0;
	    Range result = Range.shift(original, delta, true);
	    assertEquals("The lower bound should have been shifted by delta", 1.0, result.getLowerBound(), 0.001);
	    assertEquals("The upper bound should have been shifted by delta", 3.0, result.getUpperBound(), 0.001);
	}
	
	@Test
	public void testShiftNoZeroCrossing() {
	    // Not allowing zero crossing
	    Range original = new Range(-1.0, 1.0);
	    double delta = 2.0;
	    Range result = Range.shift(original, delta, false);
	    assertEquals("The lower bound should not cross zero", 0.0, result.getLowerBound(), 0.001);
	    assertEquals("The upper bound should be shifted by delta", 3.0, result.getUpperBound(), 0.001);
	}
	
	@Test
	public void testShiftZeroDelta() {
	    // Zero delta
	    Range original = new Range(-1.0, 1.0);
	    double delta = 0.0;
	    Range result = Range.shift(original, delta, true);
	    assertEquals("The range should not change when delta is zero", original, result);
	}
	
	@Test
	public void testShiftNegativeDelta() {
	    // Negative delta
	    Range original = new Range(1.0, 3.0);
	    double delta = -2.0;
	    Range result = Range.shift(original, delta, true);
	    assertEquals("The lower bound should be shifted by negative delta", -1.0, result.getLowerBound(), 0.001);
	    assertEquals("The upper bound should be shifted by negative delta", 1.0, result.getUpperBound(), 0.001);
	}
	
	@Test
	public void testShiftPositiveDelta() {
	    // Positive delta
	    Range original = new Range(-3.0, -1.0);
	    double delta = 2.0;
	    Range result = Range.shift(original, delta, true);
	    assertEquals("The lower bound should be shifted by positive delta", -1.0, result.getLowerBound(), 0.001);
	    assertEquals("The upper bound should be shifted by positive delta", 1.0, result.getUpperBound(), 0.001);
	}
	
	@Test
	public void testShiftEdgeCaseZeroRange() {
	    // Edge case with zero range
	    Range original = new Range(0.0, 0.0);
	    double delta = 2.0;
	    Range result = Range.shift(original, delta, true);
	    assertEquals("The lower bound should be shifted by delta", 2.0, result.getLowerBound(), 0.001);
	    assertEquals("The upper bound should be shifted by delta", 2.0, result.getUpperBound(), 0.001);
	}
	@Test
    public void testShiftPositiveDelta1() {
        // Positive delta
        Range base = new Range(1.0, 3.0);
        double delta = 2.0;
        Range result = Range.shift(base, delta);
        assertEquals("The lower bound should be shifted up by delta", 3.0, result.getLowerBound(), 0.001);
        assertEquals("The upper bound should be shifted up by delta", 5.0, result.getUpperBound(), 0.001);
    }

    @Test
    public void testShiftNegativeDelta1() {
        // Negative delta
        Range base = new Range(2.0, 4.0);
        double delta = -1.0;
        Range result = Range.shift(base, delta);
        assertEquals("The lower bound should be shifted down by delta", 1.0, result.getLowerBound(), 0.001);
        assertEquals("The upper bound should be shifted down by delta", 3.0, result.getUpperBound(), 0.001);
    }

    @Test
    public void testShiftZeroDelta1() {
        // Zero delta
        Range base = new Range(-2.0, 2.0);
        double delta = 0.0;
        Range result = Range.shift(base, delta);
        assertEquals("The range should not change when delta is zero", base, result);
    }

    @Test
    public void testShiftDeltaCrossingZero() {
        // Delta that would cross zero if allowed
        Range base = new Range(1.0, 3.0);
        double delta = -2.0;
        Range result = Range.shift(base, delta);
        assertEquals("The lower bound should not cross zero", 0.0, result.getLowerBound(), 0.001);
        assertEquals("The upper bound should be shifted down by delta", 1.0, result.getUpperBound(), 0.001);
    }@Test
    public void testGetCentralValuePositiveRange() {
        Range range = new Range(2.0, 10.0);
        assertEquals("The central value of a positive range should be correctly calculated",
                     6.0, range.getCentralValue(), 0.0000001);
    }

    @Test
    public void testGetCentralValueNegativeRange() {
        Range range = new Range(-10.0, -2.0);
        assertEquals("The central value of a negative range should be correctly calculated",
                     -6.0, range.getCentralValue(), 0.0000001);
    }

    @Test
    public void testGetCentralValueCrossingZero() {
        Range range = new Range(-5.0, 5.0);
        assertEquals("The central value of a range crossing zero should be correctly calculated",
                     0.0, range.getCentralValue(), 0.0000001);
    }

    @Test
    public void testGetCentralValueZeroRange() {
        Range range = new Range(0.0, 0.0);
        assertEquals("The central value of a zero range should be zero",
                     0.0, range.getCentralValue(), 0.0000001);
    }

    @Test
    public void testGetCentralValueSinglePointRange() {
        Range range = new Range(3.0, 3.0);
        assertEquals("The central value of a single-point range should be the point itself",
                     3.0, range.getCentralValue(), 0.0000001);
    }@Test
    public void testGetLengthPositiveRange() {
        Range range = new Range(2.0, 10.0);
        assertEquals("The length of a positive range should be correctly calculated",
                     8.0, range.getLength(), 0.0000001);
    }

    @Test
    public void testGetLengthNegativeRange() {
        Range range = new Range(-10.0, -2.0);
        assertEquals("The length of a negative range should be correctly calculated",
                     8.0, range.getLength(), 0.0000001);
    }

    @Test
    public void testGetLengthCrossingZero() {
        Range range = new Range(-5.0, 5.0);
        assertEquals("The length of a range crossing zero should be correctly calculated",
                     10.0, range.getLength(), 0.0000001);
    }

    @Test
    public void testGetLengthZeroRange() {
        Range range = new Range(0.0, 0.0);
        assertEquals("The length of a zero range should be zero",
                     0.0, range.getLength(), 0.0000001);
    }

    @Test
    public void testGetLengthSinglePointRange() {
        Range range = new Range(3.0, 3.0);
        assertEquals("The length of a single-point range should be zero",
                     0.0, range.getLength(), 0.0000001);
    }
	}