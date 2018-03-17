package com.jiangzh.util;

public class FloatReg {
	public static float parseString(String price) {
		return Float.parseFloat(price.replaceAll("[^0-9.]",""));
	}
}
