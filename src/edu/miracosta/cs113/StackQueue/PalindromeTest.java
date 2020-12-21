package edu.miracosta.cs113.StackQueue;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * PalindromeTest : a test class for isPalindrome, a method intended to utilize
 * stacks to evaluate if a given string is a palindrome.
 *
 * A palindrome is a word, phrase, number, or other sequence of characters which
 * reads the same backwards as it does forwards. Such sequences include "madam,"
 * "top spot," or "no lemon, no melon".
 */
public class PalindromeTest {

    /**
     * True test cases which include spaces and symbols
     */
    private static final String[] SIMPLE_TRUE = {"", " ", "A", "7", "%", "  ", "BB", "33", "**"};
    /**
     * False test cases which include spaces and symbols
     */
    private static final String[] SIMPLE_FALSE = {"AC", "71", "@+"};

    /**
     * True test cases which include spaces
     */
    private static final String[] WHITE_SPACE_TRUE = {" x ", " t   t  ", " 5 5", " #      # "};
    /**
     * False test cases which include spaces
     */
    private static final String[] WHITE_SPACE_FALSE = {"m   n  ", "   8  7 ", "  ^      &  "};

    /**
     * Case-sensitive palindromes
     */
    private static final String[] CASE_SENSITIVE_TRUE = {"ABba", "roTOR", "rAceCaR"};

    /**
     * Complex palindromes which include spaces, symbols, and varied
     * capitalization
     */
    private static final String[] COMPLEX_TRUE = {"fOO race CAR oof", "AbBa ZaBba", "1 3 3 7  331",
        "N0 LEm0n, n0 Mel0n",
        "sT RJKLEeE R#@ $A$ @# REeEL K  JRT s"};

    /**
     * Utilizes stacks to determine if the given string is a palindrome. This
     * method ignores whitespace and case sensitivity, but does not ignore
     * digits or symbols.
     *
     * @param s a string comprised of any character
     * @return returns true if a palindrome (ignoring whitespace and case
     * sensitivity), false otherwise
     */
    private boolean isPalindrome(String s) {

        if (s == null) {
            throw new IllegalArgumentException();
        }
        // Implement this method body using your ArrayListStack. Be mindful of your algorithm!
        boolean isPal = true;
        String newString1 = s.toLowerCase().trim(); //Test if lower case trim off any spaces
        String newString2 = "";

        System.out.println("original " + s);
        System.out.println("lower trimmed " + newString1);

        //Removes any white spaces 
        for (int i = 0; i < newString1.length(); i++) {
            if (Character.isWhitespace(newString1.charAt(i))) {
                continue;
            } else {
                newString2 += Character.toString(newString1.charAt(i));
            }
        }
        System.out.println("final " + newString2);  //Print the final string after we have cleaned it up

        ArrayListStack<Character> one;  //Puts the string into an array list stack
        one = new ArrayListStack<>();

        for (int i = 0; i < newString2.length(); i++) {
            one.push(newString2.charAt(i));           //pushing each char into array list "one"
        }

        ArrayListStack<Character> two;
        two = new ArrayListStack<>();

        int len = newString2.length();  //put half on to array list "two"
        if (len % 2 == 0) {
            for (int i = 0; i < (len / 2); i++) {
                two.push(one.pop());    
            }
        } else {
            for (int i = 0; i <= (len / 2); i++) {
                if (i < (len - 1) / 2) {
                    two.push(one.pop());
                } else {
                    one.pop();
                }
            }
        }

        for (int i = 0; i < (len / 2); i++) {  //This compares each of the slices
            if (!(one.pop().equals(two.pop()))) {
                isPal = false;
                break;
            }
        }
        System.out.println("palindrome? " + isPal);
        return isPal;

    } // End of method isPalindrome

    @Test
    public void testErrors() {
        try {
            isPalindrome(null);
            fail("Checking null to see if it's a palindrome should throw IllegalArgumentException!");
        } catch (IllegalArgumentException iae) {
            /* Test Passed! */ }
    }

    @Test
    public void testSimpleTrueCases() {
        for (int i = 0; i < SIMPLE_TRUE.length; i++) {
            assertTrue((i + " This test is a palindrome"), isPalindrome(SIMPLE_TRUE[i]));
        }
    }

    @Test
    public void testSimpleFalseCases() {
        for (int i = 0; i < SIMPLE_FALSE.length; i++) {
            assertFalse((i + " This test is NOT a palindrome"), isPalindrome(SIMPLE_FALSE[i]));
        }
    }

    @Test
    public void testWhitespaceTrueCases() {
        for (int i = 0; i < WHITE_SPACE_TRUE.length; i++) {
            assertTrue((i + " This test is a palindrome"), isPalindrome(WHITE_SPACE_TRUE[i]));
        }
    }

    @Test
    public void testWhitespaceFalseCases() {
        for (int i = 0; i < WHITE_SPACE_FALSE.length; i++) {
            assertFalse((i + " This test is NOT a palindrome"), isPalindrome(WHITE_SPACE_FALSE[i]));
        }
    }

    @Test
    public void testCaseSensitivityCases() {
        for (int i = 0; i < CASE_SENSITIVE_TRUE.length; i++) {
            assertTrue((i + " This test is a palindrome"), isPalindrome(CASE_SENSITIVE_TRUE[i]));
        }
    }

    @Test
    public void testComplexCases() {
        for (int i = 0; i < COMPLEX_TRUE.length; i++) {
            assertTrue((i + " This test is a palindrome"), isPalindrome(COMPLEX_TRUE[i]));
        }
    }

} // End of class PalindromeTest
