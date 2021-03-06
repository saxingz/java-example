package org.saxing.a.algorithm2;

import org.saxing.a.algorithm2.base.ListNode;
import org.saxing.a.algorithm2.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 非递归，后序遍历
 * <p>
 * 入栈顺序不变，我们只需要考虑第3点的变化。出栈的对象一定都是“左”节点（“右”节点会在转向后称为“左”节点，然后入栈），也就是实际的左或根；实际的左可以当做左右子树都为null的根，所以我们只需要分析实际的根。
 * <p>
 * 对于实际的根，需要保证先后访问了左子树、右子树之后，才能访问根。实际的右节点、左节点、根节点都会成为“左”节点入栈，所以我们只需要在出栈之前，将该节点视作实际的根节点，并检查其右子树是否已被访问即可。如果不存在右子树，或右子树已被访问了，那么可以访问根节点，出栈，并不需要转向；如果还没有访问，就转向，使其“右”节点成为“左”节点，等着它先被访问之后，再来访问根节点。
 * <p>
 * 所以，我们需要增加一个标志，记录右子树的访问情况。由于访问根节点前，一定先紧挨着访问了其右子树，所以我们只需要一个标志位。
 */
public class dfsPostOrder {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        List<Integer> list = new dfsPostOrder().dfsPostOrder11(root);
        System.out.println(list);

        List<Integer> recursionResult = new ArrayList<>();
        new dfsPostOrder().dfsPostOrder2WithRecursion(recursionResult, root);
        System.out.println(recursionResult);

        System.out.println(list.equals(recursionResult));

    }

    private void dfsPostOrder2WithRecursion(List<Integer> result, TreeNode root){
        if (root == null) return;
        dfsPostOrder2WithRecursion(result, root.left);
        dfsPostOrder2WithRecursion(result, root.right);
        result.add(root.val);
    }

    private List<Integer> dfsPostOrder11(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        TreeNode last = null;

        while (cur != null || !stack.empty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }

            if (!stack.empty()){
                cur = stack.peek();
                if (cur.right == null || cur.right == last){
                    result.add(cur.val);
                    stack.pop();
                    last = cur;
                    cur = null;
                }else{
                    cur = cur.right;
                }
            }
        }

        return result;

    }

    private List<Integer> dfsPostOrder1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        TreeNode last = null;
        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            if (!stack.empty()) {
                // 只返回栈顶，不删除
                cur = stack.peek();
                if (cur.right == null || cur.right == last) {
                    result.add(cur.val);
                    stack.pop();
                    last = cur;
                    cur = null;
                } else {
                    cur = cur.right;
                }
            }
        }

        return result;
    }

}
