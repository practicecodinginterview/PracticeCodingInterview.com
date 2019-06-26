# Code Walkthrough - IsMirrorTree

Note: Cross posted from the Practice Coding Interview [blog](https://www.practicecodinginterview.com/blog?SQF_SOURCE=github)

# How To Use This Guide

This programming problem has been solved with example test classes. If you’re just looking for solutions and don’t mind spoilers you can jump straight to the code. If you’re new to this question it’s strongly recommended that you try and work the problem by yourself before reading the walkthrough and especially before reading the code. The test cases are easily modifiable, so you can quickly `git clone` the GitHub repo and modify it to test you solution. Not sure how to test your solution? Check out the [README](https://github.com/practicecodinginterview/PracticeCodingInterview.com) on GitHub.

# The Question
Given two binary trees determine if they are mirrors of each other. A mirror tree is defined as a tree where for every node, it has the same children in the opposite order. For example, a tree rooted at `4`, with a left child `5`, and right child `6` is a mirror of a tree rooted at `4` with a left child `6`, and right child `5`.

# Walkthrough

The first thing we should see is that this is a tree traversal problem. The only "catch" is that we are traversing two trees at once, and that there is a special ordering. Let's walk through an example.

The example in the question definition is that Tree-1 and Tree-2 below are mirror trees:

```
Tree-1
     4
    / \
   5   6

Tree-2
     4
    / \
   6   5

```

To determine these are mirror trees, we could walk the tree, starting at 4, and comparing the position of it's children. So, while looking at 4 we could determine "Are these the same nodes?" and if so "Does it have the same children in the oposite order?" If those two questions are true for every node in the tree then they are mirror trees. Otherwise, they aren't. Simple!

There are multiple ways to walk a tree. The two most common are depth-first search and breadth-first search. Breadth first search would look at the tree level by level. So, for example, we'd evaluate `Tree-1` in the order: `4, 5, 6`; and `Tree-2` in the order `4, 6, 5`. This is usually accomplished with a queue. If we did this, when we reached index 1 we would be trying to compare `Tree-1`'s `5` with `Tree-2`'s `6`. That's... not quite what we want to do. Since those sequencies don't allow us to easily compare "same" nodes it hints that maybe Depth First Search is an appropriate solution. Depth First Search looks at a node and then recursively looks at it's children. There are a couple of different [standard orders](https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/) that determine which child in what order you look at including pre-order, in-order, and post-order traversals. Here we can do a pre-order traversal, where we look at the parent first, and then it's children on `Tree-1`, while doing a modified pre-order traversal on `Tree-2` looking at it's parent first and it's children in the opposite order. The result of `preOrderTraversal(Tree-1)` should be `4, 5, 6` and the `modifiedPreOrderTraversal(Tree-1)` should also be `4, 5, 6`. Now we have an easy way to compare!

Coding it up, we have a couple of choices. The example we walked through did a traversal, building a list, returning, and comparing. That's definitely something we could do at the cost of extra memory (the returned lists). It would also require us to traverse all of both trees. It may be the case that we can prove two trees aren't mirrors very early in its ancestry. It would be a waste to still touch every node if we could short-circuit. To do an in-place traversal, we would write a single traversal that recurses across both trees at once checking as it goes.

Here's what that looks like:

```java
public static boolean isMirror(TreeNode tree1, TreeNode tree2) {
	if (tree1 == null && tree2 == null) {
		return true;
	} else if (tree1 == null || tree2 == null) {
		// one is null the other is not
		return false;
	} else if (tree1.val != tree2.val) {
		// should be traversing equal values
		return false;
	} else {
		// do the kids
		boolean leftRight = isMirror(tree1.left, tree2.right);
		boolean rightLeft = isMirror(tree1.right, tree2.left);
		return leftRight && rightLeft;
	}
}
```

# Preparing For Interviews?
Learn more about what to expect in coding interviews in our blog post, [The Coding Interview](http://www.practicecodinginterview.com/blog/2019/5/20/the-coding-interview?SQF_SOURCE=github). You’ll learn about the difference between phone and in-person interviews and strategies for both. Think you’re ready to start scheduling interviews with your dream company? [Schedule a mock interview](http://www.practicecodinginterview.com/schedule-an-interview?SQF_SOURCE=github) to practice with a real engineer from top tech companies to get comfortable solving problems in front of other people and feedback on your approach.
