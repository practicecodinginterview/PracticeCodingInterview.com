package com.practicecodinginterview.question.IsMirrorTree;

/**
 * Determine if two trees are mirrors of each other
 * 
 *          1
 *        3   2
 *           5  4
 * 
 * 			1
 * 		 2    3
 *     4   5
 * 
 * Note: in the above, these are binary but not BST. No global ordering
 * 
 */
public class IsMirrorTree {

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
}
