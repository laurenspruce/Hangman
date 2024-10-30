import java.util.Scanner;

public class Main {
    private static final String[] WORDS = {"lauren", "programming", "hangman", "computer", "language"};
    private static final int MAX_ATTEMPTS = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String selectedWord = selectRandomWord();
        char[] guessedWord = new char[selectedWord.length()];
        int attemptsLeft = MAX_ATTEMPTS;

        initializeGuessedWord(guessedWord);

        while (attemptsLeft > 0 && !isWordGuessed(guessedWord)) {
            System.out.println("Current word: " + String.valueOf(guessedWord));
            System.out.println("Attempts left: " + attemptsLeft);
            char guess = getValidGuess(scanner);

            if (checkGuess(selectedWord, guessedWord, guess)) {
                System.out.println("Good guess!");
            } else {
                System.out.println("Incorrect guess.");
                attemptsLeft--;
            }
        }

        if (isWordGuessed(guessedWord)) {
            System.out.println("Congratulations! You guessed the word: " + selectedWord);
        } else {
            System.out.println("Sorry, you ran out of attempts. The correct word was: " + selectedWord);
        }

        scanner.close();
    }

    private static String selectRandomWord() {
        int index = (int) (Math.random() * WORDS.length);
        return WORDS[index];
    }

    private static void initializeGuessedWord(char[] guessedWord) {
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }
    }

    private static boolean isWordGuessed(char[] guessedWord) {
        for (char c : guessedWord) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    private static char getValidGuess(Scanner scanner) {
        char guess;
        while (true) {
            System.out.print("Enter your guess: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                guess = input.charAt(0);
                break;
            } else {
                System.out.println("Invalid input. Please enter a single letter.");
            }
        }
        return guess;
    }

    private static boolean checkGuess(String word, char[] guessedWord, char guess) {
        boolean correctGuess = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                guessedWord[i] = guess;
                correctGuess = true;
            }
        }
        return correctGuess;
    }
}