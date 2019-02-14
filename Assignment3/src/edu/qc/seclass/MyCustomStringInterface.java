package edu.qc.seclass;

/**
 * This is an interface for a simple class that represents a string, defined
 * as a sequence of characters.
 *
 * @author Rufus
 */
public interface MyCustomStringInterface {

    /**
     * Returns the current string. If the string is null, it should return null.
     *
     * @return Current string
     */
    String getString();

    /**
     * Sets the value of the current string.
     *
     * @param string The value to be set
     */
    void setString(String string);

    /**
     * Returns the number of numbers in the current string, where a number is defined as a
     * contiguous sequence of digits.
     *
     * If the current string is empty, the method should return 0.
     *
     * Examples:
     * - countNumbers would return 2 for string "My numbers are 11 and 96".
     *
     * @return Number of numbers in the current string
     * @throws NullPointerException If the current string is null
     */
    int countNumbers();

    /**
     * Returns a string that consists of all and only the characters in positions n, 2n, 3n, and
     * so on in the current string, starting either from the beginning or from the end of the
     * string. The characters in the resulting string should be in the same order and with the
     * same case as in the current string.
     *
     * If the current string is empty or has less than n characters, the method should return an
     * empty string.
     *
     * Examples:
     * - For n=2 and a string of one character, the method would return an empty string.
     * - For n=2 and startFromEnd=false, the method would return the 2nd, 4th, 6th, and so on
     *   characters in the string.
     * - For n=3 and startFromEnd=true, the method would return the 3rd from the last character
     *   in the string, 6th from the last character in the string, and so on (in the order in
     *   which they appear in the string).
     *
     * Values n and startFromEnd are passed as parameters. The starting character, whether it is
     * the first one or the last one in the string, is considered to be in Position 1.
     *
     * @param n Determines the positions of the characters to be returned
     * @param startFromEnd Determines whether to start counting from the end or from the
     *                     beginning when identifying the characters in position n, 2n, 3n, and so
     *                     on. Please note that the characters are always returned in the order in
     *                     which they appear in the string.
     * @return String made of characters at positions n, 2n, and so on in the current string
     * @throws IllegalArgumentException If "n" less than or equal to zero
     * @throws NullPointerException If the current string is null and "n" is greater than zero
     *
     */
    String getEveryNthCharacterFromBeginningOrEnd(int n, boolean startFromEnd);

    /**
     * Replace the individual digits in the current string, between startPosition and endPosition
     * (included), with the corresponding English names of those digits, with the first letter
     * capitalized. The first character in the string is considered to be in Position 1.
     * Unlike for the previous method, digits are converted individually, even if contiguous.
     *
     * Examples:
     * - String 460 would be converted to FourSixZero
     * - String 416 would be converted to FourOneSix
     *
     * @param startPosition Position of the first character to consider
     * @param endPosition   Position of the last character to consider
     * @throws IllegalArgumentException    If "startPosition" > "endPosition"
     * @throws MyIndexOutOfBoundsException If "startPosition" <= "endPosition", but either
     *                                     "startPosition" or "endPosition" are out of
     *                                     bounds (i.e., either less than 1 or greater than the
     *                                     length of the string)
     * @throws NullPointerException        If "startPosition" <= "endPosition", "startPosition" and
     *                                     "endPosition" are greater than 0, and the current
     *                                     string is null
     */
    void convertDigitsToNamesInSubstring(int startPosition, int endPosition);
}
