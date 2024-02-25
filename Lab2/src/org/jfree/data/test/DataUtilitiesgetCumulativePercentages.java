package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import org.jfree.data.DataUtilities;
import org.junit.Test;

public class DataUtilitiesgetCumulativePercentages {

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
}
