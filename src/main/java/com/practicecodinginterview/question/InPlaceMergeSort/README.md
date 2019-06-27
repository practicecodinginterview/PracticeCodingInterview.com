# Code Walkthrough - InPlaceMergeSort

Note: Cross posted from the Practice Coding Interview [blog](https://www.practicecodinginterview.com/blog?SQF_SOURCE=github)

# How To Use This Guide

This programming problem has been solved with example test classes. If you’re just looking for solutions and don’t mind spoilers you can jump straight to the code. If you’re new to this question it’s strongly recommended that you try and work the problem by yourself before reading the walkthrough and especially before reading the code. The test cases are easily modifiable, so you can quickly `git clone` the GitHub repo and modify it to test you solution. Not sure how to test your solution? Check out the [README](https://github.com/practicecodinginterview/PracticeCodingInterview.com) on GitHub.

# The Question
Given two sorted arrays, `array1` and `array2` each with n sorted values, where `array1` is of size n and `array2` is of size 2n where the right most n values are 0-padded, return a single sorted array containing the values from both.

For example, `array1` may contain the values `[1,6,10,11]` while `array2` would contain the values `[5,7,10,20,0,0,0,0]`. You function should return `[1,5,6,7,10,10,11,20]`.


# Walkthrough

To accomplish this we want to implement an modified in-place merge sort. A merge sort is when you consume from two sorted lists. [Merge Sort](https://www.geeksforgeeks.org/merge-sort/) is a general algorithm for taking two unsorted lists and combining them, by first sorting sub-lists and then merging them. Since our lists are already sorted, we can re-use the same logic as merge sort without the recursion.

Let's take the non-in-place version of this to understand the core algorithm. If you had the two lists `[1,6,10,11]` and `[5,7,10,20]` you could create a third result array `[0,0,0,0,0,0,0,0]` as a placeholder for the merged version. Looking at the head of your two original lists, you would see that `1` is greater than `5` and put it in your result array which would then be `[1,0,0,0,0,0,0,0]` leaving `array1` as `[6,10,11]`. Notice that we're always consuming from the head of the list with the smallest head value. At the second iteration, you would then see that `array2`'s `5` is less that `6` and your resulting state is `array1` = `[6,10,11]` and `array2` = `[7,10,20]` with your result array as `[1,5,0,0,0,0,0,0,0]`. You would continue this process unitl both original arrays are empty and your resulting array is a complete sorted single list.

Given that, how might we approach the inplace version of this problem? Well in the above example, we created a third resulting array. In the in-place problem, we've already precreated space that can accomplish the same thing. Remember that `array1` is `[1,6,10,11]` and `array2` is `[5,7,10,20,0,0,0,0]`. With the right padding, we can treat `array2` as our resulting array. The only trick is, we need to work backwards, filling up `array2` from right to left (note the direction) in descending order so that we can return an array that is ascending from left to right!

Let's walk through an example. Each iteration of the algorithm described in the last paragraph is shown below:

```
Step0

array1: [1,6,10,11]
array2:[5,7,10,20,0,0,0,0] 

Step1
array1: [1,6,10,11]
array2:[5,7,10,0,0,0,0,20]

Step2 
array1: [1,6,10,0]
array2:[5,7,10,0,0,0,11,20]

Step3 
array1: [1,6,0,0]
array2:[5,7,10,0,0,10,11,20]

Step4
array1: [1,6,0,0]
array2:[5,7,0,0,10,10,11,20]

Step5
array1: [1,6,0,0]
array2:[5,0,0,7,10,10,11,20]

Step6
array1: [1,0,0,0]
array2:[5,0,6,7,10,10,11,20]

Step7
array1: [1,0,0,0]
array2:[0,5,6,7,10,10,11,20]

Step8
array1: [0,0,0,0]
array2:[1,5,6,7,10,10,11,20]
```

Below is a code snippet showing how to accomplish this. Note there are only two array instances, named `left` and `right`. There is a third array alias named `sink` that is actually a pointer to the `right` array. Three indexes are used, `lIdx`, `rIdx`, and `sinkIdx`. Using these we point to the end of the left array, the end of real values in the right array, and the end of the zero padding in the right array (aliased as sink).


```java
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
```



# Preparing For Interviews?
Learn more about what to expect in coding interviews in our blog post, [The Coding Interview](http://www.practicecodinginterview.com/blog/2019/5/20/the-coding-interview?SQF_SOURCE=github). You’ll learn about the difference between phone and in-person interviews and strategies for both. Think you’re ready to start scheduling interviews with your dream company? [Schedule a mock interview](http://www.practicecodinginterview.com/schedule-an-interview?SQF_SOURCE=github) to practice with a real engineer from top tech companies to get comfortable solving problems in front of other people and feedback on your approach.
