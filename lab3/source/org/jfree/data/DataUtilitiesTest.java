package org.jfree.data;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataUtilitiesTest {

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
        // Assuming values2D has only 2 columns (indexes 0 and 1)
        int columnCount = values2D.getColumnCount();
        double expectedTotal = 0.0; // Nonexistent column index, so sum should be 0.0
        if (columnCount <= 2) {
            double actualTotal = DataUtilities.calculateColumnTotal(values2D, 2);
            assertEquals("The sum of the values in a nonexistent column index should be 0.0", expectedTotal, actualTotal, 0.0000001);
        } else {
            fail("The test data should have only 2 columns.");
        }
    }





    @Test
    public void testCalculateColumnTotalWithColumnContainingNullValues() {
        ((DefaultKeyedValues2D) values2D).addValue(null, 2, 0); // Add null value to row 2, column 0
        double expectedTotal = 4.0; // Sum of non-null values in column 0 (1 + 3)
        double actualTotal = DataUtilities.calculateColumnTotal(values2D, 0);
        assertEquals("The sum of the values in column 0 should ignore null values", expectedTotal, actualTotal, 0.0000001);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCalculateColumnTotalWithNegativeColumnIndex() {
        DataUtilities.calculateColumnTotal(values2D, -1);
    }


    @Test(expected = NullPointerException.class)
    public void testCalculateColumnTotalWithNullData() {
        DataUtilities.calculateColumnTotal(null, 0);
    }
    @Test
	public void testCreateNumberArrayWithValidData() {
	    double[] data = {1.0, 2.0, 3.0};
	    Number[] actual = DataUtilities.createNumberArray(data);
	    
	    assertNotNull("The third element in the array should not be null", actual[2]);
	    Assert.assertEquals("The third element in the array is not the expected value", 3.0, actual[2]);
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
    @Test
    public void testCreateNumberArray2DWithValidData() {
        double[][] data = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {7.0, 8.0, 9.0}};
        Number[][] expected = {
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        };
        Number[][] actual = DataUtilities.createNumberArray2D(data);

        assertNotNull("The returned array should not be null", actual);
        assertArrayEquals("The returned array does not match the expected array", expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray2DWithNullInput() {
        DataUtilities.createNumberArray2D(null);
    }

    @Test
    public void testCreateNumberArray2DWithEmptyData() {
        double[][] data = {};
        Number[][] expected = {};
        Number[][] actual = DataUtilities.createNumberArray2D(data);
        assertArrayEquals("The method did not handle empty array input correctly.", expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray2DWithInvalidData() {
        double[][] data = {{1.0, 2.0}, {3.0}}; // Irregular 2D array
        DataUtilities.createNumberArray2D(data);
    }
    @Test
    public void testGetCumulativePercentagesWithValidData() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", 5);
        data.addValue("1", 9);
        data.addValue("2", 2);

        KeyedValues expected = new DefaultKeyedValues();
        ((DefaultKeyedValues) expected).addValue("0", 0.3125); // 5 / 16
        ((DefaultKeyedValues) expected).addValue("1", 0.875);  // (5 + 9) / 16
        ((DefaultKeyedValues) expected).addValue("2", 1.0);    // (5 + 9 + 2) / 16

        KeyedValues actual = DataUtilities.getCumulativePercentages(data);

        assertNotNull("The returned KeyedValues should not be null", actual);
        assertEquals("The size of the returned KeyedValues should match the input data", data.getItemCount(), actual.getItemCount());

        for (int i = 0; i < expected.getItemCount(); i++) {
            assertEquals("The cumulative percentage value for key " + expected.getKey(i) + " is not as expected",
                    expected.getValue(i), actual.getValue(i), 0.0000001);
        }
    }

    private void assertEquals(String string, int itemCount, int itemCount2) {
		// TODO Auto-generated method stub
		
	}

	private void assertEquals(String string, Number value, Number value2, double d) {
		// TODO Auto-generated method stub
		
	}

	  @Test(expected = IllegalArgumentException.class)
	    public void testGetCumulativePercentagesWithNullInput() {
	        DataUtilities.getCumulativePercentages(null);
	    }

	    @Test
	    public void testGetCumulativePercentagesWithNullValuesInData() {
	        DefaultKeyedValues data = new DefaultKeyedValues();
	        data.addValue("A", 10);
	        data.addValue("B", null);
	        data.addValue("C", 20);
	        
	        KeyedValues result = DataUtilities.getCumulativePercentages(data);
	        // Add assertions here to verify the behavior of your SUT
	    }

}
