package org.saxing.a.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueen3Bit {


    public static void main(String[] args) {
    }

    int count = 0;

    public int totalNQueens(int n) {
        if (n < 1) return 0;
        dfs(n, 0, 0, 0, 0);
        return count;
    }

    private void dfs(int n, int row, int col, int pie, int na) {
        if (row >= n) {
            count++;
            return;
        }

        int bits = (~(col | pie | na)) & ((1 << n) - 1);

        while (bits > 0) {
            int p = bits & -bits;
            dfs(n, row + 1, col | p, (pie | p) << 1, (na | p) >> 1);
            bits = bits & (bits - 1);
        }
    }
}
