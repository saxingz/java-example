package org.saxing.a.algorithm2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leet code 52
 */
public class NQueensII {


    public static void main(String[] args) {

        int res1 = totalNQueens11(4);
        System.out.println(res1);

        int res2 = totalNQueens1(4);
        System.out.println(res2);

    }


//==================================================================================================
//==================================================================================================
//==================================================================================================




    // 方案一
    public static int totalNQueens1(int n) {
        List<String[]> res = new ArrayList<>();
        helper1(0, new boolean[n], new boolean[2 * n], new boolean[2 * n], new String[n], res);
        return res.size();
    }
    public static void helper1(int r, boolean[] cols, boolean[] d1, boolean[] d2, String[] board, List<String[]> res){
        if (r == board.length) {
            res.add(board.clone());
        } else {
            for (int c = 0; c < board.length; c++) {
                int id1 = r - c + board.length, id2 = 2 * board.length - r - c - 1;
                if (!cols[c] && !d1[id1] && !d2[id2]){
                    char[] row = new char[board.length];
                    Arrays.fill(row, '.');
                    row[c] = 'Q';
                    board[r] = new String(row);
                    cols[c] = true; d1[id1] = true; d2[id2] = true;
                    helper1(r + 1, cols, d1, d2, board, res);
                    cols[c] = false; d1[id1] = false; d2[id2] = false;
                }
            }
        }

    }


//==================================================================================================
//==================================================================================================
//==================================================================================================


    public static int totalNQueens11(int n) {
        List<String[]> res = new ArrayList<>();
        helper11(0, new boolean[n], new boolean[2*n], new boolean[2*n],
                new String[n], res);
        return res.size();
    }

    private static void helper11(int r, boolean[] cols, boolean[] d1, boolean[] d2,
                                 String[] board, List<String[]> res) {
        if (r == board.length) res.add(board.clone());
        else {
            for (int c = 0; c < board.length; c++) {
                int id1 = r - c + board.length, id2 = 2*board.length - r - c - 1;
                if (!cols[c] && !d1[id1] && !d2[id2]) {
                    char[] row = new char[board.length];
                    Arrays.fill(row, '.'); row[c] = 'Q';
                    board[r] = new String(row);
                    cols[c] = true; d1[id1] = true; d2[id2] = true;
                    helper11(r+1, cols, d1, d2, board, res);
                    cols[c] = false; d1[id1] = false; d2[id2] = false;
                }
            }
        }
    }

}
