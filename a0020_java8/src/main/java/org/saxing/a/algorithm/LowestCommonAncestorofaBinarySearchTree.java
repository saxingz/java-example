package org.saxing.a.algorithm;

import org.saxing.a.algorithm.base.TreeNode;

public class LowestCommonAncestorofaBinarySearchTree {

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        int pVal = p.val;
        int qVal = q.val;
        TreeNode node = root;
        while (node != null){
            int parentVal = node.val;
            if (pVal > parentVal && qVal > parentVal){
                node = node.right;
            }else if (pVal < parentVal && qVal < parentVal){
                node = node.left;
            }else{
                return node;
            }
        }
        return null;
    }

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

            int pVal = p.val;

            int qVal = q.val;
            TreeNode node = root;

            while (node != null) {

                // Value of ancestor/parent node.
                int parentVal = node.val;

                if (pVal > parentVal && qVal > parentVal) {
                    // If both p and q are greater than parent
                    node = node.right;
                } else if (pVal < parentVal && qVal < parentVal) {
                    // If both p and q are lesser than parent
                    node = node.left;
                } else {
                    // We have found the split point, i.e. the LCA node.
                    return node;
                }
            }
            return null;
        }
    }

}
