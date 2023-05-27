import java.util.Random;
import javax.swing.JOptionPane;

public class GuessTheNumber {
    public static void main(String[] args) {
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 5;
        int score = 0;
        
        boolean playAgain = true;
        while (playAgain) {
            int targetNumber = generateRandomNumber(minRange, maxRange);
            boolean guessedCorrectly = false;
            
            for (int attempts = 1; attempts <= maxAttempts; attempts++) {
                int guessedNumber = getGuessedNumber();
                
                if (guessedNumber == targetNumber) {
                    guessedCorrectly = true;
                    score += maxAttempts - attempts + 1;
                    JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number correctly!");
                    break;
                } else if (guessedNumber < targetNumber) {
                    JOptionPane.showMessageDialog(null, "Too low! Try again.");
                } else {
                    JOptionPane.showMessageDialog(null, "Too high! Try again.");
                }
            }
            
            if (!guessedCorrectly) {
                JOptionPane.showMessageDialog(null, "Game over! The number was: " + targetNumber);
            }
            
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
            playAgain = (choice == JOptionPane.YES_OPTION);
        }
        
        JOptionPane.showMessageDialog(null, "Your final score is: " + score);
    }
    
    private static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    
    private static int getGuessedNumber() {
        String input = JOptionPane.showInputDialog("Guess the number:");
        return Integer.parseInt(input);
    }
}
