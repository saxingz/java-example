package org.saxing.a.algorithm;

public class SudokuSolver {

    public static void main(String[] args) {
//        System.out.println(3 * 1 / 3);
//        System.out.println(3 * (1 / 3));

        char[][] boards = new char[][]
                {
                        {'.','8','7','6','5','4','3','2','1'},
                        {'2','.','.','.','.','.','.','.','.'},
                        {'3','.','.','.','.','.','.','.','.'},
                        {'4','.','.','.','.','.','.','.','.'},
                        {'5','.','.','.','.','.','.','.','.'},
                        {'6','.','.','.','.','.','.','.','.'},
                        {'7','.','.','.','.','.','.','.','.'},
                        {'8','.','.','.','.','.','.','.','.'},
                        {'9','.','.','.','.','.','.','.','.'}}
                ;

//        boolean solveed = new SudokuSolver().solve(boards);
//        System.out.println(boards);

        System.out.println(new SudokuSolver().isValidSudoku(boards));



    }

    public boolean isValidSudoku(char[][] board) {
        boolean solve = solve(board);
        return solve;
    }

    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return;
        solve(board);
    }

    public boolean solve(char[][] board){

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.'){
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)){
                            board[i][j] = c;
                            if (solve(board)){
                                return true;
                            }else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
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
                && board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false;

        }
        return true;
    }
}
