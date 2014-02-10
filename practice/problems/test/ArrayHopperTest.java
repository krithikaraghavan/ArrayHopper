package practice.problems.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.ExpectedException;

import practice.problems.ArrayHopper;


public class ArrayHopperTest {
	
	ArrayHopper h;
	@Before
	public void create(){
		h = new ArrayHopper();
	}
	@Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testFileNotFoundException() throws FileNotFoundException, NumberFormatException{
        thrown.expect(FileNotFoundException.class);
        h.readInputFromFile("D:\\eclipse\\workspace\\ArrayHopper\\src\\whitepages\\challenge\\arrayhopper\\input3.txt");
        
    }
    
	@Test(expected = IllegalArgumentException.class)
	  public void exceptionIsThrown() {
		Integer a[] = {};
	    h.findMinNoOfHops(a);
	  }
	
	@Test
	public void testArrayHopper() {
		//{2, 9,  8,  10,  1,  1,  0,  1,  1,  1};
		//result = {0, 1, 3, out};
		Integer a[] = {5, 6, 0, 4, 2, 4, 1, 0, 0, 4};
		assertEquals("0, 5, 9, out", h.findMinNoOfHops(a));
		Integer a1[] = {0, 6, 0, 0, 1, 0, 0};
		assertEquals("failure\n", h.findMinNoOfHops(a1));
	}

}
