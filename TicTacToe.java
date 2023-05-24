package gametictacktoe;

import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3]; // Create a 3x3 game board
        currentPlayer = 'X'; // Set the initial player as X
        initializeBoard(); // Initialize the game board with empty values
    }

    public void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-'; // Set each cell in the board to empty ('-')
            }
        }
    }

    public void displayBoard() {
        System.out.println("-------------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " | "); // Display each cell in the board
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '-') {
                    return false; // If any cell is empty, the board is not full
                }
            }
        }
        return true; // All cells are filled, the board is full
    }

    public boolean isWinner(char player) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true; // If any row contains all marks of a player, they are the winner
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true; // If any column contains all marks of a player, they are the winner
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; // If the top-left to bottom-right diagonal contains all marks of a player, they are the winner
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true; // If the top-right to bottom-left diagonal contains all marks of a player, they are the winner
        }

        return false; // No winning condition is met
    }

    public void makeMove(int row, int col) {
        board[row][col] = currentPlayer; // Mark the selected cell with the current player's mark
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch the current player
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();

        while (true) {
            game.displayBoard(); // Display the current game board

            System.out.print("Player " + game.currentPlayer + ", enter your move (row column): ");
            int row = scanner.nextInt(); // Read the row number entered by the player
            int col = scanner.nextInt(); // Read the column number entered by the player

            if (row < 0 || row > 2 || col < 0 || col > 2 || game.board[row][col] != '-') {
                System.out.println("Invalid move! Try again."); // Check if the move is valid
                continue;
            }

            game.makeMove(row, col); // Make the move on the game board

            if (game.isWinner('X')) {
                System.out.println("Player X wins!"); // Check if player X wins
                break;
            } else if (game.isWinner('O')) {
                System.out.println("Player O wins!"); // Check if player O wins
                break;
            } else if (game.isBoardFull()) {
                System.out.println("It's a draw!"); // Check if the game ends in a draw
                break;
            }
        }

        game.displayBoard(); // Display the final game board
        scanner.close(); // Close the scanner
    }
}

