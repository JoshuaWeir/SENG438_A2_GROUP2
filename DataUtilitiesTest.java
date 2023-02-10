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
	
	@Test
    public void test_calculatePositiveRowTotal() {
        Mockery mock = new Mockery();
        Values2D example = mock.mock(Values2D.class);
        mock.checking(new Expectations() {
            {
                one(example).getColumnCount();
                will(returnValue(5));
                
                one(example).getValue(0, 0);
                will(returnValue(1.0));
                
                one(example).getValue(0, 1);
                will(returnValue(0.5));
                
                one(example).getValue(0, 2);
                will(returnValue(0.5));
                
                one(example).getValue(0, 3);
                will(returnValue(2.5));
            }
        });
        double result = DataUtilities.calculateRowTotal(example,0);
        assertEquals("Result should be 4.5", result, 4.5, .000000001d);
    }
	
	//no rows
	@Test(expected = IllegalArgumentException.class)
    public void test_noRows(){
        Mockery mock = new Mockery();
        Values2D example = mock.mock(Values2D.class);
        mock.checking(new Expectations() {
            {
                one(example).getColumnCount();
                will(returnValue(0));
                
            }
        });
    }
	
	@Test
	//getting total of negative numbers
    public void test_calculateNegativeRowTotal() {
        Mockery mock = new Mockery();
        Values2D example = mock.mock(Values2D.class);
        mock.checking(new Expectations() {
            {
                one(example).getColumnCount();
                will(returnValue(5));
                one(example).getValue(0, 0);
                will(returnValue(-1.0));
                one(example).getValue(0, 1);
                will(returnValue(0.5));
                one(example).getValue(0, 2);
                will(returnValue(-6.5));
                one(example).getValue(0, 3);
                will(returnValue(2.5));
            }
        });
        double result = DataUtilities.calculateRowTotal(example,0);
        assertEquals("Result should be -4.5", result, -4.5, .000000001d);
    }
}
