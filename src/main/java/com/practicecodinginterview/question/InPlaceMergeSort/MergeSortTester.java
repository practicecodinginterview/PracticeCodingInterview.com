package com.practicecodinginterview.question.InPlaceMergeSort;

import org.junit.Assert;

import junit.framework.TestCase;

public class MergeSortTester extends TestCase {
	// TODO input validation, different sizes?

	public void testInputSizeOne() {
		int[] left = { 5 };
		int[] right = { 6, 0 };

		MergeSort s = new MergeSort();
		s.merge(left, right);

		for (int i = 1; i < right.length; i++) {
			Assert.assertTrue(right[i] > right[i - 1]);
		}
	}

	public void testInputSizeZero() {
		int[] left = {};
		int[] right = {};

		MergeSort s = new MergeSort();
		s.merge(left, right);
	}

	public void testAllLeftBigger() {
		int[] left = { 10, 20, 30, 40 };
		int[] right = { 1, 2, 3, 4, 0, 0, 0, 0 };

		MergeSort s = new MergeSort();
		s.merge(left, right);

		for (int i = 1; i < right.length; i++) {
			Assert.assertTrue(right[i] > right[i - 1]);
		}
	}

	public void testAllRightBigger() {
		int[] left = { 1, 2, 3, 4 };

		int[] right = { 10, 20, 30, 40, 0, 0, 0, 0 };

		MergeSort s = new MergeSort();
		s.merge(left, right);

		for (int i = 1; i < right.length; i++) {
			Assert.assertTrue(right[i] > right[i - 1]);
		}
	}

	public void testAllEquals() {
		int[] left = { 1, 1, 1, 1 };

		int[] right = { 1, 1, 1, 1, 0, 0, 0, 0 };

		MergeSort s = new MergeSort();
		s.merge(left, right);

		for (int i = 1; i < right.length; i++) {
			Assert.assertTrue(right[i] >= right[i - 1]);
		}
	}

	public void testWorkingExample() {
		int[] left = { 1, 6, 10, 11 };

		int[] right = { 5, 7, 10, 20, 0, 0, 0, 0 };

		MergeSort s = new MergeSort();
		s.merge(left, right);

		for (int i = 1; i < right.length; i++) {
			Assert.assertTrue(right[i] >= right[i - 1]);
		}
	}
}
