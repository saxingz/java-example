package org.saxing.a.algorithm2;

import java.util.HashMap;

/**
 * leetcode 146
 */
public class LRUCache {

    class DLinkedNode{
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode post;
    }

    private HashMap<Integer, DLinkedNode> cache = new HashMap<>();
    private int count;
    private int capacity;
    private DLinkedNode head, tail;

    /**
     * add node
     * @param node
     */
    private void addNode(DLinkedNode node){
        node.pre = head;
        node.post = head.post;

        head.post.pre = node;
        head.post = node;
    }

    /**
     * remove node
     *
     * @param node
     */
    private void removeNode(DLinkedNode node){
        DLinkedNode pre = node.pre;
        DLinkedNode post = node.post;

        pre.post = post;
        post.pre = pre;
    }

    /**
     * mode to head
     * @param node
     */
    private void moveToHead(DLinkedNode node){
        this.removeNode(node);
        this.addNode(node);
    }

    /**
     * pop tail
     * @return
     */
    private DLinkedNode popTail(){
        DLinkedNode res = tail.pre;
        this.removeNode(res);
        return res;
    }

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        head.pre = null;

        tail = new DLinkedNode();
        tail.post = null;

        head.post = tail;
        tail.pre = head;
    }

    public int get(int key){
        DLinkedNode node = cache.get(key);
        if (node == null){
            return -1;
        }

        this.moveToHead(node);
        return node.value;
    }





}
