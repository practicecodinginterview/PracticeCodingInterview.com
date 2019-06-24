package com.practicecodinginterview.question.IntToRoman;

import org.junit.Assert;

import junit.framework.TestCase;

public class TestIntToRoman extends TestCase {

	public void testLTTen() {
		IntToRoman r = new IntToRoman();
		Assert.assertEquals(r.intToRoman(1), "I");
		Assert.assertEquals(r.intToRoman(2), "II");
		Assert.assertEquals(r.intToRoman(3), "III");
		Assert.assertEquals(r.intToRoman(4), "IV");
		Assert.assertEquals(r.intToRoman(5), "V");
		Assert.assertEquals(r.intToRoman(6), "VI");
		Assert.assertEquals(r.intToRoman(7), "VII");
		Assert.assertEquals(r.intToRoman(8), "VIII");
		Assert.assertEquals(r.intToRoman(9), "IX");
	}

	public void testTens() {
		IntToRoman r = new IntToRoman();
		Assert.assertEquals(r.intToRoman(10), "X");
		Assert.assertEquals(r.intToRoman(20), "XX");
		Assert.assertEquals(r.intToRoman(30), "XXX");
		Assert.assertEquals(r.intToRoman(40), "XL");
		Assert.assertEquals(r.intToRoman(50), "L");
		Assert.assertEquals(r.intToRoman(60), "LX");
		Assert.assertEquals(r.intToRoman(70), "LXX");
		Assert.assertEquals(r.intToRoman(80), "LXXX");
		Assert.assertEquals(r.intToRoman(90), "XC");

	}

	public void testHundreds() {
		IntToRoman r = new IntToRoman();
		Assert.assertEquals(r.intToRoman(100), "C");
		Assert.assertEquals(r.intToRoman(200), "CC");
		Assert.assertEquals(r.intToRoman(300), "CCC");
		Assert.assertEquals(r.intToRoman(400), "CD");
		Assert.assertEquals(r.intToRoman(500), "D");
		Assert.assertEquals(r.intToRoman(600), "DC");
		Assert.assertEquals(r.intToRoman(700), "DCC");
		Assert.assertEquals(r.intToRoman(800), "DCCC");
		Assert.assertEquals(r.intToRoman(900), "CM");
	}

	public void testLowThousands() {
		IntToRoman r = new IntToRoman();
		Assert.assertEquals(r.intToRoman(1000), "M");
		Assert.assertEquals(r.intToRoman(2000), "MM");
		Assert.assertEquals(r.intToRoman(3000), "MMM");
	}

	public void testBlogExample() {
		IntToRoman r = new IntToRoman();

		Assert.assertEquals(r.intToRoman(3456), "MMMCDLVI");
	}

	public void testZero() {
		IntToRoman r = new IntToRoman();
		Assert.assertEquals(r.intToRoman(0), "");
	}

	public void testMax() {
		IntToRoman r = new IntToRoman();
		Assert.assertEquals(r.intToRoman(3999), "MMMCMXCIX");
	}
}
