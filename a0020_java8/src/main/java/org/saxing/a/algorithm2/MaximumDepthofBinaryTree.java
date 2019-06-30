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

//==================================================================================================
//==================================================================================================
//==================================================================================================


    public static void main(String[] args) {

        int result1 = maxDepth1(getTreeNode());
        System.out.println(result1);

        int result2 = maxDepth2(getTreeNode());
        System.out.println(result2);

    }

//==================================================================================================
//==================================================================================================
//==================================================================================================

    public static int maxDepth2(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth2(root.left), maxDepth2(root.right));
    }

//==================================================================================================
//==================================================================================================
//==================================================================================================

    public static int maxDepth1(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth1(root.left), maxDepth1(root.right));
    }

}



