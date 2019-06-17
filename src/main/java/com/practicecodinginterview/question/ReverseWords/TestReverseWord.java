package com.practicecodinginterview.question.ReverseWords;

import org.junit.Assert;

import junit.framework.TestCase;

public class TestReverseWord extends TestCase {
	// Given the string
	// leading whitespace
	// trailing whitespace
	// whitespace between words (spaces, not other printable whitespace like tabs)
	// single letter words

	public void testGiven() {
		String s = "given the string";
		ReverseWords r = new ReverseWords();
		String result = r.reverse(s);

		Assert.assertEquals(result, "nevig eht gnirts");
	}

	public void testGivenLeftPadd() {
		String s = "   given the string";
		ReverseWords r = new ReverseWords();
		String result = r.reverse(s);

		Assert.assertEquals(result, "   nevig eht gnirts");
	}

	public void testGivenRightPadd() {
		String s = "given the string   ";
		ReverseWords r = new ReverseWords();
		String result = r.reverse(s);

		Assert.assertEquals(result, "nevig eht gnirts   ");
	}

	public void testGivenMidPad() {
		String s = "given the   string";
		ReverseWords r = new ReverseWords();
		String result = r.reverse(s);

		Assert.assertEquals(result, "nevig eht   gnirts");
	}

	public void testSingleLetter() {
		String s = "given a string";
		ReverseWords r = new ReverseWords();
		String result = r.reverse(s);

		Assert.assertEquals(result, "nevig a gnirts");
	}
}
