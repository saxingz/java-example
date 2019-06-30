package org.saxing.a.algorithm2;

import org.saxing.a.algorithm2.base.TreeNode;

/**
 * leet code 104
 */
public class MaximumDepthofBinaryTree {

    static TreeNode getTreeNode(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }

    public static void main(String[] args) {
        int result1 = maxDepth(getTreeNode());
        System.out.println(result1);
    }



//==================================================================================================
//==================================================================================================
//==================================================================================================

    public static int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

}



