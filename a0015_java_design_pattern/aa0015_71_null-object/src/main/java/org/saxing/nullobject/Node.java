package org.saxing.nullobject;

/**
 *
 */
public interface Node {

    String getName();

    int getTreeSize();

    Node getLeft();

    Node getRight();

    void walk();

}
