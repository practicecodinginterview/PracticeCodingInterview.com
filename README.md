# [PracticeCodingInterview.com](https://www.practicecodinginterview.com?SQF_SOURCE=github)

## Mock Interviews

Get a 1 hour mock interview session with experienced engineers from top tech companies. At the end of each session, you'll get verbal and written feedback and the opportunity to ask questions. More information at [PracticeCodingInterview.com](https://www.practicecodinginterview.com?SQF_SOURCE=github)

## Pre-Mock Interviews

Not ready for mock interviews? This repo includes solutions to popular interview questions with unit tests. Explore solution by browsingn the `src` directory or visiting our [blog](https://www.practicecodinginterview.com/blog?SQF_SOURCE=github).

# Running the code

You can run all of the tests locally with the following command on Mac and Linux. On windows, substitute `gradlew` with `gradlew.bat`:

```bash
./gradlew build
```

The results are stored at `build/reports/tests/test/index.html`. You can open it from command line with the following on Linux

```bash
gnome-open build/reports/tests/test/index.html
```

or on Mac

```bash
open build/reports/tests/test/index.html
```

# Exploring Solutions

Every problem is self contained in it's own package under `src` including: solution code, test code, and a README about the problem. For an example, see the [`com.practicecodinginterview.question.TwoSum`](https://github.com/practicecodinginterview/PracticeCodingInterview.com/tree/master/src/main/java/com/practicecodinginterview/question/TwoSum) package.

# Testing your own implementation

If you're practicing problems for the first time it's helpful to see the code execute and pass tests. Every solution provided here includes at least a simple test case. All solutions exist in `src/main` and all tests in `src/test`.

For example, you can review the solution for [TwoSum](https://github.com/practicecodinginterview/PracticeCodingInterview.com/blob/master/src/main/java/com/practicecodinginterview/question/TwoSum/TwoSum.java) and see the [associated tests](https://github.com/practicecodinginterview/PracticeCodingInterview.com/blob/master/src/main/java/com/practicecodinginterview/question/TwoSum/TestTwoSum.java). If you wanted to write your own TwoSum implementation you would do the following:

## Run the pre-existing tests locally

First make sure your dev environment is working correctly. You can do that with the following commands

```bash
./gradlew clean test
gnome-open build/reports/tests/test/index.html
```

The second command should open your preferred browser. You can also navigate to it directly. In the page shown you should see a Test Summary page and "100% Successful" in green at the top. You should also see the package you're interested in listed below. For example you should see `com.practicecodinginterview.leetcode.TwoSum`.

## Write your implementation

Write your implementation anywhere you like. One reasonable choice would be to create `MyTwoSum.java` in the same package as the guide solution, `com.practicecodinginterview.leetcode.TwoSum`. You would then modify `TestTwoSum` to import and reference your implementation instead. Before doing this, it's recommended that you create your new class with your preferred method signature and return null or throw an Exception. After updating the test to reference it, run the tests the same way you did before and watch it fail. This will give you the confidence that your tests are actaully running the way you think they are and will make debugging simpler if you have problems with your implementation.

## Test your implementation

Now that you've written and implementation and updated the tests to use it, simply repeat what you did to run the tests originally and see if they pass.

## Contribute back

Have a new test case you think is important to include in the examples? Feel free to contribute it back!
