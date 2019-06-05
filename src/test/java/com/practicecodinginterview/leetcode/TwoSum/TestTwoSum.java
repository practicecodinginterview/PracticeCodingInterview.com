package com.practicecodinginterview.leetcode.TwoSum;

import org.junit.Assert;

import junit.framework.TestCase;

// Given nums = [2, 7, 11, 15], target = 9,
// return [0,1]

public class TestTwoSum extends TestCase {
	public void testGivenExample() {
		int[] ints = { 2, 7, 11, 15 };

		assertIdxs(9, 0, 1, ints);
	}

	public void testNegative() {
		int[] ints = { -1, 2, 3, 4, 10 };

		assertIdxs(9, 0, 4, ints);
	}

	private void assertIdxs(int target, int firstIdx, int secondIdx, int... ints) {
		TwoSum sum = new TwoSum();
		int[] idx = sum.twoSum(ints, target);

		Assert.assertEquals(idx[0], firstIdx);
		Assert.assertEquals(idx[1], secondIdx);

		int result = ints[idx[0]] + ints[idx[1]];
		Assert.assertEquals(target, result);
	}
}
