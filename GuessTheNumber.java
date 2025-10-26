import java.util.*;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int rounds, maxAttempts;

        System.out.print("Enter number of rounds: ");
        rounds = sc.nextInt();
        System.out.print("Enter max attempts per round: ");
        maxAttempts = sc.nextInt();

        int totalScore = 0;

        for (int r = 1; r <= rounds; r++) {
            int number = rand.nextInt(100) + 1; // random 1-100
            System.out.println("\nRound " + r + ": Guess the number between 1 and 100!");
            boolean guessed = false;

            for (int attempt = 1; attempt <= maxAttempts; attempt++) {
                System.out.print("Attempt " + attempt + ": ");
                int guess = sc.nextInt();

                if (guess == number) {
                    System.out.println("Correct! You guessed the number.");
                    int points = (maxAttempts - attempt + 1) * 10; // more points for fewer attempts
                    totalScore += points;
                    System.out.println("Points this round: " + points);
                    guessed = true;
                    break;
                } else if (guess < number) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }
            }

            if (!guessed) {
                System.out.println("Out of attempts! The number was: " + number);
            }
        }

        System.out.println("\nGame Over! Your total score: " + totalScore);
        sc.close();
    }
}
