package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    private Range exampleRange;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { exampleRange = new Range(-1, 1);
    }

    @Test
    public void firstNullValueGiven() {
    	Range expected = new Range(3.8, 10.0);
    	Range actual = Range.combine(null, expected);
        assertEquals("One null value given return should be equal to the non-null value",
        expected, actual);
    }

    @Test
    public void secondNullValueGiven() {
        Range expected = new Range(2.4, 2.8);
        Range actual = Range.combine(expected, null);
        assertEquals("One null value given return should be equal to the non-null value",
        expected, actual);
    }
    
    @Test
    public void bothNullGiven() {
    	Range actual = Range.combine(null, null);
    	assertNull("Two null values given should return null",
    	actual);
    }
    
    @Test
    public void incompleteOverlapRange2LessThanRange1() {
    	Range r1 = new Range(2, 4);
    	Range r2 = new Range(1, 3);
    	Range actual = Range.combine(r1, r2);
    	Range expected = new Range(1, 4);
    	assertEquals("Ranges of incomplete overlap", expected, actual);
    }
    
    @Test ()
    public void incompleteOverlapRange1LessThanRange2() {
    	try {
    	Range r1 = new Range(1, 9);
    	Range r2 = new Range(5, 15);
    	Range actual = Range.combine(r1, r2);
    	Range expected = new Range(1, 15);
    	assertEquals("Ranges of incomplete overlap", expected, actual);
		}
		catch(Exception e) {
			fail("Unexpected exception was thrown: " + e.getMessage());
		}
    }
    
    @Test
    public void noOverlapOnRanges() {
    	Range r1 = new Range(10, 15);
    	Range r2 = new Range(1, 5);
    	Range actual = Range.combine(r1, r2);
    	Range expected = null;
    	assertEquals("Ranges do not overlap",
    	expected, actual);
    }
    
    @Test
    public void rangesEqual() {
    	Range r1 = new Range(1.1, 2.4);
    	Range r2 = new Range(1.1, 2.4);
    	Range actual = Range.combine(r1, r2);
    	assertEquals("Ranges are equal",
    	r1, actual);
    	
    }
    
    @Test
    public void completeOverlap() {
    	try {
    	Range r1 = new Range(1.6, 7.5);
    	Range r2 = new Range(3.2, 4.5);
    	Range expected = new Range(1.6, 7.5);
    	Range actual = Range.combine(r1, r2);
    	assertEquals("One range fully encloses the other",
    	expected, actual);
    	}
    	catch(Exception e) {
    		fail("Unexpected exception was thrown: " + e.getMessage());
    	}

    }
    
    @Test
    public void oneNegativeRange() {
    	Range r1 = new Range(3.3, 5.3);
    	Range r2 = new Range(-1.0, 3.6);
    	Range expected = new Range(-1.0, 5.3);
    	Range actual = Range.combine(r1, r2);
    	assertEquals("One range was given a negative lower bound",
    	expected, actual);
    }
    
    @Test
    public void bothNegative() {
    	Range r1 = new Range(-3.2, -0.7);
    	Range r2 = new Range(-4.5, -0.5);
    	Range expected = new Range(-4.5, -0.5);
    	Range actual = Range.combine(r1, r2);
    	assertEquals("Both ranges where given negative values",
    	expected, actual);
    }
    

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
