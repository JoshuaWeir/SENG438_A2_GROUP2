package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {

	@Test
	public void validDoubleArrayProvided() {
		double[][] data = {{1,2,3,4,5}, {6,7,8,9,0}, 
				{1,3,5,7,9}, {2,4,6,8,0}, {1,4,7,0,3}};
		Number[][] actual = DataUtilities.createNumberArray2D(data);
		Number[][] expected = {{1.0,2.0,3.0,4.0,5.0}, {6.0,7.0,8.0,9.0,0.0}, 
				{1.0,3.0,5.0,7.0,9.0}, {2.0,4.0,6.0,8.0,0.0}, {1.0,4.0,7.0,0.0,3.0}};
		assertEquals("CreateNumberArray2D did not return the correct array of Numbers.",
		expected, actual);
	}

	@Test
	public void invalidNullValueProvided() {
		boolean actual = false;
		boolean expected = true;
		try {
			double[][] data = null;
			Number[][] returned = DataUtilities.createNumberArray2D(data);
		}
		catch(Exception e) {
			actual = true;
		}
		assertEquals("Invalid data provided exception was not thrown.",
				expected, actual);
		
	}
}
