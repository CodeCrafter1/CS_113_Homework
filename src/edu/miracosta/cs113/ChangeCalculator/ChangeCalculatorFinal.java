package edu.miracosta.cs113.ChangeCalculator;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class ChangeCalculatorFinal {

    private static int[] amounts = {25, 10, 5, 1};
    private static ArrayList<Integer> coins = new ArrayList<>();
    private static int nCombos = 0;
    private static FileWriter fileWriter;

    public static int calculateChange(ArrayList<Integer> coins, int highest, int sum, int goal) throws IOException {
    	System.out.println("coin: " + coins + " highest: " + highest + " sum: " + sum + " goal: " + goal);
    	

        if (sum == goal) {
            printCombinationsToFile(coins);
            nCombos++;
            return nCombos;
        }

        // We cannot go over our goal.
        if (sum > goal) {
            return nCombos;
        }

        // Add higher amounts to a new ArrayList.
        for (int value : amounts) {
            if (value >= highest) {
                ArrayList<Integer> copy = new ArrayList<>();
                copy.addAll(coins);
                copy.add(value);
                calculateChange(copy, value, sum + value, goal);
            }
        }
        return nCombos;
    }

    public static void printCombinationsToFile(ArrayList<Integer> coins) throws IOException {
        fileWriter.write("[");
        System.out.print("[");
        for (int amount : amounts) {
            int count = 0;
            for (int coin : coins) {
                if (coin == amount) {
                    count++;
                }
            }

            fileWriter.write(Integer.toString(count));
            System.out.print(count);
            if (amount == 25) {
                fileWriter.write("Q ");
                System.out.print("Q ");
            } else if (amount == 10) {
                fileWriter.write("D ");
                System.out.print("D ");
            } else if (amount == 5) {
                fileWriter.write("N ");
                System.out.print("N ");
            } else {
                fileWriter.write("P");
                System.out.print("P");
            }
        }
        fileWriter.write("]\n");
        System.out.println("]");

    }

    public static void main(String[] args) {
        int numCombos = 0;
        try {
            fileWriter = new FileWriter("CoinCombinations.txt");
            numCombos = calculateChange(coins, 0, 0, 5);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("numCombos = " + numCombos);
    }

}
