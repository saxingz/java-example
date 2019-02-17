package org.saxing.aa0015_56_iterator.bst;

/**
 *
 * TreeNode Class, 表示二进制搜索树中的一个节点。 允许一般类型的值。
 *
 * @param <T>
 *
 * @author saxing 2019/2/17 16:28
 */
public class TreeNode<T extends Comparable<T>> {

    private T val;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode(T val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public T getVal() {
        return val;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    private void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    private void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public void insert(T valToInsert) {
        TreeNode<T> parent = getParentNodeOfValueToBeInserted(valToInsert);
        parent.insertNewChild(valToInsert);
    }

    private TreeNode<T> getParentNodeOfValueToBeInserted(T valToInsert) {
        TreeNode<T> parent = null;
        TreeNode<T> curr = this;

        while (curr != null) {
            parent = curr;
            curr = curr.traverseOneLevelDown(valToInsert);
        }

        return parent;
    }

    private TreeNode<T> traverseOneLevelDown(T value) {
        if (this.isGreaterThan(value)) {
            return this.left;
        }
        return this.right;
    }

    /**
     * 添加给定值的新Child。
     *
     * @param valToInsert
     */
    private void insertNewChild(T valToInsert) {
        if (this.isLessThanOrEqualTo(valToInsert)) {
            this.setRight(new TreeNode<>(valToInsert));
        } else {
            this.setLeft(new TreeNode<>(valToInsert));
        }
    }

    private boolean isGreaterThan(T val) {
        return this.val.compareTo(val) > 0;
    }

    private boolean isLessThanOrEqualTo(T val) {
        return this.val.compareTo(val) < 1;
    }

    @Override
    public String toString() {
        return val.toString();
    }


}
