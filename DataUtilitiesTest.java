package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.DataUtilities;
import org.junit.*;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;

public class DataUtilitiesTest extends DataUtilities {
	
	private Values2D mockValues;
	private double[] doubleArray;
	
	@BeforeClass public static void setUpBeforeClass() throws Exception {
	}
	@Before
	public void setUp() throws Exception { 
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(3));
				one(values).getValue(0, 1);
				will(returnValue(6));
				one(values).getValue(1, 0);
				will(returnValue(4));
				one(values).getValue(1, 1);
				will(returnValue(8));
			}
		});
		mockValues = values;
		doubleArray = new double[] {1.2,3.4,5.5,9.6};
	}

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
	
	//calculateColumnTotal() tests
	@Test
	public void sumColumnZero() {
		assertEquals("Sum of column 0 should be 7.", 7, DataUtilities.calculateColumnTotal(mockValues, 0), 0.000000001d);
	}
	@Test
	public void sumColumnOne() {
		assertEquals("Sum of column 1 should be 14.", 14, DataUtilities.calculateColumnTotal(mockValues, 1), 0.000000001d);
	}
	@Test
	public void nullTable() {
		try {
			DataUtilities.calculateColumnTotal(null, 0);
		}
		catch(NullPointerException e) {
			//works as intended if exception is thrown
		}
	}
	//@Test
	//public void nonExistantColumn() {
	//	DataUtilities.calculateColumnTotal(mockValues, 2);
	//}
	
	//createNumberArray() tests
	@Test
	public void arrayExists() {
		assertNotNull("Number array should exist.", DataUtilities.createNumberArray(doubleArray));
	}
	@Test
	public void nullArray() {
		try {
			double[] nullArray = null;
			DataUtilities.createNumberArray(nullArray);
		}
		catch(IllegalArgumentException e) {
			//works as intended if exception is thrown
		}
	}
	@Test
	public void correctValues() {
		Number[] numberArray = new Number[] {1.2,3.4,5.5,9.6};
		assertArrayEquals("Number array should have same values as original array.", numberArray, DataUtilities.createNumberArray(doubleArray));
	}
	
	///////////////////////////////////////////
	@Test

	public void testGetCumulativePercentage() {
	Mockery mockingContext = new Mockery();
	KeyedValues exampleValue = mockingContext.mock(KeyedValues.class);
	mockingContext.checking(new Expectations() {
		{

			allowing(exampleValue).getKey(0);
			will(returnValue(0));
		
			allowing(exampleValue).getKeys();
			will(returnIterator(0,1,2));
			
			allowing(exampleValue).getKey(1);
			will(returnValue(1));
		
			allowing(exampleValue).getKey(2);
			will(returnValue(2));
			
			allowing(exampleValue).getItemCount();
			will(returnValue(3));
		

		
			allowing(exampleValue).getValue(0);
			will(returnValue(5));
		
			allowing(exampleValue).getValue(1);
			will(returnValue(9));
		
			allowing(exampleValue).getValue(2);
			will(returnValue(2));
		


		}

	});

	Mockery mocking = new Mockery();
	KeyedValues exampleValueOutput = mocking.mock(KeyedValues.class);
	mocking.checking(new Expectations() {

		{

			one(exampleValueOutput).getItemCount();
			will(returnValue(3));
		
			one(exampleValueOutput).getKeys();
			will(returnIterator(0,1,2));
		
			one(exampleValueOutput).getValue(0);
			will(returnValue(0.3125));
		
			one(exampleValueOutput).getValue(1);
			will(returnValue(0.875));
		
			one(exampleValueOutput).getValue(2);
			will(returnValue(1.0));

		}

	});

	KeyedValues actualOutput = DataUtilities.getCumulativePercentages(exampleValue);
	assertEquals(exampleValueOutput.getValue(0), actualOutput.getValue(0));
	assertEquals(exampleValueOutput.getValue(1), actualOutput.getValue(1));
	assertEquals(exampleValueOutput.getValue(2), actualOutput.getValue(2));

	}
	
	@After
	public void tearDown() throws Exception {
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
}
