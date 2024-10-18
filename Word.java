//Henry Lam
//10/14/24
//CPSC-39-12111

import java.util.Objects;

public class Word implements Comparable<Word> {
    private String word; // Private field to store the word

    // Constructor to initialize the Word object with a word string
    public Word(String word) {
        this.word = word;
    } // End of Word constructor

    // Getter method to retrieve the word
    public String getWord() {
        return word;
    } // End of getWord method

    // Override the compareTo method to allow sorting of Word objects alphabetically
    @Override
    public int compareTo(Word other) {
        return this.word.compareTo(other.word); // Compare words lexicographically
    } // End of compareTo method

    // Override toString method to return the word when printing the object
    @Override
    public String toString() {
        return word; // Return the word as a string
    } // End of toString method

    // Override equals method to compare Word objects by their word string
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // If objects are the same instance, return true
        if (obj == null || getClass() != obj.getClass()) return false; // If object is null or not the same class, return false
        Word otherWord = (Word) obj; // Cast the object to Word
        return word.equals(otherWord.word); // Compare the word strings
    } // End of equals method

    // Override hashCode method to generate a hash based on the word string
    @Override
    public int hashCode() {
        return Objects.hash(word); // Generate a hash code using the word string
    } // End of hashCode method
} // End of Word class
