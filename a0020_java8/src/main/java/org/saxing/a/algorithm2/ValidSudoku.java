package org.saxing.a.algorithm2;

/**
 * leet code 36
 */
public class ValidSudoku {

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

        boolean res1 = isValidSudoku1(getArray());
        System.out.println(res1);

        boolean res2 = isValidSudoku2(getArray());
        System.out.println(res2);


    }



//==================================================================================================
//==================================================================================================
//==================================================================================================


    public static boolean isValidSudoku2(char[][] board) {
        boolean[][] col = new boolean[9][9];
        boolean[][] row = new boolean[9][9];
        boolean[][] block = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;

                int digit = board[i][j] - '1';
                int blockId = j / 3 * 3 + i / 3;
                if (col[i][digit]){
                    return false;
                }else{
                    col[i][digit] = true;
                }
                if (row[i][digit]){
                    return false;
                }else {
                    row[i][digit] = true;
                }
                if (block[blockId][digit]){
                    return false;
                }else {
                    block[blockId][digit] = true;
                }
            }
        }

        return true;
    }



//==================================================================================================
//==================================================================================================
//==================================================================================================

    public static boolean isValidSudoku1(char[][] board) {
        boolean[][] col = new boolean[9][9];
        boolean[][] row = new boolean[9][9];
        boolean[][] block = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;

                int digit = board[i][j] - '1';
                int blockId = j / 3 * 3 + i / 3;
                if (col[j][digit]) {
                    return false;
                } else {
                    col[j][digit] = true;
                }
                if (row[i][digit]) {
                    return false;
                } else {
                    row[i][digit] = true;
                }
                if (block[blockId][digit]) {
                    return false;
                } else {
                    block[blockId][digit] = true;
                }
            }
        }

        return true;
    }

}
