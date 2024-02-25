package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.DataUtilities;
import org.junit.Test;

public class DataUtilitiesCreateNumberArray {

	@Test
	public void testCreateNumberArrayWithValidData() {
	    double[] data = {1.0, 2.0, 3.0};
	    Number[] actual = DataUtilities.createNumberArray(data);
	    
	    assertNotNull("The third element in the array should not be null", actual[2]);
	    assertEquals("The third element in the array is not the expected value", 3.0, actual[2]);
	}


    @Test
    public void testCreateNumberArrayWithEmptyData() {
        double[] data = {};
        Number[] expected = {};
        Number[] actual = DataUtilities.createNumberArray(data);
        assertArrayEquals("The method did not handle empty array input correctly.", expected, actual);
    }

    @Test
    public void testCreateNumberArrayWithNullInput() {
        try {
            DataUtilities.createNumberArray(null);
            fail("The method should throw an IllegalArgumentException when input is null.");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

}
