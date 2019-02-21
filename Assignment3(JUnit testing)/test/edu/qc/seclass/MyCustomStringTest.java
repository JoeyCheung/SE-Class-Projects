package edu.qc.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class MyCustomStringTest {

    private MyCustomStringInterface mycustomstring;

    @Before
    public void setUp() {
        mycustomstring = new MyCustomString();
    }

    @After
    public void tearDown() {
        mycustomstring = null;
    }
    // This is the default test case given to us to test if the function counts randomly placed numbers in a string
    @Test
    public void testCountNumbers1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals(7, mycustomstring.countNumbers());
    }
    // This checks if it counts anything else as a digit
    @Test
    public void testCountNumbers2() {
    	mycustomstring.setString("I'd better put some digits in this string right?");
        assertEquals(0, mycustomstring.countNumbers());
    }
    // This checks if you throw a null pointer exception for an empty string
    @Test(expected = NullPointerException.class)
    public void testCountNumbers3() {
    	mycustomstring.setString("");
    	mycustomstring.countNumbers();
    	
    }
    // This checks if only numbers are in the string
    @Test
    public void testCountNumbers4() {
    	mycustomstring.setString("1");
        assertEquals(1, mycustomstring.countNumbers());
    }

    @Test
    public void testCountNumbers5() {
        fail("Not yet implemented");
    }

    @Test
    public void testCountNumbers6() {
        fail("Not yet implemented");
    }
    // This checks to see if it the function can get every nth character starting from the beginning
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("d33p md1  i51,it", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));
    }
    // This checks to see if the function can get every nth character starting from the end
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd2() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("'bt t0 6snh r6rh", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true));
    }
    // This Checks if the string is null and n is greater than 0 it should throw a null pointer exception
    @Test(expected = NullPointerException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd3() {
    	mycustomstring.setString(null);
    	mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, false);
    }
    // This checks if the string is equal to empty string and startFromEnd is true then return an empty string
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd4() {
    	mycustomstring.setString("");
    	assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true));
    }
    // This checks if n is less than or equal to zero then throw an IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd5() {
    	mycustomstring.setString("hi");
    	mycustomstring.getEveryNthCharacterFromBeginningOrEnd(0, true);
    }
    // This checks if the string has less than n characters then return an empty string
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd6() {
    	mycustomstring.setString("hi");
    	assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd7() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd8() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd9() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd10() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd11() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd12() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd13() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd14() {
        fail("Not yet implemented");
    }
    // This checks to see if this function can convert randomly placed digits from a certain range to digits at all
    @Test
    public void testConvertDigitsToNamesInSubstring1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("I'd b3tt3r put sZerome dOneSix1ts in this 5tr1n6, right?", mycustomstring.getString());
    }
    // This checks to see if startPosition is greater than endPosition, then throw IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testConvertDigitsToNamesInSubstring2() {
        mycustomstring.setString("Hi");
        mycustomstring.convertDigitsToNamesInSubstring(2,1);
    }
    // This checks to see if startPosition is less than 1 then throw MyIndexOutOfBoundsException
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToNamesInSubstring3() {
    	mycustomstring.setString("Hi");
        mycustomstring.convertDigitsToNamesInSubstring(0,1);
    }
    // This checks to see if it throws a NullPointerException if the string is null
    @Test(expected = NullPointerException.class)
    public void testConvertDigitsToNamesInSubstring4() {
    	mycustomstring.setString(null);
        mycustomstring.convertDigitsToNamesInSubstring(1,2);
    }

    // This checks to see if endPosition is greater than the length of the string then throw MyIndexOutOfBoundsException
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToNamesInSubstring5() {
    	mycustomstring.setString("Hi");
        mycustomstring.convertDigitsToNamesInSubstring(0,5);
    }
    // This checks to see if it converts a string of only numbers to digits
    @Test
    public void testConvertDigitsToNamesInSubstring6() {
    	mycustomstring.setString("123");
        mycustomstring.convertDigitsToNamesInSubstring(1, 3);
        assertEquals("OneTwoThree", mycustomstring.getString());
    }
    // This checks to see if it converts the last character to a digit
    @Test
    public void testConvertDigitsToNamesInSubstring7() {
    	mycustomstring.setString("hello1");
        mycustomstring.convertDigitsToNamesInSubstring(1, 6);
        assertEquals("helloOne", mycustomstring.getString());
    }
 // This checks to see if it converts the last character to a digit
    @Test
    public void testConvertDigitsToNamesInSubstring8() {
    	mycustomstring.setString("1hello");
        mycustomstring.convertDigitsToNamesInSubstring(1, 6);
        assertEquals("Onehello", mycustomstring.getString());
    }

}
