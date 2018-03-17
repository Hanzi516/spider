package com.jiangzh.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class FloatRegTest {

	@Test
	public void testParseString() {
		assertEquals("4.2", String.valueOf(FloatReg.parseString("4.2元")));
	}

}
