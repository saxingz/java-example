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


        int res3 = totalNQueens2(4);
        System.out.println(res3);



        int res4 = bitNQueens1(4);
        System.out.println(res4);


        int res5 = bitNQueens2(4);
        System.out.println(res5);

    }


//==================================================================================================
//==================================================================================================
//==================================================================================================

    static int count3 = 0;
    public static int bitNQueens3(int n) {
        if (n <= 0) return 0;
        dfsBit2(n, 0, 0, 0, 0);
        return count3;
    }
    public static void dfsBit3(int n, int row, int col, int pie, int na){
        if (row >= n){
            count2++;
            return;
        }
        int bit = (~(col | pie | na)) & ((1 << n) - 1);
        while (bit > 0){
            int p = bit & -bit;
            dfsBit3(n, row + 1, col | p, (pie | p) << 1, (na | p) >> 1);
            bit &= (bit - 1);
        }
    }



//==================================================================================================
//==================================================================================================
//==================================================================================================

    static int count2 = 0;
    public static int bitNQueens2(int n) {
        if (n <= 0) return 0;
        dfsBit2(n, 0, 0, 0, 0);
        return count2;
    }
    public static void dfsBit2(int n, int row, int col, int pie, int na){
        if (row >= n){
            count2++;
            return;
        }
        int bit = (~(col | pie | na)) & ((1 << n) - 1);
        while (bit > 0){
            int p = bit & -bit;
            dfsBit2(n, row + 1, col | p, (pie | p) << 1, (na | p) >> 1);
            bit &= (bit - 1);
        }
    }



//==================================================================================================
//==================================================================================================
//==================================================================================================


    static int count1 = 0;
    // 方案二，位运算
    public static int bitNQueens1(int n) {
        if (n <= 0) return 0;
        dfsBit(n, 0, 0, 0, 0);
        return count1;
    }
    public static void dfsBit(int n, int row, int col, int pie, int na){
        if (row >= n){
            count1++;
        }
        int bit = (~(col | pie | na)) & ((1 << n) - 1);
        while (bit > 0){
            int p = bit & -bit;
            dfsBit(n, row + 1, col | p, (pie | p) << 1, (na | p) >> 1);
            bit &= (bit - 1);
        }
    }



//==================================================================================================
//==================================================================================================
//==================================================================================================

    // 方案一
    public static int totalNQueens2(int n) {
        List<String[]> res = new ArrayList<>();
        helper2(0, new boolean[n], new boolean[2 * n], new boolean[2 * n], new String[n], res);
        return res.size();
    }
    public static void helper2(int r, boolean[] cols, boolean[] d1, boolean[] d2, String[] board, List<String[]> res){
        if (r == board.length){
            res.add(board.clone());
        }else{
            for (int c = 0; c < board.length; c++) {
                int id1 = r - c + board.length, id2 = 2 * board.length - c - r - 1;
                if (!cols[c] && !d1[id1] && !d2[id2]){
                    char[] row = new char[board.length];
                    Arrays.fill(row, '.');
                    row[c] = 'Q';
                    board[r] = new String(row);
                    cols[c] = true; d1[id1] = true; d2[id2] = true;
                    helper2(r + 1, cols, d1, d2, board, res);
                    cols[c] = false; d1[id1] = false; d2[id2] = false;
                }
            }
        }
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
