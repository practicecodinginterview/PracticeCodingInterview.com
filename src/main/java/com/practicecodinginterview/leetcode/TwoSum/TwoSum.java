package com.practicecodinginterview.leetcode.TwoSum;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TwoSum {

	/**
	 * Returns indices of the operands that sum to a given
	 * target.
	 * 
	 * Note: there is an O(n^2) solution with a double loop
	 * look ahead.
	 * 
	 * Also, we can short circuit if the numbers are in sorted order
	 * and strictly positive. If negative numbers are allowed, then we can't
	 * do that.
	 * 
	 * https://leetcode.com/problems/two-sum/tabs/description
	 */
	public int[] twoSum(int[] nums, int target) {
		int[] results = new int[2];

		Map<Integer, Integer> locs = new HashMap<>();
		Set<Integer> seen = new HashSet<>();

		for (int i = 0; i < nums.length; i++) {
			int remainder = target - nums[i];
			if (seen.contains(remainder)) {
				results[0] = locs.get(remainder);
				results[1] = i;

				return results;
			}

			seen.add(nums[i]);
			locs.put(nums[i], i);
		}

		return results;
	}
}
