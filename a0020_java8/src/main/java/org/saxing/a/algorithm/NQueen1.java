package org.saxing.a.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueen1 {


    public static void main(String[] args) {

        int i = totalNQueens(4);
        System.out.println(i);

    }

    public static int totalNQueens(int n) {
        List<String[]> res = new ArrayList<>();
        helper2(0, new boolean[n], new boolean[2*n], new boolean[2*n],
                new String[n], res);
        return res.size();
    }


    /**
     * 这里使用三个数组分别标记那些不能访问的列，主对角线，和副对角线。注意cols[c] = true表示c列已经有了皇后；
     * 而d1[]表示的是副对角线，拿8皇后来说，我们把n正方形划分成14根对角线，每个对角线上有一个值，就是这根对角线上任意一个点的x + y的值(从右上角到左下角)；
     * 而d2[]表示的是主对角线，对于8皇后来说，也是划分成14根，不过对应关系是 id2 = x - y + (n-1)(从左下角到右上角)；
     * 通过上面的对应关系，我们就可以用一个数组来存这个下标，看这个点通过(x + y)或者(x-y+(n-1))求出的下标上面是不是true，如果是，说明这根对角线上有皇后，所以不能放，否则可以放；
     *
     * @param r
     * @param cols
     * @param d1
     * @param d2
     * @param board
     * @param res
     */

    private static void helper(int r, boolean[] cols, boolean[] d1, boolean[] d2, String[] board, List<String[]> res) {
        if (r == board.length) res.add(board.clone());
        else {
            for (int c = 0; c < board.length; c++) {//考察每一列
                int id1 = r - c + board.length, id2 = 2*board.length - r - c - 1;// 对角线  对应的　id值
                if (!cols[c] && !d1[id1] && !d2[id2]) {
                    //   每一个 board 是一个解　　而每一个 board 中又有n行String
                    char[] row = new char[board.length];
                    Arrays.fill(row, '.'); row[c] = 'Q';
                    board[r] = new String(row);
                    cols[c] = true; d1[id1] = true; d2[id2] = true;
                    helper(r+1, cols, d1, d2, board, res);
                    cols[c] = false; d1[id1] = false; d2[id2] = false; //递归之后还原
                }
            }
        }
    }


    private static void helper2(int r, boolean[] cols, boolean[] d1, boolean[] d2, String[] board, List<String[]> res) {
        if (r == board.length) res.add(board.clone());
        else{
            for (int c = 0; c < board.length; c++) {
                int id1 = r - c + board.length, id2 = 2 * board.length - r -c -1;
                System.out.println("id1 " + id1);
                System.out.println("id2 " + id2);
                System.out.println("d1[0] " + d1[0]);
                System.out.println("d2[0] " + d2[0]);
                System.out.println("----------------------------------------------");
                if (!cols[c] && !d1[id1] && !d2[id2]){
                    char[] row = new char[board.length];
                    Arrays.fill(row, '.'); row[c] = 'Q';
                    board[r] = new String(row);
                    cols[c] = true; d1[id1] = true; d2[id2] = true;
                    helper2(r + 1, cols, d1, d2, board, res);
                    cols[c] = false; d1[id1] = false; d2[id2] = false;
                }
            }
        }
    }
}
