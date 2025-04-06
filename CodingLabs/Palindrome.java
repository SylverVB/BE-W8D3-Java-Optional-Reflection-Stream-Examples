package CodingLabs;

public class Palindrome {
    /**
     * Checks if a given string is a palindrome. A palindrome is a word that reads the same
     * forwards and backwards, e.g., "bob", "racecar".
     * 
     * There are multiple ways to solve this problem:
     * You can reverse the string and compare it with the original,
     * or use a two-pointer approach to compare characters from both ends moving towards the center.
     * 
     * @param str A string to check.
     * @return true if str is a palindrome, false otherwise.
     */
    public boolean pal(String str){
        // Handle null, empty, or blank string
        if (str == null || str.isBlank()) {
            return true;
        }

        // Option 1. Two pointer technique
        int start = 0;
        int end = str.length() - 1;

        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false; // Characters do not match
            }
            start++;
            end--;
        }

        return true; // All characters matched

        // Option 2. Reversing and Comparing

        // Reverse the string using StringBuilder
        // String reversed = new StringBuilder(str).reverse().toString();

        // Compare the original and reversed strings
        // return str.equals(reversed);
    }

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();

        // Test cases
        System.out.println(palindrome.pal("bob"));        // Output: true
        System.out.println(palindrome.pal("racecar"));    // Output: true
        System.out.println(palindrome.pal("hello"));      // Output: false
        System.out.println(palindrome.pal(""));           // Output: true
        System.out.println(palindrome.pal(null));         // Output: true
    }
}