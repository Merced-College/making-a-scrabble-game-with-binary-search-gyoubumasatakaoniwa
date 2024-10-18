//Henry Lam
//10/16/24
//CPSC-39-12111

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class ScrabbleGame {

    private ArrayList<Word> wordList; // Array to store words from CSV file
    private int totalPoints; // New variable to track total points

    // Constructor to load the words from the file
    public ScrabbleGame() {
        wordList = new ArrayList<>();
        totalPoints = 0; // Initialize total points
        loadWords("Collins Scrabble Words (2019).txt"); // Load the word list
        Collections.sort(wordList); // Sort the words for binary search
    } // End of ScrabbleGame constructor

    // Method to load words into the ArrayList from the given file
    private void loadWords(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) { // Read each word from the file
                String word = scanner.nextLine().trim();
                wordList.add(new Word(word.toUpperCase())); // Add each word as a Word object to wordList
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename); // Print if file not found
        }
    } // End of loadWords method

    // Method to generate 4 random letters
    public char[] generateRandomLetters() {
        Random random = new Random(); // Create a Random object
        char[] letters = new char[4]; // Create an array for the letters
        for (int i = 0; i < 4; i++) {
            letters[i] = (char) ('A' + random.nextInt(26)); // Generate a random letter (A-Z)
        }
        return letters; // Return the random letters
    } // End of generateRandomLetters method

    // Method to check if the user's word is valid using binary search
    public boolean isValidWord(String inputWord) {
        Word searchWord = new Word(inputWord.toUpperCase()); // Convert input to Word object
        int index = Collections.binarySearch(wordList, searchWord); // Perform binary search on wordList
        return index >= 0; // Return true if the word is found
    } // End of isValidWord method

    // Improvement: Assign points based on word length and additional letters
    public int calculatePoints(String inputWord, char[] letters) {
        int points = inputWord.length(); // Base points based on word length
        int bonusPoints = countBonusLetters(inputWord, letters); // Bonus points for extra letters
        return points + bonusPoints; // Return total points
    } // End of calculatePoints method

    // Helper method to calculate bonus points for additional letters from the random set
    private int countBonusLetters(String inputWord, char[] letters) {
        int bonusPoints = 0; // Initialize bonus points
        boolean[] used = new boolean[4]; // Track which letters from the random set have been used

        for (int i = 0; i < letters.length; i++) {
            if (inputWord.indexOf(letters[i]) >= 0 && !used[i]) { // Check if the letter is in the word and not used
                bonusPoints++; // Increment bonus points
                used[i] = true; // Mark this letter as used to avoid double-counting
            }
        }
        return bonusPoints; // Return the total bonus points
    } // End of countBonusLetters method

    // Improved: Continue game if word is invalid, allow stopping, and track total points
    public void play() {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object to read user input
        char[] letters = generateRandomLetters(); // Generate 4 random letters
        System.out.println("Your letters are: " + new String(letters)); // Display the letters to the user

        while (true) { // Infinite loop until the user types 'STOP'
            System.out.print("Enter a word using the given letters (or type 'STOP' to end the game): ");
            String inputWord = scanner.nextLine().toUpperCase(); // Read user input

            // Check if the user wants to stop the game
            if (inputWord.equals("STOP")) {
                System.out.println("Game over! Thanks for playing.");
                System.out.println("Total points scored: " + totalPoints); // Print total points scored
                break; // Exit the loop
            }

            // Check if the word is valid
            if (isValidWord(inputWord)) { // Call isValidWord to check if the word is in the word list
                int points = calculatePoints(inputWord, letters); // Call calculatePoints to calculate points
                totalPoints += points; // Add points to total points
                System.out.println("Valid word! You scored " + points + " points."); // Display points
            } else {
                System.out.println("Invalid word. Please try again."); // If word is invalid, prompt the user again
            }
        } // End of while loop
    } // End of play method

    public static void main(String[] args) {
        ScrabbleGame game = new ScrabbleGame(); // Create a new ScrabbleGame object
        game.play(); // Start the game
    } // End of main method
} // End of ScrabbleGame class
