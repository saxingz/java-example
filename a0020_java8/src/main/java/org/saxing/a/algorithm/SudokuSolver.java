package org.saxing.a.algorithm;

public class SudokuSolver {

    public static void main(String[] args) {
        System.out.println(3 * 1 / 3);
        System.out.println(3 * (1 / 3));
    }

    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return;

    }

    public boolean solve(char[][] board){

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.'){
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)){
                            
                        }
                    }
                }
            }
        }

        return true;
    }


    private boolean isValid(char[][] board, int row, int col, char c){
        for (int i = 0; i < 9; i++) {
            if (board[i][col] != '.' && board[i][col] == c) return false;
            if (board[row][i] != '.' && board[row][i] == c) return false;
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.'
                && board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.') return false;

        }
        return true;
    }
}
