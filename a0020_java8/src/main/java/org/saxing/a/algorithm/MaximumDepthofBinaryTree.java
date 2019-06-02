package org.saxing.a.algorithm;

import org.saxing.a.algorithm.base.TreeNode;

public class MaximumDepthofBinaryTree {

    public int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

}
