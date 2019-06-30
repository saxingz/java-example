package org.saxing.a.algorithm2;

import org.saxing.a.algorithm2.base.TreeNode;

/**
 * leet code 111
 */
public class MinimumDepthofBinaryTree {

    static TreeNode getTreeNode(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }

    public static void main(String[] args) {


        int res1 = dfsMinDepth1(getTreeNode());
        System.out.println(res1);


        int res2 = dfsMinDepth2(getTreeNode());
        System.out.println(res2);


    }


//==================================================================================================
//==================================================================================================
//==================================================================================================


    public static int dfsMinDepth2(TreeNode root) {
        if (root == null) return 0;
        int left = dfsMinDepth2(root.left);
        int right = dfsMinDepth2(root.right);

        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }

//==================================================================================================
//==================================================================================================
//==================================================================================================


    public static int dfsMinDepth1(TreeNode root) {
        if (root == null) return 0;
        int left = dfsMinDepth1(root.left);
        int right = dfsMinDepth1(root.right);
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }

}
