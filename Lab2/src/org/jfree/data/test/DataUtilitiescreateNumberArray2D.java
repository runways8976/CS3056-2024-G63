package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.DataUtilities;
import org.junit.Test;

public class DataUtilitiescreateNumberArray2D {

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
}
