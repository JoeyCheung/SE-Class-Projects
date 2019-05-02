package edu.qc.seclass;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BuggyClassTestSC1a {

    //This does 100% statement coverage but not 100% branch coverage and doesn't show the fault
    @Test
    public void buggyMethod1() {
        assertEquals(1, BuggyClass.buggyMethod1(1, 2));
    }
}
