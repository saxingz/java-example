package org.saxing.a.algorithm2;

import org.saxing.a.algorithm2.base.ListNode;
import org.saxing.a.algorithm2.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 层遍历
 *
 */
public class LevelOrderTest {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root){
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            int count = q.size();
            List<Integer> list = new ArrayList<>();
            while (count > 0){
                TreeNode temp = q.poll();
                list.add(temp.val);
                if (temp.left != null) {
                    q.offer(temp.left);
                }
                if (temp.right != null){
                    q.offer(temp.right);
                }
                count--;
            }
            res.add(list);
        }
        return res;
    }

}
