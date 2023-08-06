import java.util.Scanner;
import java.util.Random;

public class PigDiceGame {
    private static int scoreOne = 0;
    private static int scoreTwo = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println(
                "Welcome To Pig Dice! First Player to get 30 accumulative points wins! (Or however much you set it to)");
        System.out.print("Please enter player one's username: ");
        String nameOne = scanner.nextLine().trim();
        System.out.print("Please enter player two's username: ");
        String nameTwo = scanner.nextLine().trim();

        System.out.print("Do you want to set a custom limit, 'Yes' or 'No' (Default limit is 30 points): ");
        String limitAsk = scanner.nextLine().trim().toUpperCase();
        int limit = 30;

        if (limitAsk.equals("YES")) {
            System.out.print("Please enter the score limit: ");
            limit = scanner.nextInt();
        }

        int currentPlayer = 1;
        boolean gameOver = false;

        while (!gameOver) {
            String currentPlayerName = (currentPlayer == 1) ? nameOne : nameTwo;

            System.out.println(currentPlayerName + " is rolling...");
            int roll = random.nextInt(6) + 1;
            printDice(roll);

            if (roll == 1) {
                System.out.println("Oh no... You lose all your points");
                if (currentPlayer == 1) {
                    scoreOne = 0;
                } else {
                    scoreTwo = 0;
                }
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
            } else {
                if (currentPlayer == 1) {
                    scoreOne += roll;
                } else {
                    scoreTwo += roll;
                }
                System.out.println("Current Score");
                System.out.println(nameOne + ": " + scoreOne + " | " + nameTwo + ": " + scoreTwo);

                System.out.print("Roll again or hold? (R = Roll / H = Hold): ");
                String choice = scanner.next().trim().toUpperCase();

                if (choice.equals("H")) {
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;
                }
            }

            if (scoreOne >= limit || scoreTwo >= limit) {
                gameOver = true;
            }
        }

        String winnerName = (scoreOne >= limit) ? nameOne : nameTwo;
        int winnerScore = (scoreOne >= limit) ? scoreOne : scoreTwo;
        System.out.println("Winner!\n" + winnerName + " wins with a score of " + winnerScore);
    }

    public static void printDice(int roll) {
        if (roll == 1) {
            System.out.println("[-----]");
            System.out.println("[     ]");
            System.out.println("[  0  ]");
            System.out.println("[     ]");
            System.out.println("[-----]");
        } else if (roll == 2) {
            System.out.println("[-----]");
            System.out.println("[ 0   ]");
            System.out.println("[     ]");
            System.out.println("[   0 ]");
            System.out.println("[-----]");
        } else if (roll == 3) {
            System.out.println("[-----]");
            System.out.println("[     ]");
            System.out.println("[0 0 0]");
            System.out.println("[     ]");
            System.out.println("[-----]");
        } else if (roll == 4) {
            System.out.println("[-----]");
            System.out.println("[0   0]");
            System.out.println("[     ]");
            System.out.println("[0   0]");
            System.out.println("[-----]");
        } else if (roll == 5) {
            System.out.println("[-----]");
            System.out.println("[0   0]");
            System.out.println("[  0  ]");
            System.out.println("[0   0]");
            System.out.println("[-----]");
        } else if (roll == 6) {
            System.out.println("[-----]");
            System.out.println("[0 0 0]");
            System.out.println("[     ]");
            System.out.println("[0 0 0]");
            System.out.println("[-----]");
        }
    }
}
