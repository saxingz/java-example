package org.saxing.a.algorithm;

import org.saxing.a.algorithm.base.TreeNode;

public class ValidBSTTest {



    public boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }


    public boolean isValid(TreeNode root, Integer min, Integer max){
        if (root == null) return true;
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;

        return isValid(root.left, min, root.val)
                && isValid(root.right, root.val, max);
    }

}
