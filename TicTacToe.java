
import java.util.Scanner;

public class TicTacToe {
    private static char[] board = new char[9];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        resetGame();
        while (true) {
            playGame();
            System.out.print("Play again? (y/n): ");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            if (!choice.toLowerCase().equals("y")) {
                break;
            }
            resetGame();
        }
    }

    private static void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 9; i += 3) {
            System.out.print("|");
            for (int j = i; j < i + 3; j++) {
                System.out.print(" " + board[j] + " |");
            }
            System.out.println("\n-------------");
        }
    }

    private static int getMove() {
        while (true) {
            try {
                System.out.print(currentPlayer + "'s turn. Enter a number from 1 to 9: ");
                Scanner scanner = new Scanner(System.in);
                int position = scanner.nextInt() - 1;
                if (position < 0 || position >= 9 || board[position] != ' ') {
                    System.out.println("Invalid move. Try again.");
                } else {
                    return position;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    private static boolean checkWinner() {
        int[][] winningCombinations = {
                { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 },
                { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
                { 0, 4, 8 }, { 2, 4, 6 }
        };
        for (int[] combo : winningCombinations) {
            if (board[combo[0]] == currentPlayer && board[combo[1]] == currentPlayer
                    && board[combo[2]] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private static void playGame() {
        while (true) {
            displayBoard();
            int move = getMove();
            board[move] = currentPlayer;
            if (checkWinner()) {
                displayBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }
            if (!new String(board).contains(" ")) {
                displayBoard();
                System.out.println("It's a draw!");
                break;
            }
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private static void resetGame() {
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        System.out.println("New Game!");
    }
}
