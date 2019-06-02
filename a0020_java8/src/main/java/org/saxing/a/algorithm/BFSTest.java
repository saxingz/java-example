package org.saxing.a.algorithm;

import org.saxing.a.algorithm.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSTest {

    public static void main(String[] args) {

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            int levelSize = q.size();
            List<Integer> currLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.poll();
                currLevel.add(node.val);
                if (node.left != null){
                    q.offer(node.left);
                }
                if (node.right != null){
                    q.offer(node.right);
                }
            }

            res.add(currLevel);
        }

        return res;
    }

}
