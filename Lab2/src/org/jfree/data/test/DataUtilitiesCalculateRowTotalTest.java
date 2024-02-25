package org.jfree.data.test;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DataUtilitiesCalculateRowTotalTest {

    private Values2D values2D;

    @Before
    public void setUp() {
        DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
        values2D = testValues;
        
        // Original values
        testValues.addValue(1, 0, 0); // Add value to row 0, column 0
        testValues.addValue(2, 0, 1); // Add value to row 0, column 1
        testValues.addValue(3, 1, 0); // Add value to row 1, column 0
        
        // Adding a null value - Assuming addValue gracefully handles null
        // Please replace this with the actual method you use to add null values, if different.
        testValues.addValue(null, 1, 1); // Adding a null value to row 1, column 1 for testing
        
        // Adding a negative value
        testValues.addValue(-4, 1, 2); // Add a negative value to row 1, column 2
    }


    @After
    public void tearDown() {
        values2D = null;
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRowTotalWithNegativeRowIndex() {
        DataUtilities.calculateRowTotal(values2D, -1);
    }



    @Test
    public void testRowTotalWithRowContainingNullValues() {
        ((DefaultKeyedValues2D) values2D).addValue(null, 2, 0); // Adding a null value to row 2, column 0
        try {
            DataUtilities.calculateRowTotal(values2D, 2);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Test passes if IllegalArgumentException is thrown
            assertTrue(true);
        }
    }


    @Test
    public void testRowTotalWithEmptyValues2D() {
        Values2D emptyValues2D = new DefaultKeyedValues2D();
        assertEquals("The sum of the values in any row of an empty Values2D object should be 0.0",
                0.0, DataUtilities.calculateRowTotal(emptyValues2D, 0), 0.0000001d);
    }

    @Test
    public void testRowTotalWithAllNullValuesRow() {
        ((DefaultKeyedValues2D) values2D).addValue(null, 0, 2); // Adding a null value to row 0, column 2
        try {
            DataUtilities.calculateRowTotal(values2D, 0);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Test passes if IllegalArgumentException is thrown
            assertTrue(true);
        }
    }


    @Test
    public void testRowTotalWithNegativeValues() {
        ((DefaultKeyedValues2D) values2D).addValue(-1, 1, 1); // Add a negative value to row 1, column 1
        assertEquals("The sum of the values in row 1, including a negative value, should be -2.0",
                -2.0, DataUtilities.calculateRowTotal(values2D, 1), 0.0000001d);
    }


}
