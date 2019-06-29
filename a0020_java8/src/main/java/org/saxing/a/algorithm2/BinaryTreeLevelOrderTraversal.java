package org.saxing.a.algorithm2;

import org.saxing.a.algorithm2.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leet code 102
 */
public class BinaryTreeLevelOrderTraversal {


    static TreeNode getTreeNode(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.left.left = new TreeNode(10);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }

    public static void main(String[] args) {
        List<List<Integer>> res1 = levelOrder1(getTreeNode());
        System.out.println(res1);
    }



    /**
     * 方案一  1
     */
    public static List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> curLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode temp = queue.poll();
                    curLevel.add(temp.val);
                if (temp.left != null){
                    queue.offer(temp.left);
                }
                if (temp.right != null){
                    queue.offer(temp.right);
                }

            }
            res.add(curLevel);
        }
        return res;
    }

}
