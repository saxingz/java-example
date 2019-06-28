package org.saxing.a.algorithm2;

import org.saxing.a.algorithm2.base.TreeNode;

/**
 * leet code 235
 */
public class LowestCommonAncestorofaBinarySearchTree {

    static TreeNode getTreeNode(){
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        root.right = new TreeNode(8);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        return root;
    }



    public static void main(String[] args) {
        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(8);

        TreeNode node = lowestCommonAncestor1(getTreeNode(), p, q);
        if (node != null){
            System.out.println(node.val);
        }else{
            System.out.println(node);
        }

        TreeNode node2 = lowestCommonAncestor2(getTreeNode(), p, q);
        if (node2 != null){
            System.out.println(node2.val);
        }else{
            System.out.println(node2);
        }

    }

    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        int pVal = p.val;
        int qVal = q.val;
        TreeNode cur = root;
        while (cur != null){
            int parentVal = cur.val;
            if (pVal > parentVal && qVal > parentVal){
                cur = cur.right;
            }else if (pVal < parentVal && qVal < parentVal){
                cur = cur.left;
            }else{
                return cur;
            }
        }
        return null;
    }



    public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        int pVal = p.val;
        int qVal = q.val;
        TreeNode node = root;
        while (node != null){
            int parentVal = node.val;
            if (pVal > parentVal && qVal > parentVal){
                node = root.right;
            }else if (pVal < parentVal && qVal < parentVal){
                node = root.left;
            }else {
                return node;
            }
        }
        return null;
    }

}
