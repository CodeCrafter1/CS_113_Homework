package edu.miracosta.cs113.MorseCode;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each
 * letter of the English alphabet, and a means of traversal to be used to
 * decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> {

    private BinaryTree<Character> tree;

    public MorseCodeTree() {
        tree = new BinaryTree(' ', null, null);
        readFile(tree);
    }

    public static void main(String[] args) { //Main to test code
        MorseCodeTree m = new MorseCodeTree();
        System.out.println(m.tree.toString());
        System.out.println(m.translateFromMorseCode("*** **- *-* *-- -** -*- --* ---"));
    }

    public void readFile(BinaryTree<Character> tree) {
        Node<Character> top = tree.root;
        try (BufferedReader readIn = new BufferedReader(new FileReader("MorseCode.txt"))) {
            String line = "";
            while ((line = readIn.readLine()) != null) {
                top = tree.root;
                String[] temp = line.split(" ");  //Split on space
                char c = temp[0].charAt(0);       //Load temp arrary
                String code = temp[1];            
                for (int i = 0; i < code.length(); i++) {
                    if (code.charAt(i) == '*') { 
                        if (top.left == null) {        //Look for leaf
                            top.left = new Node<>(c);  //If null add letter to tree
                        } else {
                            top = top.left;           //Move to the left of the tree
                        }
                    } else {
                        if (top.right == null) {    //Same thing going right
                            top.right = new Node<>(c);
                        } else {
                            top = top.right;
                        }
                    }
                }
            }
            readIn.close();
        } catch (FileNotFoundException nf) {
            System.out.println(nf.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.
    /**
     * Non-recursive method for translating a String comprised of morse code
     * values through traversals in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for
     * dots and '-' for dashes, representing only letters in the English
     * alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's
     * length exceeds that of the tree's number of possible traversals, or if
     * the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) {
        if (morseCode == null) {
            throw new IllegalArgumentException();
        }
        Node<Character> top = null;
        String[] words = morseCode.split(" ");//split into words separated by spaces
        String result = "";
        boolean ok = false;
        for (String word : words) {//loop over all of the words
            top = tree.root;
            ok = false;
            for (int j = 0; j < word.length(); j++) {//loop over each char in temp[i]  Search through the tree until you find the letter
                if (word.charAt(j) == '*') {  //If star you left
                    ok = true;
                    top = top.left;
                } else if (word.charAt(j) == '-') {
                    ok = true;
                    top = top.right;
                } else {
                    throw new IllegalArgumentException();  //if neither then throw an exception
                }
            }
            if (ok) {
                result += top.getData();
            }
        }

        return result;
    }

} // End of class MorseCodeTree