package com.practicecodinginterview.question.InPlaceMergeSort;

public class MergeSort {

	public void merge(int[] left, int[] right) {

		int lIdx = left.length - 1;
		int rIdx = left.length - 1;

		int[] sink = right;
		int sinkIdx = right.length - 1;

		while (rIdx >= 0 && lIdx >= 0) {
			// do some work
			if (right[rIdx] > left[lIdx]) {
				swap(right, rIdx, sink, sinkIdx);
				rIdx--;
			} else {
				swap(left, lIdx, sink, sinkIdx);
				lIdx--;
			}

			sinkIdx--;
		}

		while (rIdx >= 0) {
			swap(right, rIdx, sink, sinkIdx);
			rIdx--;
			sinkIdx--;
		}

		while (lIdx >= 0) {
			swap(left, lIdx, sink, sinkIdx);
			lIdx--;
			sinkIdx--;
		}
	}

	private void swap(int[] source, int sourceIdx, int[] sink, int sinkIdx) {
		int hold;

		hold = source[sourceIdx];
		source[sourceIdx] = sink[sinkIdx];
		sink[sinkIdx] = hold;
	}
}
