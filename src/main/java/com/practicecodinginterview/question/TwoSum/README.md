# Code Walkthrough - TwoSum

Note: Cross posted from the Practice Coding Interview [blog](https://www.practicecodinginterview.com/blog)

# How To Use This Guide

This programming problem has been solved with example test classes. If you’re just looking for solutions and don’t mind spoilers you can jump straight to the code. If you’re new to this question it’s strongly recommended that you try and work the problem by yourself before reading the walkthrough and especially before reading the code. The test cases are easily modifiable, so you can quickly `git clone` the GitHub repo and modify it to test you solution. Not sure how to test your solution? Check out the [README](https://github.com/practicecodinginterview/PracticeCodingInterview.com) on GitHub.

# The Question

Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

# Walkthrough
The number one question on LeetCode has consistently been [TwoSum](https://leetcode.com/problems/two-sum/) over the past few years. Of the interviewers at PracticeCodingInterview.com, most have asked or been asked this question at many companies including LinkedIn and Google. Even though it’s a published and thoroughly practiced problem, it still comes up, and so it’s worth practicing.

# Naive Solution

Well that’s easy, just check every pair of numbers!

In the example where you’re given the list `[2, 7, 11, 15]` and a target sum of `9` you can try every pair of numbers. Example pairs you would tests are `(2,7)`, `(2,11)`, `(2,15)`. Note, the pairs `(2,15)` is really the same pair as `(15,2)` so we don’t need every permutation, just every combination. Not sure the difference between permutations and combinations? It doesn’t really matter for this question, but it probably will matter for your interviews but its worth [refreshing](https://betterexplained.com/articles/easy-permutations-and-combinations/). The short answer is, if you want to consider `(2,15)` different than `(15,2)` then you’re talking about permutations where both the value and the order matters. If you consider these two to be the same (as we do) it’s a combination.

Let’s list every combination and see if we see a pattern: 
`(2,7)`, `(2,11)`, `(2,15)`, `(7,11)`, `(7,15)`, `(11,15)`

Here, we generate every combination by taking a given number combined with every number after that. To do that in code you could use a nested for-loop structure. The following code is a snippet from the [GitHub solution](https://github.com/practicecodinginterview/PracticeCodingInterview.com/blob/master/src/main/java/com/practicecodinginterview/leetcode/TwoSum/TwoSum.java).

```java
    /**
     * The naive O(n^2) solution.
     */
    private int[] twoSumNaive(int[] nums, int target) {
        int[] results = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    results[0] = i;
                    results[1] = j;
                }
            }
        }

        return results;
    }
```

# Optimal Solution

In an interview setting, if you couldn’t come up with an optimal solution and you have limited time left always bias towards getting code on the board. It’s completely OK to say “I don’t see a better solution yet, so I’m going to code up the naive solution and we can work from there.” This does two things. First, it lets the interviewer see your code, a core part of what they’re assessing in the interview. Second, it gives you a checkpoint to feel like you did something. Anything. Phycologically that’s super useful.

The naive solution is O(n^2) (pronounced n-squared). Not super efficient. Take a look at the Big-O complexity chart provided by bigocheatsheet.com. The naive solution is in the red!

There are legitimately times when you can’t do better then n^2. But you should always ask if you can. In this case, we can get to O(n).

The insight relies in the fact that we can cache the results of what we’ve already seen with a constant time lookup, or O(1). So, rather than having a nested for-loop to look foward, we can have a single for loop with memory of what we’ve already seen. Basically, we can look backwards faster than we can look forward at the cost of a little memory. Here’s what that looks like in code, taken from the GitHub solution:

```java
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
```

So what’s happening here? We’re looping through the list and asking, “Have I seen a number that when added to the number I currently have is equal to the target?” We do this by computing the remainder from the current number and the target and checking the cache of seen numbers. Since we want to know which numbers sum to the target, we also have a separate cache that tracks the indices of all the previously seen numbers.

With this solution we’ve almost tripled the amount of memory needed, but we can compute the solution much faster.

# Preparing For Interviews?
Learn more about what to expect in coding interviews in our blog post, [The Coding Interview](http://www.practicecodinginterview.com/blog/2019/5/20/the-coding-interview?SQF_SOURCE=github). You’ll learn about the difference between phone and in-person interviews and strategies for both. Think you’re ready to start scheduling interviews with your dream company? [Schedule a mock interview](http://www.practicecodinginterview.com/schedule-an-interview?SQF_SOURCE=github) to practice with a real engineer from top tech companies to get comfortable solving problems in front of other people and feedback on your approach.
