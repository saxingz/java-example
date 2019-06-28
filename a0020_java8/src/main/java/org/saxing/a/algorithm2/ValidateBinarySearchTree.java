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

        System.out.println(isValidBST1(getTreeNode1()));
        System.out.println(isValidBST1(getTreeNode2()));

    }

    public static boolean isValidBST1(TreeNode root) {
        return isValid1(root, null, null);
    }

    public static boolean isValid1(TreeNode root, Integer min, Integer max){
        if (root == null) return true;
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;
        return isValid1(root.left, min, root.val)
                && isValid1(root.right, root.val, max);
    }

}
