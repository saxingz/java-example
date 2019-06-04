package org.saxing.a.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearchI {

    /**
     * leet code 79
     *
     * board =
     * [
     *   ['A','B','C','E'],
     *   ['S','F','C','S'],
     *   ['A','D','E','E']
     * ]
     *
     * Given word = "ABCCED", return true.
     * Given word = "SEE", return true.
     * Given word = "ABCB", return false.
     */


    public static void main(String[] args) {
//        char[][] board = new char[][]
//                {
//                    {'A','B','C','E'},
//                    {'S','F','C','S'},
//                    {'A','D','E','E'}
//                };
//        String word = "SEE";
//
//        new WordSearchI().exist(board, word);

        // 这里值为A-Z a-z 要注意数组范围

        System.out.println((int)('A'));
        System.out.println((int)('Z'));
        System.out.println((int)('a'));
        System.out.println((int)('z'));

    }

    boolean result = false;

    public boolean exist(char[][] board, String word) {
        Trie trie = new Trie();
        trie.insert(word);

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, "", i, j, trie);
            }
        }

        return result;
    }

    public void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie trie) {
        if (result){
            return;
        }
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return;
        if (visited[x][y]) return;

        str += board[x][y];
        if (!trie.startsWith(str)) return;

        if (trie.search(str)) {
            result = true;
            return;
        }

        visited[x][y] = true;
        dfs(board, visited, str, x - 1, y, trie);
        dfs(board, visited, str, x + 1, y, trie);
        dfs(board, visited, str, x, y - 1, trie);
        dfs(board, visited, str, x, y + 1, trie);
        visited[x][y] = false;
    }

}
