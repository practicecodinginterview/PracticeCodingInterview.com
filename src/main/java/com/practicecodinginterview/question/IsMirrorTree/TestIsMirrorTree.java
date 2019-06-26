package com.practicecodinginterview.question.IsMirrorTree;

import org.junit.Assert;

import junit.framework.TestCase;

public class TestIsMirrorTree extends TestCase {

	public void testOneChild() {
		TreeNode two = new TreeNode(2);
		TreeNode one = new TreeNode(1);

		two.left = one;

		TreeNode two2 = new TreeNode(2);
		TreeNode one2 = new TreeNode(1);

		two2.right = one2;

		Assert.assertTrue(IsMirrorTree.isMirror(two, two2));
	}

	public void testTwoChildrenMirrored() {
		TreeNode two = new TreeNode(2);
		TreeNode one = new TreeNode(1);
		TreeNode three = new TreeNode(3);

		two.left = one;
		two.right = three;

		TreeNode two2 = new TreeNode(2);
		TreeNode one2 = new TreeNode(1);
		TreeNode three2 = new TreeNode(3);

		two2.right = one2;
		two2.left = three;

		Assert.assertTrue(IsMirrorTree.isMirror(two, two2));

	}

	public void testTwoChildrenNotMirrored() {
		TreeNode two = new TreeNode(2);
		TreeNode one = new TreeNode(1);
		TreeNode three = new TreeNode(3);

		two.left = one;
		two.right = three;

		TreeNode two2 = new TreeNode(2);
		TreeNode one2 = new TreeNode(1);
		TreeNode three2 = new TreeNode(3);

		two2.left = one2;
		two2.right = three2;

		Assert.assertFalse(IsMirrorTree.isMirror(two, two2));
	}
}
