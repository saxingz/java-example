package org.saxing.a.algorithm;

import org.saxing.a.algorithm.base.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class TreeOrder {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(0);
        treeNode.left = new TreeNode(1);
        treeNode.left.left = new TreeNode(2);
        treeNode.right = new TreeNode(4);

        List<Integer> integers = preorderTraversal(treeNode);
        System.out.println(integers);
    }



    public static List<Integer> preorderTraversal(TreeNode root){
        List<Integer> pre = new LinkedList<Integer>();
        if(root==null) return pre;
        pre.add(root.val);
        pre.addAll(preorderTraversal(root.left));
        pre.addAll(preorderTraversal(root.right));
        return pre;
    }

    public static List<Integer> inOrderTraversal(TreeNode root){
        List<Integer> pre = new LinkedList<Integer>();
        if(root==null) return pre;
        pre.addAll(preorderTraversal(root.left));
        pre.add(root.val);
        pre.addAll(preorderTraversal(root.right));
        return pre;
    }

    public static List<Integer> postOrderTraversal(TreeNode root){
        List<Integer> pre = new LinkedList<Integer>();
        if(root==null) return pre;
        pre.addAll(preorderTraversal(root.left));
        pre.addAll(preorderTraversal(root.right));
        pre.add(root.val);
        return pre;
    }


}
