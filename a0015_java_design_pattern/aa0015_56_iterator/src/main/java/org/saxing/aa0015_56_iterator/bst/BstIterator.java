package org.saxing.aa0015_56_iterator.bst;

import org.saxing.aa0015_56_iterator.Iterator;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;

/**
 * An in-order implementation of a BST Iterator. For example, given a BST with Integer values,
 * expect to retrieve TreeNodes according to the Integer's natural ordering (1, 2, 3...)
 *
 * @param <T>
 *
 * @author saxing 2019/2/17 16:51
 */
public class BstIterator<T extends Comparable<T>> implements Iterator<TreeNode<T>> {

    private ArrayDeque<TreeNode<T>> pathStack;

    public BstIterator(TreeNode<T> root) {
        pathStack = new ArrayDeque<>();
        pushPathToNextSmallest(root);
    }

    private void pushPathToNextSmallest(TreeNode<T> node) {
        while (node != null) {
            pathStack.push(node);
            node = node.getLeft();
        }
    }

    @Override
    public boolean hasNext() {
        return !pathStack.isEmpty();
    }

    @Override
    public TreeNode<T> next() throws NoSuchElementException {
        if (pathStack.isEmpty()) {
            throw new NoSuchElementException();
        }
        TreeNode<T> next = pathStack.pop();
        pushPathToNextSmallest(next.getRight());
        return next;
    }
}
