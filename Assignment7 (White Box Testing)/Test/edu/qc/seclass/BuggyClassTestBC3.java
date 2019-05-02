package edu.qc.seclass;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BuggyClassTestBC3 {
	
	@Test
	void test1() {
		assertEquals(BuggyClass.buggyMethod3(-10), 0);
	}
}
