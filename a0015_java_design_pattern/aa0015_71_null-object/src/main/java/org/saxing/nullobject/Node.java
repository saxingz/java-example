package org.saxing.nullobject;

/**
 * node
 *
 * @author saxing 2019/5/5 15:08
 */
public interface Node {

    String getName();

    int getTreeSize();

    Node getLeft();

    Node getRight();

    void walk();

}
