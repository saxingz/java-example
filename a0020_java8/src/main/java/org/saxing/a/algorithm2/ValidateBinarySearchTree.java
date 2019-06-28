package org.saxing.a.algorithm2;

import org.saxing.a.algorithm2.base.TreeNode;

/**
 * leet code 98
 */
public class ValidateBinarySearchTree {

    static TreeNode getTreeNode1(){
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        return root;
    }

    static TreeNode getTreeNode2(){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);
        return root;
    }


    public static void main(String[] args) {
        System.out.println(isValid(getTreeNode1(), null, null));
        System.out.println(isValid(getTreeNode2(), null, null));
    }

    public static boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }

    public static boolean isValid(TreeNode root, Integer min, Integer max){
        if (root == null) return true;
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;
        return isValid(root.left, min, root.val)
                && isValid(root.right, root.val, max);
    }

}
