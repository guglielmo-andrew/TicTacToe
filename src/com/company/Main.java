package com.company;
//A command line Tic Tac Toe Game in Java

import java.util.Scanner;

public class Main {
    static int gridSize = 3; //3 x 3 Tic Tac Toe board
    /**
     * Displays the tic tac toe board.
     * @param grid a 2D array with the values (in ''s, 'X's, and 'O's) of the current board
     */
    public static void display(char [][] grid) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print(grid[i][j]);
                if (j < gridSize - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < grid.length - 1) {
                System.out.println("----------");
            }

        }
    }

    /**
     * Determine if the game should continue to end. If winner or cat game determined
     * @param grid a 3x3 2D array with either ' ', 'X', or 'O' in each cell
     * @return winner ('X' or 'O', 'c' for cat, or ' ' to show continue.
     */
    public static char endGame(char [][] grid) {
        //check diagonal
        if (grid[1][1] != ' ' &&
                ((grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) || //left to right
                (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]))) { //right to left
            return grid[1][1]; //return the center
        }
        //check vertical and horizontal
        for (int i = 0; i < gridSize; i++) {
            //check rows for winners
            if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2] && grid[i][0] != ' ') {
                return grid[i][0];
            } else if (grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i] && grid[0][i] != ' ') {
                return grid[0][i];
            }
        }
        //check for cat game
        boolean catGame = true;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j] == ' ') {
                    catGame = false;
                    break;
                }
            }
        }
        if (catGame) {
            return 'C';
        }
        return ' ';
    }

    /**
     * Determine appropriate prompt for the game status.
     * @param grid main 3x3 2D array containing Xs and Os.
     * @return String containing a prompt for came continuation or conclusion.
     */
    public static String gamePrompt(char [][] grid) {
        // check for a winner or cat game
        char gameStatus = endGame(grid);
        if ( gameStatus == 'X' || gameStatus == 'O') {
            return ("The winner is " + gameStatus);
        } else if (gameStatus == 'C') {
            return "Cat's Game";
        } else {
            return "SELECT A POSITION";
        }
    }

    /**
     * Obtains user's choice for row and column to place their letter.
      * @param direction row or column.
     * @return integer of row or column.
     */
    public static int userChoice(String direction, Scanner scanner) {
        System.out.println("ENTER " + direction + " (1 - 3) AND PRESS ENTER:");
        int choice;
        do {
            choice = scanner.nextInt(); //Read user input
            if (choice < 1 || choice > 3) {
                System.out.println("PLEASE SELECT A " + direction + " FROM 1 TO 3");
            }
        } while (choice < 1 || choice > 3);
        return choice - 1; // Make the game for normal human counting by changing human counting to computer
    }

    public static void line() {
        System.out.println("==========");
    }

    public static void main(String[] args) {
	    // Start scanner
        Scanner scanner = new Scanner(System.in);
        // establish empty grid
        char[][] grid = {{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};
        // get initial game prompt
        String prompt = gamePrompt(grid);
        // establish X's turn
        char turn = 'X';
        // loop until winner or cat's game
        while (prompt.equals("SELECT A POSITION")) {
            // displays the current grid
            line();
            display(grid);

            // divider
            System.out.println();
            line();
            prompt = gamePrompt(grid);
            System.out.println(prompt);

            // get user input on which position they want their letter
            if (prompt.equals("SELECT A POSITION")) {
                System.out.println(turn + "'S TURN");
                line();
                int row;
                int col;
                do {
                    col = userChoice("COLUMN", scanner);
                    row = userChoice("ROW", scanner);
                    if (grid[row][col] != ' ') {
                        System.out.println("SPACE IS TAKEN");
                        line();
                        display(grid);
                        line();
                    }
                } while (grid[row][col] != ' ');
                grid[row][col] = turn;

                // change turn from X to O or vice versa
                if (turn == 'X') {
                    turn = 'O';
                } else {
                    turn = 'X';
                }
                line();
            }

        }
        line();
        scanner.close();
    }
}
