package org.saxing.a.algorithm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearchII {

    Set<String> res = new HashSet<String>();


    class Trie {
        public char val;
        public boolean isWord;
        public Trie[] children = new Trie[26];
        public Trie() {}
        public Trie(char c){
            Trie node = new Trie();
            node.val = c;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {

    }

}
