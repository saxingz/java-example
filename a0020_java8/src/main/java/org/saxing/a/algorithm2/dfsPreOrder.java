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


    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(3);
        node.left.right = new TreeNode(4);

        node.right = new TreeNode(5);
        node.right.left = new TreeNode(6);

        List<Integer> list = new dfsPreOrder().dfsPre11(node);
        System.out.println(list);
    }

    private List<Integer> dfsPre11(TreeNode root){
        List<Integer> results = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        while (cur != null || !stack.empty()){
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

    private List<Integer> dfsPre1(TreeNode root){

        List<Integer> results = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        while (cur != null || !stack.empty()){
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
