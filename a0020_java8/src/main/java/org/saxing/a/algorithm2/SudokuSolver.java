package org.saxing.a.algorithm2;

/**
 * leet code 37
 */
public class SudokuSolver {

    static char[][] getArray(){
        return new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
    }

    public static void main(String[] args) {
        char[][] arr1 = getArray();
        solve(arr1);
        System.out.println(arr1);
    }



//==================================================================================================
//==================================================================================================
//==================================================================================================

    public static void solveSudoku1(char[][] board) {
        if (board == null || board.length == 0) return;
        solve(board);
    }

    public static boolean solve(char[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.'){
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid1(board, i, j, c)){
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

    private static boolean isValid1(char[][] board, int row, int col, char c){
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] != '.' && board[i][col] == c) return false;
            if (board[row][i] != '.' && board[row][i] == c) return false;
            if (board[row / 3 * 3 + i / 3][(col / 3) * 3 + i % 3] != '.'
                    && board[row / 3 * 3 + i / 3][(col / 3) * 3 + i % 3] == c) return false;
        }
        return true;
    }

}
