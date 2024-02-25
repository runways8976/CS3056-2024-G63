package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.Values2D;
import org.jfree.data.DataUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataUtilitiescalculateColumnTotal {

    private Values2D values2D;

    @Before
    public void setUp() {
        DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
        values2D = testValues;
        testValues.addValue(1, 0, 0); // Add value to row 0, column 0
        testValues.addValue(2, 0, 1); // Add value to row 0, column 1
        testValues.addValue(3, 1, 0); // Add value to row 1, column 0
    }

    @After
    public void tearDown() {
        values2D = null;
    }

    @Test
    public void testCalculateColumnTotalWithValidData() {
        double expectedTotal = 4.0; // Sum of values in column 0 (1 + 3)
        double actualTotal = DataUtilities.calculateColumnTotal(values2D, 0);
        assertEquals("The sum of the values in column 0 should be 4.0", expectedTotal, actualTotal, 0.0000001);
    }

 
    @Test
    public void testCalculateColumnTotalWithEmptyValues2D() {
        Values2D emptyValues2D = new DefaultKeyedValues2D();
        double expectedTotal = 0.0; // Sum of values in any column of an empty Values2D object should be 0.0
        double actualTotal = DataUtilities.calculateColumnTotal(emptyValues2D, 0);
        assertEquals("The sum of the values in any column of an empty Values2D object should be 0.0", expectedTotal, actualTotal, 0.0000001);
    }

  
    @Test
    public void testCalculateColumnTotalWithNonexistentColumnIndex() {
        // Assuming values2D has only 3 columns (indexes 0, 1, and 2)
        int columnCount = values2D.getColumnCount();
        double expectedTotal = 0.0; // Nonexistent column index, so sum should be 0.0
        if (columnCount > 2) {
            fail("The test data should have only 3 columns.");
        }
        double actualTotal = DataUtilities.calculateColumnTotal(values2D, 2);
        assertEquals("The sum of the values in a nonexistent column index should be 0.0", expectedTotal, actualTotal, 0.0000001);
    }

    @Test
    public void testCalculateColumnTotalWithColumnContainingNullValues() {
        ((DefaultKeyedValues2D) values2D).addValue(null, 2, 0); // Add null value to row 2, column 0
        double expectedTotal = 4.0; // Sum of non-null values in column 0 (1 + 3)
        double actualTotal = DataUtilities.calculateColumnTotal(values2D, 0);
        assertEquals("The sum of the values in column 0 should ignore null values", expectedTotal, actualTotal, 0.0000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateColumnTotalWithNegativeColumnIndex() {
        DataUtilities.calculateColumnTotal(values2D, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateColumnTotalWithNullData() {
        DataUtilities.calculateColumnTotal(null, 0);
    }

}
