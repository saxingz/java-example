package org.saxing.a.algorithm2;

import org.saxing.a.algorithm2.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leet code 104
 */
public class MaximumDepthofBinaryTree {

    static TreeNode getTreeNode(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }

    public static void main(String[] args) {

        int result1 = dfsMaxDepth1(getTreeNode());
        System.out.println(result1);

        int result2 = dfsMaxDepth2(getTreeNode());
        System.out.println(result2);

        int result3 = bfsMaxDepth3(getTreeNode());
        System.out.println(result3);

    }


//==================================================================================================
//==================================================================================================
//==================================================================================================

    // 方案二 bfs
    public static int bfsMaxDepth3(TreeNode root) {
        if (root == null) return 0;
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();

                if (temp.left != null){
                    queue.offer(temp.left);
                }
                if (temp.right != null){
                    queue.offer(temp.right);
                }
            }
            count++;
        }
        return count;
    }




//==================================================================================================
//==================================================================================================
//==================================================================================================

    public static int dfsMaxDepth2(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(dfsMaxDepth2(root.left), dfsMaxDepth2(root.right));
    }

//==================================================================================================
//==================================================================================================
//==================================================================================================

    public static int dfsMaxDepth1(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(dfsMaxDepth1(root.left), dfsMaxDepth1(root.right));
    }

}



