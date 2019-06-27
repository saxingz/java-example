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
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        List<Integer> list = new dfsPreOrder().dfsPre11(root);
        System.out.println(list);

        List<Integer> resultWithRecursion = new ArrayList<>();
        new dfsPreOrder().dfsPreWithRecursion(resultWithRecursion, root);
        System.out.println(resultWithRecursion);

        System.out.println(list.equals(resultWithRecursion));
    }

    private void dfsPreWithRecursion(List<Integer> result, TreeNode root){
        if (root == null) return;
        result.add(root.val);
        dfsPreWithRecursion(result, root.left);
        dfsPreWithRecursion(result, root.right);
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
