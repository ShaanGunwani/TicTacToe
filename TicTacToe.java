import java.util.Scanner;

public class TicTacToe {

    private char[][] board;
    private char currentPlayerMark;

    // Returns the current player mark ('X' or 'O')
    public char getCurrentPlayer() {
        return currentPlayerMark;
    }

    public boolean isValidInput(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            // row or column is out of bounds
            return false;
        } else if (board[row][col] != '-') {
            // cell is already occupied
            return false;
        } else {
            // input is valid
            return true;
        }
    }

    public void placeMark(int row, int col) {
        board[row][col] = currentPlayerMark;
        changePlayer();
    }



    public TicTacToe() {
        board = new char[3][3];
        currentPlayerMark = 'X';
        initializeBoard();
    }

    // Set/Reset the board back to all empty values.
    public void initializeBoard() {

        // Loop through rows
        for (int i = 0; i < 3; i++) {
            // Loop through columns
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the current board (may be replaced by GUI implementation later)
    public void printBoard() {
        System.out.println("-------------");

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Loop through all cells of the board and if one is found to be empty (contains char '-') then return false.
    // Otherwise the board is full.
    public boolean isBoardFull() {
        boolean isFull = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    // Returns true if there is a win, false otherwise.
    // This calls our other win check functions to check the entire board.
    public boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    // Loop through rows and see if any are winners.
    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
                return true;
            }
        }
        return false;
    }

    // Loop through columns and see if any are winners.
    private boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
                return true;
            }
        }
        return false;
    }

    // Check the two diagonals to see if either is a win. Return true if either wins.
    private boolean checkDiagonalsForWin() {
        return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
    }

    // Check to see if all three values are the same (and not empty) indicating a win.
    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }

    // Change player marks back and forth.
    public void changePlayer() {
        if (currentPlayerMark == 'X') {
            currentPlayerMark = 'O';
        } else {
            currentPlayerMark = 'X';
        }
        System.out.println("It's now " + currentPlayerMark + "'s turn.");
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        int row, col;
        boolean isValidInput;

        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("-----------------------");

        do {
            // Print the current board
            game.printBoard();

            // Get the row and column from the current player
            do {
                System.out.print("Player " + game.getCurrentPlayer() + ", enter an empty row and column to place your mark (e.g. 1 2): ");
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;

                isValidInput = game.isValidInput(row, col);
                if (!isValidInput) {
                    System.out.println("Invalid input. Please try again.");
                }
            } while (!isValidInput);

            // Place the mark on the board
            game.placeMark(row, col);

            // Check if the game has been won or tied
            if (game.checkForWin()) {
                game.printBoard();
                System.out.println("Congratulations! Player " + game.getCurrentPlayer() + " wins!");
                return;
            } else if (game.isBoardFull()) {
                game.printBoard();
                System.out.println("It's a tie!");
                return;
            }

            // Change to the other player
            game.changePlayer();

        } while (true);
    }
}

