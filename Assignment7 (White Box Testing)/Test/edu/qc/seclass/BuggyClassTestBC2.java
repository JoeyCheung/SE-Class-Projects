package edu.qc.seclass;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BuggyClassTestBC2 {

    //This does greater than 50% branch coverage and only shows the fault
    @Test
    public void buggyMethod2() {
        assertEquals(5, BuggyClass.buggyMethod2(6, 2));
    }
}
