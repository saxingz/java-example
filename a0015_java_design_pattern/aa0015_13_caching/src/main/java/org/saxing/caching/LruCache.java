package org.saxing.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Data structure/implementation of the application's cache. The data structure consists of a hash
 * table attached with a doubly linked-list. The linked-list helps in capturing and maintaining the
 * LRU data in the cache. When a data is queried (from the cache), added (to the cache), or updated,
 * the data is moved to the front of the list to depict itself as the most-recently-used data. The
 * LRU data is always at the end of the list.
 *
 */
public class LruCache {

    public static final Logger LOGGER = LoggerFactory.getLogger(LruCache.class);

    class Node{
        String userId;
        UserAccount userAccount;
        Node previous;
        Node next;

        public Node(String userId, UserAccount userAccount) {
            this.userId = userId;
            this.userAccount = userAccount;
        }
    }

    int capacity;
    Map<String, Node> cache = new HashMap<>();
    Node head;
    Node end;

    public LruCache(int capacity) {
        this.capacity = capacity;
    }

    /**
     * get user account
     *
     * @param userId
     * @return
     */
    public UserAccount get(String userId){
        if (cache.containsKey(userId)){
            Node node = cache.get(userId);
            remove(node);
            setHead(node);
            return node.userAccount;
        }
        return null;
    }

    public void remove(Node node){
        if (node.previous != null){
            node.previous.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null){
            node.next.previous = node.previous;
        } else {
            end = node.previous;
        }
    }

    public void setHead(Node node){
        node.next = head;
        node.previous = null;
        if (head != null){
            head.previous = node;
        }
        head = node;
        if (end == null){
            end = head;
        }

    }

}
