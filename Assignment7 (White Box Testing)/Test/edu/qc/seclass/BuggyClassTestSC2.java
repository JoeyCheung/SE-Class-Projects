package edu.qc.seclass;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BuggyClassTestSC2 {

    //This does 100% statement coverage, but not 100% branch coverage and doesn't show the fault
    @Test
    public void buggyMethod2() {
        assertEquals(2, BuggyClass.buggyMethod2(1, 2));
    }
}
