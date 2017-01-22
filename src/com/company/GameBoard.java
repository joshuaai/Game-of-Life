package com.company;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Joshua A I on 1/19/2017.
 */
public class GameBoard {

    private boolean[][] board;
    private int height;

    public GameBoard(int height) {
        //Constructs the game and populates the board with random parameters
        board = new boolean[height][height];
        Random r = new Random();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = r.nextBoolean();
            }
        }
    }

    public GameBoard(boolean[][] board) {
        //Constructs the game and sets the board to the parameter board
        this.board = board;
    }

    public void step() {
        //Runs through one iteration of the game and modifies the board
        boolean[][] newBoard = new boolean[board.length][board.length];
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                int count = getLivingNeighbors(x, y);
                // Implement rules of Game of Life
                newBoard[x][y] = isAlive(board[x][y], count);
            }
        }
        board = newBoard;
    }

    public void print() {
        //TODO: Make this nicer w/a nested loop
        for (boolean b[] : board) {
            System.out.println(Arrays.toString(b));
        }
        System.out.println();
    }

    private int getLivingNeighbors(int row, int col) {
        /*
         * Grabs the number of neighbors that touch a cell
         * @param row The cell row number
         * @param column The cell column number
         * @return The number of surrounding cells that are alive
        */
        int count = 0;
        if (isValidCell(row, col)) {
            // Check cell on the right
            if (col+1 < board.length && board[row][col+1] == true)
                count++;
            // Check cell on the left
            if (col-1 >= 0 && board[row][col-1] == true)
                count++;
            // Check cell below
            if (row+1 < board.length && board[row+1][col] == true)
                count++;
            //Check cell above
            if (row-1 >= 0 && board[row-1][col] == true)
                count++;
            //Check diagonal cell to bottom right
            if (row+1 < board.length && col+1 < board.length && board[row+1][col+1] == true)
                count++;
            //Check diagonal cell to top right
            if (row-1 >= 0 && col+1 < board.length && board[row-1][col+1] == true)
                count++;
            //Check diagonal cell to bottom left
            if (row+1 < board.length && col-1 >= 0 && board[row+1][col-1] == true)
                count++;
            //Check diagonal cell to top left
            if (row-1 >= 0 && col-1 >= 0 && board[row-1][col-1] == true)
                count++;
        }
        else  {
            System.out.println("Row or column specified is out of the Board index.");
        }

        return count;
    }

    private boolean isValidCell(int row, int col) {
        /*
        * Makes sure the inputted row/column are valid
        * Checks that the row/column are within the array's bounds.
        * @param row The cell row number
        * @param column The cell column number
        * @return Whether or not a cell is valid
        */
        if ((row >= 0 && row < board.length) && (col >= 0 && col < board.length))
            return true;

        return false;
    }

    private boolean isAlive(boolean currentlyLiving, int livingNeighbours) {
        /*
         * Determines whether a cell is living based upon GoL rules
         * @param currentlyLiving the current state of the cell
         * @param neighbors The number of neighbors the cell has
         */
        if (currentlyLiving && livingNeighbours < 2)
            return false;
        else if (currentlyLiving && livingNeighbours > 3)
            return false;
        else if (currentlyLiving && (livingNeighbours == 2 || livingNeighbours == 3))
            return true;
        else if (!currentlyLiving && livingNeighbours == 3)
            return true;
        return false;
    }

}
