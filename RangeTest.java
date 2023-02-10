package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    private Range exampleRange;
    private Range demoRange;
    private Range nullRange;
	
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception { 
	exampleRange = new Range(-1, 1);
	demoRange = new Range (-5,5); 
	nullRange = null;
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
	
    @Test
    public void GetLowerBoundFunctionality() {
        assertEquals("The lower bound value of -1 and 1 should be -1",
        -1, exampleRange.getLowerBound(), .000000001d);
    }
	
    @Test
    public void GetOutsideLowerBound() {
    	assertFalse("The range should not be less than -1",exampleRange.contains(-2));
    }
	
    @Test
    public void GetLowerBoundEquivUpperBound() {
    	Range example = new Range(1,1);
        assertEquals("The lower bound value of 1 and 1 should be 1",
        1, example.getLowerBound(), .000000001d);
    }
	
    //contains() tests
	@Test
	public void containsValueInRange() {
		assertTrue("Value of 3 should be true as it is in range.", demoRange.contains(3));
	}
	@Test
	public void containsValueOutsideRangeHigh() {
		assertFalse("Value of 8 should be false as it is not in range.", demoRange.contains(8));
	}
	@Test
	public void containsValueOutsideRangeLow() {
		assertFalse("Value of -8 should be false as it is not in range.", demoRange.contains(-8));
	}
	@Test
	public void containsValueIsLowerBound() {
		assertTrue("Value of -5 should be true as it is in range.", demoRange.contains(-5));
	}
	@Test
	public void containsValueIsUpperBound() {
		assertTrue("Value of 5 should be true as it is in range.", demoRange.contains(5));
	}
	
	//toString() tests
	@Test
	public void correctString() {
		assertEquals("Function should return correct string.","Range[-5.0,5.0]",demoRange.toString());
	}
	@Test
	public void nullString() {
		assertNull(nullRange.toString());
	}
	
	//intersects() tests
	@Test
	public void intersectLowerBound() {
		assertTrue("Range should overlap on lower bound.",demoRange.intersects(-10, -5));
	}
	@Test
	public void intersectUpperBound() {
		assertTrue("Range should overlap on upper bound.",demoRange.intersects(5, 15));
	}
	@Test
	public void intersectLowerHalf() {
		assertTrue("Range should overlap on lower half.",demoRange.intersects(-10, -2));
	}
	@Test
	public void intersectUpperHalf() {
		assertTrue("Range should overlap on upper half.",demoRange.intersects(2, 10));
	}
	@Test
	public void noIntersectLower() {
		assertFalse("Range should not overlap under the lower bound.",demoRange.intersects(-15, -7));
	}
	@Test
	public void noIntersectUpper() {
		assertFalse("Range should not overlap over the upper bound.",demoRange.intersects(7, 15));
	}
	@Test
	public void oneValueInRange() {
		assertTrue("Range of (3,3) should intersect.",demoRange.intersects(3, 3));
	}
	@Test
	public void oneValueNotInRange() {
		assertFalse("Range of (8,8) should not intersect.",demoRange.intersects(8, 8));
	}
	@Test
	public void noIntersectInNullRange() {
		assertFalse("Null range should not have any intersect.",nullRange.intersects(0, 5));
	}
	@Test
	public void completeIntersectInside() {
		assertTrue("Range encapsulated inside should intersect.",demoRange.intersects(-3, 3));
	}
	@Test
	public void completeIntersectOutside() {
		assertTrue("Range encapsulating outside should intersect.",demoRange.intersects(-13, 13));
	}
	


    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
