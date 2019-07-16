package org.saxing.a.algorithm2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * leetcode 212
 */
public class WordSearchII {

    public static String[] getWords(){
        return new String[]{"oath","pea","eat","rain"};
    }

    public static char[][] getBoard(){
        return new char[][] {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };
    };

    public static void main(String[] args) {
        List<String> result = new WordSearchII().findWords1(getBoard(), getWords());
        System.out.println(result);
    }

    Set<String> res = new HashSet<>();
    public List<String> findWords1(char[][] board, String[] words) {
        ImplementTrie trie = new ImplementTrie();
        for (String word : words){
            trie.insert(word);
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, "", i, j, trie);
            }
        }

        return new ArrayList<>(res);
    }

    public void dfs(char[][] board, boolean[][] visited, String str, int x, int y, ImplementTrie trie){
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return;
        if (visited[x][y]) return;

        str += board[x][y];
        if (!trie.startsWith(str)) return;

        if (trie.search(str)){
            res.add(str);
        }

        visited[x][y] = true;
        dfs(board, visited, str, x - 1, y, trie);
        dfs(board, visited, str, x + 1, y, trie);
        dfs(board, visited, str, x, y - 1, trie);
        dfs(board, visited, str, x, y + 1, trie);
        visited[x][y] = false;
    }

}
