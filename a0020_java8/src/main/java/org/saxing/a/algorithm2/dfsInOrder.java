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
public class dfsInOrder {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        List<Integer> list = new dfsInOrder().dfsInOrder11(root);
        System.out.println(list);
    }

    private List<Integer> dfsInOrder11(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;

        while (cur != null || !stack.empty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }

            if (!stack.empty()){
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }

        return result;
    }

    private List<Integer> dfsInOrder1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;

        while (cur != null || !stack.empty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }

            if (!stack.empty()){
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }

        return result;
    }


}
