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


        List<List<Integer>> res2 = levelOrder2(getTreeNode());
        System.out.println(res2);


        List<List<Integer>> result1 = dfsOrder1(getTreeNode());
        System.out.println(result1);

        List<List<Integer>> result2 = dfsOrder2(getTreeNode());
        System.out.println(result2);
    }

    // DFS2
    public static List<List<Integer>> dfsOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs2(root, result, 0);
        return result;
    }
    public static void dfs2(TreeNode root, List<List<Integer>> res, int level){
        if (root == null){
            return;
        }
        if (level >= res.size()){
            res.add(new ArrayList<>());
        }

        res.get(level).add(root.val);

        dfs2(root.left, res, level + 1);
        dfs2(root.right, res, level + 1);
    }



    // DFS 1
    public static List<List<Integer>> dfsOrder1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs1(root, result, 0);
        return result;
    }
    public static void dfs1(TreeNode root, List<List<Integer>> res, int level){
        if (root == null) return;

        if (level >= res.size()){
            res.add(new ArrayList<>());
        }

        res.get(level).add(root.val);

        dfs1(root.left, res, level + 1);
        dfs1(root.right, res, level + 1);
    }

    //  BFS
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> levelRes = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                levelRes.add(temp.val);
                if (temp.left != null){
                    queue.offer(temp.left);
                }
                if (temp.right != null){
                    queue.offer(temp.right);
                }
            }

            res.add(levelRes);
        }
        return res;
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
