import java.util.Scanner;

public class NumberRecall {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Number Recall game!");
        System.out.println("You will be shown a series of numbers and must recall them.");
        System.out.println("Are you ready? (Press Enter to start)");
        scanner.nextLine();

        int level = 1;
        boolean gameOver = false;

        while (!gameOver) {
            System.out.println("Level " + level);
            String targetNumber = generateTargetNumber(level);
            System.out.println("Memorize the number: " + targetNumber);
            sleep(2000);
            clearScreen();

            System.out.println("Enter the number you recalled:");
            String userInput = scanner.nextLine();

            if (userInput.equals(targetNumber)) {
                System.out.println("Correct!");
                level++;
            } else {
                System.out.println("Incorrect! Game Over.");
                gameOver = true;
            }
        }

        System.out.println("Game Over. You reached level " + (level - 1));
        scanner.close();
    }

    private static String generateTargetNumber(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((int) (Math.random() * 10));
        }
        return sb.toString();
    }

    private static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
