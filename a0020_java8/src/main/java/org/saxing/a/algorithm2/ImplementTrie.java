package org.saxing.a.algorithm2;

import org.saxing.a.algorithm2.base.TrieNode;

/**
 * leet code 208
 */
public class ImplementTrie {

    public static void main(String[] args) {
        ImplementTrie trie = new ImplementTrie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));;     // returns false
        System.out.println(trie.startsWith("app"));; // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));;     // returns true
    }


//==================================================================================================
//==================================================================================================
//==================================================================================================

    private TrieNode root;
    public ImplementTrie() {
        root = new TrieNode();
        root.val = ' ';
    }
    public void insert(String word) {
        TrieNode ws = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (ws.children[c - 'a'] == null){
                ws.children[c - 'a'] = new TrieNode(c);
            }
            ws = ws.children[c - 'a'];
        }
        ws.isWord = true;
    }

    public boolean search(String word) {
        TrieNode ws = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return ws.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode ws = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return true;
    }


}
