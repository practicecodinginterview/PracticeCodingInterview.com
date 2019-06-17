# Code Walkthrough - ReverseWords

Note: Cross posted from the Practice Coding Interview [blog](https://www.practicecodinginterview.com/blog?SQF_SOURCE=github)

# How To Use This Guide

This programming problem has been solved with example test classes. If you’re just looking for solutions and don’t mind spoilers you can jump straight to the code. If you’re new to this question it’s strongly recommended that you try and work the problem by yourself before reading the walkthrough and especially before reading the code. The test cases are easily modifiable, so you can quickly `git clone` the GitHub repo and modify it to test you solution. Not sure how to test your solution? Check out the [README](https://github.com/practicecodinginterview/PracticeCodingInterview.com) on GitHub.

# The Question
Given a string, replace each word in-place in the string. For example, if you’re given the string “given the string” your method should return “nevig eht gnirts”.

* [Original Leetcode Question](https://leetcode.com/articles/reverse-words-in-a-string/#)
* Solution on GitHub
* Test Cases on GitHub


# Walkthrough

Depending on the language you work in, you may have libraries that can easily accomplish this for you. In compilers, and natural language processing, there is a technique called [tokenizing](https://en.wikipedia.org/wiki/Lexical_analysis#Tokenization) that takes a string and splits it into its constituent words called tokens. With the string `“given the string”` you could split it into tokens `[“given”, “the”, “string”]`, reverse each of them `[“nevig”, “eht”, “gnirts”]` and then recombine them to return `“nevig eht gnirts”`. In Java, there are a couple of libraries to accomplish this as shown in this example:

```java
    public String reverseWithLibraries(String string) {
        String words[] = string.split(" ");
        StringBuilder res = new StringBuilder();
        for (String word : words)
            res.append(new StringBuffer(word).reverse().toString() + " ");
        return res.toString().trim();
        }
```

This solution comes at the cost of memory. You have to split the string, store the tokens, and store the reversed result to return. That costs 3x the memory of the original string, although is still O(n) in memory. What if you couldn’t afford that memory cost? How would you approach it?

One approach is to modify the string in place. You do this by keeping pointers into the string and swapping as you go. Let’s first think about how we’d solve a simpler form of the problem, reversing a string in place. Given a string, you would keep a left and right pointer to the beginning of the word and swap as you go until the pointers pass each other.

For example, with the word `“given”` you would first swap the `g` and `n`, then the `i` and `e` and you wouldn’t swap the `v` with anything. The resulting steps would look like `given` -> `niveg` -> `nevig`. The following code snippet shows how you would do that with a string represented as a character array and pointers that start pointing to the beginning and end of the string.

```java
         while (lIdx < rIdx) {
                char hold = charArray[lIdx];
                charArray[lIdx] = charArray[rIdx];
                charArray[rIdx] = hold;

                lIdx++;
                rIdx--;
            }
```

Now that we know how to reverse a word or token, how do we reverse every token in a sentence while preserving the order of tokens? We can do that by iterating through the sentence and using the algorithm defined above on the sub-strings. Here’s an example of that:

```java
    public String reverse(String string) {
        char[] charArray = string.toCharArray();

        int lIdx = 0;
        int rIdx = Integer.MAX_VALUE;
        int sentenceIdx = 0;

        while (sentenceIdx < charArray.length) {
            if (charArray[sentenceIdx] == ' ') {
                sentenceIdx++; // skip whitespace
            }

            // find beginning of the word
            lIdx = sentenceIdx;

            for (int i = lIdx; i <= charArray.length; i++) {
                if (i == charArray.length || charArray[i] == ' ') {
                    sentenceIdx = i;
                    break;
                }
            }

            // remember where last word character was
            rIdx = sentenceIdx - 1;

            // meet in the middle swapping
            while (lIdx < rIdx) {
                char hold = charArray[lIdx];
                charArray[lIdx] = charArray[rIdx];
                charArray[rIdx] = hold;

                lIdx++;
                rIdx--;
            }
        }

        return new String(charArray);
    }
```

Some of this should look familiar. Near the end of the method is our snippet for word level processing. There are three pointers here: a left and right for the token currently being processed (`lIdx` and `rIdx`) as well a pointer for how much of the string has been processed `sentenceIdx`. `sentenceIdx` starts at the beginning of the sentence and always points just ahead of the most recently seen token during processing. `lIdx` points at the beginning of the token and `rIdx` points at the end of the token at the beginning of token processing and follows our swapping algorithm. When I word has been reversed in the string, we use `sentenceIdx` to find the next word to process and repeat.

# Preparing For Interviews?
Learn more about what to expect in coding interviews in our blog post, [The Coding Interview](http://www.practicecodinginterview.com/blog/2019/5/20/the-coding-interview?SQF_SOURCE=github). You’ll learn about the difference between phone and in-person interviews and strategies for both. Think you’re ready to start scheduling interviews with your dream company? [Schedule a mock interview](http://www.practicecodinginterview.com/schedule-an-interview?SQF_SOURCE=github) to practice with a real engineer from top tech companies to get comfortable solving problems in front of other people and feedback on your approach.
