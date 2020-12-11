package edu.miracosta.cs113.ChangeCalculator;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;



public class ChangeCalculator {

    private static int[] amounts = {25, 10, 5, 1};
    private static ArrayList<Integer> coins = new ArrayList<>();
    private static int nCombos = 0;
    private static FileWriter fileWriter;
    private static String[] results = new String[500];

    public static int calculateChange(int goal) {
        nCombos = 0;
        for (int i = 0; i < 500; i++) {
            results[i] = "";
        }
        int n = 0;
        n = calculateChange2(coins, 0, 0, goal);
        printCombinationsToFile(goal);
        nCombos = 0;
        for (int i = 0; i < 500; i++) {
            results[i] = "";
        }
        return n;
    }

    public static void printCombinationsToFile(int goal) {
        int i = 0;
        try {
            fileWriter = new FileWriter("src\\edu\\miracosta\\cs113\\ChangeCalculator\\CoinCombinations.txt");
            while (results[i] != "") {
                fileWriter.write(results[i]);
                i++;
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int calculateChange2(ArrayList<Integer> coins, int highest, int sum, int goal) {

        if (sum == goal) {
            display(coins);
            nCombos++;
            return nCombos;
        }

        // We cannot go over our goal.
        if (sum > goal) {
            return nCombos;
        }

        // Add higher amounts to a new ArrayList.
        for (int value : amounts) {
            if (value >= highest) {//in main highest = 0.
                ArrayList<Integer> copy = new ArrayList<>();
                copy.addAll(coins);//appends coins
                copy.add(value);//add value at the end
                calculateChange2(copy, value, sum + value, goal);//sum = sum + value in the 3rd arg. Sum is initially 0.
            }
        }
        return nCombos;
    }

    public static void display(ArrayList<Integer> coins) {
        System.out.print("[");
        results[nCombos] = "[";
        for (int amount : amounts) {
            int count = 0;
            for (int coin : coins) {
                if (coin == amount) {
                    count++;
                }
            }

            System.out.print(count);
            results[nCombos] += Integer.toString(count);
            if (amount == 25) {
                results[nCombos] += "Q ";
                System.out.print("Q ");
            } else if (amount == 10) {
                results[nCombos] += "D ";
                System.out.print("D ");
            } else if (amount == 5) {
                results[nCombos] += "N ";
                System.out.print("N ");
            } else {
                results[nCombos] += "P";
                System.out.print("P");
            }
        }
        System.out.println("]");
        results[nCombos] += "]\n";

    }

    public static void main(String[] args) {
        int numCombos = 0;
        numCombos = calculateChange(101);
        System.out.println("numCombos = " + numCombos);
    }

}

