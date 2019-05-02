package edu.qc.seclass;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BuggyClassTestSC1b {

    //This does less than 50% branch coverage and shows the fault only
	@Test
    public void buggyMethod1() {
        assertEquals(2, BuggyClass.buggyMethod1(2, 2));
    }
}