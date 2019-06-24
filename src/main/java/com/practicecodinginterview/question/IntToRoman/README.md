# Code Walkthrough - IntToRoman

Note: Cross posted from the Practice Coding Interview [blog](https://www.practicecodinginterview.com/blog?SQF_SOURCE=github)

# How To Use This Guide

This programming problem has been solved with example test classes. If you’re just looking for solutions and don’t mind spoilers you can jump straight to the code. If you’re new to this question it’s strongly recommended that you try and work the problem by yourself before reading the walkthrough and especially before reading the code. The test cases are easily modifiable, so you can quickly `git clone` the GitHub repo and modify it to test you solution. Not sure how to test your solution? Check out the [README](https://github.com/practicecodinginterview/PracticeCodingInterview.com) on GitHub.

# The Question

Convert integer values in arabic numerals to roman numerals. Arabic numerals is the way most people are familiar with numbers being represented. For example, in arabic numerals the number fifteen is shown as `15`. Roman numerals is the way romans represented numbers. In this system, the number fifteen is represented as `XV`. Write a function that converts arabic numerals into roman numerals.

Roman numerals have symbols that map as follows:
* I => 1
* V => 5
* X => 10
* L => 50
* C => 100
* D => 500
* M => 1000

When written in order of decreasing value, these symbols are added together to compute their value. For example, the number `I` is `1`, `II` is `2`, and `III` is `3`, `V` is `5`, and `VI` is `6`. When written in order of increasing value they are subtracted. So for example, `IV` is `4`. `IIII` is not a valid number. In general, subtraction is not used. There are 6 special cases where subtraction is used essentially to subtract from multiples of 5 and 10.

* I can be placed before V (5) and X (10) to make 4 and 9.
* X can be placed before L (50) and C (100) to make 40 and 90.
* C can be placed before D (500) and M (1000) to make 400 and 900.


[Original Leetcode Question](https://leetcode.com/problems/integer-to-roman/description/)

# Walkthrough

This is both a good and a bad question. It’s a good question for interview prep because it forces you to find simple patterns in a problem that looks harder than it is. It’s a bad question, because roman numerals is a very culturally specific thing. Students who went to western schools probably know it, but not well, but many interviewees are completely unfamiliar with it. It takes a lot of explaining.

But you still may get it! Many of our interviewers have seen this question at some point in their careers.

Solving this problem comes down to decomposing it and seeing if there are general patterns. First, the values 0 -> 10. 0 doesn’t exist in Roman Numerals (a great edge case to test for and call out) and the numbers 1 through 9 are represented as `I`, `II`, `III`, `IV`, `V`, `VI`, `VII`, `VIII`, `IX`, `X`. Here, the first three numbers in the series use the additive rule, four is subtractive, five is a new symbol, the next three numbers use the additive rule, 9 is subtractive, and 10 is a new symbol. Let’s look at multiples of 10 less than 100: `10`, `20`, `30`, `40`, `50`, `60`, `70`, `80`, `90`, and `100`. These are represented, respectively as `X`, `XX`, `XXX`, `XL`, `L`, `LX`, `LXX`, `LXXX`, `XC`, and `C`. This looks… kind of familiar! The first three numbers are additive, the fourth is subtractive, fifth is a new symbol, the next three are additive, ninth is subtractive and tenth is a new symbol. If you were to continue this for the next order of magnitude, multiples of one hundred, you would see the pattern repeat.

How would you discover this in an interview setting? Say you got this question and didn’t know where to begin, how would you get to a solution? Walk through examples, talk them out loud, and see if you can find a pattern. Notice the numbers used above were all simple numbers of different classes. Write out some test cases and work through them logically before writing any code. If you stumble, your interviewer may help nudge you in the right direction.

Once you see the pattern it’s decide how to solve this problem. It’s the same algorithm, with different symbols for each “place” in the numbers. The ones place uses `I`’s and `V`’s. The tens place uses `X`’s and `L`’s, and both follow the same rules! So we need to write an algorithm that can look at each place in a number and then convert that place into its corresponding value prepending the next place to the string.

Walking through a more complex example, let’s look at `3456`. Our algorithm should look at this number from left to right. What may be tricky is, it’s getting an integer as input and not a string. How do we chop off the digits we don’t need? Modulo arithmetic can be used to drop the powers greater than what we need. For example, `3456 % 10` gives us `6`. However, past the ones place we also need to clean up the lower powers. For example `3456 % 100` gives us `56`. To get just that `5` from the hundreds place we then need to divide by 100 and drop the remainder to get `5`. But once we can decompose `3456` into `3`, `4`, `5,` and `6`, we can easily map this into symbols corresponding to their Roman numerals. In this case:

`3` x 1000 => `MMM`
`4` x 100 => `CD`
`5` x 10  => `L`
`6` x 1 => `VI`

So `3456` is represented as `MMMCDLVI`. You can see this and other tests and full working code in the GitHub solution. A snippet is provided below for convenience.


The complexity of this solution is O(n) where n is the number of digits in the arabic numeral integer.

# Snippet

```java
	public String intToRoman(int num) {
		Stack<List<String>> maps = new Stack<>();

		String[] onesMap = { "I", "V" };
		String[] tensMap = { "X", "L" };
		String[] hundredsMap = { "C", "D" };
		String[] thousandsMap = { "M" };
		String[] placeholder = { "DOESNT MATTER" };

		maps.add(Arrays.asList(placeholder));
		maps.add(Arrays.asList(thousandsMap));
		maps.add(Arrays.asList(hundredsMap));
		maps.add(Arrays.asList(tensMap));
		maps.add(Arrays.asList(onesMap));

		StringBuilder b = new StringBuilder();

		int workingNum = num;
		while (workingNum > 0) {
			int leastSig = workingNum % 10;
			b.insert(0, getSymbols(leastSig, maps.pop(), maps.peek().get(0)));
			workingNum = workingNum / 10;
		}

		return b.toString();
	}

	private String getSymbols(int num, List<String> map, String nextLevel) {
		StringBuilder b = new StringBuilder();
		if (num < 4) {
			for (int i = 0; i < num; i++) {
				b.append(map.get(0));
			}
		} else if (num == 4) {
			b.append(map.get(0));
			b.append(map.get(1));
		} else if (num == 5) {
			b.append(map.get(1));
		} else if (num > 5 && num < 9) {
			b.append(map.get(1));

			int remainder = num - 5;
			for (int i = 0; i < remainder; i++) {
				b.append(map.get(0));
			}
		} else if (num == 9) {
			b.append(map.get(0));
			b.append(nextLevel);
		}

		return b.toString();
	}
```

# Preparing For Interviews?

Learn more about what to expect in coding interviews in our blog post, [The Coding Interview](http://www.practicecodinginterview.com/blog/2019/5/20/the-coding-interview?SQF_SOURCE=github). You’ll learn about the difference between phone and in-person interviews and strategies for both. Think you’re ready to start scheduling interviews with your dream company? [Schedule a mock interview](http://www.practicecodinginterview.com/schedule-an-interview?SQF_SOURCE=github) to practice with a real engineer from top tech companies to get comfortable solving problems in front of other people and feedback on your approach.
