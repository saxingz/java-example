package org.saxing.a.algorithm.base;

public class TrieNode2 {

    public char val;

    public boolean isWord;

    public TrieNode2[] children = new TrieNode2[26];

    public TrieNode2() {
    }

    public TrieNode2(char c){
        TrieNode2 node = new TrieNode2();
        node.val = c;
    }
}
