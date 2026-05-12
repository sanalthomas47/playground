public class SudokuSolver {

    private void solveSudoku(int[][] sudoku){

        solveSudokuRecursively (sudoku, 0, 0);
    }

    private boolean solveSudokuRecursively(int[][] sudoku, int row, int col) {

        if(row == 8 && col == 9){
            return true;
        }
        if(col == 9){
            row++;
            col = 0;
        }

        if(sudoku[row][col] != 0){
            return solveSudokuRecursively(sudoku, row, col+1);
        }

        for(int i=1; i<=9; i++){

            if(isSafeToInsert(sudoku, row, col, i)){
                sudoku[row][col] = i;
                if(solveSudokuRecursively(sudoku,row, col+1)){
                    return true;
                }
                sudoku[row][col] = 0;
            }
        }
        return false;
    }

    private boolean isSafeToInsert(int[][] sudoku, int row, int col, int num) {

        for(int i=0; i<9;i++){
            if(sudoku[row][i] == num)
                return false;
        }
        for(int i=0; i<9; i++){
            if(sudoku[i][col] == num)
                return false;
        }

        int startRow = row - (row%3);
        int startCol = col - (col%3);

        for(int i = 0; i<3;i++){
            for(int j=0; j<3;j++)
            if(sudoku[i+startRow][j+startCol] == num)
                return false;
        }
        return true;

    }


    public static void main(String[] args){


        //int[][] sudoku = new int[9][9];

        int[][] sudoku = {
                {3, 0, 6, 0, 0, 8, 4, 0, 0},
                {5, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 0},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 0, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {0, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 0, 2, 0, 6, 3, 0, 0}
        };
        SudokuSolver sudokuSolver = new SudokuSolver();
        sudokuSolver.solveSudoku(sudoku);

        for(int i=0; i < 9; i++){
            for(int j = 0; j<9; j++){
                System.out.print(sudoku[i][j] + "  ");
            }
            System.out.println();
        }

    }
}
