package org.saxing.nullobject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NodeImpl implements Node {

    private static final Logger LOGGER = LoggerFactory.getLogger(NodeImpl.class);

    private final String name;
    private final Node left;
    private final Node right;

    /**
     * Constructor
     */
    public NodeImpl(String name, Node left, Node right) {
        this.name = name;
        this.left = left;
        this.right = right;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getTreeSize() {
        return 1 + left.getTreeSize() + right.getTreeSize();
    }

    @Override
    public Node getLeft() {
        return left;
    }

    @Override
    public Node getRight() {
        return right;
    }

    @Override
    public void walk() {
        LOGGER.info(name);
        if (left.getTreeSize() > 0) {
            left.walk();
        }
        if (right.getTreeSize() > 0) {
            right.walk();
        }
    }
}
