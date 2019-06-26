package org.saxing.a.algorithm2;

import org.saxing.a.algorithm.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * lint code 66
 *
 * 给出一棵二叉树，返回其节点值的前序遍历。
 * 非递归
 */
public class dfsPreOrder {

    private List<Integer> dfsPre(TreeNode root){

        List<Integer> results = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        while (cur != null && !stack.empty()){
            while (cur != null){
                results.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.empty()){
                cur = stack.pop();
                cur = cur.right;
            }
        }

        return results;
    }



}
